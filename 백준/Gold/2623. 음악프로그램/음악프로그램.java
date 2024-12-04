import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 가수의 수
        int m = Integer.parseInt(st.nextToken()); // PD의 수

        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1]; // 진입 차수 배열

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken()); // PD가 정한 순서의 가수 수
            int prev = Integer.parseInt(st.nextToken());

            for (int j = 1; j < count; j++) {
                int curr = Integer.parseInt(st.nextToken());
                graph.get(prev).add(curr); // prev → curr
                inDegree[curr]++; // curr의 진입 차수 증가
                prev = curr;
            }
        }

        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        // 진입 차수가 0인 노드를 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            for (int next : graph.get(current)) {
                inDegree[next]--; // 간선 제거
                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // 결과 출력
        if (result.size() != n) {
            System.out.println(0); // 사이클이 존재하여 정렬 불가능
        } else {
            for (int singer : result) {
                System.out.println(singer);
            }
        }
    }
}
