package test;

import java.util.*;

public class SKT2_2 {
    public static void main(String[] args) {
        solution(new String[]{"1", "1", "1", "1", "1", "1", "1"}, new String[]{"write 1 12 1 5 8", "read 2 3 0 2", "read 5 5 1 2", "read 7 5 2 5", "write 13 4 0 1 3", "write 19 3 3 5 5", "read 30 4 0 6", "read 32 3 1 5"});

    }

    static public String[] solution(String[] arr, String[] processes) {
        ArrayList<ansInfo> ans = new ArrayList<>();
        PriorityQueue<Process> wait = new PriorityQueue<>();
        Queue<RunInfo> run = new LinkedList<>();
        P[] pro = new P[processes.length];
        for (int i = 0; i < processes.length; i++) {
            String[] parse = processes[i].split(" ");
            pro[i] = new P(parse[0], Integer.parseInt(parse[1]), Integer.parseInt(parse[2]), Integer.parseInt(parse[3]), Integer.parseInt(parse[4]), 0);
            if (parse[0].equals("write")) {
                pro[i].c = Integer.parseInt(parse[5]);
            }
        }

        int totalTime = 0;
        int time = 1;
        int index = 0;
        int finishCnt = 0;
        while (true) {
            for (int i = index; i < processes.length; i++) {
                String action = pro[i].action;
                int start = pro[i].start;
                if (start == time) {
                    wait.add(new Process(start, i, action));
                } else if (start > time) {
                    break;
                }
            }

            if (!wait.isEmpty()) {
                String initAction = wait.peek().action;
                if (initAction.equals("write") && run.size() == 0) {
                    run.add(new RunInfo(time, wait.poll().index));
                } else {
                    if (run.size() != 0 && pro[run.peek().idx].action.equals("write")) {
                    } else {
                        while (!wait.isEmpty()) {
                            if ("read".equals(wait.peek().action)) {
                                run.add(new RunInfo(time, wait.poll().index));
                            } else
                                break;
                        }
                    }
                }
            }

            // 지금시간 실행 ㄱㄱ
            if (run.size() != 0) {
                int runSize = run.size();
                for (int i = 0; i < runSize; i++) {
                    RunInfo info = run.poll();
                    if (pro[info.idx].play + info.startTime - 1 == time) {
                        String input = "";
                        for (int a = pro[info.idx].a; a <= pro[info.idx].b; a++) {
                            if (pro[info.idx].action.equals("read")) {
                                input = input + arr[a];
                            } else {
                                arr[a] = String.valueOf(pro[info.idx].c);
                            }
                        }
                        if (pro[info.idx].action.equals("read")) {
                            ans.add(new ansInfo(info.idx, input));
                        }
                        finishCnt++;
                    } else {
                        run.add(info);
                    }
                }
                totalTime++;
            }
            if (finishCnt == processes.length) {
                break;
            }
            time++;
        }
        Collections.sort(ans);
        String[] answer = new String[ans.size() + 1];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i).str;
        }
        answer[ans.size()] = String.valueOf(totalTime);
        return answer;
    }

    static class RunInfo {
        int startTime, idx;


        public RunInfo(int startTime, int idx) {
            this.startTime = startTime;
            this.idx = idx;
        }
    }

    static class ansInfo implements Comparable<ansInfo> {
        int idx;

        public ansInfo(int idx, String str) {
            this.idx = idx;
            this.str = str;
        }

        String str;

        @Override
        public int compareTo(ansInfo o) {
            return this.idx - o.idx;
        }
    }

    static class P {
        String action;
        int start;
        int play;
        int a;
        int b;
        int c;

        public P(String action, int start, int play, int a, int b, int c) {
            this.action = action;
            this.start = start;
            this.play = play;
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static class Process implements Comparable<Process> {
        int start;
        int index;
        String action;


        public Process(int start, int index, String action) {
            this.start = start;
            this.index = index;
            this.action = action;
        }

        @Override
        public int compareTo(Process o) {
            if (this.action.equals(o.action)) {
                return this.start - o.start;
            } else {
                if (this.action.equals("write"))
                    return -1;
                else
                    return 1;
            }
        }
    }
}
