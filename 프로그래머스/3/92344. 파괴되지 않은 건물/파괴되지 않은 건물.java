import java.util.*;

/*
    누적합 사고과정
    원본이 왼쪽이라 하고 오른쪽은 자리번호다.
    0 0 0   1 2 3
    0 0 0   4 5 6
    0 0 0   7 8 9
    의 
    1 2 4 5번을 2로 바꾸고 싶다 하면

    2 0 -2
    0 0 0
    -2 -2 0
    으로 하면 된다. 

    일단 첫번째 줄만 살펴보면
    왼쪽에서 오른쪽으로 누적합을 해주면(젤 왼쪽값을 그냥 계속 더해주는 것)
    2 0 -2 -> 2 2 0 이 된다. 

    2 2 0
    0 0 0
    0 0 0  이 되는데 

    2 2 0
    2 2 0 
    0 0 0 내가 바라는건 이거다. 그러면 어떻게 해줘야하지??

     2  2 0
     0  0 0
    -2 -2 0
    이렇게 세팅하고 위에서 아래로 누적합을 해주면

    2 2 0
    2 2 0
    0 0 0 이 된다. 

    이제 문제에 적용을 해보면
    그렇다면 skill을 차례로 읽어서
    (2, 0, 0, 1, 1, 2) -> cmd
     2 0 -2
     0 0  0
    -2 0  2 으로 세팅해두면
    
    이렇게 세팅해두는걸 반복하면 최종 skillBoard에는 세팅값들만 남아있게 되고,
    그걸 왼쪽에서 오른쪽으로 누적합해주고 
    위에서 아래로 누적합해주고 
    기존 보드와 더해주면 -> profit!
    
    2 2 0
    0 0 0
    -2 -2 0 -> 가로로 모든 행에 대해 누적합 결과
    
    2 2 0
    2 2 0
    0 0 0 -> 세로로 모든 열에 대해 누적합 결과

    그래서 skill을 불러오는 것은 O(K)의 시간 복잡도
    누적합을 수행함으로써 걸리는 시간 복잡도는 O(NM)이 되고 
    문제대로 읽고 풀어서 완탐을 수행하면 O(KMN)이 되던 것이 
    누적합으로 풀면 O(K+NM)이 된다.
*/

    // 2차원 누적합
    // 0으로 구성된 int[N+1][M+1] 보드 만들고 
    // i1, j1 = degree;
    // i1, j2+1 = -1*degree;
    // i2+1, j1 = -1*degree;
    // i2+1, j2+1 = degree;
    // 으로 설정해주고 반복

    // 젤 윗줄 누적합 왼쪽에서 오른쪽으로 수행
    // 이제 젤 윗줄 기준으로 위에서 아래로 누적합 수행

    // 기존 보드값과 더해주기 

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int M = board[0].length; // 가로
        int N = board.length; // 세로
        
        int[][] sBoard = new int[N+1][M+1];
        
        for(int[] cmd : skill){
            int type = -1;
            if(cmd[0] == 2)
                type = 1;
            int degree = type*cmd[5];
            // 1,2 = dgre
            // 1,4(+1) = -dgre
            // 3(+1),2 = -dgre
            // 3(+1),4(+1) = dgre
            sBoard[cmd[1]][cmd[2]] += degree;
            sBoard[cmd[1]][cmd[4]+1] -= degree;
            sBoard[cmd[3]+1][cmd[2]] -= degree;
            sBoard[cmd[3]+1][cmd[4]+1] += degree;
        }
        
        int[][] total = new int[N+1][M+1];
        //누적합해주기 왼 -> 오
        for(int i = 0; i<N+1; i++){
            total[i][0] = sBoard[i][0];
            for(int j = 1; j<M+1; j++){
                total[i][j] = sBoard[i][j] + total[i][j-1];
            }
        }
        // 위 -> 아래
        for(int j = 0; j<M+1; j++){
            for(int i = 1; i<N+1; i++){
                total[i][j] += total[i-1][j];
            }
        }
            
        int answer = 0;    
        for(int i = 0; i< N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] += total[i][j];
                if(board[i][j]>0)
                    answer++;
            }
        }
        return answer;
    }
}