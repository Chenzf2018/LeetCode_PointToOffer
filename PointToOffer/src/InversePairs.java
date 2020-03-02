/*
51.数组中的逆序对
P249
 */

public class InversePairs
{
    private int count;

    public int inversePairs(int[] array)
    {
        MergeSort(array, 0, array.length - 1);
        return count;
    }

    private void MergeSort(int[] array, int start, int end)
    {
        if (start >= end)
            return;
        int mid = (start + end) / 2;
        MergeSort(array, start, mid);
        MergeSort(array, mid + 1, end);
        MergeOne(array, start, mid, end);
    }

    private void MergeOne(int[] array, int start, int mid, int end)
    {
        int[] temp = new int[end - start + 1];
        int k = 0, i = start, j = mid + 1;
        while (i <= mid && j <= end)
        {
            //如果前面的元素小于后面的不能构成逆序对
            if (array[i] <= array[j])
                temp[k++] = array[i++];  // 以增序来添加
            else
            {
                //如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
                temp[k++] = array[j++];
                count = (count + (mid - i + 1)) % 1000000007;
            }
        }

        while (i <= mid)
            temp[k++] = array[i++];
        while (j <= end)
            temp[k++] = array[j++];
        for (int m = 0; m < k; m++)
            array[start + m] = temp[m];
    }
}
