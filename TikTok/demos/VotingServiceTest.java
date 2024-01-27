package demos;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class VotingServiceTest {

    @Autowired
    private VotingService votingService;

    @Test
    public void testConcurrentVoting() {
        Long itemId = 1L; // 假设有一个投票项目的ID为1
        int numberOfThreads = 100; // 并发执行的线程数
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                votingService.castVote(itemId);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();
        // 这里可以添加断言来检查投票计数是否正确
    }
}
