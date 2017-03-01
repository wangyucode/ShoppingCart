# ShoppingCart

你可以选用你所擅长的任意一种语言来实现，我们强烈建议你采用支持面向对象编程的语言来实现。   
你的代码应该能够覆盖题目所描述的各种正常和异常路径，但是不需要过多考虑题目所没有描述的场景。   
代码应当组织清晰、符合 clean code 原则，能够体现面向对象设计原则同时不过度设计，不能使用第三方框 架。   
代码需包含单元测试，代码如果包含良好的自动化构建将会是加分项。   
某电子商务网站需要实现一个虚拟的购物车，以便让它的用户能够同时购买多件商品，从而获得更好的购物体 验。在用户访问该网站的过程中，他们可以一边浏览商品，一边将自己满意的商品添加到购物车中，等到选购完毕， 再一次性结清购物车中的所有商品。购物车结算过程涉及到一些业务逻辑，需要编写程序自动计算最后购买者需要支 付的实际金额。   
其中一个影响结算金额的因素是折扣。网站会在一些法定节假日或者其他促销季节推出某些商品的折扣，例如双 十一的时候，会有针对电子产品的折扣，而在五一假期的时候，会有针对日用品的折扣，在计算购物车结算金额的时 候，需要根据结算当天的折扣计算出折后的金额。   
另外，网站为了促销，曾经在过去一年内派发了一些优惠券，这些优惠券只要有效期内，都能够用在任何一次购 物中，但是每次结算只能用一张。这些优惠通常是 “满 1000 减 200” 这样的形式，表示总金额如果超过   
1000 则立减 200。   
简化掉系统的其他部分，你需要编写一个程序，根据输入的购物车信息和促销信息来计算用户需要支付的结算金 额。同样为了简化问题，我们初步支持以下的产品目录：   
• 电子：ipad，iphone，显示器，笔记本电脑，键盘   
• 食品：面包，饼干，蛋糕，牛肉，鱼，蔬菜   • 日用品：餐巾纸，收纳箱，咖啡杯，雨伞   • 酒类：啤酒，白酒，伏特加   
以下是一些用例，你的程序必须通过这些用例，并编写自动化测试来证明你的程序在其他情形下同样工作准确、 健壮。   
====== Case A ======== 输  
⼊   2013.11.11 | 0.7 | 电⼦ //促销信息，格式为：⽇期 | 折扣 | 产品品类，可有多个，每个   ⼀⾏，如果没有则保留⼀个空⾏   //空⾏分隔   
1 * ipad : 2399.00   //所购产品，每种⼀⾏，格式为：数量 * 商品 : 单价   
1 * 显⽰器 : 1799.00   12 * 啤酒 : 25.00   
5 * ⾯包 : 9.00 //空⾏分隔   
2013.11.11 //结算⽇期   
2014.3.2 1000 200  //优惠券信息，⽰例为 2014 年 3⽉2⽇到期，满 1000 返 200，空格分隔，如果没有则 保留⼀个空⾏   
输出   3083.60  //输出结果四舍五⼊到⼩数点后 2 位。   =====================   
====== Case B ======= 输  
⼊   3 * 蔬菜 : 5.98   
8 * 餐⼱纸 : 3.20   
2014.01.01  输出   
43.54   
