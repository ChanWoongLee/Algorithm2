package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1079 {
    static int[] score;
    static int[][] info;
    static int N, me;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        score = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
        info = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        me = Integer.parseInt(st.nextToken());
        boolean[] visit = new boolean[N];
        dfs(0, visit);
        System.out.println(ans);
    }

    static void dfs(int day, boolean[] visit) {
        ans = Math.max(day, ans);
        if (visit[me])
            return;

        for (int i = 0; i < N; i++) {
            if (i == me || visit[i])
                continue;

            visit[i] = true;
            int maxScore = Integer.MIN_VALUE;
            int maxIndex = 0;
            for (int s = 0; s < N; s++) {
                score[s] += info[i][s];
                if (visit[s]) {
                    continue;
                }
                if (maxScore < score[s]) {
                    maxScore = Math.max(maxScore, score[s]);
                    maxIndex = s;
                }
            }
            visit[maxIndex] = true;
            dfs(day + 1, visit);
            visit[i] = false;
            visit[maxIndex] = false;
            for (int s = 0; s < N; s++) {
                score[s] -= info[i][s];
            }
        }
    }
}


