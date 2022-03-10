package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B19942 {
    static int result = Integer.MAX_VALUE;
    static int N;
    static Food[] food;
    static Food want;
    static ArrayList<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        want = new Food();
        String[] str = bf.readLine().split(" ");
        want.p = Integer.parseInt(str[0]);
        want.f = Integer.parseInt(str[1]);
        want.s = Integer.parseInt(str[2]);
        want.v = Integer.parseInt(str[3]);
        food = new Food[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            food[i] = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        for (int i = 1; i <= N; i++) {
            recur(0, 0, i, new Food(0, 0, 0, 0, 0), "");
        }
        if (result == Integer.MAX_VALUE) {
            System.out.println("-1");
            return;
        }
        System.out.println(result);
        Collections.sort(answer);
        System.out.println(answer.get(0).substring(0,answer.get(0).length()-1));
    }

    static void recur(int idx, int cnt, int maxCnt, Food f, String ans) {
        if (cnt == maxCnt) {
            if (want.p > f.p || want.f > f.f || want.s > f.s || want.v > f.v)
                return;
            if (result >= f.price) {
                if(result != f.price){
                    answer = new ArrayList<>();
                }
                answer.add(ans);
                result = f.price;
            }
            return;
        }
        for (int i = idx; i < N; i++) {
            Food temp = new Food(f.p + food[i].p, f.p + food[i].p, f.s + food[i].s, f.v + food[i].v, f.price + food[i].price);
            String str = ans + (i+1) + " ";
            recur(i + 1, cnt + 1, maxCnt, temp, str);
        }
    }

    static class Food {
        int p, f, s, v, price;

        public Food(int p, int f, int s, int v, int price) {
            super();
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.price = price;
        }

        public Food() {
        }

    }
}
