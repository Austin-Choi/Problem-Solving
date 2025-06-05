import java.io.*;
import java.util.*;

public class Main {
    // 유클리드 호제법
    private static int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // ans = x로 시작하면
            // ans % M == x는 항상 참
            // 이후 ans += M을 하면서
            // ans % N == y 만족하는 거 구하고

            // 주어진 문제는 1-based 문제이지만
            // 중국인의 나머지 정리에 넣고 풀려면 0-based 문제로 바꿔야 함
            // ans - 1 = n (mod M) 이면 ans = n + 1 (mod M) 이므로
            // 입력받은 x와 y에서 1씩 빼고 시작하고
            // 마지막에 계산된 ans에 +1을 하면 정답


            int limit = M*N / gcd(M,N);
            x -= 1;
            y -= 1;
            int ans = x;

            while(ans<=limit){
                if(ans % N == y){
                    sb.append(ans+1).append("\n");
                    break;
                }
                ans += M;
            }

            if(ans>limit)
                sb.append(-1).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
