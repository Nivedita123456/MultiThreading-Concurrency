package DiningPhilosopherProblem;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class SemaphoreDining {
    Semaphore[] semaphores;
    SemaphoreDining(){
        semaphores = new Semaphore[5];
        for(int i=0;i<5;i++){
            semaphores[i] = new Semaphore(1);
        }
    }

    public void wantsToEat(int i,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int left = i%5;
        int right = (i+1)%5;
        if(i%2!=0){
            //swap
              int temp = left;
              left = right;
              right = temp;
        }
        semaphores[left].acquire();
        semaphores[right].acquire();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        semaphores[left].release();
        semaphores[right].release();

    }

    public static void main(String[] args) {
        Runnable pickLeftFork = new Runnable() {
            @Override
            public void run() {
                System.out.println("Pick Left Fork"+ Thread.currentThread().getName());
            }
        };
        Runnable pickRightFork = new Runnable() {
            @Override
            public void run() {
                System.out.println("Pick Right Fork" + Thread.currentThread().getName());
            }
        };
        Runnable putLeftFork = new Runnable() {
            @Override
            public void run() {
                System.out.println("Put Left Fork"+ Thread.currentThread().getName());
            }
        };
        Runnable putRightFork = new Runnable() {
            @Override
            public void run() {
                System.out.println("Put Right Fork"+ Thread.currentThread().getName());
            }
        };
        Runnable eat = new Runnable() {
            @Override
            public void run() {
                System.out.println("Eating"+ Thread.currentThread().getName());
            }
        };
        SemaphoreDining diningPhilosphers = new SemaphoreDining();
        for(int i=0;i<5;i++){
            int philospher = i;
            Thread thread = new Thread(){
                @Override
                public void run(){
                    try {
                        diningPhilosphers.wantsToEat(philospher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            thread.setName("Thread-"+i);
            thread.start();
        }

    }
}
