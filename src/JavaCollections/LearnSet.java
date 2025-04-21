package JavaCollections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class LearnSet {
    public static void main(String[] args) {
//        All operations are O(1) in Hashset
//        All operations are O(logN) in TreeSet     (bcoz elements are stored in sorted form)


//        Set<Integer> set = new HashSet<>();       // random order of elements
//        set.add(10);
//        set.add(10);
//        set.add(20);
//        set.add(20);
//        set.add(30);
//        System.out.println(set);
//
//        System.out.println(set.size());
//
//        System.out.println(set.isEmpty());
//
//        for(Integer i: set) System.out.println(i);
//
//        System.out.println(set.contains(19));
//
//        System.out.println(set.remove(100));
//
//        set.clear();


//        Set<Integer> set = new LinkedHashSet<>();           // elemets order is maintained - insertion order is followed
//
//        set.add(20);
//        set.add(20);
//        set.add(10);
//        set.add(10);
//        set.add(30);
//        System.out.println(set);
//
//        System.out.println(set.size());
//
//        System.out.println(set.isEmpty());
//
//        for(Integer i: set) System.out.println(i);
//
//        System.out.println(set.contains(19));
//
//        System.out.println(set.remove(100));
//
//        set.clear();


//        Set<Integer> set = new TreeSet<>();           // elemets are unique + stored in sorted fashion
//        set.add(20);
//        set.add(20);
//        set.add(10);
//        set.add(10);
//        set.add(30);
//        System.out.println(set);
//
//        System.out.println(set.size());
//
//        System.out.println(set.isEmpty());
//
//        for(Integer i: set) System.out.println(i);
//
//        System.out.println(set.contains(19));
//
//        System.out.println(set.remove(100));
//
//        set.clear();



//        Custom Set of Student Type
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(new Student("Aditya",1));
        studentSet.add(new Student("Shishir", 2));
        studentSet.add(new Student("Rahul", 3));
        studentSet.add(new Student("Aman", 2));       // this rollNo already exists in set
        studentSet.add(new Student("Aditya", 1));     // this object(name,rollNo) already exists in set

        //     To handle only uniqueness of elements on basis of rollNo, implement hashCode() and isEqual() function in Student class.
        System.out.println(studentSet);             // this actully prints each Student object's address, implement toString() of Student class to see the actual object's values

        Student s1 = new Student("Raj", 2);
        Student s2 = new Student("Rakesh", 3);
        System.out.println(s1.equals(s2));          // check if s1 and s2 objects are equal. The logic for comparing is based on rollNo and equal() is overriden in Student class

        System.out.println(s1.getClass());

        System.out.println(s1.hashCode());




    }
}
