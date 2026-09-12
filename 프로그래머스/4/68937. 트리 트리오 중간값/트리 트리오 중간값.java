import java.util.*;
/*
트리니까 경로는 항상 한가지임
f의 두번째로 큰 값이 최대가 되려면 셋중 최댓값은 트리 내부에서 가장 긴 값이어야 함 
-> 트리의 지름

A,B가 트리의 지름 양 끝이라면
A에서 가능한 트리의 지름 다른 후보 갯수와
B에서 가능한 후보 갯수를 세서 각자 2개 이상이면 지름을 그대로 리턴하고
아니면 트리의 지름, 트리의 지름-1, 1 이런형식이니
트리의 지름 -1 리턴함
*/

class Solution {
    int aaa = 0;
    ArrayList<Integer>[] g;
    // 트리 지름구하기
    int[] bfs(int start, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : g[cur]) {
                if (dist[next] != -1) {
                    continue;
                }

                dist[next] = dist[cur] + 1;
                q.add(next);
            }
        }

        return dist;
    }

    public int solution(int n, int[][] edges) {
        g = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            g[a].add(b);
            g[b].add(a);
        }

        // 1. 임의의 정점 1에서 가장 먼 정점 A 찾기
        int[] dist = bfs(1, n);
        int A = 1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > dist[A]) {
                A = i;
            }
        }

        // 2. A에서 가장 먼 정점 B 찾기
        dist = bfs(A, n);
        int B = A;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > dist[B]) {
                B = i;
            }
        }

        // A에서 가장 먼 정점이 여러 개라면
        // A와 그 중 2개를 선택해서 지름을 만들 수 있음
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == dist[B]) {
                count++;
            }
        }

        if (count >= 2) {
            return dist[B];
        }

        // B에서 다시 가장 먼 정점들을 확인
        dist = bfs(B, n);
        int diameter = dist[A];
        count = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == diameter) {
                count++;
            }
        }

        if (count >= 2) {
            return diameter;
        }

        return diameter - 1;
    }
}
