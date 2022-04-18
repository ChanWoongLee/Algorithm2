package Baekjun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        System.out.println("a".compareTo("b"));
        ArrayList<Integer> ar = new ArrayList<>(Arrays.asList(1,2,3,4,2,3,2));
        for (int i = 0; i < ar.size(); i++) {
            if(ar.get(i) != 10){
                ar.remove(i);
                i--;
            }
        }
        ar.stream().forEach(System.out::print);
    }

    static class T{
        int r;

        public T(int r) {
            this.r = r;
        }
    }
}