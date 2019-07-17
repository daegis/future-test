package cn.aegisa.thread.future.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Using IntelliJ IDEA.
 *
 * @author XIANYINGDA at 2019/7/17 22:36
 */
@Target(ElementType.METHOD)
@Retention(
        RetentionPolicy.RUNTIME
)
public @interface Time {
}
