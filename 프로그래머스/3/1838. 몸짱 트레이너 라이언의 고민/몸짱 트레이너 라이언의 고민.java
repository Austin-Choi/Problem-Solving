/*
1) timetable 해석해서 어떤 시간대에 가장 많이 손님이 몰렸는지 계산
-> 만약 겹친적없으면 0 리턴
2) 그 손님 수로 n길이의 정사각형 공간에 최대한 멀리 떨어지게 배치했을때 그 값 리턴

차분배열 사용해서 들어간시간은 +1, 나간시간은 -1해서
순간의 겹친 count 최대 저장하기

최소 거리 dist를 유지하면서 배치할수 있는 최대 인원이 maxCount 이상인지를 보는 함수로
dist를 최대화하기 -> maximin
-> 문제에서 손님거리는 맨해튼거리
어.. dfs로 하면 가지치기 잘해야할거같은데 파라메트릭 서치?
*/
import java.util.*;
class Solution {
    int N;
    int getDist(int ai, int aj, int bi, int bj){
        return Math.abs(bj-aj) + Math.abs(bi-ai);
    }
    
    // int D, K;
    // int[] v = new int[100];
    // N*N 자리에 count갯수만큼 손님배치 가능한지 보기
//     boolean dfs(int idx, int count){
//         if(count == K)
//             return true;
//         if(idx == N*N)
//             return false;
//         if(count + (N*N-idx) < K)
//             return false;
        
//         int r = idx / N;
//         int c = idx % N;
//         boolean possible = true;
//         for(int i = 0; i<count; i++){
//             int prev = v[i];
            
//             // 현재 dist보다 더 적은 거리로만 배치 가능하면
//             // 이 자리는 놓을 자리가 아님
//             if(getDist(r,c,prev/N,prev%N) < D){
//                 possible = false;
//                 break;
//             }
//         }
        
//         if(possible){
//             v[count] = idx;
//             if(dfs(idx+1, count+1))
//                 return true;
//         }
        
//         return dfs(idx+1, count);
//     }
    
    // dfs면 시복 터지는데? 
    // 첫줄에 첫번째 손님 고정시켜놓고 가능한지
    boolean can(int dist, int K){
        for(int s = 0; s<N; s++){
            // 실제 i번째 손님 배치한 락커 위치 저장
            int[] locker = new int[N*N];
            int count = 1;
            locker[0] = s;
            
            // 나머지 위치 순서대로 확인함
            for(int idx = 0; idx < N*N; idx++){
                int r = idx / N;
                int c = idx % N;
                boolean possible = true;
                
                for(int i =0; i<count; i++){
                    int prev = locker[i];
                    if(getDist(r,c,prev/N,prev%N) < dist){
                        possible = false;
                        break;
                    }
                }
                if(possible){
                    locker[count++] = idx;
                    if(count >= K)
                        return true;
                }
            }
        }
        return false;
    }
    
    public int solution(int n, int m, int[][] tt) {
        N = n;
        
        int[] diff = new int[1322];
        for(int[] t : tt){
            diff[t[0]]++;
            diff[t[1]+1]--;
        }
        
        int count = 0;
        int maxCount = 0;
        for(int d = 600; d<1322; d++){
            count += diff[d];
            maxCount = Math.max(maxCount, count);
        }
        
        if(maxCount <= 1)
            return 0;
        
        int l = 0;
        int r = 2*(N-1);
        int ans = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid, maxCount)){
                ans = mid;
                l = mid +1;
            }
            else
                r = mid -1;
        }
        
        return ans;
    }
}