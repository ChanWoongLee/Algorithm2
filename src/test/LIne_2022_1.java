package test;

public class LIne_2022_1 {
    public static void main(String[] args) {
        String str = "awer:";
        String[] pa = str.split(":");
        System.out.println(solution(new String[]{"team_name : db application_name : dbtest error_level : info message : test", "team_name : test application_name : I DONT CARE error_level : error message : x", "team_name : ThisIsJustForTest application_name : TestAndTestAndTestAndTest error_level : test message : IAlwaysTestingAndIWillTestForever", "team_name : oberervability application_name : LogViewer error_level : error"}));
    }

    static String[] log = {"team_name", "application_name", "error_level", "message"};
    static int[] checkParse = {2, 5, 8, 11};
    static int[] checkParse2 = {1, 4, 7, 10};
    static int[] checkParse3 = {0, 3, 6, 9};

    public static boolean check(String value) {
        for (int i = 0; i < value.length(); i++) {
            String c = String.valueOf(value.charAt(i));
            if (c.matches("[^a-zA-Z0-9\\s]")) { // 특수문자 인 경우
                if (!c.equals(":"))
                    return false;
            }
            if (Character.isDigit(value.charAt(i)))
                return false;
        }
        return true;
    }

    static public int solution(String[] logs) {
        int answer = 0;
        for (String str : logs) {
            String[] parse = str.split(" ");
            if (parse.length != 12 || str.length() > 100 || !str.trim().equals(str)) {
                continue;
            }
            boolean isValid = true;

            for (Integer idx : checkParse) {
                if (!check(parse[idx])) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                continue;
            }
            for (Integer idx : checkParse2) {
                if (!parse[idx].equals(":")) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                continue;
            }
            int i = 0;
            for (Integer idx : checkParse3) {
                if (!log[i++].equals(parse[idx])) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                continue;
            }


            answer++;
        }
        return logs.length - answer;
    }
}