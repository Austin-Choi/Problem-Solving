/*
부모 -> 리프 방향으로
1) 부모가 초대되면 자식들은 초대 불가능
2) 자식 초대되면 부모는 불가능하지만 다른 자식들은 가능함
초기화 -> dp[u][1] = W[u]
-> dp[u][2] = u를 초대/안초대 서브트리 최대 점수

추적
dfs로 사장 고정해놓고 현재 선택됬으면 다음거 선택 x
안했으면 다음거 선택된게 선택 안한거보다 큰지 bool값 가지고 ㄱ
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] W;
    static long[][] dp;
    static ArrayList<Integer>[] board;
    static ArrayList<int[]>[][] choice;

    // 추적할때 또 비교하지말고 dp할때 경로 저장
    static void dfs(int u){
        for(int v : board[u]){
            dfs(v);

            //u 선택
            dp[u][1] += dp[v][0];
            choice[u][1].add(new int[]{v,0});

            //u 선택 x
            if(dp[v][1] > dp[v][0]){
                dp[u][0] += dp[v][1];
                choice[u][0].add(new int[]{v,1});
            }
            else{
                dp[u][0] += dp[v][0];
                choice[u][0].add(new int[]{v,0});
            }
        }
    }

    static ArrayList<Integer> output;
    static void findChoices(int u, int s){
        if(s == 1)
            output.add(u);

        for(int[] info : choice[u][s]){
            findChoices(info[0], info[1]);
        }
    }

    static StringBuilder sb = new StringBuilder();
    static void print(){
        Collections.sort(output);
        for(int i = 0; i<output.size(); i++){
            sb.append(output.get(i)+1);
            sb.append(" ");
        }
        sb.append("-1\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        W = new int[N];
        for (int i = 0; i<N; i++){
            W[i] = Integer.parseInt(st1.nextToken());
        }
        board = new ArrayList[N];
        for(int i = 0; i<N; i++)
            board[i] = new ArrayList<>();

        st1 = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N-1; i++){
            board[Integer.parseInt(st1.nextToken())-1].add(i);
        }
        choice = new ArrayList[N][2];
        for(int i = 0; i < N; i++){
            choice[i][0] = new ArrayList<>();
            choice[i][1] = new ArrayList<>();
        }
        dp = new long[N][2];
        dp[0][0] = 0;
        for(int i = 0; i<N; i++){
            dp[i][1] = W[i];
        }
        dfs(0);

        sb.append(dp[0][1]).append(" ").append(dp[0][0]).append("\n");
        output = new ArrayList<>();
        findChoices(0, 1);
        print();

        output = new ArrayList<>();
        findChoices(0, 0);
        print();

        System.out.print(sb);
    }
}