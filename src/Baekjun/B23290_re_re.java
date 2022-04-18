package Baekjun;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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
        fishs = new ArrayList<>(); // 물고기 전체 관리
        smell = new int[4][4];


        for (int i = 0; i < fishNum; i++) {
            st = new StringTokenizer(bf.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            fishs.add(new Fish(x, y, Integer.parseInt(st.nextToken())));
        }
        st = new StringTokenizer(bf.readLine());
        int sharkY = Integer.parseInt(st.nextToken())-1;
        int sharkX = Integer.parseInt(st.nextToken())-1;
        Shark shark = new Shark(sharkX, sharkY);
        boolean[][] dead; // 죽은 물고기 자리 확인
        ArrayList<Fish> copy; // 초기 물고기 복사
        for (int i = 0; i < count; i++) {
            fishMap = new int[4][4]; // 맵에 물고기 개수 관리
            copy = move(shark);
            maxFishCnt = 0;
            maxFishMove = new ArrayList<>();

        /*    System.out.println();
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    System.out.print(fishMap[x][y]);
                }
                System.out.println();
            }*/
            moveShark(shark, 0, 3, new ArrayList<>());
            dead = new boolean[4][4];
            for (int move : maxFishMove) {
                shark.x += dx2[move];
                shark.y += dy2[move];
                dead[shark.y][shark.x] = true;
            }

            //System.out.println("shark x : "+ shark.x + "  shark y :" + shark.y);
            for (int f = 0; f < fishs.size(); f++) {
                Fish fish = fishs.get(f);
                if(dead[fish.y][fish.x]){
                    smell[fish.y][fish.x] = 3;
                    fishs.remove(f);
                    f--;
                }
            }
            smellDown();
            fishs.addAll(copy);
        }
        System.out.println(fishs.size());
    }

    static void smellDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > 0)
                    smell[i][j]--;
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
                if (visit[nextY][nextX])
                    continue;
                fishCnt += fishMap[nextY][nextX];
                visit[nextY][nextX] = true;
            }
            if (fishCnt > maxFishCnt) {
                maxFishCnt = fishCnt;
                maxFishMove.clear();
                maxFishMove.addAll(save);
                //System.out.println(fishCnt + " ");
                //maxFishMove.stream().forEach(System.out::print);
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            save.add(i);
            moveShark(shark, cnt + 1, maxCnt, save);
            save.remove(save.size() - 1);
        }
    }

    static ArrayList<Fish> move(Shark shark) {
        ArrayList<Fish> copy = new ArrayList<>();
        for (int i = 0; i < fishs.size(); i++) {
            Fish fish = fishs.get(i);
            //System.out.println(fish.x + " " + fish.y + " " + fish.dir);
            copy.add(new Fish(fish.x, fish.y, fish.dir));
            int nowDir;
            for (int move = 0; move < 8; move++) {
                nowDir = fish.dir - move <= 0 ? fish.dir - move + 8: fish.dir -move;
                int nextX = fish.x + dx[nowDir];
                int nextY = fish.y + dy[nowDir];
                if (nextX == shark.x && nextY == shark.y) {
                    continue;
                }
                if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4 || smell[nextY][nextX] != 0) {
                    continue;
                }
                fish.x = nextX;
                fish.y =nextY;
                fish.dir = nowDir;
                break;
            }
            //System.out.println(fish.x + " " + fish.y + " " + fish.dir);
            fishMap[fish.y][fish.x]++;
        }
        return copy;
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
