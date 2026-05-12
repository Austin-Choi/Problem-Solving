import java.util.*;
import java.io.*;

/*
주어진 배열에 abs(A[i] - H) 해놓고 prefix sum 구한다음에
구간 크기(T)만큼 슬라이딩 윈도 하고
그게 최소값 되는거 보면 될듯
 
*/

public class Main {
    static int N,H,T;
    static int[] A;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        A = new int[N];
        int[] ps = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            ps[i+1] = Math.abs(A[i] - H) + ps[i];
        }

        int ans = Integer.MAX_VALUE;
        for(int i = T; i<=N; i++){
            int curSum = ps[i] - ps[i-T];
            ans = Math.min(ans, curSum);
        }
        System.out.print(ans);
    }
}