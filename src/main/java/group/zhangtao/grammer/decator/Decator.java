package group.zhangtao.grammer.decator;

import java.util.Arrays;

@ClassDecator
public class Decator {
    @MyDecator(getName = "me", count = 0)
    private void sayHello() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Arrays.stream(Decator.class.getAnnotations()).forEach((n)->{
            if(n instanceof ClassDecator){
                System.out.println(((ClassDecator) n).name());
            }else {
                System.out.println("not known");
            }
        });
        Decator decator = new Decator();
        ClassDecator classDecator =  decator.getClass().getAnnotation(ClassDecator.class);
        if (classDecator!=null){
            System.out.println(classDecator.name());
        }
    }
}

