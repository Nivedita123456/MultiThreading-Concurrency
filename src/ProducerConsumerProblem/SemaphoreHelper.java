package ProducerConsumerProblem;

import java.util.Stack;
import java.util.concurrent.Semaphore;

public class SemaphoreHelper {
    int top;
    int[] arr;
    Semaphore semaphoreProducer = new Semaphore(1);
    Semaphore semaphoreConsumer= new Semaphore(0);
    public SemaphoreHelper(){
        arr = new int[10];
        top = -1;
    }
    public void push(int ele) throws InterruptedException {
        //check acquire() and wait() difference
        semaphoreProducer.acquire();
        System.out.println("Adding Element");
        top++;
        arr[top] = ele;
        semaphoreConsumer.release();
    }

    public int pop() throws InterruptedException {
        semaphoreConsumer.acquire();
        System.out.println("Popping Element");
        int ele = arr[top] ;
        top--;
        semaphoreProducer.release();
        return ele;
    }

}
