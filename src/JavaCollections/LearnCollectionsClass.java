package JavaCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LearnCollectionsClass {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(10);
        list.add(4);
        list.add(5);
        list.add(34);
        list.add(12);

        System.out.println(Collections.min(list));       // min eleemnt
        System.out.println(Collections.max(list));      // max eleement
        System.out.println(Collections.frequency(list,12));         // freq of a particular key
        Collections.sort(list);             // sort in asc order
        System.out.println(list);

        Collections.sort(list, Comparator.reverseOrder());          // sort in desceneing order
        System.out.println(list);

        List<Student> students = new ArrayList<>();
        students.add(new Student("Akash", 2));
        students.add(new Student("Aman", 3));
        students.add(new Student("Bharat", 5));
        students.add(new Student("Sharath", 4));

//        Sort a custom list using a custom comparator (lambda expressions)
        Collections.sort(students, (s1,s2) -> s1.rollNo - s2.rollNo);
        System.out.println(students);

//        Sort on basis of name using custom comparator (original way)
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        System.out.println(students);

//        sorts the students based on the comparator defined in Student class
//        sorts in increasing order of roll no
        Collections.sort(students);

        System.out.println(students);


    }
}
