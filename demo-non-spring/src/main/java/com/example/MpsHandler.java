package com.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MpsHandler {

    private Object lock = new Object();
    private int mpsCount;
    private long mpsLatestTime;

    public void doMpsThrottle(int mps, String supplier) {
        synchronized (lock) {
            if (mps == 0) {
                return;
            }
            long now = System.currentTimeMillis();
            int mpsWindow = 100;

            if (this.mpsLatestTime > (now - mpsWindow) && this.mpsLatestTime <= now) {
                if (this.mpsCount >= mps / 10) {
                    try {
                        long sleepTime = this.mpsLatestTime + mpsWindow - now;
                        Thread.sleep(sleepTime);
                        log.debug("Sleeping, duration : {}", sleepTime);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    this.mpsLatestTime = System.currentTimeMillis();
                    this.mpsCount = 1;
                } else {
                    this.mpsCount++;
                }
            } else {
                this.mpsLatestTime = now;
                this.mpsCount = 1;
            }
            log.debug("Current MPS for {} : {}", supplier, mpsCount);
        }
    }
}