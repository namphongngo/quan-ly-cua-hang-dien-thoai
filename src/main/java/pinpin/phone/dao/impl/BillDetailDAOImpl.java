package pinpin.phone.dao.impl;

import java.util.List;
import pinpin.phone.dao.BillDetailDAO;
import pinpin.phone.entity.BillDetail;
import pinpin.phone.util.XJdbc;
import pinpin.phone.util.XQuery;

public class BillDetailDAOImpl implements BillDetailDAO {

    public BillDetailDAOImpl() {
    }

    private static final String CREATE_SQL =
        "INSERT INTO BillDetails(Id, BillId, ProductId, UnitPrice, Discount, Quantity) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
        "UPDATE BillDetails SET BillId=?, ProductId=?, UnitPrice=?, Discount=?, Quantity=? WHERE Id=?";

    private static final String DELETE_SQL =
        "DELETE FROM BillDetails WHERE Id=?";

    private static final String FIND_ALL_SQL =
        "SELECT bd.*, d.Name AS ProductName FROM BillDetails bd JOIN Products d ON d.Id = bd.ProductId";

    private static final String FIND_BY_ID_SQL =
        "SELECT bd.*, d.Name AS ProductName FROM BillDetails bd JOIN Products d ON d.Id = bd.ProductId WHERE bd.Id=?";

    private static final String FIND_BY_BILL_ID_SQL =
        "SELECT bd.*, d.Name AS ProductName FROM BillDetails bd JOIN Products d ON d.Id = bd.ProductId WHERE bd.BillId=?";

    private static final String FIND_BY_PRODUCT_ID_SQL =
        "SELECT bd.*, d.Name AS ProductName FROM BillDetails bd JOIN Products d ON d.Id = bd.ProductId WHERE bd.ProductId=?";

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
    public List<BillDetail> findByProductId(String productId) {
        return XQuery.getBeanList(BillDetail.class, FIND_BY_PRODUCT_ID_SQL, productId);
    }
}

