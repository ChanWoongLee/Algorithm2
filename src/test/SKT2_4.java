package test;

import java.util.*;

public class SKT2_4 {
    public static void main(String[] args) {
        solution(8, 4, 4, new int[][]{{1, 5, 1, 3}, {5, 7, 5, 6}});
    }

    static int M;
    static ArrayList<int[]> ans;
    static int[] nowRecord;
    static Integer[] nowPlace;
    static int nowPlaceDiff;

    static public int[] solution(int n, int m, int k, int[][] records) {
        M = m;
        ans = new ArrayList<>();
        for (int i = 0; i < records.length; i++) {
            nowRecord = records[i];
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < records[i].length; j++) {
                set.add(records[i][j]);
            }
            nowPlace = set.toArray(new Integer[0]);
            Arrays.sort(nowPlace);
            nowPlaceDiff = 0;
            for (int j = 0; j < nowPlace.length - 1; j++) {
                nowPlaceDiff += nowPlace[j + 1] - nowPlace[j];
            }
            int[] temp = new int[nowPlace.length];
            if (i == 0) {
                dfs(1, 0, nowPlace.length, temp);
            } else {
                for (int j = 0; j < ans.size(); j++) {
                    if (!check(ans.get(j))) {
                        ans.remove(j);
                        j--;
                    }
                }
            }
        }
        Collections.sort(ans, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                String o1s = "";
                String o2s = "";
                for (int o : o1) {
                    o1s += String.valueOf(o);
                }
                for (int o : o2) {
                    o2s += String.valueOf(0);
                }
                return o1s.compareTo(o2s);
            }
        });

        int[] first = ans.get(0);

        nowRecord = records[0];
        HashSet<Integer> set = new HashSet<>();
        for (int j = 0; j < records[0].length; j++) {
            set.add(records[0][j]);
        }
        nowPlace = set.toArray(new Integer[0]);
        Arrays.sort(nowPlace);
        HashMap<Integer,Integer> tempMap = new HashMap<>();
        for (int i = 0; i < nowPlace.length; i++) {
            tempMap.put(nowPlace[i],first[i]);
        }
        int[] answer = new int[nowRecord.length];
        for (int i = 0; i < nowRecord.length; i++) {
            answer[i] = tempMap.get(nowRecord[i]);
        }

        return answer;
    }

    static boolean check(int[] temp) {
        int tempDiff = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            tempDiff += temp[i + 1] - temp[i];
        }
        if (nowPlaceDiff < tempDiff)
            return false;
        for (int i = 0; i < nowPlace.length; i++) {
            if (nowPlace[i] < temp[i])
                return false;
        }

        return true;
    }

    static void dfs(int index, int cnt, int maxCnt, int[] temp) {
        if (cnt == maxCnt) {
            if (check(temp)) {
                int[] save = new int[temp.length];
                for (int i = 0; i < temp.length; i++) {
                    save[i] = temp[i];
                }
                ans.add(save);
            }
            return;
        }
        for (int i = index; i <= M; i++) {
            temp[cnt] = i;
            dfs(i + 1, cnt + 1, maxCnt, temp);
        }

    }

}
