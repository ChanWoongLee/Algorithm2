package test;

import java.util.*;

public class Kakao_2022_1_re {
    public static void main(String[] args) {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("11");
        System.out.println(ar.contains("11"));
    }
    // 동시에 신고할 수 없다. -> 중복을 허락하지 않는 SET 성질
    // 정보의 연결 및 공간과 변수의 활용
    public int[] solution(String[] id_list, String[] report, int k) {
        // 스트림은 컬렉션과 어레이에서 받아올수있다.
        // 1. Arrays.stream( array )
        // 2. Arrays.stream()
        Map<String, ArrayList<String>> hm = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for(String str : report){
            String[] parse = str.split(" ");
            ArrayList<String> idList = hm.getOrDefault(parse[0],new ArrayList<>());
            if(!idList.contains(parse[1])){
                idList.add(parse[1]);
                hm.put(parse[0],idList);
                count.put(parse[1],count.getOrDefault(parse[1],0)+1);
            }
        }
        Map<String, Integer> result = new LinkedHashMap<>();
        for (String id : id_list) {
            result.put(id,0);
        }

        for(String str : hm.keySet()){
            for(String reporter : hm.getOrDefault(str,new ArrayList<>())){
                if(count.getOrDefault(reporter,0) >= k){
                    result.put(str, result.get(str)+1);
                }
            }
        }
        return result.values().stream().mapToInt(Integer::intValue).toArray();
    }
}
