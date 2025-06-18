import java.util.*;
import java.io.*;

public class Main {
    private static final long MOD = 1_000_000_007;

    /*
    지수를 2진수로 분해해서 비트가 켜진 위치에 해당하는 base ^ k만 곱하는 방식
     */
    private static long pow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while(exp > 0){
            // exp가 홀수일때
            if((exp & 1) == 1)
                result = result * base % MOD;
            base = base * base % MOD;
            // 다음 비트 검사
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long sum = 0;
        int M = Integer.parseInt(br.readLine());

        for(int m = 0; m<M; m++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long S = Long.parseLong(st.nextToken());

            // 페르마의 소정리
            long inv = pow(N, MOD - 2);
            sum = (sum + S * inv % MOD) % MOD;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}
