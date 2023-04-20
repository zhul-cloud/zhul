package com.zhul.cloud.lock.redis;

import com.zhul.cloud.common.api.LockProvider;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * Created by yanglikai on 2020/12/31.
 */
public class RedissonLockClient implements LockProvider {

    private RedissonClient redissonClient;

    public RedissonLockClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void lock(String name) {
        RLock lock = redissonClient.getLock(name);
        lock.lock();
    }

    @Override
    public void lockInterruptibly(String name) throws InterruptedException {
        RLock lock = redissonClient.getLock(name);
        lock.lockInterruptibly();
    }

    @Override
    public boolean tryLock(String name) {
        RLock lock = redissonClient.getLock(name);

        return lock.tryLock();
    }

    @Override
    public boolean tryLock(String name, long time, TimeUnit unit) throws InterruptedException {
        RLock lock = redissonClient.getLock(name);

        return lock.tryLock(time, -1, unit);
    }

    @Override
    public void unlock(String name) {
        RLock lock = redissonClient.getLock(name);
        if (lock.isLocked()) {
            lock.unlock();
        }
    }
}
