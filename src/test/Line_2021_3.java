package test;

import java.util.ArrayList;
import java.util.HashMap;

public class Line_2021_3 {
    public static void main(String[] args) {

    }

    public int[] solution(int[][] jobs) {
        int[] a = new int[1000001];
        int[] c = new int[101];
        for (int i = 1; i <= jobs.length; i++) {
            a[jobs[i - 1][0]] = i;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> ready = new HashMap<>();
        for (int i = 1; i < a.length; i++) {
            if (a[i] != 0) {
                int workTime = jobs[a[i]][1];
                int nowSort = jobs[a[i]][2];
                ans.add(nowSort);
                for (int start = i + 1; start <= i + workTime; start++) {
                    int nextWorkTime = jobs[a[start]][1];
                    int nextSort = jobs[a[start]][2];
                    int nextPrio = jobs[a[start]][3];
                    if (a[start] != 0) {
                        if (nextSort == nowSort) {
                            workTime += nextWorkTime;
                        } else {
                            if (ready.containsKey(nextSort)) {
                                ready.put(nextSort, ready.get(nextSort) + nextPrio);
                            } else {
                                ready.put(nextSort, nextPrio);
                            }
                        }
                    }
                    if (start == i + workTime) {
                        i = i + workTime;
                    }
                }

            }
        }
        int[] answer = {};
        return answer;
    }

}
