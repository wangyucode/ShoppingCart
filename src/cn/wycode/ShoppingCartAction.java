package cn.wycode;

import cn.wycode.model.Coupon;
import cn.wycode.model.Product;
import cn.wycode.model.Sales;

/**
 * 购物车支持的操作
 * Created by wy
 * on 2017/3/2.
 */
public interface ShoppingCartAction {
    void addProduct(Product p, int num);
    void addSales(Sales s);
    void setCoupon(Coupon c);
    String getTotalPrice();
    void setDate(int year,int month,int day);
    void calculate();
}
