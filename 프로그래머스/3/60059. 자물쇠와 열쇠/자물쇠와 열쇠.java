/*
key는 공백도 포함해서 적용됨
1) 90도 회전 함수, 
동서남북 이동 함수 x -> 시뮬레이션인데 동서남북해서 완탐하면 구현복잡하고 터짐

항상 key가 lock 이하임. M<=N
2) 좌상단부터 시작해서 3번씩 90도 돌려보고 그 좌성단 위치 
-> 벗어난 경우도 있어서 0,0(좌상단)부터 시작하면 안됨
-> -(M-1)부터 시작해서 N-1까지 하기
*/
class Solution {
    int[][] L;
    int M,N;
    
    // i,j -> j,(size-1)-i
    int[][] rotate(int[][] A, int size){
        int[][] rst = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                rst[j][(size-1)-i] = A[i][j];
            }
        }
        return rst;
    }
    
    // matched 비교할때 lock이 달라지면 안되고 겹치는 부분만 고려해서 비교하기..
    // 모든 lock 칸에 대해서 비교하고 ki,kj를 둬서 현재 i,j일때 key에서 어떤 위치일지 저장
    // 만약 1,1에서 si,sj면 실제로는 0,0이 되어야하니까 i-si j-sj
    boolean isMatched(int[][] K, int si, int sj){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int ki = i - si;
                int kj = j - sj;
                int kv = 0;
                
                if(ki >=0 && ki < M && kj >=0 && kj < M)
                    kv = K[ki][kj];
                // 현재 L[i][j]가 1 이어야 정답조건만족
                if(kv + L[i][j] != 1)
                    return false;
            }
        }
        return true;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        L = lock;
        M = key.length;
        N = lock.length;
        
        for(int si = -(M-1); si<N; si++){
            for(int sj = -(M-1); sj<N; sj++){
                int[][] copy = key;
                
                for(int i = 0; i<4; i++){
                    // if문을 for문 안에 넣고 4번 하게 하면 한번만 써도됨
                    if(isMatched(copy, si, sj))
                        return true;
                    copy = rotate(copy, M);
                }
            }
        }
        
        return false;
    }
}