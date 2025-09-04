
/*
1-based

1~N*N에서
mid 아래의 수가 K개 이상인지 세기
가능하면 mid를 줄이고 r = mid - 1;
불가능하면 mid를 늘림 l = mid + 1

결정함수
mid 아래에 몇개의 곱셈수가 존재하는지 세기
i행에서 min(N, mid/i) i~N
 */
import java.io.*;

public class Main {
    static long N;
    static long K;
    static boolean can(long x){
        long rst = 0;
        for(int i = 1; i<=N; i++){
            rst += Math.min(N, x/i);
        }
        return rst >= K;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        K = Long.parseLong(br.readLine());
        long l = 1;
        long r = N*N;
        long ans = -1;
        while(l<=r){
            long mid = (l+r)/2;
            if(can(mid)) {
                ans = mid;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }
        System.out.println(ans);
    }
}
