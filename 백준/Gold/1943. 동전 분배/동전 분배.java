/*
금액 합은 10만 안넘기니까 가치를 인덱스로 잡기 (5만)
하나를 사용하고 나머지를 사용해서 전체 합의 절반에 도달할 수 있는지 보기
두명 다 동시에 그 가치를 만족시켜야함
multiple bounded knapsack 문제
이진 분할 써서 0-1배낭 만들기

dp[target] = 가능한지
0-1 배낭이니까 뒤에서 앞으로
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] val;
    static int[] cnt;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t<3; t++){
            N = Integer.parseInt(br.readLine());
            val = new int[N];
            cnt = new int[N];

            int target = 0;
            for(int n = 0; n<N; n++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                val[n] = Integer.parseInt(st.nextToken());
                cnt[n] = Integer.parseInt(st.nextToken());
                target += val[n]*cnt[n];
            }
            
            // total이 홀수면 절대 못함
            if(target %2 == 1){
                sb.append("0\n");
                continue;
            }
            target /= 2;

            boolean[] dp = new boolean[target+1];
            dp[0] = true;
            for(int i = 0; i<N; i++){
                int value = val[i];
                int count = cnt[i];

                int k = 1;
                while (count > 0){
                    int use = Math.min(k,count);
                    int money = value * use;

                    for(int j = target; j>=money; j--){
                        dp[j] |= dp[j-money];
                    }

                    count -= use;
                    k *= 2;
                }
            }
            if(dp[target])
                sb.append("1\n");
            else
                sb.append("0\n");
        }
        System.out.print(sb);
    }
}