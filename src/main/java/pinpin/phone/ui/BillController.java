/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pinpin.phone.ui;

import pinpin.phone.entity.Bill;
/**
 *
 * @author Nam Phong
 */
public interface BillController {
        void setBill(Bill bill); // truyền bill vào cửa sổ để hiển thị
        void open(); // Hiển thị bill
        void close(); // Xóa bill nếu ko chứa đồ uống nào
        void showProductJDialog(); // Hiển thị cửa sổ bổ sung sản phẩm vào bill
        void removeProducts(); // Xóa sản phẩm khỏi bill
        void updateQuantity(); // Thay đổi số lượng sản phẩm
        void checkout(); // Thanh toán
        void cancel(); // Hủy bill
}
