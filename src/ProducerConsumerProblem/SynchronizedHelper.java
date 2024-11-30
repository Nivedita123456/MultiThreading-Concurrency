package ProducerConsumerProblem;

import java.util.Stack;

public class SynchronizedHelper {
    int top;
    int size;
    int[] arr;
    public SynchronizedHelper(int size){
        arr = new int[size];
        this.size = size;
        top = -1;
    }
    public void push(int ele) throws InterruptedException {
        synchronized (this){
            if(top == size-1){
                wait();
            }
            top++;
            arr[top] = ele;
            System.out.println("Added Element Stack Size" + top);
            notifyAll();
        }
    }

    public int pop() throws InterruptedException {
        synchronized (this){
            if(top==-1){
                wait();
            }
            int ele = arr[top];
            top--;
            System.out.println("Popped Element Stack Size" + top);
            notifyAll();
            return ele;
        }
    }

}
