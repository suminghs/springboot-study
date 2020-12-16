package com.example.fuzz;

import com.exmple.fuzz.pojo.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/16 14:53
 */
public class TestLambdaExample {

    List<Employee> employees = Arrays.asList(
            new Employee(101, "Z3", 19, 9999.99),
            new Employee(103, "W5", 35, 6666.66),
            new Employee(104, "Tom", 44, 1111.11),
            new Employee(102, "L4", 20, 7777.77),
            new Employee(105, "Jerry", 60, 4444.44)
    );


    @Test
    public void test01() {
        employees.sort((e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getSalary().compareTo(e2.getSalary());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        employees.forEach(System.out::println);
    }

    //filter：接收 Lambda ，从流中排除某些元素
    //limit：截断流，使其元素不超过给定数量
    //skip(n)：跳过元素，返回一个舍弃了前n个元素的流；若流中元素不足n个，则返回一个空流；与 limit(n) 互补
    //distinct：筛选，通过流所生成的 hashCode() 与 equals() 取除重复元素

    //map：接收 Lambda ，将元素转换为其他形式或提取信息；接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素

    //allMatch：检查是否匹配所有元素
    //anyMatch：检查是否至少匹配一个元素
    //noneMatch：检查是否没有匹配所有元素
    //findFirst：返回第一个元素
    //findAny：返回当前流中的任意元素
    //count：返回流中元素的总个数
    //max：返回流中最大值
    //min：返回流中最小值
    @Test
    public void test02() {
        employees.stream()
                .filter((e) -> e.getAge() > 35)
                .limit(10)
                .map( (employee) -> employee.getName())
                .forEach(System.out::println);
    }


    @Test
    public void test03() {
        employees.stream()
                .sorted()
                .forEach(System.out::println);

        employees.stream()
                .sorted((e1, e2) -> {
                    return Integer.compare(e1.getAge(), e2.getAge());
                })
                .forEach(System.out::println);
    }


    //归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 可以将流中的数据反复结合起来，得到一个值
    @Test
    public void test04() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        int all = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(all);
    }

    //收集：collect 将流转换成其他形式；接收一个 Collector 接口的实现，用于给流中元素做汇总的方法
    @Test
    public void test05() {
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    //**案例一：**给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？(如：给定【1，2，3，4，5】，返回【1，4，9，16，25】)
    @Test
    public void example1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        list.stream()
                .map(x -> x * x)
                .forEach(System.out::println);

    }

    //**案例二：**怎样使用 map 和 reduce 数一数流中有多少个 Employee 呢？
    @Test
    public void example2() {
        System.out.println(employees.stream()
                .map(e -> 1)
                .reduce(0, Integer::sum));
    }

//    @Test
//    public void test03() {
//        List<String> list = Arrays.asList("a", "b", "c");
//
//        list.stream()
//                .map(String::toUpperCase)
//                .forEach(System.out::println);
//    }

}
