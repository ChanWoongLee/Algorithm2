package test;

public class Kakao_2021_2 {

    public static void main(String[] args) {
        System.out.println(solution(110000, 10));
    }

    static public int solution(int n, int k) {
        int answer = 0;
        String num = trans(n, k);
        int left = 0;

 /*       String[] parseNum = num.split("0");
        for(String parse : parseNum){
            if(!parse.equals("") && isPrime(Long.parseLong(parse))) {
                ++answer;
            }
        }*/

        for(int i = 0; i < num.length(); i++) {
            if( num.charAt(i) == '0' && i != left){
                long part = Long.parseLong(num.substring(left, i));
                System.out.println(left + " "  + i);
                answer = isPrime(part) ? answer+1 : answer;
                left = i+1;
            }
        }
        if(left < num.length()){
            long part = Long.parseLong(num.substring(left, num.length()));
            answer = isPrime(part) ? answer+1 : answer;
        }


        return answer;
    }

    static public boolean isPrime(long num) {
        if(num == 1) {
            return false;
        } else if(num == 2) {
            return true;
        }

        int limit = (int) Math.sqrt(num);
        for(int i=2; i<=limit; ++i) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
    static public String trans(int n, int k) {
        StringBuffer stb = new StringBuffer();
        while (true) {
            if (n < k) {
                stb.insert(0, n);
                break;
            }
            int remain = n % k;
            int value = n / k;
            stb.insert(0, remain);
            n = value;
        }
        return stb.toString();
    }
}
