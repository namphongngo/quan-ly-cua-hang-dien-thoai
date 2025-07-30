/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.phone.dao.impl;

import java.util.Date;
import java.util.List;
import poly.phone.dao.BillDAO;
import poly.phone.entity.Bill;
import poly.phone.util.XAuth;
import poly.phone.util.XJdbc;
import poly.phone.util.XQuery;

/**
 *
 * @author Nam Phong
 */
public class BillDAOImpl implements BillDAO {
    private static final String CREATE_SQL = "INSERT INTO Bills(Username, CardId, Checkin, Checkout, Status) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE Bills SET Username=?, CardId=?, Checkin=?, Checkout=?, Status=? WHERE Id=?";
    private static final String DELETE_SQL = "DELETE FROM Bills WHERE Id=?";
    private static final String FIND_ALL_SQL = "SELECT * FROM Bills";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM Bills WHERE Id=?";
    
    private static final String FIND_BY_USERNAME_SQL = "SELECT * FROM Bills WHERE Username=?";
    private static final String FIND_BY_CARDID_SQL = "SELECT * FROM Bills WHERE CardId=?";
    private static final String FIND_BY_TIMERANGE_SQL = "SELECT * FROM Bills WHERE Checkin BETWEEN ? AND ? ORDER BY Checkin DESC";

    @Override
    public Bill create(Bill entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCardId(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus()
        };
        XJdbc.executeUpdate(CREATE_SQL, values);
        return entity;
    }

    @Override
    public void update(Bill entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getCardId(),
            entity.getCheckin(),
            entity.getCheckout(),
            entity.getStatus(),
            entity.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Bill> findAll() {
        return XQuery.getBeanList(Bill.class, FIND_ALL_SQL);
    }

    @Override
    public Bill findById(Long id) {
        return XQuery.getSingleBean(Bill.class, FIND_BY_ID_SQL, id);
    }

    @Override
    public List<Bill> findByUsername(String username) {
        return XQuery.getBeanList(Bill.class, FIND_BY_USERNAME_SQL, username);
    }

    @Override
    public List<Bill> findByCardId(Integer cardId) {
        return XQuery.getBeanList(Bill.class, FIND_BY_CARDID_SQL, cardId);
    }

    @Override
    public List<Bill> findByTimeRange(Date begin, Date end) {
        return XQuery.getBeanList(Bill.class, FIND_BY_TIMERANGE_SQL, begin, end);
    }

    @Override
    public Bill findServicingByCardId(Integer cardId) {
        String sql = "SELECT * FROM Bills WHERE CardId=? AND Status=0";
        Bill bill = XQuery.getSingleBean(Bill.class, sql, cardId);
        if (bill == null) { // không tìm thấy -> tạo mới
            Bill newBill = new Bill();
            newBill.setCardId(cardId);
            newBill.setCheckin(new Date());

            // ✅ Gán checkout tạm thời để tránh lỗi null
            newBill.setCheckout(newBill.getCheckin());

            newBill.setStatus(0); // đang phục vụ
            newBill.setUsername(XAuth.user.getUsername());
            bill = this.create(newBill); // insert
        }
        return bill;
    }

    @Override
    public List<Bill> findByUserAndTimeRange(String username, Date begin, Date end) {
        String sql = "SELECT * FROM Bills WHERE Username=? AND Checkin BETWEEN ? AND ?";
        return XQuery.getBeanList(Bill.class, sql, username, begin, end);
    }
}


