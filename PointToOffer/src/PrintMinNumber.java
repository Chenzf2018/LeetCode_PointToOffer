/*
45.把数组排列成最小的数
P227
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PrintMinNumber
{
    public String printMinNumber(int[] numbers)
    {
        String string = "";
        ArrayList<Integer> list = new ArrayList<>();

        for (int number : numbers)
            list.add(number);  // 将数组放入ArrayList中

        //实现Comparator接口的compare方法，将集合元素按照compare方法的规则进行排序
        Collections.sort(list, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                String str1 = o1 + "" + o2;
                String str2 = o2 + "" + o1;
                return str1.compareTo(str2);
            }
        });
        
        for (int j : list)
            string += j;
        return string;
    }
}
