/*
4.二维数组中的查找
P44
 */

public class FindIn2DArray
{
    public boolean findIn2DArray(int target, int[][] array)
    {
        int rows = array.length - 1;
        int columns = array[0].length - 1;
        if (rows < 0 || columns < 0)
            return false;

        int rowIndex = 0, columnIndex = columns;  // 从右上角开始
        while (rowIndex <= rows && columnIndex >= 0)
        {
            if (target > array[rowIndex][columnIndex])
                rowIndex++;
            else if (target < array[rowIndex][columnIndex])
                columnIndex--;
            else
                return true;
        }
        return false;
    }
}
