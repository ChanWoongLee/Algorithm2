package Programers;

import java.util.ArrayList;
import java.util.HashMap;

public class P_뉴스클러스터링 {
    public static void main(String[] args) {
        System.out.println(solution("FRANCE","french"));
    }
    static public boolean checkAlpah(char c){
        if(c >= 65 && c <= 95){
            return true;
        }
        return false;
    }
    static public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        ArrayList<String> str1Parse = new ArrayList<>();
        char[] str1ToChar = str1.toCharArray();
        for(int i = 0 ; i < str1ToChar.length - 1; i++){
            char first = str1ToChar[i];
            char second = str1ToChar[i+1];
            if(checkAlpah(first) && checkAlpah(second)){
                str1Parse.add(first+""+second+"");
            }
        }
        HashMap<String, Integer> hm1 = new HashMap<>();
        for (String str : str1Parse) {
            hm1.put(str, hm1.getOrDefault(str, 0) +1);
        }
        ArrayList<String> str2Parse = new ArrayList<>();
        char[] str2ToChar = str2.toCharArray();
        for(int i = 0 ; i < str2ToChar.length - 1; i++){
            char first = str2ToChar[i];
            char second = str2ToChar[i+1];
            if(checkAlpah(first) && checkAlpah(second)){
                str2Parse.add(first+""+second+"");
            }
        }
        HashMap<String, Integer> hm2 = new HashMap<>();
        for (String str : str2Parse) {
            hm2.put(str, hm2.getOrDefault(str, 0) +1);
        }

        int same = 0;
        for (String str : hm1.keySet()) {
            if(hm2.containsKey(str)){
                same += Math.min(hm1.get(str), hm2.get(str));
            }
        }
        int sum = 0;
        for (String str : hm1.keySet()) {
            if (hm2.containsKey(str)) {
                sum += Math.max(hm1.get(str), hm2.get(str));
                hm2.remove(str);
            }else{
                sum++;
            }
        }
        for (String str : hm2.keySet()) {
            sum += hm2.get(str);
        }
        return (int)(((double)same/(double)sum)*65536.0);
    }
}
