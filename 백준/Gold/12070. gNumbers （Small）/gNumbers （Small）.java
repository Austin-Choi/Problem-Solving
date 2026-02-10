/*
optimal 하게 플레이한다면 반복나누기 결과가 소수가 되게끔 골라야 현재 턴 플레이어가 이김

dp[N] = N에서 시작하면 현재 플레이어가 이기는가
dp[gnum] = false 고정
d = n에서 p를 끝까지 나눈 값
모든 d가 = true면 상대가 이기는거니까 모든 방법에서 현재는 false가 됨
만약 하나라도 dp[d]가 false면 내가 이기는 하나의 경우라도 있으니 현재는 true가 됨
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T,N;
    // i의 가장 작은 소인수 저장 배열
    // smallest prime factor
    static int[] getSpf(){
        int[] spf = new int[1001];

        for(int i = 2; i<=1000; i++){
            // 소수
            if(spf[i] == 0){
                for(int j = i; j<=1000; j+=i){
                    if(spf[j] == 0)
                        spf[j] = i;
                }
            }
        }
        return spf;
    }

    // i의 서로 다른 소인수리스트 만들기
    static List<Integer> getFactors(int n, int[] spf){
        List<Integer> li = new ArrayList<>();

        while(n>1){
            int p = spf[n];
            li.add(p);

            while(n%p == 0)
                n /= p;
        }
        return li;
    }

    static boolean[] prime = new boolean[200];
    static void getPrime(){
        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = true;

        for(int i = 2; i*i<200; i++){
            if(prime[i]){
                for(int j = i*i; j<200; j+=i)
                    prime[j] = false;
            }
        }
    }

    static int digitSum(int n){
        int s = 0;
        while(n>0){
            s+=n%10;
            n/=10;
        }
        return s;
    }

    public static void main(String[] args) throws IOException{
        int[] spf = getSpf();
        getPrime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        boolean[] dp = new boolean[1001];
        dp[1] = false;

        for(int i = 2; i<=1000; i++){
            if(prime[digitSum(i)]){
                dp[i] = false;
                continue;
            }
            boolean win = false;

            for(int p : getFactors(i, spf)){
                int d = i;

                while(d%p == 0)
                    d /= p;

                if(!dp[d]){
                    win = true;
                    break;
                }
            }

            dp[i] = win;
        }

        for(int i = 1; i<=T; i++){
            sb.append("Case #").append(i);
            N = Integer.parseInt(br.readLine());
            if(dp[N])
                sb.append(": Laurence\n");
            else
                sb.append(": Seymour\n");
        }
        System.out.print(sb);
    }
}
