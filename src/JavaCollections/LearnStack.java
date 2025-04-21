package JavaCollections;

import java.util.Stack;

public class LearnStack {
    public static void main(String[] args){
        Stack<String> st = new Stack<>();
        st.push("Lion");
        st.push("Dog");
        st.push("Cat");
        st.push("Horse");

        System.out.println(st.size());          // stack size
        System.out.println(st.peek());          // top element
        System.out.println(st);
        System.out.println(st.pop());           // removes the topmost element

    }
}
