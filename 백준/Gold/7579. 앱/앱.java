import java.io.*;
import java.util.*;

class Info{
    int memory;
    int cost;

    public Info(int m, int c){
        this.memory = m;
        this.cost = c;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

public class Main {
    private static int N,M;
    private static Info[] board;
    private static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Info[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int mem = Integer.parseInt(st.nextToken());
            board[i] = new Info(mem, 0);
        }

        int maxC = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int cost = Integer.parseInt(st.nextToken());
            board[i].setCost(cost);
            maxC += cost;
        }

        // dp[c] = max(m)
        dp = new int[maxC+1];
        for(Info i : board){
            int mem = i.memory;
            int cost = i.cost;

            for(int j = maxC; j >= cost; j--){
                dp[j] = Math.max(dp[j], dp[j - cost] + mem);
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<dp.length; i++){
            if(dp[i] >= M){
                if(ans > i)
                    ans = i;
            }

        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
