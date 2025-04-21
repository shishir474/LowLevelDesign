package JavaCollections;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

//To define the compare function(for definign comparator) Student class needs to implement Comparator Interface
public class Student implements Comparable<Student> {
    int rollNo;
    String name;

    Student(String name, Integer rollNo){
        this.name = name;
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {                      ///  overrides the toString() of the Object class. In Java, all the classes are subclasses of the Object class
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {                 // overrides the equal() of Object class
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNo == student.rollNo;
    }

    @Override                                            // overrides the equal() of Object class
    public int hashCode() {                             // hashCode is generated only using rollNo. Hence duplicate rollNo wont be allowed
        return Objects.hashCode(rollNo);
    }

    @Override
    public int compareTo(Student o) {
        return this.rollNo - o.rollNo;
    }
}

