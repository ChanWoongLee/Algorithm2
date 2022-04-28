package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWE_4014 {
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };
	static ArrayList<Power>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int testCase = 0; testCase < t; testCase++) {
			map = new ArrayList[10][10];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = new ArrayList<Power>();
				}
			}

			st = new StringTokenizer(bf.readLine());
			int M = Integer.parseInt(st.nextToken());
			int center = Integer.parseInt(st.nextToken());

			int[] A = new int[M + 1];
			int[] B = new int[M + 1];
			String[] str = bf.readLine().split(" ");
			for (int i = 1; i <= str.length; i++) {
				A[i] = Integer.parseInt(str[i - 1]);
			}
			str = bf.readLine().split(" ");
			for (int i = 1; i <= str.length; i++) {
				B[i] = Integer.parseInt(str[i - 1]);
			}
			for (int i = 0; i < center; i++) {
				str = bf.readLine().split(" ");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int c = Integer.parseInt(str[2]);
				int p = Integer.parseInt(str[3]);
				bfs(i, x-1, y-1, c, p);
			}
			int aX = 0;
			int aY = 0;
			int bX = 9;
			int bY = 9;
			int answer = 0;
			for (int i = 0; i < A.length; i++) {
				aX += dx[A[i]];
				aY += dy[A[i]];
				bX += dx[B[i]];
				bY += dy[B[i]];

				Collections.sort(map[aY][aX]);
				Collections.sort(map[bY][bX]);

				if (map[aY][aX].size() == 0 && map[bY][bX].size() != 0) {
					answer += map[bY][bX].get(0).p;
				} else if (map[aY][aX].size() != 0 && map[bY][bX].size() == 0) {
					answer += map[aY][aX].get(0).p;
				} else if (map[aY][aX].size() != 0 && map[bY][bX].size() != 0) {
					if (map[aY][aX].get(0).idx != map[bY][bX].get(0).idx) { // ���� ���� ���� ���Ҷ� �׳� ���ϸ鵩
						answer += map[aY][aX].get(0).p + map[bY][bX].get(0).p;
					} else { // ���� �����Ҷ�
						int maxSum = map[aY][aX].get(0).p;

						if (map[aY][aX].size() > 1) {
							maxSum = Math.max(maxSum, map[aY][aX].get(1).p + map[bY][bX].get(0).p);
						}

						if (map[bY][bX].size() > 1) {
							maxSum = Math.max(maxSum, map[bY][bX].get(1).p + map[aY][aX].get(0).p);
						}
						answer += maxSum;

					}
				}
			}
			System.out.println("#" + testCase + " " + answer);
		}
	}

	static void bfs(int idx, int x, int y, int c, int p) {
		boolean[][] visit = new boolean[10][10];
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(x, y));
		visit[y][x] = true;
		map[y][x].add(new Power(idx, p));

		for (int loop = 0; loop < c; loop++) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Node now = q.poll();
				for (int move = 0; move < 5; move++) {
					int nextX = now.x + dx[move];
					int nextY = now.y + dy[move];
					if (nextX >= 10 || nextX < 0 || nextY >= 10 || nextY < 0)
						continue;
					if (visit[nextY][nextX])
						continue;
					map[nextY][nextX].add(new Power(idx, p));
					visit[nextY][nextX] = true;
					q.add(new Node(nextX, nextY));
				}
			}
		}
	}

	static class Power implements Comparable<Power> {
		int idx, p;

		public Power(int idx, int p) {
			super();
			this.idx = idx;
			this.p = p;
		}

		@Override
		public int compareTo(Power o) {
			return o.p - this.p;
		}

	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
