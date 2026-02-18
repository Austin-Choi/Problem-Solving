/*
1~320만
수열 다구하는건 무리고 K가 고정이라서 f(x)에서 x가 고정이면 결과도 고정임
따라서 그래프인데 간선이 한개인거임.. 굳이 그래프?
그리고 사이클이 있을텐데 해상 사이클의 최소값을 구해놓고 저장해놓고 사이클의 시작값을 i라고 하면

x->next->(next 사이클) .. 형태
dp[x] = Math.min(x,dp[next]); -> x부터 끝까지 최소값
일단 하나씩 계산하는데 함수 그래프 형식임

3-color dfs 써서 하기
visited 0 = 미방문, 1 = 탐색중, 2= 처리완
visited[i] = 1일때
dfs로 x가 다시 나올때까지 지나간 정점들을 싹 돌면서 dp[x]=최소값을 저장함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int A,B,K;
    // 6 * 9^6
    static final int SIZE = 3_200_000;
    static int[] dp;
    static int[] visited;
    // 빠른 제곱계산
    static int[] pow = new int[10];
    static int sfun(int n){
        int sum = 0;
        while(n>0){
            int d = n%10;
            sum += pow[d];
            n /= 10;
        }
        return sum;
    }

    // 버그 : 사이클 뿐만 아니라 전체 min값 갱신해야함
    static void dfs(int x){
        visited[x] = 1;
        int next = sfun(x);

        if(visited[next] == 0){
            dfs(next);
            dp[x] = Math.min(x,dp[next]);
        }
        // 사이클 발견
        else if(visited[next] == 1){
            int cur = next;
            int min = next;

            // 사이클 내부 최소값 구함
            while(true){
                cur = sfun(cur);
                min = Math.min(min, cur);
                if (cur == next)
                    break;
            }

            // 사이클 dp 채움
            cur = next;
            while(true){
                dp[cur] = min;
                visited[cur] = 2;
                cur = sfun(cur);
                if(cur == next)
                    break;
            }

            dp[x] = Math.min(x,min);
        }
        else
            dp[x] = Math.min(x, dp[next]);
        visited[x] = 2;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i<=9; i++){
            pow[i] = (int)Math.pow(i,K);
        }

        dp = new int[SIZE+1];
        visited = new int[SIZE+1];
        Arrays.fill(dp, 0);
        for(int i= 1; i<=SIZE; i++){
            if(visited[i] == 0)
                dfs(i);
        }

        long ans = 0;
        for(int i = A; i<=B; i++)
            ans += dp[i];
        System.out.print(ans);
    }
}
