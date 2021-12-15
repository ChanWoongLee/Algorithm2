package Programers;

import java.util.ArrayList;
import java.util.HashMap;

public class P_뉴스클러스터링 {
    public static void main(String[] args) {
        char a = 'A';
        char b = 'Z';
        System.out.println(Integer.valueOf(a) + " " + Integer.valueOf(b));
    }
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        StringBuilder stb1 = new StringBuilder();
        for(char c : str1.toCharArray()){
            if(c >= 65 && c <= 95){
                stb1.append(c);
            }
        }
        StringBuilder stb2 = new StringBuilder();
        for(char c : str2.toCharArray()){
            if(c >= 65 && c <= 95){
                stb2.append(c);
            }
        }
        ArrayList<String> str1Parse = new ArrayList<>();
        for(int i = 0 ; i < stb1.toString().toCharArray().length - 1; i++){
            str1Parse.add(stb1.toString().toCharArray()[i]+""+stb1.toString().toCharArray()[i+1]+"");
        }
        HashMap<String, Integer> hm1 = new HashMap<>();
        for (String str : str1Parse) {
            hm1.put(str, hm1.getOrDefault(str, 1));
        }

        ArrayList<String> str2Parse = new ArrayList<>();
        for(int i = 0 ; i < stb2.toString().toCharArray().length - 1; i++){
            str2Parse.add(stb2.toString().toCharArray()[i]+""+stb2.toString().toCharArray()[i+1]+"");
        }
        HashMap<String, Integer> hm2 = new HashMap<>();
        for (String str : str2Parse) {
            hm2.put(str, hm2.getOrDefault(str, 1));
        }



        return answer;
    }
}
