/*
각 간선의 입장에서
간선이 사용되는 경우를 살펴보면
간선의 자식 노드를 포함한 서브트리(A)와
간선의 부모 노드를 포함한 서브트리(B)로 나눌 수 있다. (트리는 유일경로를 가지므로)
그러면
노드를 A에서 2개 선택하기, B에서 2개 선택하기, A와B에서 각자 한개씩 선택하기 가 잇다.
노드를 고르는건 조합이므로
Ac2 + Bc2 - (A*B)
근데 A는 N-B로 나타낼 수 있고 결국
Nc2 - (N-B*B) 이다.

1. DFS를 통해 루트 노드부터 리프 노드까지 탐색해나가며
각 노드를 루트로 하는 서브트리에 포함된 총 노드 수 구하기
2. 전체 노드 수 - 선택한 간선의 도착지를 루트로 하는 서브트리 총 노드 수
-> B 영역의 노드 갯수

조합 공식
n!/2*(n-2)!
n n-1 n-2 n-3 n-4 ... 1
2* n-2 n-3 n-4 ...1
-> n*n-1 / 2
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static ArrayList<Integer>[] board;
    static boolean[] visited;
    // i번째 노드를 루트로 하는 서브트리 노드 갯수 저장
    static int[] subtree;
    static long comb2(int n){
        return (long) n*(n-1) / 2;
    }
    static int dfs(int cur){
        for(int next : board[cur]){
            if(!visited[next]){
                visited[next] = true;
                subtree[cur] += dfs(next);
            }
        }
        return subtree[cur];
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        board = new ArrayList[N+1];
        subtree = new int[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
            subtree[i] = 1;
        }

        for(int i = 0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            board[s].add(e);
            board[e].add(s);
        }

        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1);

        long ans = 0;
        // 문제 조건에서 루트 노드를 제외한 모든 노드는
        // 1번 간선을 지나게되므로 2번부터 N까지 순회하면 모든 i,j를 고려하게 됨
        for(int i = 2; i<=N; i++){
            ans += comb2(N) - comb2(N-subtree[i]);
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
