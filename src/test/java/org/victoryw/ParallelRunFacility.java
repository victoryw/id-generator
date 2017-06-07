package org.victoryw;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelRunFacility {
    public static void ParallelTestContainerMethod(Runnable runnable, int maxCount) {
        ExecutorService es = Executors.newFixedThreadPool(100);

        for (int i = 0; i< maxCount; i++){
            es.submit(runnable);
        }
        es.shutdown();
        while(true){
            if(es.isTerminated()){
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
