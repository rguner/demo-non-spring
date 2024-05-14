package com.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class MpsHandlerUsage {

    public static void main(String[] args) {
        MpsHandlerUsage mpsHandlerUsage = new MpsHandlerUsage();
        mpsHandlerUsage.callMpsHandler();
    }

    private void callMpsHandler() {
        MpsHandler handler = new MpsHandler();
        int numberOfThreads = 10; // Number of threads to create
        int mps = 50; // Example MPS value
        String supplier = "SupplierA"; // Example supplier name

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // Create and submit tasks to the executor service
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 10; j++) { // Each thread calls the method 10 times
                    handler.doMpsThrottle(mps, supplier);
                }
            });
        }

        // Shutdown the executor service
        executorService.shutdown();
    }
}