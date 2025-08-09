import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[] arr, rst;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static void dfs(int depth){
        if(depth == M){
            for(int n : rst)
                sb.append(n).append(" ");
            sb.append("\n");
            return;
        }

        int prev = -1;
        for(int i = 0; i<N; i++){
            if(prev == arr[i])
                continue;

            rst[depth] = arr[i];
            prev = arr[i];
            dfs(depth+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        rst = new int[M];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
