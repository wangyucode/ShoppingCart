import cn.wycode.ShoppingCart;
import cn.wycode.StringProcessor;
import cn.wycode.model.Coupon;
import cn.wycode.model.Product;
import cn.wycode.model.ProductType;
import cn.wycode.model.Sales;
import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试
 * Created by wy
 * on 2017/3/2.
 */
public class ShoppingCartTest {

    /*
    ====== Case A ========
    输⼊
    2013.11.11 | 0.7 | 电⼦ //促销信息，格式为：⽇期 | 折扣 | 产品品类，可有多个，每个 ⼀⾏，如果没有则保留⼀个空⾏
    //空⾏分隔
    1 * ipad : 2399.00 //所购产品，每种⼀⾏，格式为：数量 * 商品 : 单价
    1 * 显⽰器 : 1799.00
    12 * 啤酒 : 25.00
    5 * ⾯包 : 9.00
    //空⾏分隔
    2013.11.11 //结算⽇期
    2014.3.2 1000 200 //优惠券信息，⽰例为 2014 年 3⽉2⽇到期，满 1000 返 200，空格分隔，如果没有则 保留⼀个空⾏
    输出
    3083.60 //输出结果四舍五⼊到⼩数点后 2 位。
    =========================== Case B =======
    输⼊
    3 * 蔬菜 : 5.98
    8 * 餐⼱纸 : 3.20
    2014.01.01
    输出
    43.54
     */

    @Test
    public void case01() {
        ShoppingCart s = new ShoppingCart(2013, 11, 11);
        s.addProduct(new Product("iPad", ProductType.ELECTRONIC, 2399), 1);
        s.addProduct(new Product("显示器", ProductType.ELECTRONIC, 1799), 1);
        s.addProduct(new Product("啤酒", ProductType.WINE, 25), 12);
        s.addProduct(new Product("面包", ProductType.FOOD, 9), 5);
        s.addSales(new Sales(ProductType.ELECTRONIC, 2013, 11, 11, 0.7));
        s.setCoupon(new Coupon(1000, 200, 2014, 3, 2));
        s.setDate(2013, 11, 11);
        s.calculate();
        Assert.assertEquals("3083.60", s.getTotalPrice());
    }

    @Test
    public void case02() {
        String input = "2013.11.11 | 0.7 | 电子\n" +
                "\n" +
                "1 * ipad : 2399.00\n" +
                "1 * 显示器 : 1799.00\n" +
                "12 * 啤酒 : 25.00\n" +
                "5 * 面包 : 9.00\n" +
                "\n" +
                "2013.11.11\n" +
                "2014.3.2 1000 200";
        StringProcessor processor = new StringProcessor();
        Assert.assertEquals("3083.60", processor.process(input));
    }

    @Test
    public void case03() {
        String input = "\n" +
                "3 * 蔬菜 : 5.98\n" +
                "8 * 餐巾纸 : 3.20\n" +
                "\n" +
                "2014.01.01\n";
        StringProcessor processor = new StringProcessor();
        Assert.assertEquals("43.54", processor.process(input));
    }

    @Test
    public void case04() {
        String input = "\n" +
                "\n" +
                "2014.01.01\n";
        StringProcessor processor = new StringProcessor();
        Assert.assertEquals("0.00", processor.process(input));
    }

    @Test
    public void case05() {
        String input = "2013.11.11 | 0.7 | 电子\n" +
                "2013.11.11 | 0.8 | 日用品\n" +
                "2013.12.12 | 0.1 | 食品\n" +
                "\n" +
                "1 * ipad : 1000.00\n" +
                "12 * 啤酒 : 10.00\n" +
                "8 * 餐巾纸 : 1.00\n" +
                "3 * 蔬菜 : 10.00\n" +
                "\n" +
                "2013.11.11\n";
        StringProcessor processor = new StringProcessor();
        Assert.assertEquals("856.40", processor.process(input));
    }


    @Test
    public void case06() {
        String input ="\n" +
                "1 * ipad : 1000.00\n"+
                "\n" +
                "2013.11.11\n"+
                "2014.3.2 1000 2000";
        StringProcessor processor = new StringProcessor();
        Assert.assertEquals("0.00", processor.process(input));
    }

}
