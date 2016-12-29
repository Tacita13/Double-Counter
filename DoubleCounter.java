/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doublecounter;

import java.util.concurrent.locks.ReentrantLock;

class DoubleCounter extends Thread {

    private long amount; // how many times shall we count
    private long target1[]; // used as reference on value
    private ReentrantLock lock1;
    private long target2[]; // used as reference on another value
    private ReentrantLock lock2;

    DoubleCounter(long amount, long target1[], ReentrantLock lock1, long target2[], ReentrantLock lock2) {
        this.amount = amount;
        this.target1 = target1;
        this.lock1 = lock1;
        this.target2 = target2;
        this.lock2 = lock2;
    }

    public void run() {
        for (long c = 0; c < amount; c++) {
            lock1.lock(); // want to access two shared resources , need both locks 
            lock2 . lock();
            target1[0] += target2[0];
            lock2.unlock();
            lock1.unlock();
        }
    }


}