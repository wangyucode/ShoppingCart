package cn.wycode.model;

/**
 * 商品类型
 * Created by wy
 * on 2017/3/2.
 */
public enum ProductType {
    ELECTRONIC("电子", new String[]{"ipad", "iphone", "显示器", "笔记本电脑", "键盘"}),
    FOOD("食品", new String[]{"面包", "饼干", "蛋糕", "牛肉", "鱼", "蔬菜"}),
    DAILY("日用品", new String[]{"餐巾纸", "收纳箱", "咖啡杯", "雨伞"}),
    WINE("酒类", new String[]{"啤酒", "白酒", "伏特加"});


    private String name;
    private String[] products;

    ProductType(String name, String[] products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public static ProductType getType(String product) {
        for (ProductType t : ProductType.values()) {
            for (String p : t.products) {
                if (p.equals(product)) {
                    return t;
                }
            }
        }
        throw new IllegalArgumentException("没有这种商品！");

    }
}
