package com.example.fuzz;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/16 15:10
 * java8自带函数式接口
 */
public class TestFunc {

    //Consumer消费型接口 	T	void	对类型为T的对象应用操作：void accept(T t)
    @Test
    public void test01() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("consumer");
    }

    //Supplier/提供型接口	无	T	返回类型为T的对象：T get()
    @Test
    public void test02() {
        Supplier<Integer> stringSupplier = () -> RandomUtil.randomInt();
        System.out.println(stringSupplier.get());
    }

    //Function<T, R>函数型接口	T	R	对类型为T的对象应用操作，并返回结果为R类型的对象：R apply(T t)
    @Test
    public void test03() {
        Function<String, String> function = (s) -> s + RandomUtil.randomInt();
        System.out.println(function.apply("function"));
    }

    //Predicate断言型接口	T	boolean	确定类型为T的对象是否满足某约束，并返回boolean值：boolean test(T t)
    @Test
    public void test04() {
        Predicate<String> predicate = (s) -> s.length() > 10;
        System.out.println(predicate.test("dkosd"));
        System.out.println(predicate.test("dkosddfsfffffffffffffffffff"));
    }

    // ==================================================
    /**
     * 方法引用
     * **定义：**若 Lambda 表达式体中的内容已有方法实现，则我们可以使用“方法引用”
     *
     * 语法格式：
     *
     * 对象 :: 实例方法
     * 类 :: 静态方法
     * 类 :: 实例方法
     */
    @Test
    public void test05() {
        Consumer<String> s0 = (s) -> System.out.println(s);
        Consumer<String> s = System.out::println;
        s0.accept("asaqw");
        s.accept("asa");

        Comparator<Integer> comparator1 = (a, b) -> Integer.compare(a, b);
        Comparator<Integer> comparator =  Integer::compare;
        System.out.println(comparator1.compare(1, 2));
        System.out.println(comparator.compare(1, 2));

        Supplier<List> sup1 = () -> new ArrayList();

        Supplier<List> sup2 = ArrayList::new;
    }


}
