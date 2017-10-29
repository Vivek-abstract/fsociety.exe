public class ProcessSync {

    public static void main(String args[]) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[5];

        for(int i = 0; i < 5; i++) {
            chopsticks[i] = new Object();
        }

        for(int i = 0; i < 5; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[ (i+1) % 5 ];

            philosophers[i] = new Philosopher(leftChopstick, rightChopstick);

            Thread t = new Thread(philosophers[i], "Philospher " + (i+1));
            t.start();
        }
    }
}

class Philosopher implements Runnable {
    private Object leftChopstick;
    private Object rightChopstick;

    public Philosopher(Object leftChopstick, Object rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    //Exception occurs if process is sleeping and some action is performed on it
    public void action(String s) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + s);
        //Now we put it to sleep so that preference is not given to first comer
        Thread.sleep((int) Math.random()*100);
    }

    @Override
    public void run() {
        try {
            do {
                action(System.nanoTime() + " Thinking");
                String prevent_deadlock = Thread.currentThread().getName();
                int c = Integer.parseInt(prevent_deadlock.substring(prevent_deadlock.length() - 1));
                if(c % 2 != 0) {
                    //Odd so pick left and then right
                    //wait(leftChopstick)
                    synchronized(leftChopstick) {
                        action(System.nanoTime() + " Picked up left Chopstick");
                        //wait(rightChopstick)
                        synchronized(rightChopstick) {
                            action(System.nanoTime() + " Picked up right Chopstick - Started Eating");
                            //Eating
                            action(System.nanoTime() + " Put down right Chopstick");
                        }
                        action(System.nanoTime() + " Put down left Chopstick - Thinking again");
                    }
                } else {
                    //Even so pick right then left
                    synchronized(rightChopstick) {
                        action(System.nanoTime() + " Picked up right Chopstick");
                        //wait(rightChopstick)
                        synchronized(leftChopstick) {
                            action(System.nanoTime() + " Picked up left Chopstick - Started Eating");
                            //Eating
                            action(System.nanoTime() + " Put down left Chopstick");
                        }
                        action(System.nanoTime() + " Put down right Chopstick - Thinking again");
                    }
                }
            } while(true);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}