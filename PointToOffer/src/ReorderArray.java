/*
21.使数组中奇数位于偶数前面
P129
保证奇数和奇数，偶数和偶数之间的相对位置不变
 */

public class ReorderArray
{
    public void reOrderArray(int[] array)
    {
        int k = 0;  // 记录当前已经排好的奇数中最后一个的位置
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] % 2 == 1)  // 找奇数
            {
                int j = i;  // j用来将找到的新的奇数往偶数前面排
                while (j > k)
                {
                    // 将找到的新奇数向前排，排到已排好序的奇数中最后一个数之后
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    j--;  // 将找到的奇数往前排
                }
                k++;  // 又找到一个奇数并且排好顺序
            }
        }
    }
}
