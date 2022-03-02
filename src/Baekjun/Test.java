package Baekjun;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int i = N; i>0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("＊");
            }
            System.out.println();
        }
    }

    static class T{
        int r;

        public T(int r) {
            this.r = r;
        }
    }
}