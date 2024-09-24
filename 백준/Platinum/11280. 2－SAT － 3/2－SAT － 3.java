import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/*
NP Problem
2-Sat
m개의 절로 이루어지고 n개의 변수로 이루어진 논리식을
참으로 만들 수 있으면 1을, 없으면 0을 출력

n개의 변수를 vortex 로
m개의 절을 간선 정보로 나타내면
논리식에서 등장하는 vortex 를 전부 방문하는 경로가 존재하면 -> satisfied
경로가 없으면 -> unsatisfied

모든 정점에 대해 DFS를 수행하여 SCC를 찾는 알고리즘
1) 인접 정점에 방문하며 자기 자신을 스택에 넣고, 재귀적으로 DFS를 수행
2) 인접 정점에 방문했지만, 아직 처리중인 상태일 경우, 작은 값으로 부모값을 갱신
3) 부모 노드의 DFS가 끝난 경우에는, 자신의 id값이 스택에서 나올때까지
스택에 있는 노드들을 pop하고 scc 배열에 추가함.
4) 만들어진 하나의 scc를 전체 SCC 배열에 추가

경로 없으면 false 반환하고 끝내기
 */
public class Main {
    private static int N, num;
    private static ArrayList<Integer>[] graph;
    private static boolean[] check;
    private static int[] order;
    private static Stack<Integer> s;

    private static int validate(int n){
        if(n>0 && n < N+1)
            return n;
        return -n+N;
    }
    private static int SCC(int idx){
        order[idx] = ++num; // 노드마다 고유 번호 할당
        s.add(idx); // 스택에 자기 자신 삽입

        int parent = order[idx]; // 처음에는 자기 자신이 부모가 됨.
        for(int next : graph[idx]){
            if(order[next] == 0)
                // 방문하지 않은 이웃
                parent = Math.min(parent, SCC(next));
            // 방문은 됬지만 아직 처리가 안된 노드가 있음
            // 현재 dfs를 수행되고 있는 이웃,
            // 현재 처리되고 있는 이웃이 자신의 부모라면 그것도 SCC에 포함해주기 위해서
            // 같은 부모값을 가지도록 만들어 줌
            else if(!check[next])
                parent = Math.min(parent, order[next]);
        }

        // 만약 자기 자신이 parent인 경우
        // 스택에서 꺼내면서 SCC를 만듬
        if(parent == order[idx]){
            boolean[] visit = new boolean[N+1];
            while(!s.isEmpty()){
                int top = s.pop();
                int temp = validate(top);

                if(temp<0)
                    temp *= -1;
                if(visit[temp])
                    return -1;

                visit[temp] = true;
                check[top] = true;

                // 자기 자신이 나온경우 SCC 끝임
                if(top == idx)
                    break;
            }
        }
        return parent;
    }
    public static void main(String[] args) throws IOException {
        // N = 10,000 .
        // M = 100,000 .
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[2*N+1];
        check = new boolean[2*N+1];
        order = new int[2*N+1];
        s = new Stack<>();

        for(int i = 0; i < 2*N+1; i++){
            graph[i] = new ArrayList<>();
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[validate(-u)].add(validate(v));
            graph[validate(-v)].add(validate(u));
        }

        boolean flag = true;
        for(int i = 1; i< 2*N+1; i++){
            if(!check[i]){
                if(SCC(i) == -1){
                    flag = false;
                    break;
                }
            }
        }
        if(flag)
            bw.write("1\n");
        else
            bw.write("0\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
