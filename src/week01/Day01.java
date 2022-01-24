package week01;

/**
 * @author niuben
 */
public class Day01 {

    public static void main(String[] args) {
        Example1 example1 = new Example1();
        Example2 example2 = new Example2();
        Example3 example3 = new Example3();

//        String ip = "123.9.2.0";
//        String ip = "223. 33. 13 . 33";
//        String ip = "223. 3 3. 13 . 33";
//        String ip = "2ba. 33. 13 . 33";
//        String ip = "232. 33. 13";
//        String ip = "232. .33. 13";
//
//        String ip1 = null;
//        System.out.println(example1.check(ip1));
//
//        String ip2 = "123.9.2.0";
//        System.out.println(example2.defangIpAddr(ip2));

        int count3 = example3.drink(3);
        int count4 = example3.drink(4);
        int count5 = example3.drink(5);
        int count6 = example3.drink(6);
        int count7 = example3.drink(7);

    }

}

/**例题1: 拼多多面试题
 * 给定一个字符串表示的IP地址，比如“123.92.2.34"，判断其是否合法。
 * 合法P地址的规则如下：
 * a.除了空格、数字和.之外，不得包含其他字符。
 * b.IP地址由四个数字构成，由.分隔，每个.隔开的数字大小在0~255之间。
 * c.数字前后可以有空格，但中间不能有空格。比如123.92.2.34"合法，"123.92.2.34非法。
 *
 * 当然，这个问题还可以继续加一些规则，让题目变得更加复杂，
 * 比如每个数字不能有前导0，但可以为0。比如021.3.02.34"非法，"0.2.0.33"合法。
 *
 * 1. a.123.2.34     不合法(包含其他字符)
 * 2. 123.2.34       不合法(IP地址长度小于4)
 * 3. 123.92.2,34    不合法(不完全由.分隔)
 * 4. 321.92.2,34    不合法(单独的数字不在0~255之间)
 * 5. 012.123.92.2   不合法(有前导0)
 * 6. 0 2.123.92.2   不合法(中间有空格)
 * 7. 0.2.0.33       合法
 */
class Example1 {
    public boolean check(String ip) {
        if (ip == null) { return false; }

        // 不允许有魔法值
        int len = 4;

        String[] segmentIp = ip.split("\\.");

        // 整体
        if (segmentIp.length != len) {
            return false;
        }

        // 分段Ip
        for (int i = 0; i < len; i++) {
            boolean valid = checkSegmentIp(segmentIp[i]);
            if (!valid) {
                return false;
            }
        }

        return true;
    }

    private boolean checkSegmentIp(String segmentIp) {
        // 排除前导,后导空格
        String trimIp = segmentIp.trim();
        int n = trimIp.length();

        // 除去空格后一个元素都没有的情况
        if (n == 0) {
            return false;
        }

        // 有元素,且第一个元素为0.当长度等1,合法,大于1,不合法
        if (n > 1 && trimIp.charAt(0) == 0) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            char c = trimIp.charAt(i);

            // 有其他字符的情况
            if (c < '0' || c > '9') {
                return false;
            }
        }

        // 中间没有空格,没有特殊字符,便都可以转成数字
        int num = Integer.parseInt(trimIp);
        int minNum = 0;
        int maxNum = 255;
        return num >= minNum && num <= maxNum;
    }
}

/**例题2:
 * 给你一个有效的IpV4地址 address，返回这个IP地址的无效化版本。
 * 所谓无效化IP地址，其实就是用","代替了每个"."
 */
class Example2 {
    public String defangIpAddr(String ip) {
        char[] originIp = ip.toCharArray();
        int originLen = originIp.length;
        int descLen = originLen + 6;
        char[] descIp = new char[descLen];

        for (int i = 0, j = 0; i < originLen; i++) {
            if (originIp[i] == '.') {
                descIp[j++] = '[';
                descIp[j++] = ',';
                descIp[j++] = ']';
            } else {
                descIp[j++] = originIp[i];
            }
        }

        return new String(descIp);
    }
}

/**例题3 阿里暑期实习
 * 现有×瓶啤酒,
 * 每3个空瓶子换一瓶啤酒,
 * 每7个瓶盖子也可以换一瓶啤酒,
 * 问最后可以喝多少瓶啤酒。
 */
class Example3 {
    public int drink(int x) {
        int count = x;
        int a = x;
        int b = x;

        while (a >= 3) {
            a = a - 3;
            b = b + 1;
            count = count + 1;
            while (b >= 7) {
                b = b - 7;
                a = a + 1;
                count = count + 1;
            }
        }
        System.out.println("开始:" + x + "瓶,最终酒瓶:" + a + "个,瓶盖:" + b + "个,一共喝了" + count + "瓶.");
        return count;
    }
}

/**
 * 例题4: 剑指offer 61.扑克牌中的顺子
 * 从扑克牌中随机抽5张牌,判断是不是一个顺子,即这5张牌是不是连续的。
 * 2~10为数字本身,A为1, J为11, Q为12, K为13
 * 大、小王为0，可以看成任意数字。
 * A不能视为14。
 *
 */
class Example4 {
    public boolean isStraight(int[] num) {
        boolean[] dup = new boolean[14];
        int min = 100;
        int max = -1;
        for (int i = 0; i < 5; i++) {
            if (num[i] != 0) {
                if (dup[num[i]]) return false;
                else dup[num[i]] = true;

                if (num[i] < min) min = num[i];
                if (num[i] > max) max = num[i];

            }
        }
        return (max - min) < 5;
    }
}