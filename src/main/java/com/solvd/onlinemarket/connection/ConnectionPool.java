package com.solvd.onlinemarket.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final Queue<Connection> pool;
    private final int maxSize;
    private final Lock lock = new ReentrantLock();
    private final Condition available = lock.newCondition();

    public ConnectionPool(int maxSize) {
        this.maxSize = maxSize;
        this.pool = new LinkedList<>();
        for (int i = 1; i <= maxSize; i++) {
            pool.add(new Connection(i));
        }
    }

    public Connection acquire() throws InterruptedException {
        lock.lock();
        try {
            while (pool.isEmpty()) {
                LOGGER.info("{} is waiting for a connection...", Thread.currentThread().getName());
                available.await();
            }
            Connection connection = pool.poll();
            LOGGER.info("{} acquired a connection", Thread.currentThread().getName());
            return connection;
        } finally {
            lock.unlock();
        }
    }

    public void release(Connection connection) {
        lock.lock();
        try {
            pool.add(connection);
            LOGGER.info("{} released a connection", Thread.currentThread().getName());
            available.signal();
        } finally {
            lock.unlock();
        }
    }
}

