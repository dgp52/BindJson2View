package com.dgp52.bindjson2viewlib.wrappers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockWrapper {
    private static Lock lock;
    private static Condition downloadCondition;

    public static Lock getLock() {
        if(lock==null)
            lock = new ReentrantLock();
        return lock;
    }

    public static Condition getDownloadCondition() {
        if(downloadCondition==null && lock!=null)
            downloadCondition = lock.newCondition();
        return downloadCondition;
    }
}
