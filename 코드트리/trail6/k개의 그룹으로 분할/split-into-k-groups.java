import java.util.*;
import java.io.*;

/*
정확히 K개의 그룹으로 분배, N개의 수, 그룹에 최소 한개
각 그룹의 수 합이 모두 같아질수 있는지..
-> 그럼 각 그룹의 합도 따로따로 알아야되나 

K,N둘다 16이하 주어지는 수 1000이하 자연수
dp[N][K][1<<K] = N개의 숫자를 처리했을때 현재 분배 상태가 1<<k 이고 k번째 그룹의 합..?
-> 근데 조합마다 달라지는 합을 그냥 저장하자니 이상하고 최소 최대 쓴다해도 의미없는데

아 전체 합의 /K 한걸 달성할 수 있는지
dp[1<<N] = N개의 수 분배 상태가 mask일때 가능한지
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,K,T;
    static int[] arr;

    static int getSum(int mask){
        int sum = 0;
        for(int i = 0; i<N; i++){
            if((mask & (1<<i)) != 0){
                sum += arr[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        K = read();
        arr =new int[N];
        int sum = 0;
        for(int i = 0; i<N; i++){
            arr[i] = read();
            sum += arr[i];
        }
        int T = sum/K;
        if(sum%K != 0){
            System.out.print("No");
            return;
        }

        boolean[] dp = new boolean[1<<N];
        dp[0] = true;

        for(int m = 0; m<(1<<N); m++){
            if(!dp[m])
                continue;
            // 나머지연산을 하면 각 그룹당 T가 되어야하니까
            // 다음걸 더했을때 T가 되어야 한 그룹으로 끝나는 상태 
            int cur = getSum(m) % T;
            for(int next = 0; next <N; next++){
                if((m & (1<<next)) != 0)
                    continue;
                int nc = cur + arr[next];
                // T를 넘기지만 않으면 될듯
                if(nc <= T)
                    dp[m | (1<<next)] = true;
            }
        }
        if(dp[(1<<N)-1])
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}