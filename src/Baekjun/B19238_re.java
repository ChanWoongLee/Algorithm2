package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B19238_re {
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[][] custMap;
	static Map<Integer, Cust> custInfo;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		custMap = new int[N][N];
		custInfo = new HashMap<Integer, Cust>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(bf.readLine());
		Taxi taxi = new Taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, fuel, 0);
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(bf.readLine());
			custInfo.put(i, new Cust(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
			Cust cust = custInfo.get(i);
			custMap[cust.sR][cust.sC] = i;
		}
		while (true) {
			if (!findCust(taxi)) {
				System.out.println("-1");
				return;
			}
			// System.out.println(taxi.r + " " + taxi.c + " " + taxi.fuel);
			// printCustMap();
			if (!goDest(taxi)) {
				System.out.println("-1");
				return;
			}
			// System.out.println(taxi.r + " " + taxi.c + " " + taxi.fuel);
			if (checkCustMap()) {
				break;
			}
		}
		System.out.println(taxi.fuel);
	}

	static void printCustMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(custMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static boolean checkCustMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (custMap[i][j] != 0)
					return false;
			}
		}
		return true;
	}

	static boolean goDest(Taxi taxi) {
		int destR = custInfo.get(taxi.custIdx).dR;
		int destC = custInfo.get(taxi.custIdx).dC;

		Queue<Node> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		Node now = new Node(taxi.r, taxi.c);
		visit[now.r][now.c] = true;
		q.add(now);

		int fuel = taxi.fuel;
		while (!q.isEmpty()) {
			fuel--;
			if (fuel < 0)
				return false;

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node node = q.poll();
				for (int move = 0; move < 4; move++) {
					int nextR = node.r + dr[move];
					int nextC = node.c + dc[move];
					if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0)
						continue;
					if (map[nextR][nextC] == 1 || visit[nextR][nextC])
						continue;

					if (nextR == destR && nextC == destC) {
						taxi.r = destR;
						taxi.c = destC;
						taxi.fuel = fuel + (taxi.fuel - fuel) * 2;
						return true;
					}

					visit[nextR][nextC] = true;
					q.add(new Node(nextR, nextC));
				}
			}
		}

		return false;
	}

	static boolean findCust(Taxi taxi) {
		if (custMap[taxi.r][taxi.c] != 0) {
			taxi.custIdx = custMap[taxi.r][taxi.c];
			custMap[taxi.r][taxi.c] = 0;
			return true;
		}

		Queue<Node> q = new LinkedList<>();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visit = new boolean[N][N];
		Node now = new Node(taxi.r, taxi.c);
		visit[now.r][now.c] = true;
		q.add(now);

		int fuel = taxi.fuel;
		while (!q.isEmpty()) {
			fuel--;
			if (fuel <= 0)
				return false;

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node node = q.poll();
				for (int move = 0; move < 4; move++) {
					int nextR = node.r + dr[move];
					int nextC = node.c + dc[move];
					if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0)
						continue;
					if (map[nextR][nextC] == 1 || visit[nextR][nextC])
						continue;
					visit[nextR][nextC] = true;
					q.add(new Node(nextR, nextC));
					if (custMap[nextR][nextC] != 0) {
						pq.add(new Node(nextR, nextC));
					}
				}
			}
			if (!pq.isEmpty()) {
				now = pq.poll();
				taxi.r = now.r;
				taxi.c = now.c;
				taxi.fuel = fuel;
				taxi.custIdx = custMap[now.r][now.c];
				custMap[now.r][now.c] = 0;
				return true;
			}
		}
		return false;
	}

	static class Node implements Comparable<Node> {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			if (this.r == o.r)
				return this.c - o.c;
			else
				return this.r - o.r;
		}

	}

	static class Cust {
		int sR, sC, dR, dC;

		public Cust(int sR, int sC, int dR, int dC) {
			super();
			this.sR = sR;
			this.sC = sC;
			this.dR = dR;
			this.dC = dC;
		}

	}

	static class Taxi {
		int r, c, fuel, custIdx;

		public Taxi(int r, int c, int fuel, int custIdx) {
			super();
			this.r = r;
			this.c = c;
			this.fuel = fuel;
			this.custIdx = custIdx;
		}

	}
}
