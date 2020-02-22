/**
 * 67.把字符串转换成整数
 * P318
 */

public class StrToInt
{
    private static int strToInt(String str)
    {
        if (str == null || str.length() == 0)  // 想想str == null和str.length() == 0的区别
            return 0;
        boolean isNegative = str.charAt(0) == '-';  // 是'-'则为true
        long result = 0;  // 避免溢出
        for (int i = 0; i < str.length(); i++)
        {
            char aChar = str.charAt(i);
            if (i == 0 && (aChar == '+' || aChar == '-'))  // 符号判定
                continue;
            if (aChar < '0' || aChar > '9')  // 非法输入
                return 0;
            result = result * 10 + (aChar - '0');
        }
        // 处理最大正数，最大负数
        if (isNegative && (-result) < (-2147483648))
            throw new RuntimeException("下溢出");
        else if (!isNegative && result > 2147483647)
            throw new RuntimeException("上溢出");
        return isNegative ? (int)(-result) : (int)result;
    }

    public static void main(String[] args)
    {
        //System.out.println(strToInt("2147483648"));  //上溢出
        System.out.println(strToInt("2147483647"));  //2147483647
        System.out.println(strToInt("-2147483648")); // -2147483648
        //System.out.println(strToInt("-2147483649")); //下溢出
        System.out.println(strToInt("-"));
    }
}
