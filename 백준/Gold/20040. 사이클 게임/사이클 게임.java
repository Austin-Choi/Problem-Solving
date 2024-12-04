import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수

        parent = new int[n];
        rank = new int[n];

        // 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int result = 0;

        for (int i = 1; i <= m; i++) { // 간선 입력 처리
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (!union(u, v)) { // 이미 같은 집합이라면 사이클 발생
                result = i; // 첫 번째 사이클 발생 간선 번호 저장
                break;
            }
        }

        System.out.println(result);
    }

    // Find 연산 (경로 압축 사용)
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    // Union 연산 (랭크를 기반으로 병합)
    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) { // 이미 같은 집합
            return false; // 사이클 발생
        }

        // 랭크를 기반으로 병합
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true; // 병합 성공
    }
}