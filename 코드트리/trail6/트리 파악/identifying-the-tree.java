import java.util.*;
import java.io.*;

/*
먼가 트리의 구조와 현재 주어진 트리의 노드 수를 이용해서 
먼저 시작한 플레이어의 승패 여부를 판단하는 문제 같음

음..
루트에서 모든 리프 노드까지의 길이를 합하고 그게 2로 나눠떨어지는지 여부 판단??
간선이N-1개로 주어지므로 트리

루트 1번 고정
*/

public class Main {
    static int N;
    static ArrayList<Integer>[] g;
    static boolean[] visited;

    // depth 파라미터로 넘겨야 리프노드일때 깊이를 반환하고
    // 그걸 부모의 total까지 영향을 끼치게
    static int dfs(int ci, int depth){
        int total = 0;
        boolean hasNext = false;
        for(int n : g[ci]){
            if(visited[n])
                continue;
            hasNext = true;
            visited[n] = true;
            total += dfs(n, depth+1);
        }
        if(!hasNext){
            return depth;
        }
        return total;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N= Integer.parseInt(br.readLine());
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        int M = N-1;
        while(M-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 무방향이라 입력 양방향으로 넣어야함!!
            g[u].add(v);
            g[v].add(u);
        }

        visited = new boolean[N+1];
        visited[1] = true;
        int tot = dfs(1,0);
        if(tot % 2 == 1)
            System.out.print(1);
        else
            System.out.print(0);
    }
}