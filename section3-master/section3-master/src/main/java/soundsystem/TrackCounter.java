package soundsystem;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class TrackCounter {
    private Map<Integer, Integer> trackCounts = new HashMap<>();

    @Pointcut(
            "execution(* soundsystem.CompactDisc.playTrack( int )) " +
                    "&& args(trackNumber)")// args:目标方法参数的获取
    public void trackPlayed(int trackNumber) {
    }

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber) { // 这里的参数和切面目标方法的参数一致
        int currentCount = getPlayCount(trackNumber);
        trackCounts.put(trackNumber, currentCount + 1);// 歌曲编号，播放次数
    }

    public int getPlayCount(int trackNumber) { // 得到播放次数
        return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
    }
}