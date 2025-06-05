import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 유클리드 호제법
    private static long gcd(long a,long b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
    }

    // 유클리드 호제법 역추적(재귀)
    private static long[] extendedGCD(long a, long b){
        if(b == 0)
            return new long[]{a, 1, 0};
        long[] infos = extendedGCD(b, a%b);
        long gcd = infos[0];
        long x = infos[1];
        long y = infos[2];
        long beforeY = x - (a/b) * y;
        return new long[]{gcd, y, beforeY};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long K = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());

            long gcd = gcd(K,C);
            if (C == 1){
                if(K+1 > 1_000_000_000L){
                    sb.append("IMPOSSIBLE").append("\n");
                }
                else{
                    sb.append(K+1).append("\n");
                }
            }
            else if(K == 1){
                sb.append(1).append("\n");
            }
            else if(gcd != 1){
                sb.append("IMPOSSIBLE").append("\n");
            }
            else{
                long[] infos = extendedGCD(K,C);
                long y0 = infos[2];

                // y0을 [0, K-1] 범위의 정수로 정규화
                y0 %= K;
                if(y0 < 0)
                    y0 += K;

                if(y0 <= 1_000_000_000L){
                    sb.append(y0).append("\n");
                }
                else{
                    sb.append("IMPOSSIBLE").append("\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
