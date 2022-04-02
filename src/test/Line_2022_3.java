package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Line_2022_3 {
    public static void main(String[] args) {
        solution(3, new String[]{"development", "marketing", "hometask"}, new String[]{"recruitment", "education", "officetask"}, new String[]{"1 development hometask", "1 recruitment marketing", "2 hometask", "2 development marketing hometask", "3 marketing", "3 officetask", "3 development"})
        ;
    }

    static public int[] solution(int num_teams, String[] remote_tasks, String[] office_tasks, String[] employees) {
        ArrayList<String> remote = new ArrayList<>(Arrays.asList(remote_tasks));
        ArrayList<String> office = new ArrayList<>(Arrays.asList(office_tasks));
        int idx = 1;
        PriorityQueue<Emp> pq = new PriorityQueue<>();
        PriorityQueue<Emp> pq2 = new PriorityQueue<>();
        for (String employee : employees) {
            String[] parse = employee.split(" ");
            boolean isRemote = true;
            for (int i = 1; i < parse.length; i++) {
                if (office.contains(parse[i]))
                    isRemote = false;
            }
            if (isRemote)
                pq.add(new Emp(idx++, Integer.parseInt(parse[0]), isRemote));
            else
                pq2.add(new Emp(idx++, Integer.parseInt(parse[0]), isRemote));
        }
        boolean[] visit = new boolean[num_teams + 1];
        ArrayList<Integer> ar = new ArrayList<>();
        while (!pq2.isEmpty()) {
            Emp now = pq2.poll();
            if (visit[now.temp]) {
                ar.add(now.num);
                continue;
            } else {
                ar.add(now.num);
                visit[now.temp] = true;
            }
        }
        while (!pq.isEmpty()) {
            Emp now = pq2.poll();
            if (visit[now.temp]) {
                ar.add(now.num);
                continue;
            }
            else{
                visit[now.temp] = true;
            }
        }
        int[] answer = new int[ar.size()];
        for (int i = 0; i < ar.size(); i++) {
            answer[i] = ar.get(i);
        }
        return answer;
    }

    static class Emp implements Comparable<Emp> {
        int num;
        int temp;
        boolean remote;

        public Emp(int num, int temp, boolean remote) {
            this.num = num;
            this.temp = temp;
            this.remote = remote;
        }

        @Override
        public int compareTo(Emp o) {
            return this.num - o.num;
        }
    }
}
