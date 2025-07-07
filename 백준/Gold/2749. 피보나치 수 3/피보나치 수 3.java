
import java.util.*;
import java.io.*;

public class Main {
    private static Map<Long, Long> memo = new HashMap<>();
    private static long fib(long n){
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        if(memo.containsKey(n))
            return memo.get(n);

        long k = n/2;
        long val;

        if(n%2 == 0){
            long fk = fib(k);
            long fk1 = fib(k-1);
            val = (2 * fk1 + fk) * fk;
        }
        else{
            long fk = fib(k);
            long fk1 = fib(k+1);
            val = fk1 * fk1 + fk * fk;
        }
        val %= 1000000L;

        memo.put(n, val);
        return val;
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw.write(String.valueOf(fib(Long.parseLong(br.readLine()))));
        bw.flush();
        bw.close();
        br.close();
    }
}