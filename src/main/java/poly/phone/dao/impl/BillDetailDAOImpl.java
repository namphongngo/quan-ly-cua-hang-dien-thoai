/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.phone.dao.impl;

import java.util.List;
import poly.phone.dao.BillDetailDAO;
import poly.phone.entity.BillDetail;
import poly.phone.util.XJdbc;
import poly.phone.util.XQuery;
/**
 *
 * @author Nam Phong
 */
public class BillDetailDAOImpl implements BillDetailDAO {   
    private static final String CREATE_SQL =
        "INSERT INTO BillDetails(Id, BillId, DrinkId, UnitPrice, Discount, Quantity) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
        "UPDATE BillDetails SET BillId=?, DrinkId=?, UnitPrice=?, Discount=?, Quantity=? WHERE Id=?";

    private static final String DELETE_SQL =
        "DELETE FROM BillDetails WHERE Id=?";

    private static final String FIND_ALL_SQL =
        "SELECT bd.*, d.Name AS DrinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId";

    private static final String FIND_BY_ID_SQL =
        "SELECT bd.*, d.Name AS DrinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId WHERE bd.Id=?";

    private static final String FIND_BY_BILL_ID_SQL =
        "SELECT bd.*, d.Name AS DrinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId WHERE bd.BillId=?";

    private static final String FIND_BY_DRINK_ID_SQL =
        "SELECT bd.*, d.Name AS DrinkName FROM BillDetails bd JOIN Drinks d ON d.Id = bd.DrinkId WHERE bd.DrinkId=?";

    @Override
    public BillDetail create(BillDetail entity) {
        Object[] values = {
            entity.getId(),
            entity.getBillId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity()
        };
        XJdbc.executeUpdate(CREATE_SQL, values);
        return entity;
    }

    @Override
    public void update(BillDetail entity) {
        Object[] values = {
            entity.getBillId(),
            entity.getProductId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<BillDetail> findAll() {
        return XQuery.getBeanList(BillDetail.class, FIND_ALL_SQL);
    }

    @Override
    public BillDetail findById(Long id) {
        return XQuery.getSingleBean(BillDetail.class, FIND_BY_ID_SQL, id);
    }

    @Override
    public List<BillDetail> findByBillId(Long billId) {
        return XQuery.getBeanList(BillDetail.class, FIND_BY_BILL_ID_SQL, billId);
    }

    @Override
    public List<BillDetail> findByDrinkId(String drinkId) {
        return XQuery.getBeanList(BillDetail.class, FIND_BY_DRINK_ID_SQL, drinkId);
    }
}
