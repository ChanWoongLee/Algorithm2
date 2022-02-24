package Baekjun;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<T> ar = new ArrayList();
        ar.add(new T(3));
        ArrayList<T> ar2 = new ArrayList<>();
        ar2.addAll(ar);
        T ttt = ar.get(0);
        ttt.r = 1;
        System.out.println(ar2.get(0).r);
    }

    static class T{
        int r;

        public T(int r) {
            this.r = r;
        }
    }
}