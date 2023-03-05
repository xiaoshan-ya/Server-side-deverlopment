package concert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //开启AspectJ的自动代理机制
public class ConcertConfig {
    @Bean
    public Performance concert() {
        return new Concert();
    }
//    @Bean
//    public Performance concert2() {
//        return new Concert();
//    }

    @Bean
    public Audience audience() { //定义Audience的bean,实例化切面，实现面向切面编程
        return new Audience(); // 可以更改切面实例
    }


    @Bean
    public EncoreableIntroducer encoreableIntroducer() {
        return new EncoreableIntroducer();
    }
}