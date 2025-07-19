/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.phone.dao.impl;

import java.util.List;
import poly.phone.dao.UserDAO;
import poly.phone.entity.User;
import poly.phone.util.XJdbc;
import poly.phone.util.XQuery;
/**
 *
 * @author Nam Phong
 */
public class UserDAOImpl implements UserDAO {
    private static final String CREATE_SQL = "INSERT INTO Users(Username, Password, Enabled, Fullname, Photo, Manager) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE Users SET Password=?, Enabled=?, Fullname=?, Photo=?, Manager=? WHERE Username=?";
    private static final String DELETE_SQL = "DELETE FROM Users WHERE Username=?";
    private static final String FIND_ALL_SQL = "SELECT * FROM Users";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM Users WHERE Username=?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager()
        };
        XJdbc.executeUpdate(CREATE_SQL, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager(),
            entity.getUsername()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }
    @Override
    public void deleteById(String id) {XJdbc.executeUpdate(DELETE_SQL, id);}
    @Override
    public List<User> findAll() {return XQuery.getBeanList(User.class, FIND_ALL_SQL);}
    @Override
    public User findById(String id) {return XQuery.getSingleBean(User.class, FIND_BY_ID_SQL, id);}
}
