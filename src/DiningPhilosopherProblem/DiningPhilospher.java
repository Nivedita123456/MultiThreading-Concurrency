package DiningPhilosopherProblem;
//using synchronized
class DiningPhilosphers {
    public Integer[] forks ;
    public DiningPhilosphers() {
        forks = new Integer[5];
        for(int i=0;i<5;i++){
            forks[i] = new Integer(0);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int i = philosopher;
        synchronized(forks[i%5]){
            synchronized(forks[(i+1)%5]){
//                System.out.println(Thread.holdsLock(forks));
//                System.out.println(Thread.holdsLock(forks[(i+1)%5]));
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }
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
        DiningPhilosphers diningPhilosphers = new DiningPhilosphers();
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