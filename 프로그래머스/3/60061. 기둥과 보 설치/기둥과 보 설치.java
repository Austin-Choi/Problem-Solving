/*
기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나 다른 기둥 위에 있어야함
보는 한쪽 끝 부분이 기둥 위에 있거나 양쪽 끝 부분이 다른 보와 동시에 있어야함
-> 새로운 명령만 유효한지 보고 유효하다면 적용하면 됨

기둥 좌표는 기둥의 아래 좌표
보 좌표는 기둥의 왼쪽 좌표 
겹치게 주어지진 않는다하니 좌표 자체를 long으로 해싱해서 키값으로 넣고 이게 보인지 기둥인지 구분하기? value로

그럼 데이터에 기둥과 보의 시작과 끝, 기둥 보 구분을 넣고 
설치했을때 validation, 삭제했을때 각자 함수로 

전체 구조물은 뭘로 나타내야 적합하지
-> board[i][j][type] = i,j 시작점 , 기둥인지 보인지 존재하는지
result의 형식이 곧 데이터 랑 같이 가면 될거같은데 

build, delete는 일단하고 validation 돌려서 체크하고 맞으면 그대로두고아니면 돌려놓기
*/
import java.util.*;
class Solution {
    boolean[][][] board;
    boolean isValid(){
        int N = board.length;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int k = 0; k<2; k++){
                    if(!board[i][j][k])
                        continue;
                    
                    // 기둥일때 적합성
                    if(k == 0){
                        boolean b = false;
                        // 바닥
                        if(i == 0)
                            b = true;
                        // 보의 한쪽 끝 위에 있기
                        else if((j > 0 && board[i][j-1][1]) || board[i][j][1])
                            b = true;
                        // 다른 기둥 위에 있기
                        else if(board[i-1][j][0])
                            b = true;
                        if(!b)
                            return false;
                    }
                    // 보일때 적합성
                    else{
                        boolean b = false;
                        // 한쪽 끝 부분이 기둥 위에 있기
                        if(i > 0 && (board[i-1][j][0] || board[i-1][j+1][0]))
                            b = true;
                        // 양쪽 끝 부분이 다른 보와 동시에 있기
                        else if(j < N-1 && j > 0 && board[i][j-1][1] && board[i][j+1][1])
                            b = true;
                        if(!b)
                            return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public int[][] solution(int n, int[][] cmds) {
        board = new boolean[n+1][n+1][2];
        int cnt = 0;
        for(int[] cmd : cmds){
            int x = cmd[0];
            int y = cmd[1];
            int a = cmd[2];
            int b = cmd[3];
            
            if(b == 1){
                board[y][x][a] = true;
                
                if(isValid())
                    cnt++;
                else
                    board[y][x][a] = false;
            }
            else{
                board[y][x][a] = false;
                
                if(isValid())
                    cnt--;
                else
                    board[y][x][a] = true;
            }
        }
        
        int[][] rst = new int[cnt][3];
        int idx = 0;
        for(int j = 0; j<=n; j++){
            for(int i = 0; i<=n; i++){
                for(int k = 0; k<2; k++){
                    if(board[i][j][k]){
                        rst[idx++] = new int[]{j,i,k};
                    }
                }
            }
        }
        return rst;
    }
}