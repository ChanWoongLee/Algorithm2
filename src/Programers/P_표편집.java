package Programers;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class P_표편집 {
    public static void main(String[] args) {
       System.out.println(solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
    }

    static public String solution(int n, int k, String[] cmd) {
        String answer = "";
        ArrayList<Node> nodes = new ArrayList<>();
        Node node = new Node(null, null, 0);
        nodes.add(node);
        for (int i = 1; i < n; i++) {
            Node next = new Node(node, null, i);
            node.next = next;
            nodes.add(next);
            node = next;
        }
        int nowIdx = k;
        Stack<Node> stack = new Stack<>();
        for (String str : cmd) {
            if (str.equals("C")) {
                Node nowNode = nodes.get(nowIdx);
                stack.add(nowNode);
                if (nowIdx ==  nodes.size()-1) {
                    nowNode.prev.next = null;
                }else if(nowIdx == 0){
                    nowNode.next.prev = null;
                }else{
                    nowNode.prev.next = nowNode.next;
                    nowNode.next.prev = nowNode.prev;
                }
                if (nowIdx == nodes.size()-1) {
                    nowIdx -= 1;
                }
                nodes.remove(nowIdx);
            } else if (str.equals("Z")) {
                Node nowNode = stack.pop();
                int addIdx = nodes.indexOf(nowNode.prev) +1;
                if (addIdx ==  nodes.size()) {
                    nowNode.prev.next = nowNode;
                }else if(addIdx == 0){
                    nowNode.next.prev = nowNode;
                }else{
                    nowNode.prev.next = nowNode;
                    nowNode.next.prev = nowNode;
                }
                nodes.add(addIdx, nowNode);
            } else {
                String[] strParse = str.split(" ");
                String command = strParse[0];
                int num = Integer.parseInt(strParse[1]);
                if (command.equals("U")) {
                    nowIdx -= num;
                } else if (command.equals("D")) {
                    nowIdx += num;
                }
            }
        }

        String[] temp = new String[n];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = "X";
        }
        for (Node node1 : nodes) {
            temp[node1.value] = "O";
        }
        for (String s : temp) {
            answer += s;
        }
        return answer;
    }

    static class Node {
        Node prev;
        Node next;
        int value;

        public Node(Node prev, Node next, int value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
}
