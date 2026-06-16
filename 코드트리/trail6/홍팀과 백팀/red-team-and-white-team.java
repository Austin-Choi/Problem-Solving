import java.util.*;
import java.io.*;

/*
이분그래프 매칭
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<Integer>[] g;
    static int[] color;

    static boolean bfs(int s){
        Queue<Integer> q = new ArrayDeque<>();
        color[s] = 0;
        q.add(s);

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int ni : g[ci]){
                if(color[ni] == -1){
                    color[ni] = 1 - color[ci];
                    q.add(ni);
                }
                else if(color[ni] == color[ci])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();

        color = new int[N+1];
        Arrays.fill(color, -1);

        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        for(int i = 0; i<M; i++){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }
        // 모든 컴포넌트 검사해야 함.
        for(int i = 1; i<=N; i++){
            if(color[i] != -1)
                continue;
            if(!bfs(i)){
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }
}