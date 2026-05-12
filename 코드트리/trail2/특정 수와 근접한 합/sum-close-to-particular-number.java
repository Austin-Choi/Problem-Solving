import java.util.*;
import java.io.*;

public class Main {
    static int N,S;
    static int[] A;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            sum += A[i];
        }

        int ans = 100*10_000+1;
        for(int i =0; i<N-1; i++){
            for(int j = i+1; j<N; j++){
                if(i == j)
                    continue;
                ans = Math.min(ans, Math.abs(sum - A[i] - A[j] - S));
            }
        }
        System.out.print(ans);
    }
}