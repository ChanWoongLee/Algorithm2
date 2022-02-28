package Baekjun;

import sun.util.locale.StringTokenIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B23289 {
    static int R, C, K;
    static int[][] map;
    // 오른쪽, 왼쪽 위 아래
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};
    static boolean[][] wall;
    static ArrayList<Item> items;
    static ArrayList<Item> checkItems;

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
        checkItems = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info != 0) {
                    if (info < 5) {
                        items.add(new Item(i, j, info));
                    } else {
                        checkItems.add(new Item(i, j, info));
                    }
                }
            }
        }
        st = new StringTokenizer(bf.readLine());
        int w = Integer.parseInt(st.nextToken());
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            int num1 = C * r + c;
            int num2 = dir == 0 ? C * (r - 1) + c : C * r + c + 1;
            wall[num1][num2] = true;
            wall[num2][num1] = true;
        }
        int choco = 0;
        while (checkContinue()) {
            wind();
            cal();
            edge();
            choco++;
            if (choco > 100) {
                System.out.println(choco);
                return;
            }
            print();
        }
        System.out.println(choco);
    }

    static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void edge() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == 0 || i == R - 1 || j == 0 || j == C-1) {
                    if(map[i][j] != 0){
                        map[i][j]--;
                    }
                }
            }
        }
    }

    static void cal() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int pos1 = i * C + j;
                for (int dir = 1; dir < 5; dir++) {
                    int nextR = i + dr[dir];
                    int nextC = j + dc[dir];
                    int pos2 = nextR * C + nextC;
                    if (checkEdge(nextR, nextC) || wall[pos1][pos2]  || map[i][j] <= map[nextR][nextC]) {
                        continue;
                    }
                    int diff = (map[i][j] - map[nextR][nextC]) / 4;
                    temp[i][j] -= diff;
                    temp[nextR][nextC] += diff;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    static void wind() {
        for (Item item : items) {
            Queue<Item> q = new LinkedList<>();
            boolean[][] visit = new boolean[R][C];
            int initR = item.r + dr[item.dir];
            int initC = item.c + dc[item.dir];
            q.add(new Item(initR, initC, item.dir));
            visit[initR][initC] = true;
            map[initR][initC] += 5;
            int stop = 0;
            int value = 4;
            while (stop++ != 4) {
                int qSize = q.size();
                for (int i = 0; i < qSize; i++) {
                    Item now = q.poll();

                    // 한칸 앞으로간것
                    int nextR = now.r + dr[now.dir];
                    int nextC = now.c + dc[now.dir];
                    int pos1 = C * now.r + now.c;
                    int pos2 = C * nextR + nextC;
                    if (checkEdge(nextR, nextC) || visit[nextR][nextC] || wall[pos1][pos2]) {

                    } else {
                        visit[nextR][nextC] = true;
                        map[nextR][nextC] += value;
                        q.add(new Item(nextR, nextC, now.dir));
                    }

                    int spread1 = 1;
                    int spread2 = 2;
                    if (now.dir == 1 || now.dir == 2) {
                        spread1 = 3;
                        spread2 = 4;
                    }

                    // 옆으로 퍼지는것
                    checkSpread(now, nextR, nextC, visit, q, spread1, value);
                    checkSpread(now, nextR, nextC, visit, q, spread2, value);
                }

                value--;
            }
        }
    }

    static void checkSpread(Item now, int nextR, int nextC, boolean[][] visit, Queue<Item> q, int spreadDir, int value) {
        int checkR = now.r + dr[spreadDir];
        int checkC = now.c + dc[spreadDir];
        int spreadR = nextR + dr[spreadDir];
        int spreadC = nextC + dc[spreadDir];
        int pos0 = C * now.r + now.c;
        int pos1 = C * checkR + checkC;
        int pos2 = C * spreadR + spreadC;

        if (checkEdge(spreadR, spreadC) || visit[spreadR][spreadC] || wall[pos1][pos2] || wall[pos0][pos1]) {

        } else {
            visit[spreadR][spreadC] = true;
            map[spreadR][spreadC] += value;
            q.add(new Item(spreadR, spreadC, now.dir));
        }

    }

    static boolean checkEdge(int r, int c) {
        if (r >= R || c >= C || r < 0 || c < 0) {
            return true;
        }
        return false;
    }

    static boolean checkContinue() {
        for (Item item : checkItems) {
            if (map[item.r][item.c] < K) {
                return true;
            }
        }
        return false;
    }
}
