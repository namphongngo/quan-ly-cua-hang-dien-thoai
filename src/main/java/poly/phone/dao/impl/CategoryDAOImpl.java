/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.phone.dao.impl;

import java.util.List;
import poly.phone.dao.CategoryDAO;
import poly.phone.entity.Category;
import poly.phone.util.XJdbc;
import poly.phone.util.XQuery;
/**
 *
 * @author Nam Phong
 */
public class CategoryDAOImpl implements CategoryDAO {
    private static final String CREATE_SQL = "INSERT INTO Categories(Id, Name) VALUES(?, ?)";
    private static final String UPDATE_SQL = "UPDATE Categories SET Name=? WHERE Id=?";
    private static final String DELETE_SQL = "DELETE FROM Categories WHERE Id=?";
    private static final String FIND_ALL_SQL = "SELECT * FROM Categories";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM Categories WHERE Id=?";

    @Override
    public Category create(Category entity) {
        Object[] values = {
            entity.getId(),
            entity.getName()
        };
        XJdbc.executeUpdate(CREATE_SQL, values);
        return entity;
    }

    @Override
    public void update(Category entity) {
        Object[] values = {
            entity.getName(),
            entity.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }
    @Override
    public void deleteById(String id) { XJdbc.executeUpdate(DELETE_SQL, id);}
    @Override
    public List<Category> findAll() {return XQuery.getBeanList(Category.class, FIND_ALL_SQL);}
    @Override
    public Category findById(String id) {return XQuery.getSingleBean(Category.class, FIND_BY_ID_SQL, id);}
}
