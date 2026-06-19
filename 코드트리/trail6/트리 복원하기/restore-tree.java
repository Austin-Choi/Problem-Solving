import java.util.*;
import java.io.*;

/*
1-2 = 3
1-3 = 2
1-4 = 3
2-3 = 5
2-4 = 6
3-4 = 1

1-2 2-3 -> 3+5 
1-3 -> 2 t
3-4 -> 1 t

뭔가 경로 합과 각자 입력값 비교해서 맞지 않으면 입력값이 참이다 느낌으로 가는거같은데
트리라서 사이클은 없을거고 

dist[i][j] = dist[i][k] + dist[k][j];
에서 k가 존재하면 해당 간선은 버림 
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] dist;

    public static void main(String[] args) throws IOException{
        N = read();
        dist = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                dist[i][j] = read();
            }
        }

        ArrayList<int[]> ans = new ArrayList<>();

        for(int i = 1; i<=N; i++){
            for(int j = i+1; j<=N; j++){
                boolean e = true;
                for(int k = 1; k<=N; k++){
                    if(k == i || k == j)
                        continue;

                    if(dist[i][j] == dist[i][k] + dist[k][j]){
                        e = false;
                        break;
                    }
                }
                if(e)
                    ans.add(new int[]{i,j,dist[i][j]});
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int[] a : ans){
            sb.append(a[0]+" "+a[1]+" "+a[2]).append("\n");
        }
        System.out.print(sb);
    }
}