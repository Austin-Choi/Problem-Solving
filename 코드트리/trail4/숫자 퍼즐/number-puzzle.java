import java.util.*;
import java.io.*;

/*
N = 10, M = 200, K = 10^9
-> K는 상태로 넣을 수 없음
각 마법석에 새겨진 자연수는 크거나 같으므로 
이걸 만드는거 자체가 사전순임
long dp[n][m][last] = n번째까지 보고 현재 선택 수(last)까지의 마법석의 수의 합이 m일때의 경우의 수의 합
으로 놓고 이 경우의 수가 K가 될때의 수열의 상태를 출력하기?

-----------------
suffix DP 하나로 진행
dp[len][m][last] = 앞으로 len만큼 선택해야 하고 합이 m이고 첫번째로 선택할 수 있는 값이 last이상 일때 경우의 수
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
        int K = read();

        long[][][] dp = new long[N+1][M+1][M+1];        
        // len =0 더이상 고를게 없을때 1가지
        for(int i = 1; i<=M; i++){
            dp[0][0][i] = 1;
        }

        // 문제 조건대로 고르는 경우의 수 DP
        for(int len = 1; len <= N; len++){
            for(int m = 0; m<=M; m++){
                for(int prev = 0; prev <= M; prev++){
                    for(int next = prev; next <= M; next++){
                        if(m < next)
                            break;
                        dp[len][m][prev] += dp[len-1][m-next][next];
                    }
                }
            }
        }

        // 앞에서부터 suffix dp 따라가면서 복원
        int[] ans = new int[N+1];
        int len = N;
        int m = M;
        int prev = 1;
        
        for(int i =1 ; i<=N; i++){
            for(int next = prev; next<=M; next++){
                if(m < next)
                    break;
                
                long cnt = dp[len-1][m-next][next];
                if(K > cnt){
                    K -= cnt;
                }
                else{
                    ans[i] = next;
                    m -= next;
                    len--;
                    prev = next;
                    
                    break;
                }
            }
        }

        for(int i = 1; i<=N; i++){
            System.out.print(ans[i] +" ");
        }
    }
}