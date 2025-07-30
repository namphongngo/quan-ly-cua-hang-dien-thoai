/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pinpin.phone.ui.manager;

import pinpin.phone.entity.Product;
/**
 *
 * @author Nam Phong
 */
public interface ProductController extends CrudController<Product>{
    void fillCategories();
    void chooseFile();
}
