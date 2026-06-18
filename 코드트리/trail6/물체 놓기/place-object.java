import java.util.*;
import java.io.*;

/*
단방향 간선이라는데 그러면 i->j랑 j->i 값이 다를수 있다는걸 염두해두고 풀어야한다는건가?
모든 정점에 물체를 놓는 최소 비용이라 하면 
이미 물체가 존재하는 정점에서 연결만 하면 해당 정점 놓는 물체 비용이 0이 됨
-> 가장 적은 직접물체 비용 정점에서 시작해서 최소 가중치 cost로 MST 구성하기
3
4->1->2->3
0 2 4 7

-----------
반례 : 직접 설치를 여러번 하는게 더 최적일 수 있음
-> 가상 정점을 만들어서 설치도 가중치로 넣기

완전그래프의 경우 배열 기반 풀이가 훨씬 빠르다.
-> 우선순위 큐 풀이 -> N^2 log V
-> 배열 풀이 -> N^2
*/

public class Main {
    static StreamTokenizer sst =
        new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] cost;

    static long prim() {
        boolean[] v = new boolean[N + 1];
        int[] minEdge = new int[N + 1];

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[0] = 0;

        long sum = 0;

        for (int cnt = 0; cnt <= N; cnt++) {
            int cur = -1;

            // 아직 방문하지 않은 정점 중 가장 싼 정점 선택
            for (int i = 0; i <= N; i++) {
                if (!v[i] && (cur == -1 || minEdge[i] < minEdge[cur])) 
                    cur = i;
            }

            v[cur] = true;
            sum += minEdge[cur];

            // 비용 갱신
            for (int next = 0; next <= N; next++) {
                if (!v[next]) 
                    minEdge[next] = Math.min(minEdge[next], cost[cur][next]);
            }
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        N = read();
        cost = new int[N + 1][N + 1];

        // 가상 정점 0 -> i
        for (int i = 1; i <= N; i++) {
            int c = read();
            cost[0][i] = c;
            cost[i][0] = c;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cost[i][j] = read();
            }
        }

        System.out.print(prim());
    }
}