package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10159 {
    static int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int K = Integer.parseInt(st.nextToken());
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                dist[i][j] = MAX;
                if (i == j) {
                    dist[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dist[start][end] = 1;
        }
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    dist[i][j] = dist[i][j] > dist[i][k] + dist[k][j] ? dist[i][k] + dist[k][j] : dist[i][j];
                }
            }
        }
        int[] ans = new int[N];
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                if (dist[i][j] != MAX) {
                    cnt++;
                }
                if (dist[j][i] != MAX) {
                    cnt++;
                }
            }
            ans[i-1] = cnt;
        }
        for (int answer : ans) {
            System.out.println(answer);
        }
    }
}
