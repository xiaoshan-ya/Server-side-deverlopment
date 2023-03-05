package soundsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration // 配置类
@Import(CDPlayerConfig.class) // 引入另一个类之类配置组件
@ImportResource("classpath:cd-config.xml") // 通过XML方式将文件组装起来，配置
// 共同组成Spring上下文
public class SoundSystemConfig {

}
