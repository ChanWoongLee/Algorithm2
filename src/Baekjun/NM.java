package Baekjun;

public class NM {
    public static void main(String[] args) {
        System.out.println("조합");
        dfs1(0, 0, 3, "");
        System.out.println("중복조합");
        dfs2(0, 0, 3, "");
        System.out.println("순열");
        dfs3(0, 0, 3, "");
        System.out.println("순열조합");
        dfs4(0, 0, 3, "");
    }

    // 조합
    static void dfs1(int index,int cnt, int maxCnt, String str){
        if (cnt == maxCnt) {
            System.out.println(str);
            return;
        }

        for(int i = index; i < 4; i++){
            String temp = str;
            dfs1(i+1,cnt+1,maxCnt,temp+i);
        }

    }

    // 중복 조합
    static void dfs2(int index,int cnt, int maxCnt, String str){
        if (cnt == maxCnt) {
            System.out.println(str);
            return;
        }

        for(int i = index; i < 4; i++){
            String temp = str;
            dfs2(index,cnt+1,maxCnt,temp+i);
        }
    }
    // 순열
    static boolean[] visit = new boolean[4];
    static void dfs3(int index,int cnt, int maxCnt, String str){
        if (cnt == maxCnt) {
            System.out.println(str);
            return;
        }

        for(int i = 0; i < 4; i++){
            String temp = str;
            if(visit[i]){
                continue;
            }
            visit[i]=true;
            dfs3(index+1,cnt+1,maxCnt,temp+i);
            visit[i] = false;
        }
    }
    // 순열 조합
    static void dfs4(int index,int cnt, int maxCnt, String str){
        if (cnt == maxCnt) {
            System.out.println(str);
            return;
        }

        for(int i = 0; i < 4; i++){
            String temp = str;
            dfs4(index,cnt+1,maxCnt,temp+i);
        }
    }
}
