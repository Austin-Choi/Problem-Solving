import java.util.*;
import java.io.*;
/*
C(s[i], K) 의 합
N*N의 파스칼의 삼각형 계산해놓고
tri[S[i], K] 합하기?
-> 터짐

확률 = C(s[i], K) / C(N,M)
S[i]/N * s[i]-1/N-1
 */

public class Main {
    static int N,M,K;
    static int[] stone;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        stone = new int[M];
        for(int i = 0; i<M; i++){
            stone[i] = Integer.parseInt(st.nextToken());
            N += stone[i];
        }
        K = Integer.parseInt(br.readLine());

        double ans = 0;
        for(int i = 0; i<M; i++){
            if(stone[i] < K)
                continue;
            double prob = 1.0;
            for(int j = 0; j<K; j++){
                prob *= (stone[i]-j) / (double) (N-j);
            }
            ans += prob;
        }
        System.out.printf("%.11f", ans);
    }
}
