/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.phone.dao;

import java.util.List;
import poly.phone.entity.Product;
/**
 *
 * @author Nam Phong
 */
public interface ProductDAO extends CrudDAO<Product, String>{
        List<Product> findByCategoryId(String categoryId);
}
