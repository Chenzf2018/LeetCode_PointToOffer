# 剑指Offer
## 按类型
### 字符串
#### 5.替换空格
P51：

&emsp;&emsp;**实现一个函数，<u>把字符串中的每个空格都替换成“%20”</u>。例如，输入“We are happy.”，则输出为“We%20are%20happy.”。**

**Java StringBuffer和StringBuilder类**
&emsp;&emsp;当<u><font color=red>对字符串[^1]进行修改的时候</font></u>，需要使用StringBuffer和StringBuilder类。
[^1]: 一般，字符串不可修改！

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
**解题思路**
思路详解见《剑指Offer》P51

&emsp;&emsp;先遍历一次字符串，计算出替换后字符串的总长度；<font color=red>从字符串的后面开始复制和替换</font>：<font color=red>准备两个指针$P_{1}$和$P_{2}$。$P_{1}$指向原始字符串的末尾，$P_{2}$指向替换后字符串的末尾</font>。向前移动指针$P_{1}$，逐个把它指向的字符复制到$P_{2}$指向的位置，直到碰到空格为止。碰到空格后，把$P_{1}$向前移动1格，在$P_{2}$之前插入字符串"%20"，将$P_{2}$向前移动3格。接着按上述方法复制。当$P_{1}$和$P_{2}$指向同一位置时，表明所有空格都已经替换完毕！

&emsp;&emsp;所有的字符都只复制一次，因此这个算法的时间效率是$O\left ( n \right )$。

**ReplaceSpaces_5.java**
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

下面的方法，空间复杂度提高了！
**ReplaceSpaces.java**
```java {.line-numbers highlight=12}
/**
 * 5.替换空格
 */

public class ReplaceSpaces
{
    public static String replaceSpace(StringBuffer str)
    {
        if (str == null)
            return null;
        StringBuffer newStr = new StringBuffer();
        for (int i=0; i < str.length(); i++)
        {
            if (str.charAt(i) == ' ')
            {
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
            }
            else
                newStr.append(str.charAt(i));
        }
        return newStr.toString();
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

#### 19 正则表达式匹配
P124
&emsp;&emsp;实现一个函数用来匹配包括'.'和'\*'的正则表达式。<font color=red>模式中的字符'.'表示任意一个字符，而'\*'表示它前面的字符可以出现任意次(包含0次)</font>。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab\*ac\*a"匹配，但是与"aa.a"和"ab\*a"均不匹配。

**解析**：
&emsp;&emsp;每次从字符串里拿出一个字符和模式中的字符去匹配。<font color=red>先来分析如何匹配一个字符</font>。如果模式中的字符ch是'.'， 那么它可以匹配字符串中的任意字符。如果模式中的字符ch不是'.'，而且字符串中的字符也是ch(字符串中的字符与模式中的字符一样)，那么它们相互匹配。当字符串中的字符和模式中的字符相匹配时，接着匹配后面的字符。

<font color=red>当模式中的第二个字符不是'\*'时</font>，如果字符串中的第一个字符和模式中的第一个字符相匹配， 那么在字符串和模式上都向后移动一个字符，然后匹配剩余的字符串和模式。<font color=red>如果字符串中的第一个字符和模式中的第一个字符不相匹配，则直接返回false</font>。

<font color=red>当模式中的第二个字符是'\*'时</font>，可能有多种不同的匹配方式。(<font color=red>一种选择是<u>在模式上</u>向后移动两个字符。这相当于'\*'和它前面的字符被忽略了</font>，因为'\*'可以匹配字符串中的0个字符。<font color=red>如果模式中的第一个字符和字符串中的第一个字符相匹配，则在字符串上向后移动一个字符，而在模式上有两种选择：可以在模式上向后移动两个字符，也可以保持模式不变</font>。)

如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
* 模式后移2字符，相当于x\*被忽略；
* 字符串后移1字符，模式后移2字符；
* 字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位。

```java
public class RegularExpressionMatching
{
    private static boolean match(char[] str, char[] pattern)
    {
        if (str == null || pattern == null)
            return false;

        int strIndex = 0;
        int patternIndex = 0;
        return matchCore(str, strIndex, pattern, patternIndex);
    }

    private static boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex)
    {
        //str到尾，pattern到尾，匹配成功(所谓到尾，指：xxIndex==xx.length)
        if (strIndex == str.length && patternIndex == pattern.length)  // 数组长度的函数不是length()
            return true;

        //str未到尾，pattern到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length)
            return false;

        //str到尾，pattern未到尾(不一定匹配失败，因为a*可以匹配0个字符)
        if (strIndex == str.length && patternIndex < pattern.length) {
            //只有pattern剩下的部分类似a*b*c*的形式，才匹配成功
            if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
            return false;
        }

        //str未到尾，pattern未到尾

        //模式第2个是*
        // 字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*')
        {
            if (pattern[patternIndex] == str[strIndex] || (pattern[patternIndex] == '.' && strIndex != str.length)) // &&存在可排除a与a.*
            {
                return matchCore(str, strIndex, pattern, patternIndex + 2)  //模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)  //字符串后移1字符，模式后移2字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);  //字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位
            }
            else  //字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配
                return matchCore(str, strIndex, pattern, patternIndex + 2);
        }

        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if (pattern[patternIndex] == str[strIndex] || (pattern[patternIndex] == '.' && strIndex != str.length))  // a与a.使得&&存在
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);

        return false;
    }

    public static void main(String[] args)
    {
        char[] str1 = {'a', 'a', 'a'};
        //char[] str2 = {'a', '.', 'a'};
        //char[] str3 = {'a', 'b', '*', 'a'};
        char[] str4 = {'a', '*', 'a', 'a'};
        System.out.println(match(str1, str4));
    }
}
```

#### 20.表示数值的字符串
P127
&emsp;&emsp;请实现一个函数用来判断字符串是否表示数值(包括整数和小数)。例如，字符串"+100", "5e2", "-123", "3.1416"和"-1E-16"都表示数值。但是"12e", "1a3.14", "1.2.3", "+-5"和"12e+4.3"都不是。

**解析**：
&emsp;&emsp;表示数值的字符串遵循模式 **A[.[B]]['e|E'C]** 或者 **.B['e|E'C]**。A,B,C表示整数(A不是必需的，如.123=0.123) ( | 表示或。[]表示可有可无)。

A和C都是可能以'+'或者'-'开头的0\~9的数位串；B也是0\~9的数位串，但是前面不能有正负号。

&emsp;&emsp;<font color=red>判断一个字符串是否符合上述模式时，首先尽可能多地扫描0\~9的数位(有可能在起始处有'+'或者'-'，也就是前面模式中表示数值整数的A部分。如果遇到小数点'.'，则开始扫描表示数值小数部分的B部分。如果遇到'e'或者'E'，则开始扫描表示数值指数的C部分。</font>
```Java
/**
 * 20.表示数值的字符串
 */

public class NumberStrings
{
    private static boolean isNumeric(char[] str)
    {
        String string = String.valueOf(str);
        return string.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
        // return string.matches("[\\+\\-]?\\d*(\\.\\d+)?([eE][\\+\\-]?\\d+)?");
    }

    public static void main(String[] args)
    {
        System.out.println(isNumeric(new char[] {'2', 'e', '+', '5', '.', '4'}));
    }
}
```
对正则进行解释：
```
[+-]?                  -> 正或负符号出现与否
[0-9]*                 -> 整数部分是否出现，如-.34 或 +3.34均符合
(\\.[0-9]*)?           -> 如果出现小数点，那么小数点后面必须有数字；否则一起不出现
([eE][+-]?[0-9]+)?     -> 如果存在指数部分，那么e或E肯定出现，+或-可以不出现，紧接着必须跟着整数；或者整个部分都不出现

限定符：
*	匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。
+	匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。
?	匹配前面的子表达式零次或一次。例如，"do(es)?" 可以匹配 "do", "does" 中的 "does", "doxy" 中的 "do" 。
( )	标记一个子表达式的开始和结束位置。
\\.     表示'.'，Java中转义需要两个反斜杠
```

#### 58.翻转单词顺序(\*****)
P284
&emsp;&emsp;输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student."，则输出"student. a am I"。

**解析**：
&emsp;&emsp;方法一：第一步翻转句子中所有的字符。比如翻转"I am a student."中所有的字符得到".tneduts a ma I"，此时不但翻转了句子中单词的顺序，连单词内的字符顺序也被翻转了。第二步再翻转每个单词中字符的顺序，就得到了"student. a am I"。句子中，单词被空格符号分隔，因此我们可以通过扫描空格来确定每个单词的起始和终止位置。
```java
/**
 * 58.翻转单词顺序 P284
 * 方法一
 */

public class ReverseSentence
{
    private static String reverseSentence(String str)
    {
        char[] chars = str.toCharArray();
        reverse(chars, 0, chars.length - 1);
        int blank = -1;
        for (int i = 0; i < chars.length; i++)  // 不会对最后一个单词进行翻转处理
        {
            if (chars[i] == ' ')  // 找到最后一个单词前的空格就结束了
            {
                int nextBlank = i;
                reverse(chars, blank + 1, nextBlank - 1);
                blank = nextBlank;
            }
        }
        reverse(chars, blank + 1, chars.length - 1);  // 最后一个单词单独进行翻转
        return new String(chars);
    }

    private static void reverse(char[] chars, int low, int high)
    {
        while (low < high)
        {
            char temp = chars[high];
            chars[high] = chars[low];
            chars[low] = temp;
            low++;high--;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(reverseSentence("student. a am I"));
    }
}
```

&emsp;&emsp;方法二：将单词放入数组中，只翻转数组中单词的位置，不改变单词内字母的位置。
```java
public class ReverseSentence
{
    private static String reverseSentence(String str)
    {
        if (str == null || str.trim().equals("")) // "" or " "
            return str;

        String[] strings = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--)
        {
            if (i == 0)
                stringBuilder.append(strings[0]);
            else
            {
                stringBuilder.append(strings[i]);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(reverseSentence(""));
    }
}
```

#### 58.2.左旋转字符串
P286
&emsp;&emsp;汇编语言中有一种移位指令叫做循环左移(ROL)，现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”，要求输出循环左移3位后的结果，即“XYZdefabc”。

字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串 "abcdefg"和数字2, 该函数将返回左旋转两位得到的结果"cdefgab"。

**解析**：
&emsp;&emsp;以"abcdefg"为例，我们可以把它分为两部分。由于想把它的前两个字符移到后面，我们就把前两个字符分到第一部分；把后面的所有字符分到第二部分。我们先分别翻转这两部分，于是就得到"bagfedc"。接下来翻转整个字符串，得到的"cdefgab"刚好就是把原始字符串 左旋转两位的结果。
```java
/**
 * 58.2.左旋转字符串
 * P286
 */

public class LeftRotateString
{
    private static String leftRotateString(String str, int n)
    {
        char[] chars = str.toCharArray();
        if (chars.length < n)
            return "";
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars)
            stringBuilder.append(c);
        return stringBuilder.toString();
    }

    private static void reverse(char[] chars, int low, int high)
    {
        while (low < high)
        {
            char temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;high--;
        }
    }

    public static void main(String[] args)
    {
        String string = "abcdefg";
        int n = 2;
        System.out.println(leftRotateString(string, n));
    }
}
```

#### 67.把字符串转换成整数
P318
&emsp;&emsp;将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。数值为0或者字符串不是一个合法的数值则返回0。输入：1a33；输出：0。
```java {.line-numbers highlight=46}
/**
 * 67.把字符串转换成整数
 * P318
 */

public class StrToInt
{
    private static int strToInt(String str)
    {
        if (str == null || str.length() == 0)  // 想想str == null和str.length() == 0的区别
            return 0;
            //throw new Exception("待转换字符串为null或空串");
        int tag;  // 首位是否'-'
        int start;  // 遍历从何处开始
        if (str.charAt(0) == '+') {tag  = 1; start = 1;}
        else if (str.charAt(0) == '-'){tag = 0; start = 1;}
        else {tag = 1; start = 0;}

        long result = 0;
        for (int i = start; i < str.length(); i++)
        {
            char temp = str.charAt(i);
            if (temp >= '0' && temp <= '9')
            {
                result = result * 10 + (temp - '0');
                if (tag == 1 && result > Integer.MAX_VALUE)
                    throw new RuntimeException("上溢出");
                if (tag == 0 && result < Integer.MIN_VALUE)
                    throw new RuntimeException("下溢出");  // 输入-2147483649并未出现下溢出
            }
            else
                return 0;
        }

        if (tag == 0)
            return (int) (-1 * result);
        else
            return (int) result;
    }

    public static void main(String[] args)
    {
        //System.out.println(strToInt("2147483648"));  //上溢出
        System.out.println(strToInt("2147483647"));  //2147483647
        System.out.println(strToInt("-2147483648")); // -2147483648
        System.out.println(strToInt("-2147483649")); //2147483647
    }
}
```
<font color=red>没有解决“下溢出”问题</font>，错误结果如代码第46行所示！

把对最大值和最小值的处理放在for循环外：

```java
/**
 * 67.把字符串转换成整数
 * P318
 */

public class StrToInt
{
    private static int strToInt(String str)
    {
        if (str == null || str.length() == 0)  // 想想str == null和str.length() == 0的区别
            return 0;
        boolean isNegative = str.charAt(0) == '-';  // 是'-'则为true
        long result = 0;  // 避免溢出
        for (int i = 0; i < str.length(); i++)
        {
            char aChar = str.charAt(i);
            if (i == 0 && (aChar == '+' || aChar == '-'))  // 符号判定
                continue;
            if (aChar < '0' || aChar > '9')  // 非法输入
                return 0;
            result = result * 10 + (aChar - '0');
        }
        // 处理最大正数，最大负数
        if (isNegative && (-result) < (-2147483648))
            throw new RuntimeException("下溢出");
        else if (!isNegative && result > 2147483647)
            throw new RuntimeException("上溢出");
        return isNegative ? (int)(-result) : (int)result;
    }

    public static void main(String[] args)
    {
        //System.out.println(strToInt("2147483648"));  //上溢出
        System.out.println(strToInt("2147483647"));  //2147483647
        System.out.println(strToInt("-2147483648")); // -2147483648
        //System.out.println(strToInt("-2147483649")); //下溢出
        System.out.println(strToInt("-"));
    }
}
```

### 栈和队列
&emsp;&emsp;栈的特点是后进先出，即最后被压入(push)栈的元素会第一个被弹出(pop)。通常栈是一个不考虑排序的数据结构，我们需要$O(n)$时间才能找到栈中最大或者最小的元素。和栈个同的是，队列的特点是先进先出。  

#### 9.用两个栈实现队列
P68
&emsp;&emsp;用两个栈实现一个队列。队列的声明如下，请实现它的两个函数push(appendTail)和pop(deleteHead), 分别完成在队列尾部插入节点和在队列头部删除节点的功能。队列中的元素为int类型。

**解析**：
&emsp;&emsp;首先插入一个元素a，把它插入stack1，此时stack1中的元素有{a}，stack2为空。再压入两个元素b和c，还是插入stack1，此时stack1中的元素有{a, b, c}，其中c位于栈顶，而stack2仍然是空的，如图(a)所示：
<div align=center><img src=PointToOffer_Images/用两个栈模拟一个队列的橾作.png width=80%></div>

&emsp;&emsp;这时候我们试着从队列中删除一个元素。按照队列先入先出的规则，最先被删除的元素应该是a。元素a存储在stack1中，但并不在栈顶上，因此不能直接进行删除。可以把stack1中的元素逐个弹出并压入stack2。因此经过3次弹出stack1和压入stack2的操作之后，stack1为空，而stack2中的元素是{c,b,a}，这时候就可以弹出stack2的栈顶a了。此时stack2的元素为{c, b}，其中b在栈顶，如图(b)所示。

如果我们还想继续删除队列的头部应该怎么办呢？剩下的两个元素是b和c，此时b恰好在栈顶上，因此直接弹出stack2的栈顶即可。

&emsp;&emsp;从上面的分析中我们可以总结出删除一个元素的步骤：当stack2不为空时，在stack2中的栈顶元素是最先进入队列的元素，可以弹出。当stack2为空时，我们把stack1中的元素逐个弹出并压入stack2。由于先进入队列的元素被压到stack1的底端，经过弹出和压入操作之后就处于stack2的顶端，又可以直接弹出。(<font color=red>stack2如果不空，想再添加元素并推出时，得先将stack2清空</font>)

&emsp;&emsp;接下来再插入一个元素d。得把它压入stack1，如图(d)所示。考虑下一次删除队列的头部stack2不为空，直接弹出它的栈顶元素c，如图(e)所示。而c的确比d先进入队列，应该在d之前从队列中删除，因此不会出现任何矛盾。

&emsp;&emsp;总结下，stack1负责插入，stack2负责弹出。如果stack2为空了，将stack1的元素依次**全部**弹出并存放到stack2中，之后对stack2进行弹出操作。**<font color=red>*push*操作只需包含将元素压入stack1；*pop()* 操作包含：先将stack2弹空，再将stack1中元素压入stack2中</font>**。
