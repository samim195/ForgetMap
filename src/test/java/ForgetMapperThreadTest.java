import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class ForgetMapperThreadTest {
    private ForgetMap forgetMap;

    @Test
    public void whenMultipleThreadsRunAgainstSameMapResourceNoConflictsShouldOccur() {
        ForgetMap forgetMap = new ForgetMap(3);
        ForgetMapperThread forgetMapperThread1 = new ForgetMapperThread(forgetMap);
        forgetMapperThread1.start();
        ForgetMapperThread forgetMapperThread2 = new ForgetMapperThread(forgetMap);
        forgetMapperThread2.start();;
        ForgetMapperThread forgetMapperThread3 = new ForgetMapperThread(forgetMap);
        forgetMapperThread3.start();;

        assertTrue(forgetMap.max > 0);

//        Updated my solutation for threaded testing as I found a better solution:
//        https://www.baeldung.com/java-start-thread
    }
}
