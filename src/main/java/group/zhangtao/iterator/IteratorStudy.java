package group.zhangtao.iterator;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IteratorStudy {
    /**
     * 测试驱动
     * @param function 测试用的function
     */
    public void testFun(Function<List<String>, List<String>> function) {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(UUID.randomUUID().toString());
        }
        List<String> list1 = function.apply(list);

        Assert.assertEquals(0, list1.size());
    }

    @Test
    public void testLambda() {
        testFun((list) -> list.stream()
                .filter(i -> false)
                .collect(Collectors.toList()));
    }


    @Test
    public void testIterator() {
        testFun((list) -> {
            //iterator true
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
            return list;
        });
    }

    @Test
    public void testFor0(){
        testFun((list)->{
            //for wrong
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.get(i));
            }
            return list;
        });
    }
    @Test
    public void testFor1(){
        testFun((list)->{
            //for wrong
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
            }
            return list;
        });
    }
    @Test
    public void testFor2(){
        testFun((list)->{
            //for wrong
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.remove(i);
            }
            return list;
        });
    }

    @Test
    public void testForI(){
        testFun((list)->{
            //fori wrong
            for (String s : list) {
                list.remove(s);
            }
            return list;
        });
    }
    @Test
    public void testForEach(){
        testFun((list)->{
                    //foreach  wrong
            list.forEach(i -> list.remove(i));
            return list;
        });
    }
}
