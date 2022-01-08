package week01;

/**
 * @author niuben
 */
public class day01 {

    /**
     * 给定一个字符串表示的IP地址，比如“123.92.2.34"，判断其是否合法。
     * 合法P地址的规则如下：
     * a.除了空格、数字和.之外，不得包含其他字符。
     * b.IP地址由四个数字构成，由.分隔，每个.隔开的数字大小在0~255之间。
     * c.数字前后可以有空格，但中间不能有空格。比如123.92.2.34"合法，"123.92.2.34非法。
     * <p>
     * 当然，这个问题还可以继续加一些规则，让题目变得更加复杂，
     * 比如每个数字不能有前导0，但可以为0。比如021.3.02.34"非法，"0.2.0.33"合法。
     * <p>
     * <p>
     * 1. a.123.2.34     不合法(包含其他字符)
     * 2. 123.2.34       不合法(IP地址长度小于4)
     * 3. 123.92.2,34    不合法(不完全由.分隔)
     * 4. 321.92.2,34    不合法(单独的数字不在0~255之间)
     * 5. 012.123.92.2   不合法(有前导0)
     * 6. 0 2.123.92.2   不合法(中间有空格)
     * 6. 0.2.0.33       合法
     */
    public boolean check(String ip) {
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

    public static void main(String[] args) {
        day01 exampl1 = new day01();

//        String ip = "123.9.2.0";
//        String ip = "223. 33. 13 . 33";
//        String  ip = "223. 3 3. 13 . 33";
//        String ip = "2ba. 33. 13 . 33";
//        String ip = "232. 33. 13";
        String ip = "232. .33. 13";

        System.out.println(exampl1.check(ip));
    }

}
