import java.util.*;
/*
prefix max + sweepline
다른 임의의 사원보다 두 점수가 모두 낮은 경우를 n log n에 탐지하던가 n에 탐지해야할거같은데
a를 기준으로 정렬하고 a가 클때를 보면서 b 최댓값을 갱신함
*/

class Solution {
    public int solution(int[][] scores) {
        int wa = scores[0][0];
        int wb = scores[0][1];
        
        Arrays.sort(scores, (a,b)->{
            if (a[0] != b[0]) {
                return Integer.compare(b[0], a[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        
        int N = scores.length;

        // 현재 그룹보다 a가 더 큰 사람들의 b최대값
        int maxB = -1;
        // 완호보다 큰 인센티브 대상자 수
        int cnt = 0;
        
        // 나보다 a가 큰 사람들 중에서 b도 큰 사람이 존재하는가
        int i = 0;
        while(i<N){
            int a = scores[i][0];
            int curmax = 0;
            // i는 그룹의 시작점이므로 j로 따로 관리
            int j = i;
            while(j < N && a == scores[j][0]){
                if(curmax < scores[j][1]){
                    curmax = scores[j][1];
                }
                j++;
            }
            
            // 현재 그룹 검사
            for(int k = i; k<j; k++){
                int b = scores[k][1];
            
                if(maxB > b){
                    // 완호 본인인지 체크
                    if(a == wa && b == wb)
                        return -1;
                    continue;
                }
                
                // 인센티브 대상이고 완호 합보다 점수가 더 큼
                if(a + b > wa+wb)
                    cnt++;
            }
            
            // 현재 그룹을 다음 그룹의 비교대상으로 추가
            maxB = Math.max(maxB, curmax);
            // 처리한 그룹크기만큼 넘어가기
            i=j;
        }
        return cnt+1;
    }
}