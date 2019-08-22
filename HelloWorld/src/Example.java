import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author fengyuhui
 * @create 2019-08-16 11:32
 */
public class Example {
    public static void testExcuters() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        service.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("ScheduledExecutorService:测试开始");

            }
        }, 5, 3, TimeUnit.SECONDS);
    }
}
