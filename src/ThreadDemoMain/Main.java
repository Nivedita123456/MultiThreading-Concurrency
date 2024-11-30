package ThreadDemoMain;

import ProducerConsumerProblem.SemaphoreHelper;
import ProducerConsumerProblem.SynchronizedHelper;

public class Main {
    public static void main(String[] args) {
        // Both Implementations of Producer-Consumer Problem using wait() notify() and semaphores
        SynchronizedHelper synchronizedHelper = new SynchronizedHelper(5);
        SemaphoreHelper semaphoreHelper = new SemaphoreHelper();
        for(int i=0;i<5;i++){
            new Thread(){
                @Override
                public void run(){
                    try {
                        semaphoreHelper.push(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }
        for(int i=0;i<5;i++){
            new Thread(){
                @Override
                public void run(){
                    try {
                        semaphoreHelper.pop();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }


    }
}
