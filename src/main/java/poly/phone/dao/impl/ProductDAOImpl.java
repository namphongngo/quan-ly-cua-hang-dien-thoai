package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.entity.Product;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import poly.cafe.dao.ProductDAO;

public class ProductDAOImpl implements ProductDAO {

  
private final String createSql = "INSERT INTO KhuyenMai (LoaiDienThoai, TenSanPham, NgayBatDau, NgayKetThuc, TyLeGiamGia) VALUES (?, ?, ?, ?, ?)";
private final String updateSql = "UPDATE KhuyenMai SET NgayBatDau=?, NgayKetThuc=?, TyLeGiamGia=? WHERE LoaiDienThoai=? AND TenSanPham=?";
private final String deleteByIdSql = "DELETE FROM KhuyenMai WHERE LoaiDienThoai=? AND TenSanPham=?";
private final String findAllSql = "SELECT * FROM KhuyenMai";
private final String findByIdSql = findAllSql + " WHERE LoaiDienThoai=? AND TenSanPham=?";


    @Override
    public Product create(Product entity) {
        Object[] values = {
            entity.getLoaidienthoai(),
            entity.getTensanpham(),
            entity.getNgayBatDau(),
            entity.getNgayKetThuc(),
            entity.getTyLeGiamGia()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Product entity) {
        Object[] values = {
            entity.getLoaidienthoai(),     // 1
            entity.getTensanpham(),        // 2
            entity.getNgayBatDau(),        // 3
            entity.getNgayKetThuc(),       // 4
            entity.getTyLeGiamGia()     // 5
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String maChuongTrinh) {
        XJdbc.executeUpdate(deleteByIdSql, maChuongTrinh);
    }

    @Override
    public List<Product> findAll() {
        return XQuery.getBeanList(Product.class, findAllSql);
    }

    @Override
    public Product findById(String maChuongTrinh) {
        return XQuery.getSingleBean(Product.class, findByIdSql, maChuongTrinh);
    }
}
