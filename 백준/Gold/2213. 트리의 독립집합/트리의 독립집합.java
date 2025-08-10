/*
서로 인접해있지 않은(간선이 없는) 정점의 부분집합
-> 독립 집합

속한 정점의 가중치의 합이 최대가 되게끔
-> 최대 독립 집합
트리의 서브트리에 속한 정점들을 오름차순으로 정렬해서
출력하기
답 -> List<Integer> 형식
각 정점의 가중치 주어지는거 map으로 (int 정점, int 가중치) 저장
그리고 N-1개의 간선 정보 주어지는거
board[u].add v
v . add u

dp[N+1][2] = 정점을 선택했냐 안했냐

이렇게 받고 인접하면 안된다 했으니
현재를 선택하면 자식을 선택할 수 없음, cur 선택했을때의 최대 가중치 합
현재 노드를 선택하지 않으면 dp[child][0]. dp[child][1] 중 큰 값

트리 독립 집합에서 dp
자식 dp를 계산하고 부모 dp를 계산해야함(post order)
1) dfs(cur, parent)로 자식으로 내려감
2) 자식들의 dp 값을 모두 모음
3) 그걸 합산해서 부모 dp 채움

역추적
dfs-> 부모 선택여부를 기준으로(dp)
어떤 노드를 선택할지 확정하고 result에 add

역추적 할때 주의점!
1번에서 시작하면 부모노드는 0(존재x)니까 false로 1번만 호출
그리고 trace 내부에서 자식노드 계산한거 부모노드 계산한거 같을땐
부모노드를 포함하게끔 하기 (>를 >=로 변경)
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static Map<Integer, Integer> m = new HashMap<>();
    static ArrayList<Integer>[] board;
    static int dp[][];
    static boolean[] visited;
    static ArrayList<Integer> result = new ArrayList<>();
    static int ans = 0;
    static void dfs(int cur, int parent){
        visited[cur] = true;
        dp[cur][0] = 0;
        dp[cur][1] = m.get(cur);

        for(int next : board[cur]){
            if(next == parent)
                continue;
            dfs(next, cur);

            dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
            dp[cur][1] += dp[next][0];
        }
    }

    static void trace(int cur, int parent, boolean isParentIncluded){
        if(!isParentIncluded && dp[cur][1] >= dp[cur][0]){
            result.add(cur);
            for(int next : board[cur]){
                if(next != parent)
                    trace(next, cur, true);
            }
        }
        else {
            for(int next : board[cur]){
                if(next != parent)
                    trace(next, cur, false);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            m.put(i, Integer.parseInt(st.nextToken()));
        }
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        dp = new int[N+1][2];
        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u].add(v);
            board[v].add(u);
        }

        visited = new boolean[N+1];
        dfs(1, 0);
        trace(1, 0, false);

        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        for(int n : result){
            ans += m.get(n);
            sb.append(n).append(" ");
        }
        bw.write(ans+"\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
