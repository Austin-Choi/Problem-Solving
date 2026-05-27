import java.util.*;
import java.io.*;

/*
부모 자식이 명확하게 주어지므로 입력받은 parent 배열을 통해서
그래프를 구성하고 

i의 서브트리에 매번 다시 w를 더해줘야하는데 
N이랑 M이 둘다 최대 10만인데 
그럼 그래프 구성하고 서브트리 리스트를 구해놔도 전처리로
매번 dfs하는거나 리스트 참조해서 rst[i] += w 하나 똑같지않나..


*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] parent;
    static ArrayList<Integer>[] g;
    static int[] dp;
    static int[] rst;

    static void dfs(int ci, int tot){
        tot += dp[ci];
        rst[ci] = tot;

        for(int n : g[ci]){
            if(n == parent[ci])
                continue;
            rst[n] += tot;
            dfs(n, tot);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();

        parent = new int[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        for(int i = 1; i<=N; i++){
            parent[i] = read();
            if(parent[i] != -1){
                g[parent[i]].add(i);
                g[i].add(parent[i]);
            }
        }
        
        //1이 루트라고함
        dp = new int[N+1];
        rst = new int[N+1];
        while(M-->0){
            int i = read();
            int w = read();
            // i번 노드에 더해야할 점수 갱신
            dp[i] += w;
        }

        dfs(1,0);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            sb.append(rst[i]).append(" ");
        }
        System.out.print(sb);
    }
}