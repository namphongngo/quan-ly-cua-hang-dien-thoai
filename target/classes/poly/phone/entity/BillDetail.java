/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Nam Phong
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BillDetail {
    private Long id;
    private Long billId;
    private String productId;
    private double unitPrice;
    private double discount;
    private int quantity;
    
    private String productName;
}
