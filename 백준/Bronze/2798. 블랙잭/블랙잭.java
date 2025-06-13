import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static int[] li;
    private static boolean[] visited;
    private static int[] out;
    private static int ans = 0;
    private static void dfs(int n, int depth){
        if(depth == 3){
            int sum = Arrays.stream(out).sum();
            if(sum <= M)
                ans = Math.max(ans, sum);
            return;
        }

        for(int i = n; i<N; i++){
            if(!visited[n]){
                visited[n] = true;
                out[depth] = li[i];
                dfs(i+1, depth+1);
                visited[n] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        li = new int[N];
        visited = new boolean[N];
        out = new int[3];

        for(int i = 0; i<N; i++){
            li[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
