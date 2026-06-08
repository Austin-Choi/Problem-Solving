import java.util.*;
import java.io.*;

/*
동서남북 방향 고르고
r이면 i행마다 N-1부터 보고
l이면 i행마다 0부터 보고 
u면 j열마다 0부터 보고
D면 j열마다 N-1부터 봄

i = 0;
while(A[i] == 0)
    i++
prev = A[i++];

prev = 현재면 두개 더해서 li에 넣기 
-> 그리고 나서 prev = [i+1] i++
아니라면 li에 그냥 넣고 prev = [i]
0은 continue

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

    static int N;
    static int[][] board;

    static int[] move(int[] A){
        ArrayList<Integer> li = new ArrayList<>();
        // 0 제거
        for(int x : A){
            if(x != 0)
                li.add(x);
        }

        int[] rst = new int[N];
        int idx = 0;

        // 합치기
        for(int i = 0; i<li.size(); i++){
            if(i+1 < li.size() && li.get(i).equals(li.get(i+1))){
                rst[idx++] = li.get(i)*2;
                i++;
            }
            else
                rst[idx++] = li.get(i);
            
        }
        return rst;
    }

    static void tilt(char dir){
        /*
        A로 보고
        둘이 더하면 앞쪽에 더한값 넣고 뒤에는 0
        나중에 0 없이 방향으로 당기기
        */
        if(dir == 'L'){
            for (int i = 0; i < N; i++) {
                int[] A = new int[N];
                for (int j = 0; j < N; j++) {
                    A[j] = board[i][j];
                }
                A = move(A);
                for (int j = 0; j < N; j++) {
                    board[i][j] = A[j];
                }
            }
        }
        else if (dir == 'R') {
            for (int i = 0; i < N; i++) {
                int[] A = new int[N];
                for (int j = 0; j < N; j++) {
                    A[j] = board[i][N - 1 - j];
                }
                A = move(A);
                for (int j = 0; j < N; j++) {
                    board[i][N - 1 - j] = A[j];
                }
            }
        }
        else if (dir == 'U') {
            for (int j = 0; j < N; j++) {
                int[] A = new int[N];
                for (int i = 0; i < N; i++) {
                    A[i] = board[i][j];
                }
                A = move(A);
                for (int i = 0; i < N; i++) {
                    board[i][j] = A[i];
                }
            }
        }
        else { // D
            for (int j = 0; j < N; j++) {
                int[] A = new int[N];
                for (int i = 0; i < N; i++) {
                    A[i] = board[N - 1 - i][j];
                }
                A = move(A);
                for (int i = 0; i < N; i++) {
                    board[N - 1 - i][j] = A[i];
                }
            }
        }
    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException{
        N = 4;
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j= 0; j<N; j++){
                board[i][j] = read();
            }
        }
        tilt(rc());
        print();
    }
}