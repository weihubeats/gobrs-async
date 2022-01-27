package seq;


import io.github.memorydoc.executor.Async;
import io.github.memorydoc.executor.timer.SystemClock;
import io.github.memorydoc.wrapper.TaskWrapper;

import java.util.concurrent.ExecutionException;

/**
 * 串行测试
 * @author sizegang wrote on 2019-11-20.
 */
public class TestSequential {
    public static void main(String[] args) throws InterruptedException, ExecutionException {


        SeqTask w = new SeqTask();
        SeqTask1 w1 = new SeqTask1();
        SeqTask2 w2 = new SeqTask2();

        //顺序0-1-2
        TaskWrapper<String, String> workerWrapper2 =  new TaskWrapper.Builder<String, String>()
                .worker(w2)
                .callback(w2)
                .param("2")
                .build();

        TaskWrapper<String, String> workerWrapper1 =  new TaskWrapper.Builder<String, String>()
                .worker(w1)
                .callback(w1)
                .param("1")
                .next(workerWrapper2)
                .build();

        TaskWrapper<String, String> workerWrapper =  new TaskWrapper.Builder<String, String>()
                .worker(w)
                .callback(w)
                .param("0")
                .next(workerWrapper1)
                .build();

//        testNormal(workerWrapper);

        testGroupTimeout(workerWrapper);
    }

    private static void testNormal(TaskWrapper<String, String> workerWrapper) throws ExecutionException, InterruptedException {
        long now = SystemClock.now();
        System.out.println("begin-" + now);

        Async.startTaskFlow(3500, workerWrapper);

        System.out.println("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }

    private static void testGroupTimeout(TaskWrapper<String, String> workerWrapper) throws ExecutionException, InterruptedException {
        long now = SystemClock.now();
        System.out.println("begin-" + now);

        Async.startTaskFlow(2500, workerWrapper);

        System.out.println("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }
}
