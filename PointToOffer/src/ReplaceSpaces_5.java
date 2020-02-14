public class ReplaceSpaces_5
{
    public static String replaceSpace(StringBuffer stringBuffer)
    {
        int P1= stringBuffer.length() - 1;
        //System.out.println(P1);
        for (int i = 0; i <= P1; i++)
        {
            if (stringBuffer.charAt(i) == ' ')
                stringBuffer.append("  ");  // 两个空格
        }

        int P2 = stringBuffer.length() - 1;
        //System.out.println(P2);
        while (P1 >= 0 && P2 > P1)
        {
            char c = stringBuffer.charAt(P1--);
            if (c == ' ')
            {
                stringBuffer.setCharAt(P2--, '0');
                stringBuffer.setCharAt(P2--, '2');
                stringBuffer.setCharAt(P2--, '%');
            }
            else
                stringBuffer.setCharAt(P2--, c);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args)
    {
        StringBuffer preString = new StringBuffer("We are happy ");
        String newString = replaceSpace(preString);
        System.out.println(preString);
        System.out.println(newString);
    }
}
/*
We%20are%20happy%20
We%20are%20happy%20
 */