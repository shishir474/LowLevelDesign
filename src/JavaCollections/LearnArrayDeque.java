package JavaCollections;

import java.util.ArrayDeque;

public class LearnArrayDeque {
    public static void main(String[] args){
//    Can insert/remove/peek elements at both ends of the queue- front and back
        ArrayDeque<Integer> adq = new ArrayDeque<>();           // ArrayDeque implements queue interface(so it will have all the methods that queue interface has)
        adq.offer(10);              // offer() -> normal insert from back just like queue -- provided by queue interface
        adq.offer(20);
        adq.offerFirst(30);         // offerFirst() -> inserts element at front -- provided by ArrayDeque
        adq.offerLast(40);         // offerLast() -> inserts element at back, just like offer() -- provided by ArrayDeque

        System.out.println(adq);

        System.out.println(adq.peek());             // gives first element
        System.out.println(adq.peekFirst());        // gives first element
        System.out.println(adq.peekLast());         // gives last element

        System.out.println(adq.poll());
        System.out.println(adq.pollFirst());
        System.out.println(adq.pollLast());

        System.out.println(adq);


    }
}
