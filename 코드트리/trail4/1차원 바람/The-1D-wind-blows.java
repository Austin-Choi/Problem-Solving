import java.util.*;
import java.io.*;

/*
0) void wind(입력 행, 입력 방향 dir){
shift(입력행, dir)
updir = 입력dir
downdir = 입력dir

    for(int i = 입력행, i>=1; i--){
        while(hasSameVal(i, i-1)){
            updir = 1 - updir
            shift(i-1, updir)
        }
    }

    for(int i = 입력행, i<N-1; i++){
        while(hasSameVal(i, i+1)){
            downdir = 1 - downdir
            shift(i+1, downdir)
        }
    }
}


1) void shift(원본 행, 방향){
원본 배열에서 원본 행 크기 copy 배열 준비함
방향에 맞춰서 한칸씩 밀기
-> copy 값 원본 배열에 적용
}

2) boolean hasSameVal(기준 행, 비교할 행){
for(int j= 0; j<M; j++){
if(기준행[j] == 비교행[j])
    return true
}
return false
}


*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static char rc() throws IOException{
        sst.nextToken();
        return (char) sst.sval.charAt(0);
    }

    static int N,M, Q;
    static int[][] board;

    static boolean hasSameVal(int a, int b){
        for(int j = 0; j<M; j++){
            if(board[a][j] == board[b][j])
                return true;
        }
        return false;
    }

    static void shift(int a, int dir){
        int[] copy = new int[M];

        // 왼 -> 오
        if(dir == 1){ 
            for(int j=0;j<M;j++){
                copy[(j+1)%M] = board[a][j];
            }
        }
        // 오 -> 왼
        else{ 
            for(int j=0;j<M;j++){
                copy[(j-1+M)%M] = board[a][j];
            }
        }

        board[a] = copy;
    }

    //in-place 버전 copy 안쓰는거
    // -> 방향 구현하면서 inplace에서 30분 넘게 구현문제 걸림
    // -> 템플릿 : 왼->오 면 맨 오른쪽 temp, [M-2, 0] -> board[j+1] = board[j] 해주고 board[0] = temp;
    // 오->왼 이면 맨 왼쪽 temp, [1, M-1] -> board[j-1] = board[j] 해주고 board[M-1] = temp;
    static void shift2(int a, int dir){
        // 왼->오
        if(dir == 1){
            int temp = board[a][M-1];
            for(int j=M-2;j>=0;j--){
                board[a][j+1] = board[a][j];
            }
            board[a][0] = temp;
        }
        // 오->왼
        else{
            int temp = board[a][0];
            for(int j=1;j<M;j++){
                board[a][j-1] = board[a][j];
            }
            board[a][M-1] = temp;
        }
    }

    static void wind(int srow, int sdir){
        shift2(srow, sdir);

        int updir = sdir;
        int downdir = sdir;
        int uprow = srow;
        int downrow = srow;

        // 위쪽 전파하고 아래쪽 전파 한번씩 하고 다시 
        while((uprow>=1) && hasSameVal(uprow, uprow-1)){
            updir = 1 - updir;
            shift2(uprow-1, updir);
            uprow--;
        }

        while((downrow < N-1) && hasSameVal(downrow, downrow+1)){
            downdir = 1- downdir;
            shift2(downrow+1, downdir);
            downrow++;
        }
    }

    static StringBuilder sb = new StringBuilder();
    static void boardPrint(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }


    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        Q = read();
        board = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] = read();
            }
        }

        while(Q-->0){
            int row = read()-1;
            char type = rc();
            // dir = 1이 왼->오, 0이 오->왼
            int dir = 0;
            if(type == 'L')
                dir = 1;
            
            wind(row, dir);
        }
        boardPrint();
    }
}