import java.util.*;
import java.io.*;

/*
dfs로 루트노드에서 타고내려가면서 처음으로 자식 노드가 2개 이상이 되는 노드를 중앙 노드라고 하기 
중앙 노드 찾으면 바로 리턴하고 끝

중앙 노드의 자식을 루트로 하는 서브트리 중, max 서브 정점 수 - min 서브 정점 수
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, R;
    static ArrayList<Integer>[] g;
    static int[] dp;

    static int findMid(int ci, int p){
        // 인접노드니까 자식수는 부모빼고 (-1)
        int size = g[ci].size()-1;
        if(p == -1)
            size += 1;

        if(size >= 2 || size == 0)
            return ci;
        
        for(int n : g[ci]){
            if(n == p)
                continue;
            return findMid(n, ci);
        }

        return 0;
    }

    static int dfs(int ci, int prev){
        int tot = 0;
        for(int n : g[ci]){
            if(n == prev)
                continue;
            tot += dfs(n, ci);
        }
        dp[ci] = 1 + tot;
        return dp[ci];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        R = read();

        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        for(int i = 0; i<N-1; i++){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        // 중앙 노드를 구하고
        int mid = findMid(R, -1);
        dp = new int[N+1];

        ArrayList<Integer> li = new ArrayList<>();
        // mid의 자식노드를 각자 루트로 갖는 서브트리 크기를 dfs로 계산하고
        // list에 저장하고 정렬해서 [size-1]-[0]
        for(int n : g[mid]){
            li.add(dfs(n,mid));
        }
        Collections.sort(li);
        System.out.print(li.get(li.size()-1) - li.get(0));
    }
}