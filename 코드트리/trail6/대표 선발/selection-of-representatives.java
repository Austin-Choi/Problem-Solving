import java.util.*;
import java.io.*;

/*
입력은 n개의 줄에 걸쳐서 주어지는데
m c1 c2 c3
m은 구성 인원 c1..cn은 해당 그룹 속하는 사람 번호로 들어오는걸
사람 번호 -> 가능한 그룹 번호 list로 받아서 
전이할때 현재 마스크에서 가능한 그룹번호가 추가된 상태가 nm이면 가능한쪽으로 전이하기

dp[i][1<<N] = 앞에서 처리한 사람 수가 i이고 N개의 그룹에 대해 대표를 뽑은 상태가 mask일때 
서로 다른 경우의 수를 10,007로 나머지 연산한 값
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException{
        int N = read();
        ArrayList<Integer>[] ppl = new ArrayList[101];
        for(int i = 0; i<100; i++){
            ppl[i] = new ArrayList<>();
        }
        for(int i= 0; i<N; i++){
            int cnt = read();
            for(int c = 0; c<cnt; c++){
                int p = read()-1;
                ppl[p].add(i);
            }
        }
        
        // 처리한 사람수 , mask 이므로 101로 잡기
        int[][] dp = new int[101][1<<N];
        dp[0][0] = 1;

        for(int i = 0; i<100; i++){
            for(int m= 0; m<(1<<N); m++){
                if(dp[i][m] == 0)
                    continue;
                // i+1번째 사람을 대표로 안뽑음
                dp[i+1][m] = (dp[i][m] + dp[i+1][m]) % MOD;
                // i+1번째 사람을 대표로 뽑음
                for(int g : ppl[i]){
                    if((m & (1<<g)) != 0)
                        continue;
                    int nm = m | (1<<g);
                    dp[i+1][nm] = (dp[i][m] + dp[i+1][nm]) % MOD;
                }
            }
        }
        System.out.print(dp[100][(1<<N)-1]);
    }
}