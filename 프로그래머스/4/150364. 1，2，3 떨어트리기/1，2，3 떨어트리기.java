/*
1번이 루트노드
숫자를 떨어트리는 모든 경우 중 가장 적은 숫자를 사용하며 그중 사전 순으로 가장 빠른 경우

drop으로 next(현재 노드가 가르키는 다음 노드) 리프 노드 아닐때만 조건에 맞게 길 바꾸고
최악의 방문 횟수는 모든 리프 노드의 합을 1로 채우는 거임 -> target 다 더해주기

*/
import java.util.*;

class Solution {
    ArrayList<Integer>[] g;
    int[] next;
    int N;

    // 현재 길로 떨어뜨렸을 때 도착하는 리프 반환 + 다음 길 고르기
    int drop() {
        int cur = 1;
        while (!g[cur].isEmpty()) {
            int child = g[cur].get(next[cur]);
            next[cur] = (next[cur] + 1) % g[cur].size();
            cur = child;
        }
        return cur;
    }

    public int[] solution(int[][] edges, int[] target) {
        N = target.length;
        g = new ArrayList[N + 1];
        next = new int[N + 1];

        for (int i = 1; i <= N; i++) 
            g[i] = new ArrayList<>();
        for (int[] e : edges) 
            g[e[0]].add(e[1]);
        
        for (int i = 1; i <= N; i++) 
            Collections.sort(g[i]);

        // 방문 횟수만 시뮬레이션해서 최소 길이 찾기 
        int[] cnt = new int[N + 1];
        // 리프 방문 순서
        ArrayList<Integer> order = new ArrayList<>();   
        boolean can = false;
        int maxDrop = 0;
        // 최악의 경우 limit
        for (int t : target) 
            maxDrop += t;         

        for (int dropCnt = 1; dropCnt <= maxDrop; dropCnt++) {
            int leaf = drop();
            order.add(leaf);
            cnt[leaf]++;

            boolean ok = true;
            for (int i = 1; i <= N; i++) {
                // 리프만 검사
                if (g[i].isEmpty()) {               
                    int c = cnt[i];
                    int t = target[i - 1];
                    
                    // 한 리프 노드에 숫자가 c번 떨어졌을 때, 
                    // 그 합을 정확히 t로 만들 수 있는지 검사
                    if (c > t || t > 3 * c) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                can = true;
                break;
            }
        }

        if (!can) 
            return new int[]{-1};

        // 찾은 최소 길이의 방문 순서에 대해 사전순 최소 숫자 채우기
        int len = order.size();
        // 남은 합
        int[] remain = target.clone();
        // 남은 방문 횟수
        int[] remainCnt = new int[N + 1];           
        for (int leaf : order) 
            remainCnt[leaf]++;

        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            int leaf = order.get(i);
            
            // 가능한 가장 작은 숫자 선택
            for (int v = 1; v <= 3; v++) {
                int afterSum = remain[leaf - 1] - v;
                int afterCnt = remainCnt[leaf] - 1;
                
                // 남은 방문으로 남은 합을 만들 수 있는지
                if (afterCnt <= afterSum && afterSum <= 3 * afterCnt) {
                    answer[i] = v;
                    remain[leaf - 1] -= v;
                    remainCnt[leaf]--;
                    break;
                }
            }
        }
        return answer;
    }
}