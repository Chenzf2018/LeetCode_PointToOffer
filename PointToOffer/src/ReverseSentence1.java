/**
 * 58.翻转单词顺序 P284
 * 方法一
 */

public class ReverseSentence1
{
    private static String reverseSentence1(String str)
    {
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length - 1);
        int blank = -1;
        for (int i = 0; i < chars.length; i++)  // 不会对最后一个单词进行翻转处理
        {
            if (chars[i] == ' ')  // 找到最后一个单词前的空格就结束了
            {
                int nextBlank = i;
                reverse(chars, blank + 1, nextBlank - 1);
                blank = nextBlank;
            }
        }
        reverse(chars, blank + 1, chars.length - 1);  // 最后一个单词单独进行翻转
        return new String(chars);
    }

    private static void reverse(char[] chars, int low, int high)
    {
        while (low < high)
        {
            char temp = chars[high];
            chars[high] = chars[low];
            chars[low] = temp;
            low++;high--;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(reverseSentence1("student. a am I"));
    }
}
