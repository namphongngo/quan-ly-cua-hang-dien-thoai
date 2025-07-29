/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.phone.ui.manager;

import poly.phone.entity.Product;
/**
 *
 * @author Nam Phong
 */
public interface ProductController extends CrudController<Product>{
    void fillCategories();
    void chooseFile();
}
