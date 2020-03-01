/*
39.数组中出现次数超过一半的数字
P205
 */

public class MoreThanHalfNum
{
    public int moreThanHalfNum(int[] array)
    {
        int temp = 0;
        int count = 0;
        // 找出疑似数字
        for (int i = 0; i < array.length; i++)
        {
            if (count == 0)
                temp = array[i];
            if (temp == array[i])
                count++;
            else
                count--;
        }

        // 确认数字出现的次数是否过半
        count = 0;
        /*
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == temp)
                count++;
        }*/

        for (int item : array)
        {
            if (item == temp)
                count++;
        }

        return count > array.length / 2 ? temp : 0;
    }
}
