import java.util.*;
import java.io.*;

/*
step1
1) 모든 열에 대해서 세로로 A를 만듬
2) A에 대해서 M개 이상인 터뜨려야하는 구간들 list<int[2]> 반환함 (findPos)
3) empty list 가 나올때까지 계속 터뜨려 줌 (bomb)
4) 모든 열에 대해서 bomb가 끝났으면 모든 열에 대해서 세로 중력처리 해줌 (pullDown)
-> i = [0,N-1]일때, 
A[i]!=0 이면 temp[i-zerocount] = A[i], 
A[i]==0 zerocount++, 

step2
5) rotate에서는 빈 copyboard 하나 만들고 거기다가 돌린 최종값 업뎃 (rotate)
6) 모든 행에 대해서[N-1,0] (돌렸을때 맨밑이 1번째라서) 오른쪽 끝을 시작으로 하는 A를 만듬
7) 그리고 4번 방법 참고해서 temp에 오른쪽으로 민 배열 만들고
8) copyboard의 각 열[0,N-1] 의 아래쪽부터 세로로 temp [0,N-1] 값 저장
9) board = copy

주의
1~8) K번째까지 다하고 findpos+bomb+pulldown 해주고 출력
-> K*(step1 + step2) + step1
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M, K;
    static int[][] board;

    static boolean isAllZero(int[] A){
        for(int i = 0; i<N; i++){
            if(A[i] != 0)
                return false;
        }
        return true;
    }

    static void step1(){
        for(int j = 0; j<N; j++){
            int[] A = new int[N];
            for(int i = 0; i<N; i++){
                A[i] = board[N-1-i][j];
            }

            if(isAllZero(A))
                continue;
 
            while(true){
                // TODO: findpos li가 비어서나옴 -> 해결함
                ArrayList<int[]> li = findPos(A);
                if(li.isEmpty()){
                    // System.out.println("li is empty");
                    break;
                }
                bomb(A,li);
                A = pull(A);
                
            }

            for(int i = 0; i<N; i++){
                board[N-1-i][j] = A[i];
            }
            //pb();
        }
    }

    static ArrayList<int[]> findPos(int[] A){
        ArrayList<int[]> rst = new ArrayList<>();
        int i = 0;
        while((i < N) && A[i] == 0)
            i++;
        if(i >= N)
            return rst;

        int prev = A[i];
        int s = i++;
        int cnt = 1;

        for(; i<A.length; i++){
            // prev는 0이 아니어야함!!!!!
            if(prev != 0 && prev == A[i]){
                cnt++;
            }
            else{
                if(cnt >= M){
                    int e = s + cnt - 1;
                    rst.add(new int[]{s, e});
                }
                s = i;
                prev = A[i];
                cnt = 1;
            }
        }
        //여기도
        if(prev != 0 && cnt >= M){
            rst.add(new int[]{s, s + cnt - 1});
        }
        return rst;
    }

    static int[] bomb(int[] A, ArrayList<int[]> li){
        for(int[] l : li){
            int s= l[0];
            int e = l[1];
            for(int i = s; i<=e; i++){
                A[i] = 0;
            }
        }
        return A;
    }

    static int[] pull(int[] A){
        int[] temp = new int[A.length];
        int zeros = 0;
        for(int i= 0; i<N; i++){
            if(A[i] != 0)
                temp[i-zeros] = A[i];
            else
                zeros++;
        }
        return temp;
    }

    /*
    step2
    5) step2에서는 빈 copyboard 하나 만들고 거기다가 돌린 최종값 업뎃
    6) 모든 행에 대해서[N-1,0] (돌렸을때 맨밑이 1번째라서) 오른쪽 끝을 시작으로 하는 A를 만듬
    7) 그리고 4번 방법 참고해서 temp에 오른쪽으로 민 배열 만들고 (pullRight)
    8) copyboard의 각 열[0,N-1] 의 아래쪽부터 세로로 temp [0,N-1] 값 저장 (rotate)
    */

    static void step2(){
        int[][] copy = new int[N][N];

        for(int i = N-1; i>=0; i--){
            int[] A = new int[N];
            for(int j = 0; j<N; j++){
                A[j] = board[i][N-j-1];
            }

            if(isAllZero(A))
                continue;

            // A는 행을 오른쪽 -> 왼쪽으로 읽은 배열이라 당기면 오른쪽으로 당겨짐
            int[] t = pull(A);
            copy = rotate(t, i, copy);
        }
        board = copy;
    }

    // ci는 A 만들 당시 행번호임
    // A[i]를 차례대로 copy[N-1-i][N-1-ci]에 저장 
    static int[][] rotate(int[] t, int ci, int[][] copy){
        for(int i = 0; i<N; i++){
            copy[N-1-i][N-1-ci] = t[i];
        }
        return copy;
    }

    static int count(){
        int cnt = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] > 0)
                    cnt++;
            }
        }
        return cnt;
    }

    // 디버그용 board, array print
    static void pb(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void pa(int[] A){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j<A.length; j++){
            sb.append(A[j]).append(",");
        }
        System.out.println(sb);
    }

    static int[] T = {1,1,1,2,2,3,5,2,2};

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();

        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        while(K-->0){
            step1();
            step2();
        }
        step1();
        System.out.print(count());
    }
}