package JavaCollections;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LearnArrayList {
    public static void main(String[] args){
        ArrayList<String> studentNames = new ArrayList<>();         // ArrayList class's object
        studentNames.add("Shishir");        // adds element at end of list


//        Create an array of size 5 and initialise with -1
//        Using normal integer arrays
        int[] arr = new int[5];         // creates an array of size 5, default value is 0 for all positions
        for(int i=0;i<5;i++){
            arr[i] = -1;
        }
        System.out.println(Arrays.toString(arr));               // arr contains the address, wrap it under Arrays.toString() to print the array

//        Using ArrayList
        ArrayList<Integer> arrlist = new ArrayList<>();         // just declares a dynamic vector, initially the size if 0
        for(int i=0;i<5;i++){
            arrlist.add(-1);
        }

        boolean[] vis = new boolean[10];
        for (boolean vi : vis) System.out.print(vi + " ");
        System.out.println();


//        ArrayList is the class which actually inmplements the List interface
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);
        list.add(1, 50);
        System.out.println(list);

        List<Integer> newList = new ArrayList<>();
        newList.add(150);
        newList.add(200);

        list.addAll(1,newList);
        System.out.println(list);

        System.out.println(list.get(1));

        System.out.println(list.remove(1));         // removes a specific index

        list.remove(Integer.valueOf(50));               // removes a specific value

        System.out.println(list);

        list.add(3);

        System.out.println(list);

        list.remove(Integer.valueOf(4));            // remove(object)   --- only removes 1 single occurence, not all the occrences
        System.out.println(list);


//        list.clear();                               // empty list


        list.set(2,1000);                              // to set a particular index

        System.out.println(list);                       // SOUT(object) invokes toString() of the object which basically prints that object

        System.out.println(list.contains(500));

//        Iterate over arraylist

        for(int i=0;i<list.size();i++){
            System.out.println("the element is " + list.get(i));
        }

        for(Integer element : list){
            System.out.println("foreach element is " + element);
        }

        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            System.out.println("iterator " + it.next());
        }





    }

}
