/*
모든 시곗바늘이 12시를 향해야함
자신의 위쪽은 자신만이 영향을 줌
clockhands의 길이가 최대 8이니까 비트마스크로 1행 상태 표현?

1) 첫번째 열 완탐해서 비트마스크로 표현함
->근데 상태가 4개임.. 2비트씩 할당
2) 윗 열 상태 결정되면 사실상 아래도 따라감
-> 첫 열 완탐 + 그리디?
*/
class Solution {
    // 북동남서
    int[] di = {-1, 0, 1, 0};
    int[] dj = {0, 1, 0, -1};
    
    public int solution(int[][] clockHands) {
        int N = clockHands.length;
        int M = clockHands[0].length;
        int limit = 1 << (2*N);
        int ans = Integer.MAX_VALUE;
        
        for(int mask = 0; mask < limit; mask++){
            // mask마다 돌려볼 clockhands 복사본만들기
            int[][] copy = new int[N][M];
            for(int n = 0; n<N; n++){
                for(int m = 0; m<M; m++){
                    copy[n][m] = clockHands[n][m];
                }
            }
            
            // mask마다 조작횟수
            int sum = 0;
            for(int j = 0; j<M; j++){
                // 현재 첫번째열 마스크에서 각 칸마다 움직일 횟수 추출하고 직접돌리기
                int count = (mask >> (2*j)) % 4;
                // 자기 자신 먼저돌림
                copy[0][j] = (copy[0][j] + count) % 4;
                sum += count;

                for(int d = 0; d<4; d++){
                    int ni = 0+di[d];
                    int nj = j+dj[d];
                    if(ni < 0 || ni >= N || nj < 0 || nj >= M) 
                        continue;
                    copy[ni][nj] = (copy[ni][nj] + count) % 4;
                }
            }
            
            // 첫번째 열 count계산해주고 아래는 따라가니까 i=1로
            for(int i = 1; i<N; i++){
                for(int j = 0; j<M; j++){
                    int count = (4 - copy[i-1][j]) % 4;
                    copy[i][j] = (copy[i][j] + count) % 4;
                    sum+= count;
                    
                    for(int d = 0; d<4; d++){
                        int ni = i+di[d];
                        int nj = j+dj[d];
                        if(ni < 0 || ni >= N || nj < 0 || nj >= M) 
                            continue;
                        copy[ni][nj] = (copy[ni][nj] + count) % 4;
                    }
                }
            }
            
            // 마지막 열이 모두 0일때 갱신
            boolean isAllZero = true;
            for(int j = 0; j<M; j++){
                if(copy[N-1][j] != 0){
                    isAllZero = false;
                    break;
                }
            }

            if(isAllZero)
                ans = Math.min(ans, sum);
        }
        return ans;
    }
}