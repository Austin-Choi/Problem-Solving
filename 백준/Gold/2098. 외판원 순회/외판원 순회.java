/*
비용 1,000,000 * 간선 갯수 16*16=256
limit = 256,000,000
한번 갔던 도시로는 다시 방문 x -> 비트마스크 쓰기
어느 한 도시를 출발해 N개의 도시를 모두 거쳐 원래 도시로 돌아오는 순회 여행 경로를 계획
항상 순회할 수 있는 경우만 입력으로 들어옴
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    각 도시에서 출발하여 나머지 도시를 모두 방문하고, 각 부분의 최소 비용을 기록/갱신
    상태 : 현재까지 방문한 도시들의 집합 과 현재 위치한 도시
    방문한 도시들을 비트마스크로 표현
    -> N = 4 일때, 1번과 2번 도시를 방문했으면 0110
    -> N = 5 일때, 0번, 2번, 3번 도시를 방문했으면 01101
    -> N = 3 일때, 0번 도시를 방문했으면 001

    dp[mask][i] 지금까지 방문한 도시가 mask 일때, 마지막으로 도시 i를 방문하는 최소 비용을 저장
    -> dp[mask][i] = Math.min(dp[mask][j], dp[
 */

public class Main {
    private static int N;
    private static int[][] cost;
    private static int[][] dp;
    private static int VISIT_ALL;

    // 재귀 + dfs의 형태를 띔 N이 작으니까 가능
    // dfs로 0번 도시부터 방문하지 않은 모든 도시들을 방문하고
    // 출발 도시까지 도달 했을 때 드는 비용들을 구한 뒤 그 비용들 중 최소값을 구하면 됨
    public static int tsp(int mask, int node){
        // 모든 노드를 방문했으면
        if(mask == VISIT_ALL)
            // pos 에서 시작점으로 가는 비용
            // 모든 도시를 방문한 후 출발점으로 돌아갈 때
            // cost[node][0] == 0인 경우 경로가 없으므로, 그 경우는 MAX_VAL 반환
            return cost[node][0] == 0 ? Integer.MAX_VALUE : cost[node][0];

        // 이미 계산된 결과가 있으면 반환
        // 모든 경로에 대해서 계산할 때 남은 경로의 비용을 일일히 구할 필요가 없음
        // -> 남은 경로의 최소비용은 남은 도시들과 목적지가 같다면 어차피 계산하면 같은 값임
        // -> memoization
        if(dp[mask][node] != -1)
            return dp[mask][node];

        // 최소비용 초기화
        // 16 * 16 * 1,000,000
        int minCost = Integer.MAX_VALUE;

        for(int next = 0; next < N; next++){
            // next가 3이고 원래 mask가 3을 방문하지 않은 0011 상태라고 하면
            // 0011 AND 1000을 하면 1<<next 값에 의해 아랫 부분은 죄다 0이 나오는게 고정이므로
            // next 자리 자체만 비교하는 연산이 됨
            // 따라서 -> next를 방문하지 않았는가 와 같은 말임
            // -> boolean check[next] == false;
            if((mask & (1 << next)) == 0 && cost[node][next] != 0){
                // 다음 도시 방문 cost 계산
                // OR 연산은 원래 이진수가 뭐든지 간에 합쳐지니까
                // mask | (1 << next)를 하면 다음 도시를 방문한 상태를 나타내게 됨
                int nextCost = tsp(mask | (1 << next), next);
                if(nextCost != Integer.MAX_VALUE)
                    nextCost += cost[node][next];
                minCost = Math.min(minCost, nextCost);
            }
        }
        return dp[mask][node] = minCost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        // 방문한 도시 상태가 mask이고 현재 방문한 도시가 i일때 최소 비용 저장
        // 1 << N은 N만큼 1을 왼쪽으로 비트를 밀어서 N개의 도시의 모든 방문 상태를 저장할 수 있는 값을 나타냄
        // N칸만큼 표기하므로 N이 매우 작을 때만 사용 가능
        dp = new int[1 << N][N];
        for(int[] row : dp){
            Arrays.fill(row, -1); // dp 계산전 초기값인 -1로 채우기
        }
        // 3개의 노드 방문 여부를 저장한다 하면
        // 1번 옮김 10
        // 2번 옮김 100
        // 3번 옮김 1000
        // 이고 1000 에서 -1 을 하면 0111
        // 즉 3개의 노드를 모두 방문하는 비트마스크로 표현할 수 있으므로
        // VISIT_ALL 은 (1 << N) - 1이 된다.
        VISIT_ALL = (1 << N) - 1;
        cost = new int[N][N];

        // 비트마스킹으로 방문여부 표기하기 때문에 실제 노드가 몇번인지는 해당 자리로 나타내므로 0번부터 담아도 됨
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 출발 도시로 어디로 하던지는 상관 없음
        // -> 사이클이 발생하기 때문에 어디로 시작해도 상관없음

        // 0번 도시에서 시작해서 모든 도시를 방문하는 최소 비용 계산
        // 0번 도시를 방문한 mask 값은 N이 4라고 하면 0001이고
        // 0번 도시부터 시작하므로 node는 0
        bw.write(tsp(1,0)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
