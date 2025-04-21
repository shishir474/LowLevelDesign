package JavaCollections;

import java.util.LinkedList;
import java.util.Queue;

public class LearnLinkedListQueue {
    public static void main(String[] args){
        Queue<Integer> q = new LinkedList<>();          // Queue interface implemented by LinkedList Class
        q.offer(12);                        // add element, returns true if add is successfull otherwise returns false
        q.offer(24);
        q.offer(36);
        q.add(48);                              // add's element, if add operation is successfull, returns true otherwise it throws an exception

        System.out.println(q);

        System.out.println(q.poll());           // remove front element, returns null if the queue is empty

        System.out.println(q.remove());         // removes front element, throws an exception if the queue is empty

        System.out.println(q);

        System.out.println(q.peek());           /// front element, returns null if queue is empty


        System.out.println(q.element());        // front element,  but throws an exception if queue is empty






    }
}
