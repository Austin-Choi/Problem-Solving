import java.util.*;
/*
dlru
남서동북

일반 bfs로 풀면 상태가 많아질 수 있음.
남서동북으로 선택을 고르되 홀짝성 판정해야하함
*/

class Solution { 
    String[] itos = {"d", "l", "r", "u"}; 
    int[] di = {1,0,0,-1};
    int[] dj = {0,-1,1,0};
    
    // 탈출지점, 출발지점 둘다 1-based
    public String solution(int n, int m, int si, int sj, int ei, int ej, int k) {
        si -= 1;
        sj -= 1;
        ei -= 1;
        ej -= 1;
        
        int dist = Math.abs(si - ei) + Math.abs(sj - ej);
        // 가야하는 맨해튼 거리가 K를 넘어가면 도달 못함
        // 홀수여도 왓다갓다로 못채우니 못함
        if(dist > k || (k-dist) % 2 != 0)
            return "impossible";
        
        String ans = "";
        int ci = si;
        int cj = sj;
        int ck = 0;
        
        while(ck < k){
            // !! 이거 넣고 돌리면 마지막에 왔다갔다하면서 채우는 경우의 수 사라짐
            // if(ci == ei && cj == ej)
            //     break;
            
            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                
                if(ni < 0 || nj < 0 || ni >= n || nj >= m)
                    continue;
                // 현재까지 가고 남은거리
                int remain = k - (ck+1);
                int nextDist = Math.abs(ni - ei) + Math.abs(nj - ej);
                
                // K번 안에 도착가능? 혹은 왓다갓다로 채우기 가능?
                if(nextDist <= remain && (remain - nextDist) % 2 == 0){
                    ans += itos[d];
                    ci = ni;
                    cj = nj;
                    ck++;
                    break;
                }
            }
        }
        return ans;
    }
}