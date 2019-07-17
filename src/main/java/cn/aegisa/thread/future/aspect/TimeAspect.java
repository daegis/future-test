package cn.aegisa.thread.future.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Using IntelliJ IDEA.
 *
 * @author XIANYINGDA at 2019/7/17 22:37
 */
@Aspect
@Slf4j
@Component
public class TimeAspect {

    @Around("@annotation(Time)")
    public Object doAspect(ProceedingJoinPoint pjp) throws Throwable {
        long l2 = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long l3 = System.currentTimeMillis();
        long l = l3 - l2;
        log.info("method time:{}ms", l);
        return proceed;
    }
}
