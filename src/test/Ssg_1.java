package test;

import java.util.PriorityQueue;

public class Ssg_1 {
    public static void main(String[] args) {
        solution(new int[]{4,4,3} , 2, 1);
    }
    static public int solution(int []v, int a, int b){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((c, d) -> d-c);
        int min = Integer.MAX_VALUE;
        for(int car : v){
            pq.add(car);
            if(min > car)
                min = car;
        }
        int sum = 0;
        int answer = 0;
        while(true){
            int first = pq.poll();
            int afterFirst = first - (a-b);
            if(min > afterFirst)
                min = afterFirst;
            pq.add(afterFirst);
            sum += b;
            answer++;
            if(min - sum <=0)
                break;
        }
        return answer;
    }


    static class Car implements Comparable<Car>{

        @Override
        public int compareTo(Car o) {
            return 0;
        }
    }
}
