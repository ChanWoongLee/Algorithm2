package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Ssg_3 {
    static int[][] map;
    static int N;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
       // String[] str = solution(new int[][]{{1,1},{2,1},{1,2},{3,3},{6,4},{3,1},{3,3},{3,3},{3,4},{2,1}});
        String[] str = solution(new int[][]{{1,1},{1,2},{1,4},{2,1},{2,2},{2,3},{3,4},{3,1},{3,2},{3,3},{3,4},{4,4},{4,3},{5,4},{6,1}});
        Arrays.stream(str).forEach(System.out::println);
    }
    public static void print(){
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static String[] solution(int [][] macaron){
        N = 6;
        map = new int[N][N];
        for(int i = 0 ; i < macaron.length; i++){
            int line = macaron[i][0] -1;
            int color = macaron[i][1];

            init(line, color);
            while(check()){
            print();
                drop();
            };
        }
        String[] answer = new String[N];
        for(int i = 0 ; i < N ; i ++){
            String str = "";
            for(int j = 0 ; j < N ; j++){
                str += String.valueOf(map[i][j]);
            }
            answer[i] = str;
        }
        return answer;
    }
    static void drop(){
        Queue<Integer> q ;
        int[][] temp = new int[N][N];
        for(int j = 0 ; j < N ; j++){
            q = new LinkedList<>();
            for(int i = N-1 ; i >= 0 ; i--){
                if(map[i][j] != 0)
                    q.add(map[i][j]);
            }
            for(int i = N-1 ; i >= 0 ; i--){
                if(q.isEmpty())
                    break;
                temp[i][j] = q.poll();
            }
        }
        map = temp;
    }
    static boolean check(){
        boolean bomb = false;
        boolean[][] visit = new boolean[N][N];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(visit[i][j] || map[i][j] ==0)
                    continue;
                if(i == 4 && j == 1)
                    System.out.println(" ");
                int nowColor = map[i][j];
                Queue<Pos> q = new LinkedList<>();
                Queue<Pos> save = new LinkedList<>();
                q.add(new Pos(i,j));
                save.add(new Pos(i, j));
                visit[i][j] = true;
                while(!q.isEmpty()){
                    Pos now = q.poll();
                    for(int move = 0 ; move <4 ; move++){
                        int nextR = now.r + dr[move];
                        int nextC = now.c + dc[move];
                        if(nextR >= N || nextR < 0 || nextC >= N || nextC < 0 || visit[nextR][nextC])
                            continue;
                        if(map[nextR][nextC] != nowColor)
                            continue;
                        save.add(new Pos(nextR, nextC));
                        q.add(new Pos(nextR, nextC));
                        visit[nextR][nextC] = true;
                    }
                }
                if(save.size() >= 3){
                    for(Pos pos :save){
                        map[pos.r][pos.c] = 0;
                    }
                    bomb =true;
                }
            }
        }
        return bomb;
    }
    static class Pos{
        int r,c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static void init(int line, int color){
        for(int i = 0; i < N; i++){
            if(i == N-1){
                map[i][line] = color;
                return;
            }else{
                if(map[i+1][line] !=0){
                    map[i][line] = color;
                    return;
                }
            }
        }
    }
}
