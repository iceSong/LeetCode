import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 题目：多线程 计算 1 + 2 + 3 + ..... + 100
 *
 * @author Cyrus
 * @version v1.0, 2018/3/16 下午7:25
 */
public class OneAddTo1000 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        sampleThread();
        threadPool();
        future();
        parallel();
        System.exit(0);
    }

    /**
     * 将数据拆分为10组，每组100个, 使用普通线程
     */
    public static void sampleThread() throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger sum = new AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int count = i;
            Thread thread = new Thread(() -> {
                for (int j = count * 100 + 1; j <= count * 100 + 100; j++) {
                    sum.addAndGet(j);
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(sum.get() + " " + (System.currentTimeMillis() - start));
    }

    /**
     * 使用线程池和CountDownLatch
     */
    public static void threadPool() throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger sum = new AtomicInteger();
        ExecutorService pool = new ThreadPoolExecutor(2, 10, 2, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int cur = i;
            pool.execute(() -> {
                for (int j = cur * 100 + 1; j <= cur * 100 + 100; j++) {
                    sum.addAndGet(j);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(sum.get() + " " + (System.currentTimeMillis() - start));
    }


    public static void future() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService pool = new ThreadPoolExecutor(2, 10, 1, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int cur = i;
            Future<Integer> f = pool.submit(() -> {
                int tmp = 0;
                for (int j = cur * 100 + 1; j <= cur * 100 + 100; j++) {
                    tmp += j;
                }
                return tmp;
            });
            futures.add(f);
        }

        int sum = 0;
        for (Future<Integer> f : futures) {
            sum += f.get();
        }
        System.out.println(sum + " " + (System.currentTimeMillis() - start));
    }

    public static void parallel() {
        long start = System.currentTimeMillis();
        int sum = IntStream.range(0, 10).boxed().parallel().map(i -> IntStream.rangeClosed(i * 100 + 1, i * 100 + 100).sum()).mapToInt(i -> i).sum();
        System.out.println(sum + " " + (System.currentTimeMillis() - start));
    }

}
