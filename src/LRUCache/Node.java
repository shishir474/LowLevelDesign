package LRUCache;

public class Node {
    Integer key;
    String value;
    Node prev;
    Node next;

    public Node(Integer key, String value){
        this.key = key;
        this.value = value;
        prev = null;
        next = null;
    }
}

