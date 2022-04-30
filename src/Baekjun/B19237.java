package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B19237 {
	static int N;
	static int[][] map;
	static int[][] smell;
	static int[][] smellOwner;
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, -1, 1, 0, 0 };
	static HashMap<Integer, Shark> sharks;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		smell = new int[N][N];
		smellOwner = new int[N][N];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(bf.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		sharks = new HashMap<Integer, Shark>();
		int[] sharkDIr = new int[M + 1];
		st = new StringTokenizer(bf.readLine());
		for (int i = 1; i <= M; i++) {
			sharkDIr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= M; i++) {
			int[][] move = new int[4][4];
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(bf.readLine());
				for (int k = 0; k < 4; k++) {
					move[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			sharks.putIfAbsent(i, new Shark(sharkDIr[i], move));
		}
		int answer = 0;
		while (sharks.size() != 1) {
			if (answer == 1000)
				System.out.println("-1");
			answer++;
			emit(K);
			smellDown();
			moveShark();
			printMap();
		}
		System.out.println(answer);
	}

	static void printMap() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void moveShark() {
		int[][] temp = new int[N][N];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] != 0) {
					int idx = map[y][x];
					Shark shark = sharks.get(idx);
					int dir = shark.dir;
					int[] moveDir = shark.moveLike[dir];
					boolean find = false;
					// ��� ã��
					for (int move = 0; move < 4; move++) {
						int nextY = y + dy[moveDir[move]];
						int nextX = x + dx[moveDir[move]];
						if (nextY >= N || nextX >= N || nextY < 0 || nextX < 0)
							continue;
						if (smell[nextY][nextX] == 0)
							continue;
						// ������
						shark.dir = move;
						if (temp[nextY][nextX] == 0)
							temp[nextY][nextX] = idx;
						else if (temp[nextY][nextX] > idx) {
							sharks.remove(temp[nextY][nextX]);
							temp[nextY][nextX] = idx;
						} else {
							sharks.remove(idx);
						}
						find = true;
						break;
					}
					if (find)
						continue;
					for (int move = 0; move < 4; move++) {
						int nextY = y + dy[moveDir[move]];
						int nextX = x + dx[moveDir[move]];
						if (nextY >= N || nextX >= N || nextY < 0 || nextX < 0)
							continue;
						if (smellOwner[nextY][nextX] != idx)
							continue;
						// �� ä�뿡�� ������ �ٸ���� ������� �׳� �̵�
						shark.dir = move;
						temp[nextY][nextX] = idx;
					}
				}
			}
		}
		map = temp;
	}

	static void smellDown() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (smell[y][x] != 0) {
					smell[y][x] -= 1;
					if (smell[y][x] == 0)
						smellOwner[y][x] = 0;
				}
			}
		}
	}

	static void emit(int k) {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] != 0) {
					smell[y][x] = k;
					smellOwner[y][x] = map[y][x];
				}
			}
		}
	}

	static class Shark {
		int dir;
		int[][] moveLike;

		public Shark(int dir, int[][] moveLike) {
			super();
			this.dir = dir;
			this.moveLike = moveLike;
		}

	}
}
