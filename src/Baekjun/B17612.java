package Baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B17612 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Customer> wait = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            wait.add(new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
        }
        PriorityQueue<Customer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            if(wait.isEmpty())
                break;
            Customer customer = wait.poll();
            customer.seq = i;
            pq.add(customer);
        }
        ArrayList<Integer> answer = new ArrayList<>();
        Stack<Integer> out = new Stack<>();

        while (!wait.isEmpty()) {
            int nowTime = pq.peek().time;
            while (!pq.isEmpty() && pq.peek().time == nowTime) {
                Customer customer = pq.poll();
                out.add(customer.seq);
                answer.add(customer.id);
            }
            while (!wait.isEmpty() && pq.size() != k) {
                Customer customer = wait.poll();
                customer.time += nowTime;
                customer.seq = out.pop();
                pq.add(customer);
            }
        }
        while (!pq.isEmpty()) {
            answer.add(pq.poll().id);
        }
        long ans = 0;
        for (int i = 0; i < answer.size(); i++) {
            ans += (long) (i + 1) * answer.get(i);
        }
        System.out.println(ans);
    }

    static class Customer implements Comparable<Customer> {
        int id, time, seq;

        public Customer(int id, int time, int seq) {
            this.id = id;
            this.time = time;
            this.seq = seq;
        }


        @Override
        public int compareTo(Customer o) {
            if (this.time == o.time) {
                return o.seq - this.seq;
            } else {
                return this.time - o.time;
            }
        }
    }
}
