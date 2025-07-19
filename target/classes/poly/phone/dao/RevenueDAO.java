/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.phone.dao;

import java.util.List;
import poly.phone.entity.Revenue;
/**
 *
 * @author Nam Phong
 */
public interface RevenueDAO {
/**
* Truy vấn doanh thu từng loại theo khoảng thời gian
*
* @param begin thời gian bắt đầu
* @param end thời gian kết thúc
* @return kết quả truy vấn
*/
    List<Revenue.ByCategory> getByCategory(java.util.Date begin, java.util.Date end);
/**
* Truy vấn doanh thu từng nhân viên theo khoảng thời gian
*
* @param begin thời gian bắt đầu
* @param end thời gian kết thúc
* @return kết quả truy vấn
*/
    List<Revenue.ByUser> getByUser(java.util.Date begin, java.util.Date end);
}
