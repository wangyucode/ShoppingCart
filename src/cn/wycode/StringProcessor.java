package cn.wycode;

import cn.wycode.model.Coupon;
import cn.wycode.model.Product;
import cn.wycode.model.ProductType;
import cn.wycode.model.Sales;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * 处理输入字符串
 * Created by wy
 * on 2017/3/2.
 */
public class StringProcessor {


    static final int STATE_SALES = 1;
    static final int STATE_PRODUCT = 2;
    static final int STATE_DATE = 3;
    static final int STATE_COUPON = 4;
    static final int STATE_END = 5;

    private int state = STATE_SALES; //状态机状态

    ShoppingCart shoppingcart;

    public String process(String input) {
        BufferedReader reader = new BufferedReader(new StringReader(input));
        shoppingcart = new ShoppingCart();
        try {
            readString(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        shoppingcart.calculate();
        return shoppingcart.getTotalPrice();
    }

    private void readString(BufferedReader reader) throws Exception {
        while (state < STATE_END) {
            switch (state) {
                case STATE_SALES:
                    String s1 = reader.readLine();
//                    System.out.println(s1);
                    if (s1.trim().length() == 0) {
                        state++;
                    } else {
                        parseSales(s1);
                    }
                    break;
                case STATE_PRODUCT:
                    String s2 = reader.readLine();
//                    System.out.println(s2);
                    if (s2.trim().length() == 0) {
                        state++;
                    } else {
                        parseProduct(s2);
                    }
                    break;
                case STATE_DATE:
                    String s3 = reader.readLine();
//                    System.out.println(s3);
                    parseDate(s3);
                    state++;
                    break;
                case STATE_COUPON:
                    String s4 = reader.readLine();
//                    System.out.println(s4);
                    if (s4 != null && s4.length() > 0)
                        parseCoupon(s4);
                    state++;
                    break;
            }
        }
    }

    /**
     * 解析优惠券
     *
     * @param s
     */
    private void parseCoupon(String s) {
        //2014.3.2 1000 200
        String[] strings = s.split(" ");

        double reachPrice = Double.parseDouble(strings[1]);
        double reducePrice = Double.parseDouble(strings[2]);
        String[] dateString = strings[0].split("\\.");
        int year = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int day = Integer.parseInt(dateString[2]);
        Coupon coupon = new Coupon(reachPrice, reducePrice, year, month, day);
        shoppingcart.setCoupon(coupon);
    }

    /**
     * 解析结算日期
     *
     * @param s
     */
    private void parseDate(String s) {
        String[] dateString = s.split("\\.");
        int year = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int day = Integer.parseInt(dateString[2]);
        shoppingcart.setDate(year, month, day);
    }

    /**
     * 解析商品信息
     *
     * @param s
     */
    private void parseProduct(String s) {
        String[] strings = s.split(" \\* ");
        int num = Integer.parseInt(strings[0]);
        String[] s1 = strings[1].split(" : ");
        String name = s1[0];
        ProductType type = ProductType.getType(name);
        double price = Double.parseDouble(s1[1]);
        Product p = new Product(name, type, price);
        shoppingcart.addProduct(p, num);
    }

    /**
     * 解析打折信息
     *
     * @param s
     */
    private void parseSales(String s) {
        String[] strings = s.split(" \\| ");
        ProductType type = ProductType.ELECTRONIC;
        for (ProductType t : ProductType.values()) {
            if (t.getName().equals(strings[2])) {
                type = t;
                break;
            }
        }
        String[] dateString = strings[0].split("\\.");
        int year = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int day = Integer.parseInt(dateString[2]);
        Sales sales1 = new Sales(type, year, month, day, Double.parseDouble(strings[1]));
        shoppingcart.addSales(sales1);

    }
}
