package pinpin.phone.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pinpin.phone.dao.ProductDAO;
import pinpin.phone.entity.Product;
import pinpin.phone.util.XJdbc;

/**
 *
 * @author Nam Phong
 */
public class ProductDAOImpl implements ProductDAO {
    private static final String CREATE_SQL = 
        "INSERT INTO Products (Id, Name, UnitPrice, Discount, Image, Available, CategoryId) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = 
        "UPDATE Products SET Name=?, Image=?, UnitPrice=?, Discount=?, Available=?, CategoryId=? WHERE Id=?";
    private static final String DELETE_SQL = 
        "DELETE FROM Products WHERE Id=?";
    private static final String FIND_ALL_SQL = 
        "SELECT Id, Name, UnitPrice, Discount, Image, Available, CategoryId FROM Products";
    private static final String FIND_BY_ID_SQL = 
        "SELECT Id, Name, UnitPrice, Discount, Image, Available, CategoryId FROM Products WHERE Id=?";
    private static final String FIND_BY_CATEGORY_ID_SQL = 
        "SELECT Id, Name, UnitPrice, Discount, Image, Available, CategoryId FROM Products WHERE CategoryId=?";

    @Override
    public Product create(Product entity) {
        try {
            Object[] values = {
                entity.getId(),
                entity.getName(),
                entity.getUnitPrice(),
                entity.getDiscount(),
                entity.getImage(),
                entity.isAvailable(),
                entity.getCategoryId()
            };
            XJdbc.executeUpdate(CREATE_SQL, values);
            System.out.println("Đã thêm sản phẩm: " + entity.getId());
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi thêm sản phẩm: " + e.getMessage());
        }
    }

    @Override
    public void update(Product entity) {
        Object[] values = {
            entity.getName(),
            entity.getImage(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.isAvailable(),
            entity.getCategoryId(),
            entity.getId()
        };
        XJdbc.executeUpdate(UPDATE_SQL, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(FIND_ALL_SQL)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Product findById(String id) {
        try (ResultSet rs = XJdbc.executeQuery(FIND_BY_ID_SQL, id)) {
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sản phẩm theo ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Product> findByCategoryId(String categoryId) {
        List<Product> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(FIND_BY_CATEGORY_ID_SQL, categoryId)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sản phẩm theo CategoryId: " + e.getMessage(), e);
        }
        return list;
    }

    // === Hàm map ResultSet -> Product ===
    private Product mapRow(ResultSet rs) throws Exception {
        return Product.builder()
                .id(rs.getString("Id"))
                .name(rs.getString("Name"))
                .unitPrice(rs.getDouble("UnitPrice"))
                .discount(rs.getDouble("Discount"))
                .image(rs.getString("Image"))
                .available(rs.getBoolean("Available"))
                .categoryId(rs.getString("CategoryId"))
                .build();
    }
}





