package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17616 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] ar = new ArrayList[n + 1];
        ArrayList<Integer>[] ar2 = new ArrayList[n + 1];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = new ArrayList();
            ar2[i] = new ArrayList();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ar[start].add(end);
            ar2[end].add(start);
        }
        int min = n - bfs(ar, x);
        int max = bfs(ar2, x) + 1;
        System.out.println(max + " " + min);
    }

    static int bfs(ArrayList<Integer>[] ar, int x) {
        int check = 0;
        boolean[] visit = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visit[x] = true;
        while (!q.isEmpty()) {
            int now = q.poll();

            for (Integer node : ar[now]) {
                if (visit[node])
                    continue;
                check++;
                visit[node] = true;
                q.add(node);
            }
        }
        return check;
    }
}
