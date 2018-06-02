package own.stu.java.concurrence.action;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Questions {
    //输入一行字符，分别统计出其英文字母、空格、数字和其他字符出现的次数。
    public void count(String source){
        if(StringUtils.isEmpty(source))
            return;

        int wordsCount = 0, blankCount = 0, numCount = 0, otherCount = 0;
        String p_words = "[a-z|A-Z]";
        String p_blank = "\\s";
        String p_num = "\\d";

        wordsCount = countNumByPattern(source, p_words);
        blankCount = countNumByPattern(source, p_blank);
        numCount = countNumByPattern(source, p_num);
        otherCount = source.length() - wordsCount - blankCount - numCount;
        System.out.println("字母：" + wordsCount + ", 空格：" + blankCount + "数字：" + numCount +", 其他：" + otherCount);
    }

    //利用条件运算符的嵌套来完成此题：学习成绩>=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示，如果离特殊线分数x差5分的用D表示。
    public void setLevel(int score, int specialSocre){

        String level = "";
        level = (specialSocre - score) <= 5 && (specialSocre - score) >=0 ? "D" :
                (score >= 90 ? "A" : score >= 60 ? "B" : "C");
        System.out.println(level);
    }

    //打印出能被3整除的所有"水仙花数"，所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。
    // 例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方
    public void print(){
        for(int i=100;i<=999;i++){
            //截取个位和十位，百位
            int a=i%10,  b=i/10%10 , c=i/100%10;

            if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == i) {
                System.out.println(i);
            }
        }
    }

    private int countNumByPattern(String source, String pattern_Str){
        Pattern pattern = Pattern.compile(pattern_Str);
        Matcher matcher = pattern.matcher(source);
        int count = 0;
        while (matcher.find())
            count ++ ;

        return count;
    }

    @Test
    public void test() {
        print();
    }

    public static void main(String[] args) {
        /*int p, i, s = 0;
        for (int x = 1; x < 4; x++) {
            for (p = i = 1; i < x; i++) {
                p *= x;
            }
            System.out.println(p);
            s += p;
        }
        System.out.println(s);*/

        /*Classz c1 = new Classz();
        Classz c2 = new Classz();

        c1.a = (c1.a++) + (c2.b++);
        System.out.println(c1.a + "," + c1.b);*/

        /*C c1 = new C();
        c1.i = 1;
        change(c1);
        System.out.println(c1.i)*/;

        /*int [] array = {0, 4 ,10, 12, 21, 83, 100};
        System.out.println(index(array, -1));*/

        System.out.println(fibonacci3(6));
    }

    private static int fibonacci3(int n) {
        if (n == 0 || n == 1)
            return n;
        else
            return fibonacci3(n - 1) + fibonacci3(n - 2);
    }

    private static int index(int [] array, int target){
        if(array == null  || array.length == 0 )
            return -1;
        return index(array, target, 0, array.length - 1);
    }

    private static int index(int[] array, int target, int low, int high) {
        if (low <= high) {
            int middle = (low + high) / 2;
            if (array[middle] == target)
                return middle;
            else if (array[middle] > target)
                return index(array, target, low, middle - 1);
            else
                return index(array, target, middle + 1, high);
        }

        return -1;
    }

    private static void change(C c){
        c.i = 2;
        c = new C();
        c.i = 3;
    }

}
class C{
    public int i = 0;
}
class Classz {
    public static int a = 0;
    public int b = 0;

    public Classz() {
        a++;
        b++;
    }
}
