package test;

import java.util.ArrayList;

public class Kakao_2022_5_good {
    static ArrayList<Integer>[] ar;
    static int MAX_SHEEP = 0;
    static int[] INFO;

    public static void main(String[] args) {
        String info = "[0,0,1,1,1,0,1,0,1,0,1,1]".replace("[", "{").replace("]", "}");
        String edge = "[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]".replace("[", "{").replace("]", "}");
        System.out.println(solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}));

    }

    // 2 : 40 시작
    static public int solution(int[] info, int[][] edges) {
        if (info[0] == 1)
            return 0;
        INFO = info;
        ar = new ArrayList[info.length];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < edges.length; i++) {
            ar[edges[i][0]].add(edges[i][1]);
        }
        dfs(0,0,0,new ArrayList<>());
        return MAX_SHEEP;
    }

    static void dfs(int node, int sheep, int wolf, ArrayList<Integer> next) {
        if (INFO[node] == 0) {
            sheep++;
        }else{
            wolf++;
        }
        if (sheep <= wolf) {
            return;
        }


        MAX_SHEEP = Math.max(MAX_SHEEP, sheep);
        ArrayList<Integer> nodes = new ArrayList<>(next);
        nodes.addAll(ar[node]);
        if(nodes.contains(node))
            nodes.remove(Integer.valueOf(node));

        for (int n : nodes) {
            dfs(n, sheep, wolf, nodes);
        }
    }
}
