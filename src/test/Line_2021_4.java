package test;

import java.util.Arrays;

public class Line_2021_4 {
    static boolean[] checkPrime;

    public static void main(String[] args) {
        int[] num =  solution(12);
        System.out.println(num);
    }
    static public int[] solution(int n) {
        checkPrime = new boolean[n + 1];

        checkPrime[0] = true;
        checkPrime[1] = true;

        for (int i = 2; i <= n; i++) {
            if (checkPrime[i]) continue;
            for (int j = 2 * i; j <= n; j += i) {
                checkPrime[j] = true;
            }
        }
        int[] num = new int[n];
        for (int i = 1; i <= num.length; i++) {
            num[i - 1] = i;
        }
        mergeSort(num, 0, num.length - 1);

        return num;
    }

    private static void mergeSort(int[] a, int left, int right) {

        int p = 1;
        int len = right - left + 1;
        for (int i = 2; i < len; i++) {
            if (!checkPrime[i] && len % i == 0) {
                p = i;
                break;
            }
        }
        if (p == 1)
            return;

        int[] cloneA = new int[a.length];
        int index = left;
        for(int i = 0; i < p; i++){
            for(int j = left; j <= right; j+= p){
                cloneA[index++] = a[j +i];
            }
        }
        for(int i= left ; i <= right ; i++){
            a[i] = cloneA[i];
        }

        for (int i = left; i <= right; i += len/p) {

            mergeSort(a, i, i + (len / p)-1);
        }

    }


}
