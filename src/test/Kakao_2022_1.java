package test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Kakao_2022_1 {
    public static void main(String[] args) {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("11");
        System.out.println(ar.contains("11"));
    }
    // 동시에 신고할 수 없다. -> 중복을 허락하지 않는 SET 성질
    // 정보의 연결 및 공간과 변수의 활용
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Integer> hm = new HashMap<>();
        int index = 0;
        for (String id : id_list) {
            hm.put(id, index++);
        }
        ArrayList<Integer>[] ar = new ArrayList[id_list.length];
        for(int i = 0; i < ar.length; i++){
            ar[i] = new ArrayList<Integer>();
        }
        int[] count = new int[id_list.length];
        for(String str : report){
            String[] parse = str.split(" ");
            String first = parse[0];
            String second = parse[1];

            int firstIndex = hm.get(first);
            int secondIndex = hm.get(second);
            if (!ar[firstIndex].contains(second)) {
                count[secondIndex]++;
                ar[firstIndex].add(secondIndex);
            }
        }
        ArrayList<Integer> reportUserIndex = new ArrayList<>();
        for(int i = 0; i < count.length; i++){
            if (count[i] >= k) {
                reportUserIndex.add(i);
            }
        }
        int[] answer = new int[id_list.length];

        for(int i = 0; i < ar.length; i++) {
            for(int secondIndex : ar[i]){
                if(reportUserIndex.contains(secondIndex)){
                    answer[i]++;
                }
            }
        }
        return answer;
    }
}
