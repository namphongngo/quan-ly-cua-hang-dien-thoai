/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pinpin.phone.ui;


import java.awt.Frame;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pinpin.phone.dao.BillDAO;
import pinpin.phone.dao.BillDetailDAO;
import pinpin.phone.dao.impl.BillDAOImpl;
import pinpin.phone.dao.impl.BillDetailDAOImpl;
import pinpin.phone.entity.Bill;
import pinpin.phone.entity.BillDetail;
import pinpin.phone.util.XDate;
import pinpin.phone.util.XDialog;

/**
 *
 * @author Nam Phong
 */
    public final class BillJDialog extends javax.swing.JDialog implements BillController{
        private Bill bill;
        private List<BillDetail> billDetails = new ArrayList<>();
        private BillDAO billDao = new BillDAOImpl();
        private BillDetailDAO billDetailDao = new BillDetailDAOImpl();
    /**
     * Creates new form BillJDialog
     */
    public BillJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
@Override
public void setBill(Bill bill) {
    this.bill = bill;
    System.out.println("📌 setBill: billId = " + (bill != null ? bill.getId() : "null"));
}


    @Override
    public void open() {     
    if (this.bill == null) {
        this.setBill(null); // sẽ tự tạo bill mới
    }
   

    this.setForm(bill);
    this.fillBillDetails();
    this.setVisible(true);
}
    @Override
    public void close() {
        if (billDetails.isEmpty()) {
        billDao.deleteById(bill.getId()); // xóa bill khỏi DB nếu không có chi tiết
    }
    this.dispose(); // đóng cửa sổ
    }
    
private void fillBillDetails() {
    if (this.bill == null) {
        System.out.println("⛔ fillBillDetails: BILL NULL ❌");
        return;
    }

    System.out.println("✅ fillBillDetails: billId = " + bill.getId());
    billDetails = billDetailDao.findByBillId(bill.getId());
    System.out.println("📦 Số chi tiết hóa đơn: " + billDetails.size());
}
    @Override
    public void removeProducts() { // xóa đồ uống được tích chọn
        for (int i = 0; i < tblBillDetails.getRowCount(); i++) {
        Boolean checked = (Boolean) tblBillDetails.getValueAt(i, 0);
        if(checked)
            billDetailDao.deleteById(billDetails.get(i).getId());
        }
        this.fillBillDetails();
    }

    @Override
    public void showProductJDialog() { // hiển thị cửa sổ chọn và bổ sung đồ uống
        ProductJDialog dialog = new ProductJDialog((Frame) this.getOwner(), true);
        dialog.setBill(bill); // Khai báo vào DrinkJDialog @Setter Bill bill
        dialog.setVisible(true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {  
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                BillJDialog.this.fillBillDetails();
            }
        });
    }
    @Override
    public void updateQuantity() { // thay đổi số lượng đồ uống
        if (bill.getStatus() == 0) { // chưa thanh toán hoặc chưa bị canceled
            String input = XDialog.prompt("Số lượng mới?");
            if (input != null && input.length() > 0) {
                BillDetail detail = billDetails.get(tblBillDetails.getSelectedRow());
                detail.setQuantity(Integer.parseInt(input));
                billDetailDao.update(detail);
                this.fillBillDetails();
            }
        }
    }

    @Override
    public void checkout() {
        if (XDialog.confirm("Bạn muốn thanh toán phiếu bán hàng?")) {
            bill.setStatus(Bill.Status.Completed.ordinal());
            bill.setCheckout(new Date());
            billDao.update(bill);
            this.setForm(bill);
        }
    }

    @Override
    public void cancel() {
        if (billDetails.isEmpty()) {
            billDao.deleteById(bill.getId());
            this.dispose();
        } else if (XDialog.confirm("Bạn muốn hủy phiếu bán hàng?")) {
            bill.setStatus(Bill.Status.Canceled.ordinal());
            billDao.update(bill);
            this.setForm(bill);
        }
    }
    void setForm(Bill bill) { // hiển thị bill lên form
        txtId.setText(String.valueOf(bill.getId()));
        txtCardId.setText("Card #" + bill.getCardId());
        txtCheckin.setText(XDate.format(bill.getCheckin(), "HH:mm:ss dd-MM-yyyy"));
        txtUsername.setText(bill.getUsername());
        String[] statuses = {"Servicing", "Completed", "Canceled"};
        txtStatus.setText(statuses[bill.getStatus()]);
        if (bill.getCheckout() != null) {
            txtCheckout.setText(XDate.format(bill.getCheckout(), "HH:mm:ss dd-MM-yyyy"));
        }
        boolean editable = (bill.getStatus() == 0);
        btnAdd.setEnabled(editable);
        btnCancel.setEnabled(editable);
        btnCheckout.setEnabled(editable);
        btnRemove.setEnabled(editable);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCardId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCheckin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        txtCheckout = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBillDetails = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Phiếu bán hàng");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Mã phiếu");

        jLabel2.setText("Thẻ số");

        jLabel3.setText("Thời điểm đặt hàng");

        jLabel4.setText("Nhân viên");

        jLabel5.setText("Trạng thái");

        jLabel6.setText("Thời điểm thanh toán");

        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Mã phiếu", "Điện thoại", "Đơn giá", "Giảm giá", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblBillDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBillDetails);

        btnRemove.setText("Xóa Sản Phẩm");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm Sản Phẩm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancel.setText("Hủy phiếu");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCheckout.setText("Thanh toán");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtUsername))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtCardId, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtStatus))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtCheckin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCheckout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCheckout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCardId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemove)
                    .addComponent(btnAdd)
                    .addComponent(btnCancel)
                    .addComponent(btnCheckout))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        this.showProductJDialog();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        this.removeProducts();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        this.checkout();
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.cancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tblBillDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillDetailsMouseClicked
        // TODO add your handling code here:
        this.updateQuantity();
    }//GEN-LAST:event_tblBillDetailsMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.close();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BillJDialog dialog = new BillJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBillDetails;
    private javax.swing.JTextField txtCardId;
    private javax.swing.JTextField txtCheckin;
    private javax.swing.JTextField txtCheckout;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}