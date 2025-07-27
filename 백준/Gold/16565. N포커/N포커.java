/*
n! / r!*(n-r)!
dp[N] = N장을 뽑았을때 이기는 경우의 수를 10007로 나눈 나머지
dp[3] 까지는 0
dp[4]에서 13

포함 배제의 원리
여러 조건을 동시에 만족하는 경우의 수를 계산시 중복제거 위한 공식
총합 = 단일조건만족 sum - 2개조건만족sum + 3개조건만족sum - 4개조건만족sum ...

포카드에 사용된 숫자 자체를 제거하고 나머지 조합을 만들어야
->
dp[n] 은 포카드가 하나 이상 포함된 n장의 조합 수
이걸 포카드 갯수 k에 따라 포함/배제로 누적합 구하기

C(13,k) * C(52-4k, N-4k)
->
k 포카드 갯수
C(13,k) 13개 숫자 중 k개를 골라서 포카드로 만들기
C(52-4k, N-4k) 남은 카드 중에서 남은 자리수 채우기

음수 고려 모듈러 연산
(x % MOD + MOD) % MOD
0이상 MOD 미만의 정수로 안전하게 정규화됨

MOD 연산에서는 나눗셈 = 곱셈역원
-> 페르마의 소정리 이용하기
n! * (r!(n-r)!)의역원 % MOD
------------------------------------------------------
조합 문제에서는 파스칼의 삼각형 활용
각 숫자는 바로 위 행의 왼쪽과 오른쪽 숫자를 더한 값으로 이루어져있음
삼각형의 n번째 행 k번째 숫자는 조합 C(n, k)

답 1개만 출력하면 되서 단순 누적 변수로 dp[N] 사용함
 */
import java.io.*;
public class Main {
    static final int MOD = 10007;
    static int[][] comb;
    // 파스칼의 삼각형
    static void makeComb(){
        for(int i = 0; i<53; i++){
            comb[i][0] = 1;
        }
        for(int i = 1; i<53; i++){
            for(int j = 1; j<=i; j++){
                comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % MOD;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[53];
        comb = new int[53][53];
        makeComb();
        dp[1] = dp[2] = dp[3] = 0;

        for(int k = 1; k*4 <= N && k <= 13; k++){
            int sign = (k%2==1) ? 1 : -1;
            int use = comb[13][k];
            int rest = comb[52-4*k][N-4*k];
            dp[N] = (dp[N] + sign * use * rest) % MOD;
        }
        if(dp[N]<0)
            dp[N] += MOD;

        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}
