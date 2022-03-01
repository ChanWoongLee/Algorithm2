package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B11779 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            map[start][end] = value;
        }
        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        visit[start] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.value - b.value);
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < n + 1; i++) {
                if(visit[i])
                    continue;

            }
        }
    }

    static class Node {
        int node, value;

        public Node(int node, int value) {
            this.node = node;
            this.value = value;
        }
    }
}
