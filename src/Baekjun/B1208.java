package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B1208 {
    static long[] ar;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        ar = new long[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            ar[i] = Long.parseLong(st.nextToken());
        }
        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();
        dfs(0, ar.length / 2, 0, left);
        dfs(ar.length / 2, ar.length, 0, right);
        Collections.sort(left);
        Collections.sort(right);

        int start = 0;
        int end = right.size() - 1;
        long ans = 0;
        while (start < left.size() && end >= 0) {
            Long sum = left.get(start) + right.get(end);
            if (sum == S) {
                long cnt1 = 0;
                long cnt2 = 0;
                long leftNum = left.get(start);
                while (start < left.size() && leftNum == left.get(start)) {
                    start++;
                    cnt1++;
                }
                long rightNum = right.get(end);
                while (end < right.size() && rightNum == right.get(end)) {
                    end--;
                    cnt2++;
                }
                ans += cnt1 * cnt2;
            } else if (sum > S) {
                end--;
            } else if (sum < S) {
                start++;
            }
        }
        if(S ==0)
            ans--;
        System.out.println(ans);
    }

    static void dfs(int start, int end, long sum, ArrayList<Long> list) {
        if (start == end) {
            list.add(sum);
            return;
        }

        dfs(start + 1, end, sum + ar[start], list);
        dfs(start + 1, end, sum, list);
    }
}
