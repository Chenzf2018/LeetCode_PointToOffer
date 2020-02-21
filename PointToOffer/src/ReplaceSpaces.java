/**
 * 5.替换空格
 */

public class ReplaceSpaces
{
    public static String replaceSpace(StringBuffer str)
    {
        if (str == null)
            return null;
        StringBuffer newStr = new StringBuffer();
        for (int i=0; i < str.length(); i++)
        {
            if (str.charAt(i) == ' ')
            {
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            }
            else
                newStr.append(str.charAt(i));
        }
        return newStr.toString();
    }

    public static void main(String[] args)
    {
        StringBuffer preString = new StringBuffer("We are happy ");
        String newString = replaceSpace(preString);
        System.out.println(preString);
        System.out.println(newString);
    }
}
