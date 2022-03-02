package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B6443 {
    static String[] str;
    static boolean[] visit;
    static HashSet<String> hs;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            hs = new HashSet<>();
            st = new StringTokenizer(bf.readLine());
            str = st.nextToken().split("");
            visit = new boolean[str.length];
            Arrays.sort(str);
            dfs(0, 0, new StringBuffer());
        }
    }

    static void dfs(int index, int cnt, StringBuffer temp) {
        if (cnt == str.length) {
            if (hs.contains(temp.toString()))
                return;
            hs.add(temp.toString());
            System.out.println(temp.toString());
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (visit[i])
                continue;
            visit[i] = true;
            StringBuffer stb = new StringBuffer(temp.toString());
            dfs(i + 1, cnt + 1, stb.append(str[i]));
            visit[i] = false;
        }
    }
}
