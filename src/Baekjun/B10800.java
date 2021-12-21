package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B10800 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Ball> ar = new ArrayList<>();
        for(int i = 0 ; i < N ; i ++) {
            st = new StringTokenizer(bf.readLine());
            ar.add(new Ball(i,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        int[] players = new int[N];
        int[] partSum = new int[N+1];
        int sum = 0;
        Collections.sort(ar,(a,b) -> a.size - b.size);
        for(int i = 0; i < N ; i ++){
            Ball now = ar.get(i);
            int j = i+1;
            while(j < N && now.size == ar.get(j).size){

            }


            sum += ar.get(i).size;
            partSum[ar.get(i).color] += ar.get(i).size;
            players[ar.get(i).idx] = sum - partSum[ar.get(i).color];
        }
        for(int p : players){
            System.out.println(p);
        }
    }
    static class Ball{
        int idx;
        int color;
        int size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }
}
