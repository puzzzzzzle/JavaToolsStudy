package group.zhangtao.grammer.decator;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassDecator {
    String name() default "nothing";
}
