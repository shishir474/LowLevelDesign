package LRUCache;

import java.util.HashMap;

public class LRUCache {
    private final int capacity;
    private final Node head;
    private final Node tail;
    private HashMap<Integer, Node> cache;


    public LRUCache(int capacity){
        this.capacity = capacity;
        cache = new HashMap<Integer, Node>(capacity);
        head = new Node(null,null);
        tail = new Node(null,null);
        head.next = tail;
        tail.prev = head;
    }

    public synchronized String getValue(Integer key){
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        else return null;
    }

    private void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

//    adds node at the start of the DLL
    private void addNode(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public synchronized void put(Integer key, String value){
        Node node = cache.get(key);
        if(node == null){
            // create new node, insert into cache map, add node to head of the DLL, & if the cache's size > capacity, remnove the tail.prev node
            node = new Node(key,value);
            cache.put(key,node);
            addNode(node);
            if(cache.size() > capacity){        // first remove the entry from the cache map & then remove node from the DLL
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
            }
        }
        else{   // node already exists in cache, update its value and moveToHead()
            node.value = value;
            moveToHead(node);
        }
    }

}
