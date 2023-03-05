package concert;

import org.aspectj.lang.annotation.*;

@Aspect
public class Audience1 {
    @Pointcut("execution(* concert.Performance.perform( .. ))") //切点，切面表达式
    public void performance() {// 使用Opencut依附于performance方法，实现切面表达式去重
    }

    @Before("performance()")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    @Before("performance()")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demand a refund");
    }
}