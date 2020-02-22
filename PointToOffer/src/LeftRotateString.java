/**
 * 58.2.左旋转字符串
 * P286
 */

public class LeftRotateString
{
    private static String leftRotateString(String str, int n)
    {
        char[] chars = str.toCharArray();
        if (chars.length < n)
            return "";
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars)
            stringBuilder.append(c);
        return stringBuilder.toString();
    }

    private static void reverse(char[] chars, int low, int high)
    {
        while (low < high)
        {
            char temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;high--;
        }
    }

    public static void main(String[] args)
    {
        String string = "abcdefg";
        int n = 2;
        System.out.println(leftRotateString(string, n));
    }
}
