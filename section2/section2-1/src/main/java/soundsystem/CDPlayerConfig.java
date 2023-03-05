package soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 做配置用的注解，将类做相应的配置
@ComponentScan // 在当前注解的类所在的包（以及子包）里自动搜索类的实例和对象
public class CDPlayerConfig {
}
