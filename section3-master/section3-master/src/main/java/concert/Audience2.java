package concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience2 {
    @Pointcut("execution(* concert.Performance.perform( .. )) ") // 切点
    public void performance() {
    }

    @Around("performance()") // 既可以before也可以after,实现环绕切面
    public void watchPerformance(ProceedingJoinPoint joinPoint) { // ProceedingJoinPoint:连接点，当前方法
        try {
            // before
            System.out.println(".Silencing cell phones");
            System.out.println(".Taking seats");

            // 执行当前方法
            joinPoint.proceed();

            // after
            System.out.println(".CLAP CLAP CLAP!!!");
        } catch (Throwable e) {

            // afterThrow
            System.out.println(".Demanding a refund");
        }
    }
}