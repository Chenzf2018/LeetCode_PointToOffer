/*
53.2. 0~(n−1)中缺失的数字
 */

public class FindMissingNumber
{
    public static int finMissingNumber(int[] data)
    {
        if (data == null || data.length <= 0)
            return -1;

        int left = 0, right = data.length - 1;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (data[mid] != mid)
            {
                // 如果中间元素的值和下标不相等，并且它前面一个元素和它的下标相等
                // if (mid == 0 || data[mid - 1] == mid - 1)
                if (data[mid - 1] == mid - 1)
                    return mid;
                else
                {
                    // 如果中间元素的值和下标不相等，并且它前面一个元素和它的下标不相等
                    right = mid - 2;  // 下一轮查找只需要在左半边查找
                }
            }
            else
            {
                // 如果中间元素的值和下标相等，那么下一轮查找只需要查找右半边
                left = mid + 1;
            }
        }

        if (left == data.length)
            return data[right];
        
        return -1;
    }
}
