import java.util.*;
/*
탐색할때 그냥 dfs적으로 최적 늑대를 찾는건지
문제 설명을 보면 맨 아래에 양이 있어서 거기까지 가도 뭐 늑대가 따라와서 안된다고 하는데
그냥 어차피 거기까지 가도 결과적으로 현재 경로상 양 > 늑대기만 하면 되는거 아닌가

*/
class Solution {
    int[] parent;
    ArrayList<Integer>[] g;
    int ans = 0;
    int[] nodeInfo;
    
    // cur양, cur늑, 방문할 수 있는 집합
    void dfs(int curSheep, int curWolf, ArrayList<Integer> cand){
        ans = Math.max(ans, curSheep);
        
        for(int i = 0; i<cand.size(); i++){
            int n = cand.get(i);
            int nSheep = 0;
            int nWolf = 0;
            if(nodeInfo[n] == 0)
                nSheep++;
            else
                nWolf++;
            if(curSheep + nSheep > curWolf + nWolf){
                ArrayList<Integer> nextCand = new ArrayList<>(cand);
                nextCand.remove(i);
                for(int c : g[n]){
                    nextCand.add(c);
                }
                dfs(curSheep + nSheep, curWolf + nWolf, nextCand);
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        parent = new int[info.length];
        g = new ArrayList[info.length];
        nodeInfo = info;
        
        for(int i = 0; i<info.length; i++){
            g[i] = new ArrayList<>();
        }
        for(int[] e : edges){
            parent[e[1]] = e[0];
            g[e[0]].add(e[1]);
        }
        
        dfs(1,0,g[0]);
        return ans;
    }
}