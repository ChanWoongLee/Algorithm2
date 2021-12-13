package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B17352 {
    static int[] parent;

    public static void main(String[] args) throws IOException { 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        if (N == 2) {
            System.out.println("1 2");
            return;
        }

        parent = new int[N + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < N - 2; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            union(start, end);
        }
        for (int i = 1; i < parent.length; i++) {
           find(i);
        }
        HashSet<Integer> hs = new HashSet<>();
        for (int p : parent) {
            if (p == 0)
                continue;
            hs.add(p);
        }
        hs.stream().forEach(a -> System.out.print(a + " ")) ;
    }


    static int find(int node) {
        if (parent[node] == node) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        parent[parentA] = parentB;
    }

    ;
}
