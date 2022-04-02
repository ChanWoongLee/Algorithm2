package test;

import java.lang.reflect.Array;
import java.util.*;

public class SKT2_1 {
    public static void main(String[] args) {
        String a = "1234";
        System.out.println(a.substring(0, 1));
        String[] str = solution(new String[]{"pencil", "cilicon", "contrabase", "picturelist"});
        for (String s : str) {
            System.out.println(s);
        }
    }

    static public String[] solution(String[] goods) {
        ArrayList<String> dup = new ArrayList<>();
        Map<String, Integer> key = new LinkedHashMap<>();
        int index = 0;
        for (String good : goods) {
            int slice = 1;
            while (good.length() + 1 != slice) {
                for (int i = 0; i <= good.length() - slice; i++) {
                    String part = good.substring(i, i + slice);
                    //System.out.println(part);
                    if (dup.contains(part))
                        continue;
                    if (key.containsKey(part) && key.get(part) != index) {
                        key.remove(part);
                        dup.add(part);
                    } else {
                        key.put(part, index);
                    }
                }
                slice++;
            }
            index++;
        }
        int[] minLen = new int[goods.length];
        Arrays.fill(minLen, Integer.MAX_VALUE);

        for (String part : key.keySet()) {
            int idx = key.get(part);
            minLen[idx] = Math.min(minLen[idx], part.length());
        }
        ArrayList<Item> ans = new ArrayList<>();
        for (String part : key.keySet()) {
            int idx = key.get(part);
            if (minLen[idx] == part.length()) {
                ans.add(new Item(part, idx));
            }
        }
        Collections.sort(ans);
        String[] answer = new String[goods.length];
        Arrays.fill(answer, "None");
        for (Item item : ans) {
            if (answer[item.index].equals("None")) {
                answer[item.index] = item.part;
            } else {
                answer[item.index] = answer[item.index] + " " + item.part;
            }
        }

        return answer;
    }

    static class Item implements Comparable<Item> {
        public String part;
        int index;

        public Item(String part, int index) {
            this.part = part;
            this.index = index;
        }

        @Override
        public int compareTo(Item o) {
            if (this.index == o.index) {
                return this.part.compareTo(o.part);
            } else {
                return this.index - o.index;
            }
        }
    }
}
