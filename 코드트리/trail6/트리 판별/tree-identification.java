import java.util.*;
import java.io.*;

/*
정점 갯수가 주어지지 않고, 모든 노드는 반드시 들어오는 간선이 있다고 했으니
최소, 최대 노드값을 구하면 그 안에 있는 모든 노드들은 다른 노드로 이어져있으니
그걸로 g 크기를 잡기

모든 노드 도달 가능하고는 하지만 경로가 유일하다고 했으니 
visited 체킹하고 어떤 점에서 dist[] 체킹해봤는데 갱신되거나 하면 바로 false

parent 초기값 -1로 잡고 

어.. 근데 문제 설명이 약간 DAG 트리같은데 왜 그냥 트리라고하지
*/

public class Main {
    static int N;
    static int[][] input;
    static ArrayList<Integer>[] g;
    static int[] indegree;
    static boolean[] visited;

    // 노드 번호가 띄엄띄엄 들어올수도 있을거같음
    static Set<Integer> nodes = new HashSet<>();

    static boolean dfs(int ci){
        if(visited[ci])
            return false;
        
        visited[ci] = true;

        for(int n : g[ci]){
            if(!dfs(n))
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N][2];
        int max = -1;
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            max = Math.max(max, Math.max(u,v));
            input[i][0] = u;
            input[i][1] = v;
        }
        g = new ArrayList[max+1];
        for(int i = 1; i<=max; i++)
            g[i] = new ArrayList<>();

        indegree = new int[max+1];

        for(int i = 0; i<N; i++){
            int u = input[i][0];
            int v = input[i][1];
            g[u].add(v);

            nodes.add(u);
            nodes.add(v);
            
            indegree[v]++;
        }

        // 1,2번 조건 처리
        int cnt = 0;
        int root = -1;
        for(int i : nodes){
            if(cnt == 2){
                System.out.print(0);
                return;
            }

            if(indegree[i] == 0){
                root = i;
                cnt++;
            }
                
            if(indegree[i] > 1){
                System.out.print(0);
                return;
            }
        }

        if(cnt != 1){
            System.out.print(0);
            return;
        }


        visited = new boolean[max+1];
        // 위에서 indegree > 1로 걸러서 여기서 재방문하면 사이클임
        if(!dfs(root)){
            System.out.print(0);
            return;
        }

        for(int n : nodes){
            if(!visited[n]){
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }
}