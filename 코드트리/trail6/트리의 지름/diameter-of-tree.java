import java.util.*;
import java.io.*;

/*
트리는 경로가 유일하고 
한 정점에서 최대 경로 + 2번째 최대 경로의 max 값이면 해당 트리에서 취할 수 있는 최대 값이니까
이걸 최대화하면 트리의 지름이 구해질 것

최대경로는 자식으로 계속 타고 내려가서 -> dfs
dfs(자식) + 현재 weight = total로 갱신하고
total이 현재 최대경로보다 크면 1번 = total, 2번 = 1번
total이 현재 2번째 최대경로보다 크면 2번 = total
*/

public class Main {
    static int N;
    static ArrayList<int[]>[] g;
    static boolean[] visited;
    static int ans = -1;

    static int dfs(int ci){
        int curMax = 0;
        int curSecondMax = 0;

        for(int[] n : g[ci]){
            int ni = n[0];
            int nw = n[1];
            
            if(visited[ni])
                continue;
            visited[ni] = true;
            
            int total = dfs(ni) + nw;
            if(total > curMax){
                curSecondMax = curMax;
                curMax = total;
            }
            else if(total > curSecondMax){
                curSecondMax = total;
            }
        }
        ans = Math.max(ans, curMax + curSecondMax);
        return curMax;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = N-1;
        g = new ArrayList[N+1];
        for(int i= 1; i<=N; i++){
            g[i] = new ArrayList<>();
        }

        while(M-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1);
        System.out.print(ans);
    }
}