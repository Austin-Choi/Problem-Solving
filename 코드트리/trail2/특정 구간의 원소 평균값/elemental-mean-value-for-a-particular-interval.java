import java.util.*;
import java.io.*;

/*
구간은 연속된것 
구간 크기 , 시작점 만 다루면 될듯

curSum/가 정확히 나눠떨어질때만 체킹
*/

public class Main {
    static int N;
    static int[] A;
    static int[] ps;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        ps = new int[N+1];
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            ps[i+1] = A[i] + ps[i]; 
        }

        int ans = N;
        for(int k = 2; k<=N; k++){
            for(int i = 0; i<=N-k; i++){
                int curSum = ps[i+k] - ps[i];

                if(curSum%k != 0)
                    continue;

                for(int s = i; s<i+k; s++){
                    if(curSum/k == A[s]){
                        ans++;
                        break;
                    }
                }
            }
        }
        System.out.print(ans);
    }
}