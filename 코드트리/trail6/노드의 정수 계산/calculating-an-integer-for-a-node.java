import java.util.*;
import java.io.*;

/*자식에서 시작해서 부모노드로 가야하는데 값을 재사용하는게 좋을거 같음..

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int[] nodes;
    static ArrayList<Integer>[] g;

    static int dfs(int curIdx){
        int sum = nodes[curIdx];
        for(int n : g[curIdx]){
            sum += dfs(n);
        }
        return Math.max(0, sum);
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        nodes = new int[N+1];
        int[] parent = new int[N+1];
        int[] dp = new int[N+1];

        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        // t,a,p 
        // t가 1이면 +a, 0이면 -a
        // p는 해당노드 부모 노드
        for(int i = 2; i<=N; i++){
            int type = read();
            int val = read();
            if(type == 0)
                val = -val;

            nodes[i] = val;
            g[read()].add(i);
        }
        System.out.print(dfs(1));
    }
}