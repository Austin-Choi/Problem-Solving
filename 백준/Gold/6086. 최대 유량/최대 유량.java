import java.util.*;
import java.io.*;
class Info{
    int dest;
    int revDest; //역방향 계산하기 위해 간선정보 읽을때 필요함 O(1)
    int capacity;

    public Info(int d, int rd, int c){
        this.dest = d;
        this.revDest = rd;
        this.capacity = c;
    }
}

public class Main {
    private static int N;
    private static ArrayList<Info>[] board;
    private static int[] level = new int[52];
    private static int[] work = new int[52];

    private static int charToInt(char c){
        if(Character.isLowerCase(c))
            return c - 'a' + 26;
        else
            return c - 'A';
    }
    private static void addEdge(int from, int to, int cap){
        Info forward = new Info(to, board[to].size(), cap);
        // 초기에 역방향 유량은 0임
        Info backward = new Info(from, board[from].size(), 0);

        board[from].add(forward);
        board[to].add(backward);
    }
    // 레벨 그래프 구성
    private static boolean bfs(int s, int t){
        Arrays.fill(level, -1);
        Queue<Integer> q = new ArrayDeque<>();
        level[s] = 0;
        q.add(s);

        while(!q.isEmpty()){
            int cur = q.poll();
            for(Info next : board[cur]){
                if(next.capacity > 0 && level[next.dest] == -1){
                    level[next.dest] = level[cur]+1;
                    q.add(next.dest);
                }
            }
        }

        return level[t] != -1;
    }
    // 유량 보내기
    private static int dfs(int cur, int t, int flow){
        if(cur == t)
            return flow;

        for(; work[cur] < board[cur].size(); work[cur]++){
            Info e = board[cur].get(work[cur]);

            if(e.capacity > 0 && level[e.dest] == level[cur]+1){
                int pushed = dfs(e.dest, t, Math.min(flow, e.capacity));
                if(pushed > 0){
                    e.capacity -= pushed;
                    board[e.dest].get(e.revDest).capacity += pushed;
                    return pushed;
                }
            }
        }

        return 0;
    }
    private static int maxFlow(){
        int s = charToInt('A');
        int t = charToInt('Z');
        int total = 0;

        while(bfs(s,t)){
            Arrays.fill(work, 0);

            int flow;
            while((flow = dfs(s,t,Integer.MAX_VALUE)) > 0)
                total += flow;
        }

        return total;
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new ArrayList[52];
        for(int i = 0; i<52; i++){
            board[i] = new ArrayList<>();
        }

        int[][] cap = new int[52][52];
        for(int n = 0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = charToInt(st.nextToken().charAt(0));
            int e = charToInt(st.nextToken().charAt(0));
            int f = Integer.parseInt(st.nextToken());

            cap[s][e] += f;
            cap[e][s] += f;
        }

        for(int i = 0; i<52; i++){
            for(int j = 0; j<52; j++){
                if(cap[i][j] > 0)
                    addEdge(i,j,cap[i][j]);
            }
        }

        bw.write(String.valueOf(maxFlow()));
        bw.flush();
        bw.close();
        br.close();
    }
}
