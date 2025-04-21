package JavaCollections;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LearnPriorityQueue {
    public static void main(String[] args){
        Queue<Integer> pq = new PriorityQueue<>();              // by default min heap
        pq.offer(200);
        pq.offer(100);
        pq.offer(300);
        pq.add(50);
        System.out.println(pq);
        System.out.println(pq.peek());
        System.out.println(pq.poll());
        System.out.println(pq.element());
        System.out.println(pq.size());


//        max heap
        Queue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());            // passesd reverseOrder comparator to convert the min heap to max heap
        pq1.offer(200);
        pq1.offer(100);
        pq1.offer(300);
        pq1.add(50);
        System.out.println(pq1);
        System.out.println(pq1.peek());
        System.out.println(pq1.poll());
        System.out.println(pq1.element());
        System.out.println(pq1.size());
    }
}
