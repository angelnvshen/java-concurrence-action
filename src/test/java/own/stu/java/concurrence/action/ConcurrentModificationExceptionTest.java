package own.stu.java.concurrence.action;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by CHANEL on 2018/5/18.
 */
public class ConcurrentModificationExceptionTest {


    static List<String> list = new ArrayList<>();
    static{
        list.addAll(Arrays.asList("000", "1111", "22222", "3333"));
    }
    /**
     *  1：for 循环遍历 list 过程中删除元素，出现 concurrentModificationException ;
     *      解决方法 使用 iterator 遍历
     *  2：iterator遍历的底层
     *  3：增强for循环的底层
     *  4：concurrentModifiedException出现的原因及底层:
     *      迭代容器过程中被修改，就会抛出-个concurrentModificationException 异常，这种"及时失败"的迭代器
     *      只是善意的捕获并发错误，是一种设计上的权衡，从而降低并发修改操作的检查代码对程序性能
     *      带来的影响。
     */
    @Test
    public void happened(){

        for(String str : list){
            System.out.println(str);
            list.remove(str);
        }
    }

    @Test
    public void solve(){
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String cur = iterator.next();
            System.out.println(cur);
            if("22222".equals(cur))
                iterator.remove();
        }
        System.out.println(list);
    }
}
