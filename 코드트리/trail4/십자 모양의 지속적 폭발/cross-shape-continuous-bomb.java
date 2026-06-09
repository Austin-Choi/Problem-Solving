import java.util.*;
import java.io.*;

/*
1) 골라진거 값 읽어서 동서남북으로 일단 터뜨리고
2) 모든 열 보면서 중력처리함
-> 아래서부터 다시 읽은 배열 A 만들고 0아닐때만 땡겨서 저장
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N,M;
    static int[][] board;

    // 열 선택했을때 i = [0,N-1]방향으로 내려가면서 첫번째 0이 아닌 i,j 반환하기
    // 없으면 -1,-1
    static int[] find(int j){
        for(int i = 0; i<N; i++){
            if(board[i][j] != 0){
                return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }

    // 선택한 값 -1 만큼 동서남북으로 board 0 만들기
    static void bomb(int si, int sj){
        int size = board[si][sj];
        for(int d = 0; d<4; d++){
            for(int n = 0; n<size; n++){
                int ni = si + n*di[d];
                int nj = sj + n*dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    break;
                board[ni][nj] = 0;
            }
        }
        // System.out.print("afterbomb:\n");
        // print();
        // System.out.println();
    }
    //디버그용1
    static void pa(int[] t){
        System.out.println("pa : ");
        for(int i = 0; i<t.length; i++){
            System.out.print(t[i]+" ");
        }
        System.out.println();
        System.out.println();
    }

    // 모든 열마다 중력처리하기
    // 땡기는게 또 안되고있음;;
    // 1 2 0 3
    // 1 2 3
    static void pullDown(){
        for(int j = 0; j<N; j++){
            int[] A = new int[N];
            for(int i = N-1; i>=0; i--){
                A[N-1-i] = board[i][j];
            }
            //pa(A); ->정상

            int[] temp = new int[N];
            int zeroCount = 0;
            for(int i = 0; i<N; i++){
                if(A[i] != 0){
                    temp[i-zeroCount] = A[i];
                }
                else{
                    zeroCount++;
                }
            }
            //pa(temp);

            for(int i = 0; i<N; i++){
                board[N-1-i][j] = temp[i];
            }
        }
    }

    //print
    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }
        while(M-->0){
            int c = read()-1;
            int[] pos = find(c);
            if(pos[0] == -1)
                continue;
            bomb(pos[0], pos[1]);
            pullDown();
        }
        print();
    }
}