import java.util.*;
import java.io.*;

/*
자기 자신은 항상 도달 가능함 [i][i] = true
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static boolean[][] g;

    public static void main(String[] args) throws IOException{
        N = read();
        g = new boolean[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                int cur = read();
                if(i == j || cur == 1)
                    g[i][j] = true;
            }
        }

        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                if(!g[i][k])
                    continue;
                for(int j = 1; j<=N; j++){
                    if(!g[k][j])
                        continue;
                    g[i][j] = g[i][k] & g[k][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(g[i][j])
                    sb.append(1);
                else
                    sb.append(0);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}