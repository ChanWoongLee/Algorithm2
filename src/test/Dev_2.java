package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dev_2 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"??b", "abc", "cc?"}));
    }

    static String[][] map;
    static ArrayList<Pos> pos;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer = 0;

    static public int solution(String[] grid) {

        map = new String[grid.length][grid[0].length()];
        pos = new ArrayList<>();
        int maxCnt = 0;
        for (int i = 0; i < grid.length; i++) {
            String[] parse = grid[i].split("");
            for (int j = 0; j < parse.length; j++) {
                map[i][j] = parse[j];
                if (map[i][j].equals("?")) {
                    maxCnt++;
                    pos.add(new Pos(i, j));
                }
            }
        }
        dfs(0, maxCnt);

        return answer;
    }

    static boolean bfs() {
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visit = new boolean[map.length][map[0].length];
        ArrayList<String> save = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(visit[i][j])
                    continue;
                if (save.contains(map[i][j]))
                    return false;
                else {
                    String nowStr = map[i][j];
                    save.add(nowStr);
                    visit[i][j] = true;
                    q.add(new Pos(i, j));
                    while (!q.isEmpty()) {
                        Pos now = q.poll();
                        for (int move = 0; move < 4; move++) {
                            int nextR = now.r + dr[move];
                            int nextC = now.c + dc[move];
                            if (nextR >= map.length || nextR < 0 || nextC >= map[0].length || nextC < 0)
                                continue;
                            if (visit[nextR][nextC] || !map[nextR][nextC].equals(nowStr))
                                continue;
                            visit[nextR][nextC] = true;
                            q.add(new Pos(nextR, nextC));
                        }
                    }
                }

            }
        }

        return true;
    }

    static void dfs(int cnt, int maxCnt) {
        if (cnt == maxCnt) {
            if (bfs()) {
                answer++;
            }
            return;
        }

        for (int i = 97; i <= 99; i++) {
            Pos now = pos.get(cnt);
            map[now.r][now.c] = (char) i + "";
            dfs(cnt + 1, maxCnt);
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
