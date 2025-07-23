package poly.cafe.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {

    private String Loaidienthoai;
    private String Tensanpham;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private double tyLeGiamGia;

    public Object getMaKhuyenMai() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
