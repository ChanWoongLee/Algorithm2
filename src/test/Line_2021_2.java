package test;

public class Line_2021_2 {
    static int[][] cnt;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"yxxy", "xxyyy"}, 2, 1);
    }

    static class Solution {
        public String solution(String[] research, int n, int k) {
            cnt = new int[123][research.length];
            for (int i = 0; i < research.length; i++) {
                String now = research[i];
                for (int j = 0; j < now.length(); ) {
                    int beforeLen = now.length();
                    char search = now.charAt(j);
                    now = now.replace(search + "", "");
                    int afterLen = now.length();
                    cnt[search][i] = beforeLen - afterLen;
                }
            }
            int totalNeed = 2 * n * k;

            int[] issueCnt = new int[123];
            int maxCnt = 0;
            for (int i = 97; i < 123; i++) {
                for (int j = 0; j <= research.length - n; j++) {
                    int totalDay = 0;
                    for (int day = j; day < j + n; day++) {
                        if (cnt[i][day] == 0 || cnt[i][day] < k) {
                            totalDay = 0;
                            break;
                        }else{
                            totalDay += cnt[i][day];
                        }
                    }
                    if(totalDay >= totalNeed){
                        issueCnt[i]++;
                    }
                }
                maxCnt = maxCnt < issueCnt[i] ? issueCnt[i] : maxCnt;
            }
            if(maxCnt == 0)
                return "None";
            else{
                for (int i = 0; i < issueCnt.length; i++) {
                    if(issueCnt[i]==maxCnt)
                        return (char)i+"";
                }
            }
            return "None";
        }
    }
}
