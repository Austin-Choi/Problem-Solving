/*
a,b를 합성할때 cost a*b
cost를 최소로 하는 1마리 합성
제일 작은 거와 다음으로 작은거 곱하고 다시 넣고 1개 될때까지 반복?
-> a*b 삽입될때 정렬되어야해서 pq
-> 숫자가 커서 mod 연산하면 pq정렬 깨짐 -> BigInteger
 */
import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class Main {
    static int T,N;
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            ArrayList<BigInteger> li = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++) {
                BigInteger bi = BigInteger.valueOf(Long.parseLong(st.nextToken()));
                li.add(bi);
            }
            PriorityQueue<BigInteger> q = new PriorityQueue<>(li);

            long ans = 1;
            while(q.size() > 1){
                BigInteger a = q.poll();
                BigInteger b = q.poll();
                BigInteger ab = a.multiply(b);
                q.add(ab);
                ans = (ans * ab.mod(BigInteger.valueOf(MOD)).longValue()) %MOD;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
