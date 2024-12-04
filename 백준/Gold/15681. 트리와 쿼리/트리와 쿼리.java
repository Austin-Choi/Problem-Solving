import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] subtreeSize;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 노드 수
        int r = Integer.parseInt(st.nextToken()); // 루트 노드
        int q = Integer.parseInt(st.nextToken()); // 쿼리 수

        // 트리 초기화
        subtreeSize = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 입력
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        // DFS를 이용해 서브트리 크기 계산
        dfs(r);

        // 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(subtreeSize[query]).append("\n");
        }
        System.out.print(sb);
    }

    // DFS로 서브트리 크기 계산
    static int dfs(int node) {
        visited[node] = true;
        subtreeSize[node] = 1; // 자기 자신을 포함
        for (int neighbor : tree.get(node)) {
            if (!visited[neighbor]) {
                subtreeSize[node] += dfs(neighbor); // 자식 노드의 서브트리 크기를 더함
            }
        }
        return subtreeSize[node];
    }
}
