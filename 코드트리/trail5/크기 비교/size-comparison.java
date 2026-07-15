import java.util.*;
import java.io.*;

/*
u->v 는 u>v 라는 뜻이라서 단방향 그래프로 모델링
입력받을때도 그렇게 해야함
boolean 2차원 배열로 모델링하고 플로이드로 이으면 대소 관계 파악 여부 계산 가능
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static boolean[][] cmp;

    public static void main(String[] args) throws IOException{
        N= read();
        M = read();
        cmp = new boolean[N+1][N+1];

        while(M-->0){
            int u = read();
            int v = read();
            cmp[u][v] = true;
        }

        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                if(!cmp[i][k])
                    continue;
                for(int j = 1; j<=N; j++){
                    if(!cmp[k][j])
                        continue;
                    cmp[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            int cnt = 0;
            for(int j = 1;j<=N; j++){
                if(i == j)
                    continue;
                if(!cmp[i][j] && !cmp[j][i])
                    cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}