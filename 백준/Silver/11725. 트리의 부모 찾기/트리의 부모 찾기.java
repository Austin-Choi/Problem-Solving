import java.util.*;
import java.io.*;

// 재귀 dfs로 순회하면서
// parents 역추적용 배열을 만들어놓고 채워나간다.

public class Main {
    private static boolean[] visited;
    private static ArrayList<Integer>[] al;
    private static int[] parents;
//    private static int parent = 0;
//    private static void dfs(int start, int target, int before){
//        visited[start] = true;
//        if(start == target){
//            parent = before;
//            return;
//        }
//
//        for(int i = 0; i<al[start].size(); i++){
//            int next = al[start].get(i);
//            if(!visited[next]){
//                visited[next] = true;
//                dfs(next, target, start);
//                visited[next] = false;
//            }
//        }
//    }
    private static void dfs(int start){
        visited[start] = true;
        for(int n : al[start]){
            if(!visited[n]){
                parents[n] = start;
                dfs(n);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        al = new ArrayList[N+1];
        visited = new boolean[N+1];
        visited[0] = true;
        parents = new int[N+1];
        for(int i = 0; i<al.length; i++){
            al[i] = new ArrayList<Integer>();
        }

        for(int n = 0; n<N-1; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            al[start].add(end);
            al[end].add(start);
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
//        for(int n = 2; n<=N; n++){
//            visited = new boolean[N+1];
//            dfs(1,n,0);
//            sb.append(parent).append("\n");
//        }
        for(int n = 2; n<=N; n++){
            sb.append(parents[n]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}