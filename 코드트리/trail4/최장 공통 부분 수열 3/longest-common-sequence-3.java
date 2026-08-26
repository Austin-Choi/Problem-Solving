import java.util.*;
import java.io.*;

/*
na, nb 역추적을 위해  idx 부터 시작해서 각 값이 언제 처음 나타나는지 저장

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();

        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }
        int[][] na = new int[N+1][1001];
        Arrays.fill(na[N], -1);
        for(int i = N-1; i>=0; i--){
            System.arraycopy(na[i+1], 0, na[i], 0, 1001);
            na[i][A[i]] = i;
        }

        int[] B = new int[M];
        for(int j= 0; j<M; j++){
            B[j] = read();
        }
        int[][] nb = new int[M+1][1001];
        Arrays.fill(nb[M], -1);
        for(int i = M-1; i>=0; i--){
            System.arraycopy(nb[i+1], 0, nb[i], 0, 1001);
            nb[i][B[i]] = i;
        }

        // 뒤로 보는 dp로 해야함
        int[][] dp = new int[N+1][M+1];

        for(int i = N-1; i>=0; i--){
            for(int j = M-1; j>=0; j--){
                if(A[i] == B[j])
                    dp[i][j] = dp[i+1][j+1]+1;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
            }
        }

        // 역추적
        int i = 0;
        int j = 0;
        int len = dp[0][0];
        StringBuilder sb =new StringBuilder();

        while(len > 0){
            for(int x = 1; x<=1000; x++){
                int p = na[i][x];
                int q = nb[j][x];

                if(p == -1 || q == -1)
                    continue;
                if(dp[p+1][q+1] == len-1){
                    sb.append(x).append(" ");
                    i = p+1;
                    j = q+1;
                    len -= 1;
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}