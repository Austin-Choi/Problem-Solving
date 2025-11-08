/*
어떤 도미노 덩어리가 있을건데
덩어리 다음에 덩어리가 있으면 안 밀어주고 쭉쭉 나가면 되지만
단절되고 덩어리면 걘 밀어줘야함

SCC 나눌때 SCC하나를 한 정점으로 보고 SCC count가 SCC의 id가 됨
이렇게 부여한 id 배열을 가지고 모든 정점에 대해 연결된 정점의 id 값이 다르면
다른 SCC니까 이때 id배열값에 대해 진입차수를 +1

진입차수 0인거 갯수가 답
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int cur;
    static int[] id;
    static int[] low;
    static boolean[] isSCC;
    static Deque<Integer> stack;
    static ArrayList<Integer>[] board;
    // 이거 기준으로 indegree짜기
    static int[] compCount;
    static int[] indegree;
    // scc 갯수, 이게 scc id역할임
    static int count;
    static void setIndegree(){
        indegree = new int[count];
        for(int u = 1; u<=N; u++){
            for(int v : board[u]){
                if(compCount[u] != compCount[v])
                    indegree[compCount[v]]++;
            }
        }
    }
    static int dfs(int u){
        id[u] = ++cur;
        low[u] = id[u];
        stack.push(u);

        for(int next : board[u]){
            if(id[next] == 0){
                low[u] = Math.min(low[u], dfs(next));
            }
            else if(!isSCC[next]){
                low[u] = Math.min(low[u], id[next]);
            }
        }

        if(low[u] == id[u]){
            while(true){
                int v = stack.pop();
                isSCC[v] = true;
                compCount[v] = count;
                if(v == u)
                    break;
            }
            count++;
        }

        return low[u];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new ArrayList[N+1];
            for(int i = 1; i<=N; i++){
                board[i] = new ArrayList<>();
            }
            while(M-->0){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[x].add(y);
            }

            id = new int[N+1];
            low = new int[N+1];
            isSCC = new boolean[N+1];
            compCount = new int[N+1];
            cur = 0;
            count = 0;
            stack = new ArrayDeque<>();

            for(int i = 1; i<=N; i++)
                if(id[i] == 0)
                    dfs(i);

            setIndegree();
            int ans = 0;
            for(int in : indegree){
                if(in == 0)
                    ans++;
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
