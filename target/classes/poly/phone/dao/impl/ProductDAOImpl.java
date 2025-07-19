/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.phone.dao.impl;

import java.util.List;
import poly.phone.entity.Product;
import poly.phone.util.XJdbc;
import poly.phone.util.XQuery;
import poly.phone.dao.ProductDAO;
/**
 *
 * @author Nam Phong
 */
public class ProductDAOImpl implements ProductDAO {
    private static final String CREATE_SQL = "INSERT INTO Products(Id, Name, Image, UnitPrice, Discount, Available, CategoryId) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE Products SET Name=?, Image=?, UnitPrice=?, Discount=?, Available=?, CategoryId=? WHERE Id=?";
    private static final String DELETE_SQL = "DELETE FROM Products WHERE Id=?";
    private static final String FIND_ALL_SQL = "SELECT * FROM Products";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM Products WHERE Id=?";
    
    private static final String FIND_BY_CATEGORY_ID_SQL = "SELECT * FROM Products WHERE CategoryId=?";

    @Override
    public Product create(Product entity) {
        Object[] values = {
            entity.getId(),
            entity.getName(),
            entity.getImage(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.isAvailable(),
            entity.getCategoryId()
        };
        XJdbc.executeUpdate(CREATE_SQL, values);
        return entity;
    }

    @Override
    public void update(Product entity) {
        Object[] values = {
            entity.getName(),
            entity.getImage(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.isAvailable(),
            entity.getCategoryId(),
            entity.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }
    @Override
    public void deleteById(String id) {XJdbc.executeUpdate(DELETE_SQL, id);}
    @Override
    public List<Product> findAll() {return XQuery.getBeanList(Product.class, FIND_ALL_SQL);}
    @Override
    public Product findById(String id) {return XQuery.getSingleBean(Product.class, FIND_BY_ID_SQL, id);}    
    @Override
    public List<Product> findByCategoryId(String categoryId) {return XQuery.getBeanList(Product.class, FIND_BY_CATEGORY_ID_SQL, categoryId);}
}
