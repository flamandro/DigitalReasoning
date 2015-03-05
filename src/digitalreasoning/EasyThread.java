/*
 * Property of by John M. Lien
 */
package digitalreasoning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John M. Lien
 * Property of by John M. Lien
 */
public final class EasyThread {

    private final ConcurrentLinkedQueue<Runnable> fifo;
    private final PriorityQueue<Integer> availableThreads;


    private int requestThread() {
        if (availableThreads.peek() == null) {
            return -1;
        } else {
            return availableThreads.poll();
        }
    }

    private boolean threadsAreAlive(Thread[] threads) {
        int thread_useage = 0;
        for (Thread thread : threads) {
            if (thread != null) {
                if (thread.isAlive()) {
                    thread_useage++;
                } else {
                    availableThreads.add(Integer.valueOf(thread.getName()));
                }
            }
        }
        return thread_useage == threads.length;
    }

    public void runTasks() {
        int thread_id;
        Thread[] threads = new Thread[availableThreads.size()];
        while (fifo.peek() != null) {
            if ((thread_id = this.requestThread()) != -1) {
                threads[thread_id] = new Thread(fifo.poll(), String.valueOf(thread_id));
                threads[thread_id].start();
            } else {
                while (threadsAreAlive(threads)) {
                    Thread.yield();
                }
            }
        }
        for (Thread thread : threads) {
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(EasyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        threadsAreAlive(threads);
    }

    public void addRunnableTask(Runnable task) {
        fifo.add(task);
    }

    public void addRunnableTasks(Runnable[] tasks) {
        fifo.addAll(Arrays.asList(tasks));
    }

    public void addRunnableTasks(ArrayList<Runnable> tasks) {
        fifo.addAll(tasks);
    }

    public void setNumberOfThreads(int numThreads) {
        availableThreads.clear();
        numThreads = (numThreads <= 0) ? 4 : numThreads;
        for (int i = 0; i < numThreads; i++) {
            availableThreads.add(i);
        }
    }
    
    public void clearRunnableTasks(){
        fifo.clear();
    }
    
    public void cancelRunningThreads(){
        
    }

    public EasyThread() {
        fifo = new ConcurrentLinkedQueue();
        availableThreads = new PriorityQueue();
        this.setNumberOfThreads(1);
    }

    public EasyThread(int numThreads) {
        fifo = new ConcurrentLinkedQueue();
        availableThreads = new PriorityQueue();
        this.setNumberOfThreads(numThreads);
    }

    public EasyThread(Runnable[] input, int numThreads) {
        fifo = new ConcurrentLinkedQueue();
        availableThreads = new PriorityQueue();
        this.addRunnableTasks(input);
        this.setNumberOfThreads(numThreads);
    }

    public EasyThread(ArrayList<Runnable> input, int numThreads) {
        fifo = new ConcurrentLinkedQueue();
        availableThreads = new PriorityQueue();
        this.addRunnableTasks(input);
        this.setNumberOfThreads(numThreads);
    }
}
