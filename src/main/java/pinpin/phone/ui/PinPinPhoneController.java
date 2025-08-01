/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pinpin.phone.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import pinpin.phone.util.XDialog;
import pinpin.phone.ui.manager.BillManagerJDialog;
import pinpin.phone.ui.manager.CardManagerJDialog;
import pinpin.phone.ui.manager.CategoryManagerJDialog;
import pinpin.phone.ui.manager.ProductManagerJDialog;
import pinpin.phone.ui.manager.RevenueManagerJDialog;
import pinpin.phone.ui.manager.UserManagerJDialog;
/**
 *
 * @author Nam Phong
 */
public interface PinPinPhoneController {
/**
* Hiển thị cửa sổ chào
* Hiển thị cửa sổ đăng nhập
* Hiển thị thông tin user đăng nhập
* Disable/Enable các thành phần tùy thuộc vào vai trò đăng nhập
*/
    void init();
    
    default void exit(){
        if(XDialog.confirm("Bạn muốn kết thúc?")){
            System.exit(0);
        }
    }

    default void showJDialog(JDialog dialog){
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void showWelcomeJDialog(JFrame frame){this.showJDialog(new WelcomeJDialog(frame, true));}
    default void showLoginJDialog(JFrame frame){this.showJDialog(new LoginJDialog(frame, true));}
    default void showChangePasswordJDialog(JFrame frame){this.showJDialog(new ChangePasswordJDialog(frame, true));}
    default void showSalesJDialog(JFrame frame){this.showJDialog(new SalesJDialog(frame, true));}
    default void showHistoryJDialog(JFrame frame){this.showJDialog(new HistoryJDialog(frame, true));}
    default void showProductsManagerJDialog(JFrame frame){this.showJDialog(new ProductManagerJDialog(frame, true));}
    default void showCategoryManagerJDialog(JFrame frame){this.showJDialog(new CategoryManagerJDialog(frame, true));}
    default void showCardManagerJDialog(JFrame frame){this.showJDialog(new CardManagerJDialog(frame, true));}
    default void showBillManagerJDialog(JFrame frame){this.showJDialog(new BillManagerJDialog(frame, true));}
    default void showUserManagerJDialog(JFrame frame){this.showJDialog(new UserManagerJDialog(frame, true));}
    default void showRevenueManagerJDialog(JFrame frame){this.showJDialog(new RevenueManagerJDialog(frame, true));}
}

