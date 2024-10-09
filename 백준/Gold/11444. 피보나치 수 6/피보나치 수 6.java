
import java.io.*;
import java.util.*;

public class Main {
    public static Map<Long, Long> m = new HashMap<>();
    private static long DIVIDER = 1000000007L;
    public static long fibo(long n){
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        if(n == 2)
            return 1;
        if(m.containsKey(n))
            return m.get(n);

        long rst;
        if(n % 2 == 0){
            long nn = n / 2;
            long tmp1 = fibo(nn);
            long tmp2 = fibo(nn-1);
            rst = (tmp1 * ((2L * tmp2 % DIVIDER + tmp1) % DIVIDER)) % DIVIDER;
        }
        else{
            long nn = (n+1) / 2;
            long tmp1 = fibo(nn);
            long tmp2 = fibo(nn-1);
            rst = (tmp1 * tmp1 % DIVIDER + tmp2 * tmp2 % DIVIDER) % DIVIDER;
        }
        m.put(n, rst);
        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(fibo(n));
    }
}
