/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.phone.ui;

import poly.phone.entity.Bill;
/**
 *
 * @author Nam Phong
 */
public interface ProductController {
    void setBill(Bill bill); // nhận bill từ BillJDialog
    void open(); // hiển thị loại và sản phẩm
    void fillCategories(); // tải và hiển thị loại sản phẩm
    void fillProducts(); // tải và hiển thị sản phẩm
    void addProductToBill(); // thêm sản phẩm vào bill 
}
