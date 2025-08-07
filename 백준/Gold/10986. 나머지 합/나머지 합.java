import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] modCount = new long[M];
        long sum = 0;
        long answer = 0;

        modCount[0] = 1;  // 초기 상태: 합이 0인 경우도 포함

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            sum += num;
            int mod = (int)((sum % M + M) % M);
            answer += modCount[mod];
            modCount[mod]++;
        }

        bw.write(Long.toString(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}