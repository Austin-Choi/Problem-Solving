import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[] arr, result;
    static StringBuilder sb = new StringBuilder();
    static void dfs(int depth, int start){
        if(depth == M){
            for(int n : result)
                sb.append(n).append(" ");
            sb.append("\n");
            return;
        }

        int prev = -1;
        for(int i = start; i<N; i++){
            if(arr[i] == prev)
                continue;

            result[depth] = arr[i];
            prev = arr[i];

            dfs(depth+1, i+1);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs(0,0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}
