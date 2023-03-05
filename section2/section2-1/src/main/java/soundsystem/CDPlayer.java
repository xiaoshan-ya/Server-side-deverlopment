package soundsystem;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("myComponent") // 通过注解自动配置，Spring配置的“自动化配置”方法,value参数设置组件名称
public class CDPlayer implements MediaPlayer, BeanNameAware {
    private CompactDisc cd; // cd的类是一个接口，可以实现随时换CD唱片

    @Autowired // Spring配置的“自动化配置”方法，去寻找实现了当前接口的组件，让Spring进行注入
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }

    @Override
    // 给组件重命名, name参数为component的value值
    public void setBeanName(String name) {
        System.out.println("=v= " + name);
    }
}
