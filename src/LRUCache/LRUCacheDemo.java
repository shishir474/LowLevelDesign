package LRUCache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,"Value 1");
        lruCache.put(2,"Value 2");
        lruCache.put(3,"Value 3");

        System.out.println(lruCache.getValue(1));
        System.out.println(lruCache.getValue(2));

        lruCache.put(4, "Value 4");

        System.out.println(lruCache.getValue(3));
        System.out.println(lruCache.getValue(4));

        lruCache.put(2, "Updated 2");

        System.out.println(lruCache.getValue(1));
        System.out.println(lruCache.getValue(2));


    }
}

// 2 4 1