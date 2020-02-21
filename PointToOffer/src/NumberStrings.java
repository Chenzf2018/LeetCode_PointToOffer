/**
 * 20.表示数值的字符串
 */

public class NumberStrings
{
    private static boolean isNumeric(char[] str)
    {
        String string = String.valueOf(str);
        return string.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
        // return string.matches("[\\+\\-]?\\d*(\\.\\d+)?([eE][\\+\\-]?\\d+)?");
    }

    public static void main(String[] args)
    {
        System.out.println(isNumeric(new char[] {'2', 'e', '+', '5', '.', '4'}));
    }
}
