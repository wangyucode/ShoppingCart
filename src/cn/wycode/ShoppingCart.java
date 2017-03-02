package cn.wycode;

import cn.wycode.model.Coupon;
import cn.wycode.model.Product;
import cn.wycode.model.Sales;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wy
 * on 2017/3/2.
 */
public class ShoppingCart implements ShoppingCartAction {

    private Map<Product, Integer> items = new HashMap<>();
    private List<Sales> sales = new ArrayList<>(); //
    private Calendar settleDate = Calendar.getInstance();
    private BigDecimal totalPrice;
    private Coupon coupon;


    public ShoppingCart() {

    }

    public ShoppingCart(int year, int month, int day) {
        settleDate.set(year, month - 1, day);
    }

    /**
     * 添加商品，并实时更新商品总价
     *
     * @param p   商品
     * @param num 数量
     */
    @Override
    public void addProduct(Product p, int num) {
        items.put(p, num);
    }

    /**
     * 添加优惠信息
     *
     * @param s
     */
    @Override
    public void addSales(Sales s) {
        sales.add(s);
    }

    /**
     * 设置优惠券
     *
     * @param c
     */
    @Override
    public void setCoupon(Coupon c) {
        coupon = c;
    }

    /**
     * 获取格式化的总价
     *
     * @return
     */
    @Override
    public String getTotalPrice() {
        return totalPrice.toPlainString();
    }

    @Override
    public void setDate(int year, int month, int day) {
        settleDate.set(year, month - 1, day);
    }

    /**
     * 计算商品总价
     */
    @Override
    public void calculate() {
        totalPrice = MoneyUtils.cleanToZero();
        for (Map.Entry<Product, Integer> item : items.entrySet()) {
            double d = 1;
            Product p = item.getKey();
            int num = item.getValue();
            //是否有打折
            for (Sales s : sales) {
                if (s.isValid(settleDate) && s.getType() == p.getType()) {
                    d = s.getDiscount();
                }
            }
            //单个商品总价=(折扣*数量*单价)
            BigDecimal productPrice = MoneyUtils.multiply(d, MoneyUtils.multiply(num, p.getPrice()).doubleValue());
            totalPrice = MoneyUtils.add(totalPrice.doubleValue(), productPrice.doubleValue());
        }
        //使用优惠券
        if (coupon != null && coupon.isValid(settleDate)
                && totalPrice.doubleValue() >= coupon.getReachPrice()) {
            totalPrice = MoneyUtils.subtract(totalPrice.doubleValue(), coupon.getReducePrice());
        }
        //总价不能为负
        if (totalPrice.doubleValue() < 0) {
            totalPrice = MoneyUtils.cleanToZero();
        }
    }


}
