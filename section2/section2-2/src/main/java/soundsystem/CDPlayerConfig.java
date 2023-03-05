package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDPlayerConfig {

    @Bean
    public CompactDisc compactDisc() {
        return new SgtPeppers();
    }

    @Bean // 告诉Spring如何去组装组件
    public CDPlayer cdPlayer(CompactDisc cd) {
        return new CDPlayer(cd);
    }

}
