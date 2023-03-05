package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import soundsystem.CDPlayerConfig;
import soundsystem.MediaPlayer;

public class MyAnnotationApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(CDPlayerConfig.class); // 创建上下文
        MediaPlayer player = ctx.getBean(MediaPlayer.class); // 从上下文中获得对象
        player.play();
    }
}
