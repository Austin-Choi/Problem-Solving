
import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static ArrayList<Integer> al;
    private static boolean[] visited;
    private static StringBuilder sb;

    private static int[] out;

    private static void dfs(int depth){
        if(depth == M){
            for(int i = 0; i<M; i++){
                sb.append(out[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for(int n = 0; n<al.size(); n++){
            if(!visited[n]){
                if(before != al.get(n)){
                    visited[n] = true;
                    out[depth] = al.get(n);
                    before = al.get(n);
                    dfs(depth+1);
                    visited[n] = false;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        al = new ArrayList<>();
        out = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n<N; n++){
            al.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(al);
        visited = new boolean[N];
        sb = new StringBuilder();

        dfs(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
