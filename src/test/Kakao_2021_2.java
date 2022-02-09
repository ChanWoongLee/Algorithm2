package test;

public class Kakao_2021_2 {
    public static void main(String[] args) {
        solution(437674,3);
    }
    static public int solution(int n, int k) {
        int answer = -1;
        System.out.println(trans(n,k));
        return answer;
    }

    static public String trans(int n, int k) {
        StringBuffer stb = new StringBuffer();
        while (true) {
            if(n < k){
                stb.insert(0,n);
                break;
            }
            int remain = n % k;
            int value = n / k;
            stb.insert(0,remain);
            n = value;
        }
        return stb.toString();
    }
}
