package Baekjun;

import sun.util.locale.StringTokenIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B23289 {
    static int R, C, K;
    static int[][] map;
    // 오른쪽, 왼쪽 위 아래
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static boolean[][] wall;
    static ArrayList<Item> items;

    public static class Item {
        int r, c, dir;

        public Item(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        wall = new boolean[R * C][R * C];
        items = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    items.add(new Item(i, j, map[i][j]));
                }
            }
        }
        st = new StringTokenizer(bf.readLine());
        int w = Integer.parseInt(st.nextToken());
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int num1 = C * r + c;
            int num2 = dir == 0 ? C * (r - 1) + c : C * r + c + 1;
            wall[num1][num2] = true;
            wall[num2][num1] = true;
        }
        int choco = 0;
        while (checkContinue()) {
            wind();

        }
    }

    static void wind() {
        for (Item item : items) {
            Queue<Item> q = new LinkedList<>();
            boolean[][] visit = new boolean[R][C];
            q.add(item);
            int stop = 0;
            while (stop++ == 5) {
                int qSize = q.size();
                for (int i = 0; i < qSize; i++) {
                    Item now = q.poll();
                    visit[now.r][now.c] = true;

                }


            }
        }
    }

    static boolean checkContinue() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!(map[i][j] >= K)) {
                    return true;
                }
            }
        }
        return false;
    }
}
