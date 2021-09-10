package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B11399 {
    static String[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int N, H, D;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        Person now = null;
        map = new String[N][N];
        for (int i = 0; i < N; i++) {
            String[] str = bf.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = str[j];
                if (map[i][j].equals("S"))
                    now = new Person(i, j,0,H);
            }
        }
        boolean[][][] visit = new boolean[2][N][N];
        visit[0][now.r][now.c] = true;
        Queue<Person> q = new LinkedList<>();
        q.add(now);
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                now = q.poll();
                for (int move = 0; move < 4; move++) {
                    int nextR = now.r + dr[move];
                    int nextC = now.c + dc[move];
                    if (nextR >= N || nextR < 0 || nextC >= N || nextC < 0) {
                        continue;
                    }
                    if (map[nextR][nextC].equals("E")) {
                        System.out.println(cnt);
                        return;
                    }
                    int nextUmbrella = now.u == 0 ? 0 : now.u - 1;
                    if (map[nextR][nextC].equals("U")) {
                        now.u= D;
                        nextUmbrella = D;
                    }

                    if ((nextUmbrella > 0 && visit[1][nextR][nextC]) || (nextUmbrella == 0 && visit[0][nextR][nextC])) {
                        continue;
                    }

                    if (now.u > 0) {
                        q.add(new Person(nextR, nextC,now.u - 1,now.h ));
                        visit[1][nextR][nextC] = true;
                    }else{
                        if(now.h -1 == 0)
                            continue;
                        q.add(new Person(nextR,nextC,now.u,now.h-1));
                        visit[0][nextR][nextC] = true;
                    }
                }
            }
        }
        System.out.println("-1");
    }

    static class Person {
        int r, c, u, h;

        public Person(int r, int c, int u, int h) {
            this.r = r;
            this.c = c;
            this.u = u;
            this.h = h;
        }
    }
}
