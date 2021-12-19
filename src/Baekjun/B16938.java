package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B16938 {
    static int[] ar;
    static int L, X, R, ans = 0;
    static ArrayList<Integer> temp = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ar = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < ar.length; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        recur(0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        System.out.println(ans);
    }

    static void recur(int index, int max, int min, int sum, int cnt) {
        if (cnt >= 2) {
            if (sum >= L && sum <= R && max - min >= X) {
                ans++;
            }
            if(sum > R){
                return;
            }
        }
        for (int i = index; i < ar.length; i++) {
            recur(i + 1, Math.max(max, ar[i]), Math.min(min, ar[i]), sum + ar[i], cnt + 1);
        }
    }
}
