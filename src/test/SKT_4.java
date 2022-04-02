package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SKT_4 {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}}));
    }

    static boolean[] visit;
    static int[] dep;
    static long answer = 0;
    static int N;
    static public long solution(int n, int[][] edges) {
        ArrayList<Integer>[] ar = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            ar[i] = new ArrayList();
        }
        for (int i = 0; i < edges.length; i++) {
            ar[edges[i][0]].add(edges[i][1]);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        N = n;
        visit = new boolean[n];
        dep = new int[n];
        int depth = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (Integer node : ar[now]) {
                dep[node] = depth;
                q.add(node);
            }
            depth++;
        }
        dfs(0, 0, 3, new ArrayList<>());
        return answer;
    }

    static void dfs(int index, int cnt, int maxCnt, ArrayList<Integer> save) {
        if (cnt == maxCnt) {
            int i = save.get(0);
            int j = save.get(1);
            int k = save.get(2);
            int ij = Math.abs(dep[i] - dep[j]);
            int jk = Math.abs(dep[j] - dep[k]);
            int ik = Math.abs(dep[i] - dep[k]);

            if (ij + jk == ik) {
                System.out.println(ij + " "+ jk +" "+ik);
                answer++;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            save.add(i);
            dfs(index + 1, cnt + 1, maxCnt, save);
            save.remove(save.size() - 1);
            visit[i] = false;
        }
    }
}
