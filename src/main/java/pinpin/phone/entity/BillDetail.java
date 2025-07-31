package pinpin.phone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BillDetail {
    private Long id;
    private Long billId;
    private String productId;
    private BigDecimal unitPrice;  // đổi từ double sang BigDecimal
    private BigDecimal discount;   // đổi từ double sang BigDecimal
    private int quantity;
    
    private String productName;
}

