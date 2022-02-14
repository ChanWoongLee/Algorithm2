package test;

import java.util.Arrays;
import java.util.stream.Collectors;

//5 11 시작
public class Kakao_2022_4 {
    static int[] INFO;
    static int[] MAX_ARROW = null;
    static int MAX_DIFF = 0;

    public static void main(String[] args) {
        solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1});
    }

   static public int[] solution(int n, int[] info) {
        int[] answer = {};
        INFO = info;
        dfs(0,0,n,new int[11]);
        if (MAX_ARROW == null) {
            return new int[]{-1};
        }else{
            return MAX_ARROW;
        }
    }

    static public void dfs(int n, int cnt, int maxCnt, int[] arrow) {
        if (cnt == maxCnt) {
            int a = 0, l = 0;
            for (int i = 0; i < INFO.length; i++) {
                if (INFO[i] == 0 && arrow[i] == 0) {
                } else if (INFO[i] >= arrow[i]) {
                    a += 10-i;
                } else {
                    l += 10-i;
                }
            }
            if(MAX_DIFF < l -a){
                MAX_ARROW = Arrays.copyOf(arrow, arrow.length);
                MAX_DIFF = l -a;
            }else if(MAX_DIFF == l-a){
                if (MAX_ARROW != null) {
                    for (int i = arrow.length - 1; i >= 0; i--) {
                        if (arrow[i] != MAX_ARROW[i]) {
                            if (arrow[i] > MAX_ARROW[i]) {
                                MAX_ARROW = Arrays.copyOf(arrow, arrow.length);
                            }
                            break;
                        }
                    }
                } else {
                    MAX_ARROW = Arrays.copyOf(arrow, arrow.length);
                }
            }
            return;
        }

        for (int i = n; i <= 10; i++) {
            arrow[i]++;
            dfs(i,cnt+1, maxCnt, arrow);
            arrow[i]--;
        }
    }

}
