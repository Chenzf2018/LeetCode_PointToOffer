/*
53.数字在排序数组中出现的次数
如果数据都是整数，可以不搜索K所在的位置，而是搜索(k-0.5)和(k+0.5)这两个数应该插入的位置，然后相减即可
 */

public class GetNumberOfK
{
    public int getNumberOfK(int[] array, int k)
    {
        if (array == null || array.length == 0)
            return 0;
        return binarySearch(array, k + 0.5) - binarySearch(array, k - 0.5);
    }

    private int binarySearch(int[] array, double n)
    {
        int low = 0, high = array.length - 1;
        while (low <= high)
        {
            int mid = low + (high - low) / 2;
            if (array[mid] > n)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }
}
