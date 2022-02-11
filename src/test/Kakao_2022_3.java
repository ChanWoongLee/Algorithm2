package test;

import java.util.*;

public class Kakao_2022_3 {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("0010"));
        int [] ans = solution(new int[]{1, 10, 1, 11},
                new String[]{"23:58 1234 IN"});
        Arrays.stream(ans).boxed().forEach(System.out::println);
                //.stream().
        // forEach(a-> System.out.println(a));
    }

    static public class Info {
        int totalTime;
        String carNum;
        boolean isIn;
        int startTime;

        public Info(int totalTime, String carNum, boolean isIn, int startTime) {
            this.totalTime = totalTime;
            this.carNum = carNum;
            this.isIn = isIn;
            this.startTime = startTime;
        }


    }

    static public int[] solution(int[] fees, String[] records) {
        Map<String, Info> map = new HashMap<String, Info>();
        for (String str : records) {
            String[] parse = str.split(" ");
            String carNum = parse[1];
            int nowTime = transTime(parse[0]);
            Info info = map.getOrDefault(carNum, new Info(0, carNum, true, nowTime));
            if (parse[2].equals("IN")) {
                info.startTime = nowTime;
                info.isIn = true;
            } else {
                info.totalTime = nowTime - info.startTime;
                info.isIn = false;
            }
            map.put(carNum, info);
        }
        ArrayList<Info> ar = new ArrayList<>(map.values());
        return ar.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.carNum))).mapToInt(info -> {
            if (info.isIn) {
                info.totalTime += transTime("23:59") - info.startTime;
            }
            if (info.totalTime <= fees[0]) {
                return fees[1];
            } else {
                return fees[1] +  (int)Math.ceil((info.totalTime - fees[0]) / (double)fees[2]) * fees[3];
            }
        }).toArray();
    }

    static public int transTime(String time) {
        String[] parse = time.split(":");
        return Integer.parseInt(parse[0]) * 60 + Integer.parseInt(parse[1]);
    }

}
