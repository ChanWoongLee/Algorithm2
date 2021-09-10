package Baekjun;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // 내림차순
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>((a, b) -> Integer.compare(a, b)); // 오름차순
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
           if (leftPQ.size() == rightPQ.size()) {
                leftPQ.add(num);
                if (!rightPQ.isEmpty() && (leftPQ.peek() > rightPQ.peek())) {
                    rightPQ.add(leftPQ.poll());
                    leftPQ.add(rightPQ.poll());
                }
            } else {
                rightPQ.add(num);
                if (!leftPQ.isEmpty() && (rightPQ.peek() < leftPQ.peek())) {
                    rightPQ.add(leftPQ.poll());
                    leftPQ.add(rightPQ.poll());
                }
            }
            bw.write(leftPQ.peek().toString());
            bw.flush();
        }
        bw.close();
    }
}
