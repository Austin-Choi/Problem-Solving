import java.util.*;
import java.io.*;

/*
음 파라메트릭 서치??
가능한 능력 차이 구간 [0, total]
이걸 최소화해야함

can 함수 N/2명을 뽑았을때 합이 X가 되는가?
*/

public class Main {
    static int N = 6;
    static int[] pool;
    static int[] rst = new int[3];
    static int total = 0;
    static int ans = 6 * 1_000_000 + 1;

    static void dfs(int cur, int depth){
        if(depth == 3){
            int sum = 0;
            for(int n : rst){
                sum += n;
            }
            ans = Math.min(ans, Math.abs((total - sum)-sum));
            return;
        }

        for(int i = cur; i<N; i++){
            rst[depth] = pool[i]; 
            // distinct 하게 뽑아야함
            dfs(i+1, depth+1);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pool = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            pool[i] = Integer.parseInt(st.nextToken());
            total += pool[i];
        }
        dfs(0,0);
        System.out.print(ans);
    }
}