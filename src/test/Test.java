import com.google.common.collect.Lists;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : ly.
 * @Date : 2018/4/26.
 */
public class Test {

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        /*Person person = new Person();
        person.setAgeOne(1);
        person.setAgeTwo(2);

        Class<? extends Person> personClazz = person.getClass();
        //获取Person类的ageOne属性值
        PropertyDescriptor descriptor = new PropertyDescriptor("ageOne", personClazz);
        Method readMethod = descriptor.getReadMethod();
        Object invoke = readMethod.invoke(person);

        People people = new People();
        Class<? extends People> peopleClass = people.getClass();
        //为People类的ageOne属性赋值，值为Person类的ageOne值
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("ageOne", peopleClass);
        Method writeMethod = propertyDescriptor.getWriteMethod();
        writeMethod.invoke(people, invoke);

        System.out.println(people.getAgeOne());

        System.out.println(invoke);*/

        /*************************************************************************************************/

            final ReadWrite rd = new ReadWrite();

            for(int i=0;i<5;i++){
                int count = 0;
                System.out.println(count++);
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        rd.read();
                    }
                }).start();
            }

            new Thread(new Runnable() {

                @Override
                public void run() {
                    rd.write();
                }
            }).start();

    }

    @org.junit.Test
    public void test(){
        int i = 1;
        Integer[] intArr = new Integer[10000];
        for (int j=0; j<10000; j++) {
            intArr[j] = i++;
        }
//        String[] strArr = {"a", "sa", "ab", "dfae", "gaed"};
        List<Integer> asList = Arrays.asList(intArr);

        long count = asList.stream().filter(a -> a > 100 && a % 2 == 0).count();
        System.out.println(count);

        asList.stream().filter(a -> a>2 && a%2==0).skip(3).limit(10).collect(Collectors.toList()).forEach(System.out::println);




        /*Long start3 = System.currentTimeMillis();
        for (Integer str : asList) {
        }
        Long end3  = System.currentTimeMillis();
        System.out.println("========================time=" + (end3-start3));

        Long start = System.currentTimeMillis();
        asList.forEach(a -> a=0);
        Long end  = System.currentTimeMillis();
        System.out.println("========================time=" + (end-start));

        Long start2 = System.currentTimeMillis();
        asList.stream().forEach(a -> a=0);
        Long end2  = System.currentTimeMillis();
        System.out.println("========================time=" + (end2-start2));*/


    }

    @org.junit.Test
    public void tests(){

        List<Person> personList = new ArrayList<>();
        int i=0;
        while (i<10) {
            Person person = new Person();
            person.setAgeOne(i);
            person.setAgeTwo(i*100);
            if (i < 5) {
                person.setName("张三"+i);
            } else {
                person.setName("李四" + i);
            }
            personList.add(person);
            i++;
        }
        personList.parallelStream().map(Person::getAgeOne).collect(Collectors.toList()).forEach(System.out::println);
        personList.parallelStream().collect(Collectors.groupingBy(p -> p.getName().contains("张三"))).forEach((a,b) -> b.forEach(l -> System.out.println("a---b====" + a + "," + l)));


    }

    @org.junit.Test
    public void test2(){
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(4, 5);

        List<int[]> pairs = num1.stream()
                .flatMap(i -> num2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(i -> Arrays.stream(i).forEach(System.out::println));

        List<Integer> integers = Arrays.asList(1, 2, 3, 45, 6, 7);
        Integer reduce = integers.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        Integer reduce2 = integers.stream().reduce(0, (a,b) -> a+b);
        System.out.println(reduce2);

        Integer reduce1 = integers.stream().reduce(0, (a, b) -> {
            if (a > b) return a;
            return b;
        });
        System.out.println(reduce1);

    }

    @org.junit.Test
    public void test3(){

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setAgeOne(i+1);
            personList.add(person);
        }
        int sum = personList.stream().mapToInt(Person::getAgeOne).sum();
        System.out.println(sum);

        IntStream intStream = personList.stream().mapToInt(Person::getAgeOne);
        Stream<Integer> stream = intStream.boxed();
        long count = stream.skip(1).count();
        System.out.println(count);

    }

    @org.junit.Test
    public void test4(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        //range和rangeClosed。这两个方法都是第一个参数接受起始值，第二个参数接受结束值。但 range是不包含结束值的，而rangeClosed则包含结束值。
        IntStream.range(0, 40).filter(a -> a>=10 && a<=40 && a%5==0).boxed().collect(Collectors.toList()).forEach(System.out::println);
        IntStream.rangeClosed(0, 40).filter(a -> a>=10 && a<=40 && a%5==0).boxed().collect(Collectors.toList()).forEach(System.out::println);

    }

    @org.junit.Test
    public void test5(){
        //勾股数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a,100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
                        .map(b -> new int[]{a,b,(int)Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ";" + t[1] +";" + t[2]));
    }

    @org.junit.Test
    public void test6(){
        Stream.of("java", "python").map(String::toUpperCase).forEach(System.out::println);
        Stream.concat(Stream.of("java", "c"), Stream.of("ruby")).forEach(System.out::println);

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int sum = Arrays.stream(numbers).sum();
    }

    @org.junit.Test
    public void test7() throws IOException {
        //文件生成流
        String ret = Files.lines(Paths.get("E:\\workspaces\\dubbo\\ht_info.properties"), Charset.defaultCharset())
                .reduce("", (a, b) -> a + " " + b);
        System.out.println(ret);

    }

    @org.junit.Test
    public void test8(){
    //函数生成流：创建无限流
        //迭代
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        //生成
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

    }

    @org.junit.Test
    public void test9(){

        Trader liu = new Trader("Lau","Beijing");
        Trader lee = new Trader("Lee","Shanghai");
        Trader zhang = new Trader("Zhang","Guangzhou");
        Trader wang = new Trader("Wang","Beijing");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(liu,2016,300),
                new Transaction(lee,2015,100),
                new Transaction(lee,2016,500),
                new Transaction(zhang,2016,9000),
                new Transaction(wang,2017,1000),
                new Transaction(liu,2016,1500)
        );

        //(1) 找出2016年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> collect = transactions.stream().filter(t -> t.getYear() == 2016).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        //(2) 交易员都在哪些不同的城市工作过？
        List<String> collect1 = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        //(3) 查找所有来自于北京的交易员，并按姓名排序。
        List<Trader> collect2 = transactions.stream().map(Transaction::getTrader).filter(t -> "Beijing".equals(t.getCity())).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> collect3 = transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().sorted().collect(Collectors.toList());
        //(5) 有没有交易员是在深圳工作的？
        boolean match = transactions.stream().anyMatch(t -> "Shenzhen".equals(t.getTrader().getCity()));
        //(6) 打印生活在北京的交易员的所有交易额。
        transactions.stream().filter(t -> "Beijing".equals(t.getTrader().getCity())).forEach(t -> System.out.println(t.getValue()));
        Integer reduce = transactions.stream().filter(t -> "Beijing".equals(t.getTrader().getCity())).map(Transaction::getValue).reduce(0, Integer::sum);
        //(7) 所有交易中，最高的交易额是多少？
        Integer reduce1 = transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
        //(8) 找到交易额最小的交易。
        Transaction transaction = transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst().get();
        System.out.println(transaction);
        Transaction transaction1 = transactions.stream().sorted(Comparator.comparing(Transaction::getValue).reversed()).findFirst().get();
        System.out.println(transaction1);

        transactions.stream().sorted((a,b) -> {
            if (a.getValue() == b.getValue()) {
                return a.getYear()-b.getYear();
            } else {
                return a.getValue()-b.getValue();
            }
        });

    }

}
