package cn.wycode.model;

import java.util.Calendar;

/**
 * 优惠券
 * Created by wy
 * on 2017/3/2.
 */
public class Coupon {
    double reachPrice; //达到金额
    double reducePrice; //减去金额
    Calendar calendar = Calendar.getInstance(); //截止日期

    public Coupon(double reachPrice, double reducePrice, int year,int month,int day) {
        this.reachPrice = reachPrice;
        this.reducePrice = reducePrice;
        this.calendar.set(year,month-1,day,23,59,59);
    }

    public boolean isValid(Calendar settleDate) {
        return settleDate.before(calendar);
    }

    public double getReachPrice() {
        return reachPrice;
    }

    public double getReducePrice() {
        return reducePrice;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "reachPrice=" + reachPrice +
                ", reducePrice=" + reducePrice +
                ", year=" + calendar.get(Calendar.YEAR) +
                ", month=" + (calendar.get(Calendar.MONTH) + 1) +
                ", day=" + calendar.get(Calendar.DAY_OF_MONTH) +
                '}';
    }
}
