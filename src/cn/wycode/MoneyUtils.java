package cn.wycode;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * 金额计算
 *
 * Created by wy
 * on 2017/3/2.
 */
public final class MoneyUtils {

    public static BigDecimal add(double d1, double d2) {
        return new BigDecimal(d1).add(new BigDecimal(d2)).setScale(2, ROUND_HALF_UP);
    }

    public static BigDecimal subtract(double d1, double d2) {
        return new BigDecimal(d1).subtract(new BigDecimal(d2)).setScale(2, ROUND_HALF_UP);
    }

    public static BigDecimal multiply(double d1, double d2) {
        return new BigDecimal(d1).multiply(new BigDecimal(d2)).setScale(2, ROUND_HALF_UP);
    }

    public static BigDecimal divide(double d1, double d2) {
        return new BigDecimal(d1).multiply(new BigDecimal(d2)).setScale(2, ROUND_HALF_UP);
    }

    public static BigDecimal cleanToZero(){
        return new BigDecimal(0).setScale(2, ROUND_HALF_UP);
    }
}
