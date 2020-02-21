public class RegularExpressionMatching
{
    private static boolean match(char[] str, char[] pattern)
    {
        if (str == null || pattern == null)
            return false;

        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    private static boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex)
    {
        //str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length)  // 数组长度的函数不是length()
            return true;

        //str未到尾，pattern到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length)
            return false;

        //str到尾，pattern未到尾(不一定匹配失败，因为a*可以匹配0个字符)
        if (strIndex == str.length && patternIndex < pattern.length) {
            //只有pattern剩下的部分类似a*b*c*的形式，才匹配成功
            if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
            return false;
        }

        //str未到尾，pattern未到尾

        //模式第2个是*
        // 字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*')
        {
            if (pattern[patternIndex] == str[strIndex] || (pattern[patternIndex] == '.' && strIndex != str.length))
            {
                return matchCore(str, strIndex, pattern, patternIndex + 2)  //模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)  //字符串后移1字符，模式后移2字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);  //字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位
            }
            else  //字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配
                return matchCore(str, strIndex, pattern, patternIndex + 2);
        }

        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if (pattern[patternIndex] == str[strIndex] || (pattern[patternIndex] == '.' && strIndex != str.length))  // a与a.
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);

        return false;
    }

    public static void main(String[] args)
    {
        char[] str1 = {'a', 'a', 'a'};
        //char[] str2 = {'a', '.', 'a'};
        //char[] str3 = {'a', 'b', '*', 'a'};
        char[] str4 = {'a', '*', 'a', 'a'};
        System.out.println(match(str1, str4));
    }
}