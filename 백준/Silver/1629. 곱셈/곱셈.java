
import java.io.*;
import java.util.StringTokenizer;
/*
거듭제곱 분할
a ^ b = a ^ b/2 * a ^ b/2
a ^ b = a ^ b/2 * a ^ b/2+1
 */
public class Main {
    public static long power(long a, long b, long c){
        if(b == 0)
            return 1;
        if(b == 1)
            return a % c;

        // 분할 - 정복 메모이제이션
        // 변수 안 쓰고 그때마다 호출하면 시간초과
        long temp = power(a, b/2, c) % c;
        if(b % 2 == 0)
            return temp * temp % c;
        return temp * temp % c * a % c;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        bw.write(power(A, B, C)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
