package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1039 {
    static HashSet<String> hs = new HashSet<>();
    static int M;
    static ArrayList<Integer> temp = new ArrayList<>();
    static Queue<StringBuffer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        M = String.valueOf(N).length();
        int ans = -1;
        q.add(new StringBuffer(String.valueOf(N)));
        for(int i = 0 ; i < K ; i ++){
            int size= q.size();
            for (int j = 0; j < size; j++) {
                recur(q.poll(),0,0,2);
            }
            hs.clear();
        }
        for (StringBuffer stb : q) {
            if (ans < Integer.parseInt(stb.toString())) {
                ans = Integer.parseInt(stb.toString());
            }
        }
        System.out.println(ans);
    }
    static void recur(StringBuffer stb,int index, int cnt, int maxCnt){
        if(cnt == maxCnt){
            int firstIndex = temp.get(0);
            int secondIndex = temp.get(1);
            if(firstIndex == 0 && stb.charAt(secondIndex) == '0'){
                return;
            }
            StringBuffer stbClone = new StringBuffer(stb.toString());
            char firstIndexCharacter = stbClone.charAt(firstIndex);
            char secondIndexCharacter = stbClone.charAt(secondIndex);
            stbClone.setCharAt(secondIndex,firstIndexCharacter);
            stbClone.setCharAt(firstIndex,secondIndexCharacter);
            if (hs.contains(stbClone.toString())) {
                return;
            }else{
                hs.add(stbClone.toString());
                q.add(stbClone);
            }
            return;
        }
        for (int i = index; i < M; i++) {
            temp.add(i);
            recur(stb,i+1,cnt+1, maxCnt);
            temp.remove(temp.size() - 1);
        }
    }
}
