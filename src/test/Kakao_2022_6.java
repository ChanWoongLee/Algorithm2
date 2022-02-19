package test;

public class Kakao_2022_6 {
    public static void main(String[] args) {

    }
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] newBoard = new int[board.length + 2][board[0].length + 2];

        for (int i = 0; i < skill.length; i++) {
            int degree = skill[i][0] == 1 ? skill[i][5] : -skill[i][5];
            int r1 = skill[i][1]+1;
            int c1 = skill[i][2]+1;
            int r2 = skill[i][3]+1;
            int c2 = skill[i][4]+1;

            newBoard[r1][c1] += degree;
            newBoard[r1][c2+1] -= degree;
            newBoard[r2+1][c1] -= degree;
            newBoard[r2+1][c2+1] += degree;
        }
        for(int i =0; i < newBoard.length; i++){
            for (int j = 0; j < newBoard[0].length; j++) {

            }
        }

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        return answer;
    }
}
