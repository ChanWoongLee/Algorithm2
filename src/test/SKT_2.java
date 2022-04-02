package test;

public class SKT_2 {
    public static void main(String[] args) {
        solution(3, false);
    }

    static int[][] answer;

    static public int[][] solution(int n, boolean clockwise) {
        answer = new int[n][n];
        int value = 1;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            value = draw(start++, end--, value);
        }
        int[][] temp = new int[n][n];
        if (!clockwise) {
            int r = 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    temp[r][j] = answer[i][j];
                }
                r++;
            }
            answer = temp;
        }

        for (int ii = 0; ii < n; ii++) {
            for (int j = 0; j < n; j++) {
                System.out.print(answer[ii][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        return answer;
    }

    static int draw(int start, int end, int value) {
        if (start == end) {
            answer[start][end] = value;
            return value;
        }
        int initValue = value;
        for (int i = start; i < end; i++) {
            answer[start][i] = initValue;
            answer[i][end] = initValue;
            initValue++;
        }
        initValue = value;
        for (int i = end; i > start; i--) {
            answer[end][i] = initValue;
            answer[i][start] = initValue;
            initValue++;
        }
        return initValue;
    }
}
