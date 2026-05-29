import java.util.*;
import java.io.*;

/*
dp[i][2] = 정확히 현재 노드까지 봤을때, 0=현재노드 선택안된 상태, 1=현재노드 선택된 상태
이때의 모든 선택한 노드에 적힌 값 최대 총합
트리니까 무방향 그래프로 받기
0 += max(dp[n][0], dp[n][1])
1 += dp[n][0]
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] A;
    static ArrayList<Integer>[] g;
    static int[][] dp;
    static int max;

    static void dfs(int ci, int parent){
        for(int n : g[ci]){
            if(n == parent)
                continue;
            dfs(n, ci);
            dp[ci][0] += Math.max(dp[n][0], dp[n][1]);
            dp[ci][1] += dp[n][0]; 
        }
    }

    // 복원은 루트에서 시작해서 dp값으로 조건에 맞게 추적해나가면 될 듯
    // 최대합 경로가 여러갈래라서 dp값으로 추적안됨..
    // 부모가 선택 안된 경우만 지금 선택 가능
    static ArrayList<Integer> path = new ArrayList<>();
    static void find(int ci, int parent, boolean parentSelected){
        boolean selected = false;
        // [1]이 더 크면 현재를 선택해야함
        if(!parentSelected && dp[ci][1] > dp[ci][0]){
            selected = true;
            path.add(ci);
        }

        for(int n : g[ci]){
            if(n == parent)
                continue;
            find(n, ci, selected);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        A = new int[N+1];
        for(int i = 1; i<=N; i++){
            A[i] = read();
        }        
        g = new ArrayList[N+1];
        for(int i=1 ;i<=N; i++)
            g[i] = new ArrayList<>();

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        dp = new int[N+1][2];
        for(int i =1 ; i<=N; i++){
            dp[i][1] = A[i];
        }

        dfs(1, -1);
        max = Math.max(dp[1][0], dp[1][1]);

        find(1,-1,false);
        //정렬조건빼먹지말기
        Collections.sort(path);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n");
        for(int n : path){
            sb.append(n).append(" ");
        }
        System.out.print(sb);
    }
}