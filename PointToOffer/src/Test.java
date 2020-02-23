import java.util.ArrayList;
public class Test {
    public static ArrayList<Integer>  maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> maxA = new ArrayList<>();
        if(num == null || size < 1 || num.length < size )  // 关键是此处的条件
            return maxA;
        int len = num.length;
        //preMaxIndex上一个滑动窗口最大值的下标
        int preMaxIndex = findIndexOfMax(num,0,size - 1);
        maxA.add(num[preMaxIndex]);
        for(int start=1,end = size;start <= (len - size);start++,end++){
            //找每个滑动窗口的max值，i为每个滑动窗口起始index值
            int newMaxIndex = maxNumIndexOfWindow(num,preMaxIndex,start,end);
            maxA.add(num[newMaxIndex]);
            preMaxIndex = newMaxIndex;
        }
        return maxA;

    }
    static int maxNumIndexOfWindow(int[] num,int preMaxIndex,int start,int end){
        if(start <= preMaxIndex && preMaxIndex <= end){
            // 如果preMaxIndex在新的滑动窗口[start,end]之间
            // 则判断上一个最大值与新增的一个值num[end]比较大小，返回index
            return (num[preMaxIndex] <= num[end])? end : preMaxIndex;
        }else{
            // 如preMaxIndex不在新的滑动窗口[start,end]之间
            // 直接找新滑动窗口的max值的下标
            return findIndexOfMax(num,start,end);
        }
    }

    static int findIndexOfMax(int[] num,int start,int end){
        // 返回 数组[start,end]范围内的最大值的下标(靠右)
        int max = num[start];
        int index = start;
        for(int i=start;i<=end;i++){
            if(num[i] >= max){
                max = num[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args)
    {
        int[] num = {2, 3, 4, 2, 6, 2, 5, 1};
        int size = 3;
        System.out.println(maxInWindows(num, size));
    }
}