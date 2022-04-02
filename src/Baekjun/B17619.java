package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B17619 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        ArrayList<Node> ar = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ar.add(new Node(x1, x2, y, i + 1));
        }
        Collections.sort(ar);
        for (int i = 0; i < ar.size(); i++) {
            Node now = ar.get(i);
            if(i == ar.size()-1){

            }
            Node next = ar.get(i+1);
            int nowEnd = now.x2;
            int nextStart = next.x1;


        }
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

        }
    }

    static class Node implements Comparable<Node>{
        int x1, x2, y, id;

        public Node(int x1, int x2, int y, int id) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.id = id;
        }

        @Override
        public int compareTo(Node o) {
            if(this.x1 == o.x1)
                return this.id - o.id;
            else
                return this.x1 -o.x1;
        }
    }
}
