package test;

import java.util.*;

class STK_4_re {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}}));
    }
    static public int solution(int n, int[][] edges) {
        ArrayList<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for (int[] e : edges) {
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        int[] result = bfs(list, 0, n);

        int s = 0, max = 0, cnt = 0;
        for (int i = 1; i < n; i++) {
            if (result[i] > result[s]) s = i;
        }
        result = bfs(list, s, n);
        s = 1;
        for (int i = 1; i <= n; i++) {
            if (result[i] > result[s]) s = i;
        }
        for (int i : result) {
            max = Math.max(max, i);
        }
        for (int i : result)
            if (max == i) cnt++;
        if (cnt >= 2) return max;
        max = 0;
        cnt = 0;
        result = bfs(list, s, n);
        for (int i : result) max = Math.max(max, i);
        for (int i : result) if (max == i) cnt++;
        if (cnt >= 2) return max;
        return max - 1;
    }

    private static int[] bfs(ArrayList<Integer>[] list, int s, int n) {
        boolean[] visit = new boolean[n];
        int[] dist = new int[n];
        LinkedList<Integer> qu = new LinkedList<Integer>();
        qu.add(s);
        visit[s] = true;

        while (!qu.isEmpty()) {
            int num = qu.poll();
            for (int i : list[num]) {
                if (i == num || visit[i]) continue;
                visit[i] = true;
                qu.add(i);
                dist[i] = dist[num] + 1;
            }
        }
        return dist;
    }
}