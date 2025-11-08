/*
정점 하나 잡고 연결된거중에서 아직 안 방문한거 재귀적으로 방문함
방문할때마다 전역 스택에 넣음
-> 근데 그렇다고 해서 항상 그 사이클로 방문할거라는 보장이없지 않나,,?
-> 방문할때마다 id 값 매기고 가장 작은 id 값을 low로 함
어떤 정점 u에 대해 low u == id u가 되면 u는 root고
u가 나올때까지 pop하면 한 덩어리

자기자신으로 가는 간선도 없고 이어지는것도 없는애들은 어떡하지?
-> 걔네 주변엔 isSCC가 true이고 id값 할당된 애들만 있을거니까
low u == id u일거임
-> 위에걸로 처리됨
 */
import java.util.*;
import java.io.*;
public class Main {
    static int V,E;
    static ArrayList<Integer>[] board;
    static Deque<Integer> stack = new ArrayDeque<>();
    // scc 완료된거 담기
    static ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
    static int cur = 0;
    static int[] id;
    static boolean[] isSCC;
    static int[] low;

    static int dfs(int u){
        // id[u] = 0 미방문 유지하려면 앞에 ++
        id[u] = ++cur;
        low[u] = id[u];
        stack.push(u);

        for(int next : board[u]){
            // id 안매겨짐(방문x)
            if(id[next] == 0){
                low[u] = Math.min(low[u], dfs(next));
            }
            // 방문했는데 ssc아님
            // -> 후보일 수 있음
            else if(!isSCC[next]){
                low[u] = Math.min(low[u], id[next]);
            }
        }

        // u가 현재 scc의 루트정점
        if(low[u] == id[u]){
            ArrayList<Integer> comp = new ArrayList<>();
            // 전부같은 scc소속
            while(true){
                int a = stack.pop();
                isSCC[a] = true;
                comp.add(a);
                if(a == u)
                    break;
            }
            comps.add(comp);
        }

        return low[u];
    }
    static void print(){
        StringBuilder sb = new StringBuilder();
        sb.append(comps.size()).append("\n");

        for(List<Integer> comp : comps)
            Collections.sort(comp);
        comps.sort(Comparator.comparingInt(o->o.get(0)));

        for(List<Integer> comp : comps){
            for(int i : comp){
                sb.append(i).append(" ");
            }
            sb.append("-1\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        board = new ArrayList[V+1];

        for(int i = 1; i<=V; i++)
            board[i] = new ArrayList<>();

        while(E-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            board[s].add(e);
        }

        id = new int[V+1];
        isSCC = new boolean[V+1];
        low = new int[V+1];
        // 전부 연결되있다곤 안함
        for(int i = 1; i<=V; i++)
            if(id[i] == 0)
                dfs(i);

        print();
    }
}
