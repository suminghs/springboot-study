package com.example.fuzz;

import com.exmple.fuzz.interf.MyFun;
import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/16 13:36
 */
public class TestLambda {

    @Test
    public void test01() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> set = new TreeSet<>(comparator);
    }

    @Test
    public void test02() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet set = new TreeSet(comparator);
    }

    //无参数，无返回值：() -> sout
    @Test
    public void test03() {
        new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
            }
        }.run();
        Runnable runnable = () -> System.out.println("start2");
        runnable.run();
    }

    //有一个参数，无返回值
    @Test
    public void test04() {
        Consumer<Integer> consumer = a -> System.out.println(a);
        consumer.accept(1001);
    }

    //有两个及以上的参数，有返回值，并且 Lambda 体中有多条语句
    @Test
    public void test05() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("比较。。");
            return Integer.compare(x, y);
        };
        Comparator<Integer> comparator2 = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(2, 1));
        System.out.println(comparator2.compare(1, 3));
    }

    //测试自定义函数式接口
    @Test
    public void test06() {
        MyFun myFun = (x, y) -> x + y + 1;
        MyFun myFun2 = (x, y) -> x * y;
        System.out.println(myFun.option(1, 3));
        System.out.println(myFun2.option(2, 3));
    }

}
