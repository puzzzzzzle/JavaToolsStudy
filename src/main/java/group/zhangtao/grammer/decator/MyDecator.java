package group.zhangtao.grammer.decator;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyDecator {
    String getName() default "tao";
    int count();
}
