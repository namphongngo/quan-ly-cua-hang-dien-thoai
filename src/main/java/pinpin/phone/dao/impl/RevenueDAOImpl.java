/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pinpin.phone.dao.impl;

import java.util.List;
import pinpin.phone.dao.RevenueDAO;
import pinpin.phone.entity.Revenue.ByCategory;
import pinpin.phone.entity.Revenue.ByUser;
import pinpin.phone.util.XQuery;
/**
 *
 * @author Nam Phong
 */
public class RevenueDAOImpl implements RevenueDAO {
    @Override
    public List<ByCategory> getByCategory(java.util.Date begin, java.util.Date end) {
        String revenueByCategorySql
            = "SELECT category.Name AS Category, "
            + "       SUM(detail.UnitPrice * detail.Quantity * (1 - detail.Discount)) AS Revenue, "
            + "       SUM(detail.Quantity) AS Quantity, "
            + "       MIN(detail.UnitPrice) AS MinPrice, "
            + "       MAX(detail.UnitPrice) AS MaxPrice, "
            + "       AVG(detail.UnitPrice) AS AvgPrice "
            + "FROM BillDetails detail "
            + "JOIN Products product ON product.Id = detail.ProductId "
            + "JOIN Categories category ON category.Id = product.CategoryId "
            + "JOIN Bills bill ON bill.Id = detail.BillId "
            + "WHERE bill.Status = 1 "
            + "  AND bill.Checkout IS NOT NULL "
            + "  AND bill.Checkout BETWEEN ? AND ? "
            + "GROUP BY category.Name "
            + "ORDER BY Revenue DESC";

        return XQuery.getBeanList(ByCategory.class, revenueByCategorySql, begin, end);
    }

    @Override
    public List<ByUser> getByUser(java.util.Date begin, java.util.Date end) {
        String revenueByUserSql
            = "SELECT bill.Username AS [User], "
            + "       SUM(detail.UnitPrice * detail.Quantity * (1 - detail.Discount)) AS Revenue, "
            + "       COUNT(DISTINCT detail.BillId) AS Quantity, "
            + "       MIN(bill.Checkin) AS FirstTime, "
            + "       MAX(bill.Checkin) AS LastTime "
            + "FROM BillDetails detail "
            + "JOIN Bills bill ON bill.Id = detail.BillId "
            + "WHERE bill.Status = 1 "
            + "  AND bill.Checkout IS NOT NULL "
            + "  AND bill.Checkout BETWEEN ? AND ? "
            + "GROUP BY bill.Username "
            + "ORDER BY Revenue DESC";

        return XQuery.getBeanList(ByUser.class, revenueByUserSql, begin, end);
    }
}

