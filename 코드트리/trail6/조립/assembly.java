import java.util.*;
import java.io.*;

/*
입력
중간 조각 번호, 해당 조각 만드는데 필요한 작은 조각 종류, 그 작은 종류 필요 갯수

출력은 n번째 완성된 조각을 완성하는데 필요한 각 가장 작은 조각의 번호와 갯수 
가장 작은 조각의 번호가 작은 것부터 출력..
-> 기본 조각
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] indeg;
    static ArrayList<int[]>[] g;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i= 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        while(M-->0){
            int a = read();
            int b = read();
            int w = read();
            // a를 완성하려면 b가 w개 필요함
            g[b].add(new int[]{a,w});
            indeg[a]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> minPart = new ArrayList<>();
        // dp[i][j] = i번째 조각을 만들 때 필요한 기본부품 j의 갯수 (N+1*N+1)
        int[][] dp = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0){
                q.add(i);
                // 기본조각은 1개만 필요
                dp[i][i] = 1;
                minPart.add(i);
            }
        }

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int[] n : g[ci]){
                int ni = n[0];
                int nw = n[1];
                // ni 부품을 만들기 위해 ci 부품이 nw개만큼 필요함
                // dp[ci]의 int 배열은 ni를 만들기 위한 조립도니까 거기 모두한테 nw 곱해서 더해줘야함
                for(int k= 1; k<=N; k++){
                    dp[ni][k] += dp[ci][k]*nw;
                }

                if(--indeg[ni] == 0){
                    q.add(ni);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int x : minPart){
            if(dp[N][x] > 0)
                sb.append(x).append(" ").append(dp[N][x]).append("\n");
        }
        System.out.print(sb);
    }
}