import java.util.*;
import java.io.*;

/*
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static long[][] g;
    static final long INF = 100_00L * 1_000_000 + 1;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        g = new long[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j)
                    g[i][j] = 0;
                else
                    g[i][j] = INF;
            }
        }
        
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                g[i][j] = read();
            }
        }

        for(int k = 1; k<=N; k++){
            for(int u = 1; u<=N; u++){
                if(g[u][k] == INF)
                    continue;
                for(int v = 1; v<=N; v++){
                    if(g[k][v] == INF)
                        continue;
                    if(g[u][v] > g[u][k] + g[k][v]){
                        g[u][v] = g[u][k] + g[k][v];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(M-->0){
            sb.append(g[read()][read()]).append("\n");
        }
        System.out.print(sb);
    }
}