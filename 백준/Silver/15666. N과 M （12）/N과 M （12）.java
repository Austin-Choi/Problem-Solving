import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int[] li, out;
    private static StringBuilder sb = new StringBuilder();

    private static void dfs(int start, int depth){
        if(depth == M){
            for(int i = 0; i<M; i++){
                sb.append(out[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = -1;
        for(int i = start; i <N; i++){
            if(before != li[i]){
                out[depth] = li[i];
                before = li[i];
                dfs(i, depth+1);
            }
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        li = new int[N];
        out = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n<N; n++){
            li[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(li);
        dfs(0,0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
