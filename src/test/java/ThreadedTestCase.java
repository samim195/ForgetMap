import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadedTestCase {
    private ForgetMap forgetMap;

    @Test
    public void testForgetMapperWithConcurrency() throws InterruptedException {
        // We try to access the same instance of ForgetMap With Multiple Threads
        // To see its behavior. We add to the forgetMap across 10 threads
        // without any issues
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        forgetMap = new ForgetMap(3);
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                forgetMap.add("DV21 AMG", "E63 AMG");
                forgetMap.add("AT19 OPY", "4 Series BMW");
                forgetMap.add("SK17 MN1", "A3 Audi");

                forgetMap.find("DV21 AMG");
                forgetMap.find("AT19 OPY");
                forgetMap.find("AT19 OPY");
                forgetMap.add("MT71 TYS", "Model 3 Tesla");

                latch.countDown();
            });
        }
        latch.await();

        assertTrue(forgetMap.mapContainsValue("MT71 TYS"));
        assertFalse(forgetMap.mapContainsValue("SK17 MN1"));
    }
}
