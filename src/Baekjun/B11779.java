package Baekjun;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B11779 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][n + 1];
        int[] route = new int[n + 1];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 987654321;
            }
        }
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
        Arrays.fill(dist, Integer.MAX_VALUE);
        route[start] = 0;
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.value - b.value);
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if(!visit[now.node]) visit[now.node] = true;
            else continue;

            for (int i = 1; i < n + 1; i++) {
                if (dist[i] > now.value + map[now.node][i]) {
                    dist[i] = now.value + map[now.node][i];
                    pq.add(new Node(i, dist[i]));
                    route[i] = now.node;
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        int temp = end;
        while(temp!=0){
            stack.add(temp);
            temp = route[temp];
        }
        System.out.println(dist[end]);
        System.out.println(stack.size());
        while(!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
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
