import java.io.*;

public class Main {
    static long S;

    static boolean can(long x) {
        return x * (x + 1) / 2 <= S;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Long.parseLong(br.readLine());

        long l = 0;
        long r = 2_000_000_000L;
        long ans = 0;
        while (l <= r) {
            long mid = (l+r) / 2;

            if (can(mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(ans);
    }
}