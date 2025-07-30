/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pinpin.phone.dao.impl;

import java.util.List;
import pinpin.phone.dao.CardDAO;
import pinpin.phone.entity.Card;
import pinpin.phone.util.XJdbc;
import pinpin.phone.util.XQuery;
/**
 *
 * @author Nam Phong
 */
public class CardDAOImpl implements CardDAO {
    private static final String CREATE_SQL = "INSERT INTO Cards(Id, Status) VALUES(?, ?)";
    private static final String UPDATE_SQL = "UPDATE Cards SET Status=? WHERE Id=?";
    private static final String DELETE_SQL = "DELETE FROM Cards WHERE Id=?";
    private static final String FIND_ALL_SQL = "SELECT * FROM Cards";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM Cards WHERE Id=?";

    @Override
    public Card create(Card entity) {
        Object[] values = {
            entity.getId(),
            entity.getStatus()
        };
        XJdbc.executeUpdate(CREATE_SQL, values);
        return entity;
    }

    @Override
    public void update(Card entity) {
        Object[] values = {
            entity.getStatus(),
            entity.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }
    @Override
    public void deleteById(Integer id) {XJdbc.executeUpdate(DELETE_SQL, id);}
    @Override
    public List<Card> findAll() {return XQuery.getBeanList(Card.class, FIND_ALL_SQL);}
    @Override
    public Card findById(Integer id) {return XQuery.getSingleBean(Card.class, FIND_BY_ID_SQL, id);}
}
