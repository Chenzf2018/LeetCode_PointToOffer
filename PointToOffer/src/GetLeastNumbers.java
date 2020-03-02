/*
40.最小的k个数
P209
 */

import java.util.ArrayList;

public class GetLeastNumbers
{
    public ArrayList<Integer> getLeastNumbers(int[] input, int k)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (input == null || k > input.length || k == 0)
            return arrayList;
        for (int i = 0; i < k; i++)  // 只需要找到k个数即可
        {
            for (int j = 0; j < input.length - 1 - i; j++)
            {
                if (input[j] < input[j + 1])
                {
                    int temp = input[j + 1];
                    input[j + 1] = input[j];
                    input[j] = temp;
                }
            }

            arrayList.add(input[input.length - 1 - i]);
        }

        return arrayList;
    }

}
