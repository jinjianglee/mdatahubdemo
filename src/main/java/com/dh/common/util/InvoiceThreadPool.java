package com.dh.common.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public enum InvoiceThreadPool {

    /**
     * @Description: 线程池枚举对象
     * @author: NevernowHttpTimerTask
     * @Date: 2019/5/16 15:35
     */
    THREAD_POOL;

    /**
     * @Description: 核心数量
     * @Date: 2019/4/25 17:06
     */
    public final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
    /**
     * http线程等待时间 3秒
     * http线程执行时间 1秒
     * ((3 + 1) / 1) * CORE_SIZE;
     */
    public final int HTTP_MAX_CORE_SIZE = 4 * CORE_SIZE;
    public final int MAX_CORE_POOL_SIZE = CORE_SIZE < 2 ? 2 : CORE_SIZE * 2 + 1;
    private final CustomizedFactory FACTORY = new CustomizedFactory("Ews");

    /**
     * @Description: 其他任务自定义线程线程池
     */
    private ExecutorService taskPool = new ThreadPoolExecutor(CORE_SIZE + 1, MAX_CORE_POOL_SIZE,
            0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100),
            FACTORY,
            (r, e) -> {
                throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + e.toString());
            });

    public ExecutorService getTaskPool() {
        return taskPool;
    }

    static final class CustomizedFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomizedFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name + "-" +
                    POOL_NUMBER.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }


}
