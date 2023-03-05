package concert;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // 指定是一个切面
@Component // 实例化切面
public class Audience {
    // 被切入方法之前执行，只要是来自于Performance接口中名为perform方法都会切入，注意“*”表示多个
    @Before("execution(* concert.Performance.perform( .. ))")
    public void silenceCellPhones() {
        System.out.println("Silencing cell phones");
    }

    @Before("execution(* concert.Performance.perform( .. ))")
    public void takeSeats() {
        System.out.println("Taking seats");
    }

    // 被切入方法return之后
    @AfterReturning("execution(* concert.Performance.perform( .. ))")
    public void applause() {
        System.out.println("CLAP CLAP CLAP!!!");
    }

    // 被切入方法throw之后
    @AfterThrowing("execution(* concert.Performance.perform( .. ))")
    public void demandRefund() {
        System.out.println("Demand a refund");
    }
}