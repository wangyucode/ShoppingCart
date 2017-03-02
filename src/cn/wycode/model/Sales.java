package cn.wycode.model;

import java.util.Calendar;

/**
 * 商品折扣
 * Created by wy
 * on 2017/3/2.
 */
public class Sales {
    private ProductType type;
    private Calendar date;
    private double discount;

    public Sales(ProductType type, int year, int month, int day, double discount) {
        date = Calendar.getInstance();
        date.set(year, month-1, day);
        this.type = type;
        this.discount = discount;
    }

    public boolean isValid(Calendar settleDate) {
        return settleDate.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                && settleDate.get(Calendar.MONTH) == date.get(Calendar.MONTH)
                && settleDate.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH);
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "type=" + type +
                ", year=" + date.get(Calendar.YEAR) +
                ", month=" + (date.get(Calendar.MONTH) + 1) +
                ", day=" + date.get(Calendar.DAY_OF_MONTH) +
                ", discount=" + discount +
                '}';
    }
}
