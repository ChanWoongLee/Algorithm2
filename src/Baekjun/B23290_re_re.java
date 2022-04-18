package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B23290_re_re {
    static ArrayList<Fish> fishs;
    static int[] dx2 = {0, -1, 0, 1};
    static int[] dy2 = {-1, 0, 1, 0};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[][] smell;
    static int[][] fishMap;
    static int maxFishCnt = 0;
    static ArrayList<Integer> maxFishMove;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int fishNum = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        fishs = new ArrayList<>();
        smell = new int[4][4];
        fishMap = new int[4][4];

        for (int i = 0; i < fishNum; i++) {
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            fishs.add(new Fish(y, x, Integer.parseInt(st.nextToken())));
        }
        st = new StringTokenizer(bf.readLine());
        int sharkY = Integer.parseInt(st.nextToken());
        int sharkX = Integer.parseInt(st.nextToken());
        Shark shark = new Shark(sharkX, sharkY);

        for (int i = 0; i < count; i++) {
            move(shark);
            maxFishCnt = 0;
            maxFishMove = new ArrayList<>();
            moveShark(shark, 0, 3, new ArrayList<>());
            for (int move : maxFishMove) {
                shark.x += dx2[move];
                shark.y += dy2[move];
                smell[shark.y][shark.x] = 2;
            }
            smellDown();
        }

    }

    static void smellDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > 0)
                    smell[i][j]-- ;
            }
        }
    }

    static void moveShark(Shark shark, int cnt, int maxCnt, ArrayList<Integer> save) {
        if (cnt == maxCnt) {
            boolean[][] visit = new boolean[4][4];
            int fishCnt = 0;
            int nextX = shark.x;
            int nextY = shark.y;
            for (int move : save) {
                nextX += dx2[move];
                nextY += dy2[move];
                if (nextX < 0 || nextY < 0 || nextX >= 4 || nextY >= 4)
                    return;
                if (visit[nextX][nextY])
                    continue;
                fishCnt += fishMap[nextX][nextY];
                visit[nextX][nextY] = true;
            }
            if (fishCnt > maxFishCnt) {
                maxFishCnt = fishCnt;
                maxFishMove.clear();
                maxFishMove.addAll(save);
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            save.add(i);
            moveShark(shark, cnt + 1, maxCnt, save);
            save.remove(save.size() - 1);
        }
    }

    static void move(Shark shark) {
        for (int i = 0; i < fishs.size(); i++) {
            Fish fish = fishs.get(i);
            int nowDir = fish.dir;
            for (int move = 0; move < 8; move++) {
                nowDir = (nowDir + move) % 8;
                int nextX = fish.x + dx[nowDir];
                int nextY = fish.y + dy[nowDir];
                if (nextX == shark.x && nextY == shark.y) {
                    continue;
                }
                if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4 || smell[nextY][nextX] != 0) {
                    continue;
                }
                fish = new Fish(nextX, nextY, nowDir);
                break;
            }
            fishMap[fish.y][fish.x]++;
        }
    }

    static class Shark {
        int x, y;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Fish {
        int x, y, dir;

        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
