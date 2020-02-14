# 剑指Offer
## 按类型
### 字符串
#### 5.替换空格
P51：

&emsp;&emsp;**实现一个函数，<u>把字符串中的每个空格都替换成“%20”</u>。例如，输入“We are happy.”，则输出为“We%20are%20happy.”。**
##### Java StringBuffer和StringBuilder类
&emsp;&emsp;当<u><font color=red>对字符串[^1]进行修改的时候</font></u>，需要使用StringBuffer和StringBuilder类。

&emsp;&emsp;与String类不同的是，StringBuffer和StringBuilder类的对象能够被多次的修改，并且<font color=red><u>不产生新的未使用对象</u></font>。StringBuilder类在Java 5中被提出，它和StringBuffer之间的最大不同在于<u>StringBuilder的方法不是线程安全的</u>(不能同步访问)。由于StringBuilder相较于StringBuffer有速度优势，所以多数情况下建议使用StringBuilder类。然而在应用程序要求线程安全的情况下，则必须使用StringBuffer类。
```Java
public class Test
{
  public static void main(String args[])
  {
    StringBuffer sBuffer = new StringBuffer("sjtu");
    sBuffer.append("www");
    sBuffer.append(".sjtu");
    sBuffer.append(".edu.cn");
    System.out.println(sBuffer);  
  }
}
```
##### 解题思路
思路详解见《剑指Offer》P51

&emsp;&emsp;先遍历一次字符串，计算出替换后字符串的总长度；<font color=red>从字符串的后面开始复制和替换</font>：<font color=red>准备两个指针$P_{1}$和$P_{2}$。$P_{1}$指向原始字符串的末尾，$P_{2}$指向替换后字符串的末尾</font>。向前移动指针$P_{1}$，逐个把它指向的字符复制到$P_{2}$指向的位置，直到碰到空格为止。碰到空格后，把$P_{1}$向前移动1格，在$P_{2}$之前插入字符串"%20"，将$P_{2}$向前移动3格。接着按上述方法复制。当$P_{1}$和$P_{2}$指向同一位置时，表明所有空格都已经替换完毕！

&emsp;&emsp;所有的字符都只复制一次，因此这个算法的时间效率是$O\left ( n \right )$。
##### ReplaceSpaces_5.java
```Java
public class ReplaceSpaces_5
{
    public static String replaceSpace(StringBuffer stringBuffer)
    {
        int P1= stringBuffer.length() - 1;
        //System.out.println(P1);
        for (int i = 0; i <= P1; i++)
        {
            if (stringBuffer.charAt(i) == ' ')
                stringBuffer.append("  ");  // 两个空格
        }

        int P2 = stringBuffer.length() - 1;
        //System.out.println(P2);
        while (P1 >= 0 && P2 > P1)
        {
            char c = stringBuffer.charAt(P1--);
            if (c == ' ')
            {
                stringBuffer.setCharAt(P2--, '0');
                stringBuffer.setCharAt(P2--, '2');
                stringBuffer.setCharAt(P2--, '%');
            }
            else
                stringBuffer.setCharAt(P2--, c);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args)
    {
        StringBuffer preString = new StringBuffer("We are happy ");
        String newString = replaceSpace(preString);
        System.out.println(preString);
        System.out.println(newString);
    }
}
```
按Github的Markdown格式修改

[^1]: 一般，字符串不可修改！
