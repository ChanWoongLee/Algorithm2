package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.sound.midi.Soundbank;

public class B21611_re_re {
	// 7 : 12 ����
	static int N;
	static int[][] map;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	static int[] moveDr = { 0, 1, 0, -1 };
	static int[] moveDc = { -1, 0, 1, 0 };
	static int[] bombCnt = { 0, 0, 0, 0 };
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			remove(dir, dis);
			collect();
			while (bomb()) {
			}
			plus();
		}
		System.out.println(answer);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void plus() {
		int sharkR = N / 2;
		int sharkC = N / 2;
		int idx = 1;
		int move = 0;
		boolean out = false;
		
		Queue<Integer> q = new LinkedList<Integer>();
		int cnt = 0;
		int color = 0;
		map[sharkR][sharkC] =123;
		while (!(sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N) && !(map[sharkR][sharkC] == 0)) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < idx; i++) {
					sharkR += moveDr[move];
					sharkC += moveDc[move];
					if (sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N || map[sharkR][sharkC] == 0) {
						out = true;
						break;
					}
					if (color != map[sharkR][sharkC]) {
						if (cnt != 0) {
							q.add(cnt);
							q.add(color);
						}
						color = map[sharkR][sharkC];
						cnt = 1;
					} else {
						cnt++;
					}
				}
				if(out)
					break;
				move = (move + 1) % 4;
			}
			if(out)
				break;
			idx++;
		}
		q.add(cnt);
		q.add(color);
		idx = 1;
		sharkR = N / 2;
		sharkC = N / 2;
		move = 0;
		out = false;
		int[][] temp = new int[N][N];
		while (!(sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N) && !q.isEmpty()) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < idx; i++) {
					sharkR += moveDr[move];
					sharkC += moveDc[move];
					if (sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N || q.isEmpty()) {
						out = true;
						break;
					}
					temp[sharkR][sharkC] = q.poll();
				}
				if(out)
					break;
				move = (move + 1) % 4;
			}
			if(out)
				break;
			idx++;
		}
		map = temp;

	}

	static boolean bomb() {
		boolean checkBomb = false;
		int sharkR = N / 2;
		int sharkC = N / 2;
		int idx = 1;
		int move = 0;
		boolean out= false;
		Deque<Integer> dq = new LinkedList<Integer>();
		int cnt = 0;
		int color = 0;
		map[N/2][N/2] = 123;
		while (!(sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N) && !(map[sharkR][sharkC] == 0)) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < idx; i++) {
					sharkR += moveDr[move];
					sharkC += moveDc[move];
					if (sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N || map[sharkR][sharkC]==0) {
						out = true;
						break;
					}
					if (color != map[sharkR][sharkC]) {
						if (cnt >= 4) {
							answer += cnt * color;
							checkBomb = true;
							while (cnt-- != 0) {
								dq.pollFirst();
							}
						}
						color = map[sharkR][sharkC];
						cnt = 1;
					} else {
						cnt++;
					}
					
					dq.addFirst(map[sharkR][sharkC]);
				}
				if(out)
					break;
				move = (move + 1) % 4;
			}
			if(out)
				break;
			idx++;
		}
		if (cnt >= 4) {
			answer += cnt * color;
			checkBomb = true;
			while (cnt-- != 0) {
				dq.pollFirst();
			}
		}
		idx = 1;
		sharkR = N / 2;
		sharkC = N / 2;
		out = false;
		move = 0;
		int[][] temp = new int[N][N];
		while (!(sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N) && !dq.isEmpty()) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < idx; i++) {
					sharkR += moveDr[move];
					sharkC += moveDc[move];
					if (sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N || dq.isEmpty()) {
						out= true;
						break;
					}
					temp[sharkR][sharkC] = dq.pollLast();
				}
				if(out)
					break;
				move = (move + 1) % 4;
			}
			if(out)
				break;
			idx++;
		}
		map = temp;

		return checkBomb;
	}

	static void collect() {
		int sharkR = N / 2;
		int sharkC = N / 2;
		int idx = 1;
		int move = 0;
		boolean out = false;
		Queue<Integer> q = new LinkedList<Integer>();
		while (!(sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N)) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < idx; i++) {
					sharkR += moveDr[move];
					sharkC += moveDc[move];
					if (sharkR < 0 || sharkC < 0 || sharkR >= N || sharkC >= N) {
						out = true;
						break;
					}
					if (map[sharkR][sharkC] != 0) {
						q.add(map[sharkR][sharkC]);
					}
				}
				if(out)
					break;
				move = (move + 1) % 4;
			}
			if(out)
				break;
			idx++;
		}

		sharkR = N / 2;
		sharkC = N / 2;
		idx = 1;
		move = 0;
		out = false;
		int[][] temp = new int[N][N];
		while (!q.isEmpty()) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < idx; i++) {
					sharkR += moveDr[move];
					sharkC += moveDc[move];
					if (q.isEmpty()) {
						out = true;
						break;
					}
					temp[sharkR][sharkC] = q.poll();
				}
				if(out)
					break;
				move = (move + 1) % 4;
			}
			if(out)
				break;
			idx++;
		}
		map = temp;
	}
	
	static void remove(int dir, int dis) {
		int sharkR = N / 2;
		int sharkC = N / 2;
		for (int i = 0; i < dis; i++) {
			sharkR += dr[dir];
			sharkC += dc[dir];
			map[sharkR][sharkC] = 0;
		}
	}
}
