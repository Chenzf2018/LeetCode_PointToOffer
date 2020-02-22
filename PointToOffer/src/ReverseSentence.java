/**
 * 58.翻转单词顺序 P284
 * 方法二
 */

public class ReverseSentence
{
    private static String reverseSentence(String str)
    {
        if (str == null || str.trim().equals("")) // "" or " "
            return str;

        String[] strings = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--)
        {
            if (i == 0)
                stringBuilder.append(strings[0]);
            else
            {
                stringBuilder.append(strings[i]);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(reverseSentence("I am a student."));
    }
}
