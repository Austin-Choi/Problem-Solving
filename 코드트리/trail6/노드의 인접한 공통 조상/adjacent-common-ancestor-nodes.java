import java.util.*;
import java.io.*;

/*
1) 트리dp로 모든 노드의 루트노드로부터의 깊이를 구함
2) 두 노드의 깊이가 같아질때까지 큰쪽만 부모를 타고 올라감
3) 깊이가 같아지면 두 노드가 가리키는 값이 같아질때까지 부모를 타고 올라감
4) 두 노드가 똑같이 가리키는 값이 LCA
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static ArrayList<Integer>[] g;
    static int[] depth;
    static int[] parent;

    static void dfs(int u){
        for(int v : g[u]){
            depth[v] = depth[u] + 1;
            dfs(v);
        }
    }

    static int getLCA(int a, int b){
        // 큰게 뒤로오게
        if(depth[a] > depth[b]){
            int t = b;
            b = a;
            a = t;
        }

        while(depth[a] != depth[b]){
            b = parent[b];
        }
        
        while(a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
        }

        int M = N-1;
        parent = new int[N+1];
        // 입력에서 부모-자식 방향성이 명확하게 주어지니까
        int[] indegree = new int[N+1];
        while(M-->0){
            int u = read();
            int v = read();
            parent[v] = u;
            indegree[v]++;
            g[u].add(v);
        }

        int root = 1;
        for(int i = 1; i<=N; i++){
            if(indegree[i] == 0){
                root = i;
                break;
            }
        }
        depth = new int[N+1];
        dfs(root);
        System.out.print(getLCA(read(), read()));
    }
}