/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doublecounter;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    public static void main(String args[]) throws InterruptedException {
        long dvalue1[] = {0};
        long dvalue2[] = {1};
        long dinc = 1000000; // must be big enough !
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
// why does dc3 dead lock ?
        DoubleCounter dc3 = new DoubleCounter(dinc, dvalue2, lock1, dvalue1,
                lock1);
// why do dc1 and dc2 dead lock when run together , how can that be fixed?
        DoubleCounter dc1 = new DoubleCounter(dinc, dvalue1, lock1, dvalue2,
                lock2);
        DoubleCounter dc2 = new DoubleCounter(dinc, dvalue2, lock2, dvalue1,
                lock1);
        dc1.start();
        dc2.start();
        dc3.start();
        dc1.join();
        dc2.join();// will probably never return
        dc3.join();// will never return
    }
}
