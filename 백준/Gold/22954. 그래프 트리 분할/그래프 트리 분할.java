/*
spanning tree를 만들고 -> dfs로 부모관계, 그걸 잇는 간선 id 저장
서브트리 dfs로 계산함 -> 여기서 sub size가 서로 다르면 거기서 분할점, 분할간선 id 저장
collect -> 분할된 간선 기준으로 sn쪽 서브트리(A) 수집

반례 : 처음부터 트리가 2개고 크기가 달라서 그대로 정답이면 어캄?
1개면 그냥 st->sub size->collect
2개 사이즈 다를대 출력
3개부터 불가능
-> 뭔가 틀리는데 모르겠음 .. 처음부터 다시
 */
/*
모든 정점에 대해서 스패닝 트리를 만듬 visited 공유해야함(중복안되게)
그래서 각자의 소속 정점, 간선을 리스트로 해서 저장함
cn의 크기는 결국 컴포넌트 갯수가 되는데 이거 3개이상이면 안되고
2개일때 사이즈 다른지 봐서 (cn[0],cn[1]) 출력하고
1개면 그냥 하나떼서 출력함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static ArrayList<int[]>[] g;
    static boolean[] visited;
    static List<Integer> ns, es;
    // 스패닝 트리 소속 간선, 노드 저장
    static void dfs(int u){
        for (int[] e : g[u]){
            int v = e[0];
            int id = e[1];

            if(visited[v])
                continue;
            visited[v] = true;

            ns.add(v);
            es.add(id);
            dfs(v);
        }
    }
    static void print(List<Integer> A,List<Integer> B,List<Integer> ea,List<Integer> eb){
        StringBuilder sb=new StringBuilder();
        sb.append(A.size()).append(" ").append(B.size()).append("\n");
        for(int x:A) sb.append(x).append(" ");
        sb.append("\n");
        for(int x:ea) sb.append(x).append(" ");
        sb.append("\n");
        for(int x:B) sb.append(x).append(" ");
        sb.append("\n");
        for(int x:eb) sb.append(x).append(" ");
        System.out.print(sb);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if(N<=2){
            System.out.print(-1);
            return;
        }

        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v,i});
            g[v].add(new int[]{u,i});
        }
        visited = new boolean[N+1];

        // 컴포넌트당 노드 저장, 간선 저장
        List<List<Integer>> cN = new ArrayList<>();
        List<List<Integer>> cE = new ArrayList<>();

        for(int i = 1;i<=N; i++){
            if(visited[i])
                continue;
            if(cN.size()==2){
                System.out.print(-1);
                return;
            }

            ns = new ArrayList<>();
            es = new ArrayList<>();

            visited[i] = true;
            ns.add(i);
            dfs(i);

            cN.add(new ArrayList<>(ns));
            cE.add(new ArrayList<>(es));
        }

        if(cN.size() == 2){
            List<Integer> A = cN.get(0);
            List<Integer> B = cN.get(1);
            if(A.size() == B.size()){
                System.out.print(-1);
                return;
            }
            print(A,B,cE.get(0),cE.get(1));
            return;
        }

        // 하나떼기
        List<Integer> n = cN.get(0);
        List<Integer> e = cE.get(0);
        print(n.subList(0,n.size()-1), List.of(n.get(n.size()-1)), e.subList(0,e.size()-1), new ArrayList<>());
    }
}
