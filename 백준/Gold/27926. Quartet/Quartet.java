/*
4개의 정점으로만 구성된 연결그래프의 가중치 최대화
중앙 간선을 고정하고(정점 고정)
정점 겹치지 않게 4개 정점 잇기

여기서 간선이 많으므로 가중치 큰 순으로 미리 정렬하고
중복 중앙간선 피하기 위해서 정점 오름차순으로도 정렬
top3만 보기
-> top3 중에서 r,l 때문에 건너뛰는 경우 0,0로 넣어서 보완

a-left-right-b말고도
a-left right-b 도 가능함 4명정점이 중요한거니까
 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, d});
            graph[v].add(new int[]{u, d});
        }

        long ans = 0;

        // 가중치 큰 순으로 정렬하는데 정점은 오름차순으로
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i], (o1, o2) -> {
                if (o1[1] != o2[1])
                    return o2[1] - o1[1];
                return o1[0] - o2[0];
            });
        }

        //최상위 2개
        int max1 = 0;
        int max2 = 0;

        for (int i = 1; i <= n; i++) {
            for (int[] x : graph[i]) {
                // 중복 중앙간선 피하기
                if (i > x[0])
                    continue;

                // a-i x-b 같은 두개로 나눠지지만
                // 정점 4개일 때
                if (max1 == 0) {
                    max1 = x[1];
                } else if (max2 == 0) {
                    max2 = Math.min(x[1], max1);
                    max1 = Math.max(x[1], max1);
                } else {
                    int temp = Math.min(x[1], max1);
                    max1 = Math.max(x[1], max1);
                    max2 = Math.max(temp, max2);
                }

                //top3만 보기
                // a-i-x-b
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        int[] a;
                        int[] b;

                        if (j >= graph[i].size())
                            a = new int[]{0, 0};
                        else
                            a = graph[i].get(j);

                        if (k >= graph[x[0]].size())
                            b = new int[]{0, 0};
                        else
                            b = graph[x[0]].get(k);

                        // i!=x는 입력에 의해서 보장
                        if (a[0] == x[0] || b[0] == i || a[0] == b[0])
                            continue;

                        ans = Math.max(ans, (long)x[1] + a[1] + b[1]);
                    }
                }
            }
        }

        ans = Math.max(ans, (long)max1 + max2);
        System.out.println(ans);
    }
}