package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B2632 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[] ar1 = new int[a];
        int[] ar2 = new int[b];
        for (int i = 0; i < a; i++) {
            st = new StringTokenizer(bf.readLine());
            ar1[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(bf.readLine());
            ar2[i] = Integer.parseInt(st.nextToken());
        }

        
    }
}
