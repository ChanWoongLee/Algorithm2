//package Baekjun;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class B23290_re {
//    // 2:35
//    static int[] dr2 = {-1, 0, 1, 0};
//    static int[] dc2 = {0, -1, 0, 1};
//    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
//    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
//    static int[][] smell = new int[4][4];
//    static int N = 4;
//    static int maxFish;
//    static ArrayList<Integer> maxHistory;
//    static ArrayList<Fish>[][] fishs;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//        int fishNum = Integer.parseInt(st.nextToken());
//        int count = Integer.parseInt(st.nextToken());
//        fishs = new ArrayList[4][4];
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                fishs[i][j] = new ArrayList<>();
//            }
//        }
//
//        for (int i = 0; i < fishNum; i++) {
//            st = new StringTokenizer(bf.readLine());
//            int r = Integer.parseInt(st.nextToken()) - 1;
//            int c = Integer.parseInt(st.nextToken()) - 1;
//            int dir = Integer.parseInt(st.nextToken());
//            fishs[r][c].add(new Fish(r,c,dir));
//        }
//
//        st = new StringTokenizer(bf.readLine());
//        int sharkR = Integer.parseInt(st.nextToken()) - 1;
//        int sharkC = Integer.parseInt(st.nextToken()) - 1;
//
//        for (int i = 0; i < count; i++) {
//            ArrayList<Fish> copy = new ArrayList<>();
//            copy(copy, sharkR, sharkC);
//            move(sharkR, sharkC);
//            remove(sharkR, sharkC);
//            for (int dir : maxHistory) {
//                sharkR += dr2[dir];
//                sharkC += dc2[dir];
//            }
//            for (Fish f : copy) {
//                fishs.add(new Fish(f.r, f.c, f.dir));
//            }
//            System.out.println();
//        }
//        System.out.println(fishs.size());
//
//    }
//
//    static void remove(int sharkR, int sharkC) {
//        boolean[][] map = new boolean[4][4];
//        for (int dir : maxHistory) {
//            sharkR += dr2[dir];
//            sharkC += dc2[dir];
//            map[sharkR][sharkC] = true;
//        }
//        double start = System.currentTimeMillis();
//        for (int i = 0; i < fishs.size(); i++) {
//            Fish f = fishs.get(i);
//            int nowR = f.r;
//            int nowC = f.c;
//            if (map[nowR][nowC]) {
//                fishs.remove(i);
//                smell[nowR][nowC] = 3;
//                i--;
//            }
//        }
//        for (int i = 0; i < smell.length; i++) {
//            for (int j = 0; j < smell[0].length; j++) {
//                smell[i][j]--;
//            }
//        }
//    }
//
//    static void move(int sharkR, int sharkC) {
//        maxFish = 0;
//        dfs(0, 0, sharkR, sharkC, new ArrayList<>());
//    }
//
//    static void dfs(int cnt, int fishCnt, int r, int c, ArrayList<Integer> history) {
//        if (cnt == 3) {
//            if (maxFish <= fishCnt) {
//                if (maxFish == fishCnt) {
//                    StringBuffer nowHistoryStr = new StringBuffer();
//                    for (Integer h : history) {
//                        nowHistoryStr.append(h);
//                    }
//                    StringBuffer maxHistoryStr = new StringBuffer();
//                    for (Integer h : maxHistory) {
//                        maxHistoryStr.append(h);
//                    }
//                    if (Integer.valueOf(nowHistoryStr.toString()) < Integer.valueOf(maxHistoryStr.toString())) {
//                        maxHistory = new ArrayList<Integer>(history);
//                    }
//                } else {
//                    maxHistory = new ArrayList<Integer>(history);
//                }
//                maxFish = fishCnt;
//            }
//            return;
//        }
//
//        for (int move = 0; move < 4; move++) {
//            int nextR = r + dr2[move];
//            int nextC = c + dc2[move];
//            if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0) {
//                continue;
//            }
//            int nextFishCnt = fishCnt + fish[nextR][nextC];
//
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    fishs[i][j] = new ArrayList<>();
//                }
//            }
//            temp[nextR][nextC] = 0;
//
//            history.add(move);
//            dfs(cnt + 1, nextFishCnt, nextR, nextC, history, temp);
//            history.remove(history.size() - 1);
//        }
//    }
//
//    static void copy(ArrayList<Fish> copy, int sharkR, int sharkC) {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                for (Fish f : fishs[i][j]) {
//                    copy.add(new Fish(f.r, f.c, f.dir));
//                    for (int move = 0; move <= 8; move++) {
//                        int nowDir = f.dir - move <= 0 ? f.dir - move + 8 : f.dir - move;
//                        int nextR = f.r + dr[nowDir];
//                        int nextC = f.c + dc[nowDir];
//                        if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0) {
//                            continue;
//                        }
//                        if (nextR == sharkR && nextC == sharkC) {
//                            continue;
//                        }
//                        if (smell[nextR][nextC] > 0) {
//                            continue;
//                        }
//                        f.r = nextR;
//                        f.c = nextC;
//                        f.dir = nowDir;
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    static class Fish {
//        int r, c;
//        int dir;
//
//        public Fish(int r, int c, int dir) {
//            this.r = r;
//            this.c = c;
//            this.dir = dir;
//        }
//    }
//}
