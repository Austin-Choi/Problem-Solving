
import java.util.*;
import java.io.*;

public class Main {
    private static int[][] dp = new int[1000000][2];
    /*
        1 - 2, 3, 4라는 트리가 있다고 할 때
        1이 가질 수 있는 값 = 자식 노드에서 가능한 최소값
        1은 두가지 상태를 가질 수 있음 (얼리거나 아니거나)
        dp[N][2] = 모두 전파할 수 있는 얼리 최소값
        (i) 얼리가 아닐 경우 : 자식들이 전부 얼리어야 하므로
        자식들의 값을 모두 더해주기
        (ii) 얼리일 경우 : 자식들이 전부 얼리일수도 있고 아닐 수도 있음.
        자식들의 값 중 가장 작은 값으로 설정
        리프노드부터 계산해서 루트노드까지 계산하고
        루트 노드값 반환하기
     */
    public static boolean[] check;
    public static void dfs(int node){
        check[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;
        for(int next : nodeList.get(node)){
            if(!check[next]){
                // 이렇게 하면 재귀로 타고들어가서 리프노드 dp값부터 아래서부터 위로 갱신됨
                dfs(next);
                // 현재 노드가 얼리가 아니면 자식노드가 전부 얼리여야 함
                dp[node][0] += dp[next][1];
                // 현재 노드가 얼리라면
                // 자식 노드가 얼리든 아니든 상관없어서 그중 최소값을 뽑음
                dp[node][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
    public static ArrayList<ArrayList<Integer>> nodeList;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        //Node head = new Node(1, null);
        check = new boolean[N+1];
        nodeList = new ArrayList<>();
        for(int nn = 0; nn <= N; nn++){
            nodeList.add(new ArrayList<>());
        }
        for(int nn = 0; nn < N-1; nn++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodeList.get(u).add(v);
            nodeList.get(v).add(u);
        }

        dfs(1);
        //노드 1까지 타고 올라온 dp값의 최소 값이 정답
        int answer = Math.min(dp[1][0], dp[1][1]);
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
