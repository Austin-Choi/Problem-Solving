import java.util.*;
import java.io.*;

/*
1) 일단 터뜨려서 di dj val-1 만큼 0 만들기 copy에다가
2) 중력 적용하기 
-> 아래쪽부터 0에 넣는 배열 A 만들고 A[i]가 0이 아닐때 zeros 만큼 땡겨서 저장, 0이면 zeros++
-> temp[i-zeros] = A[i]
-> 그리고 temp를 copy에 덮어쓰기
-> 모든 열에 대해서 시행
3) copy에서 모든 열과 행에 대해서 i-1과 i가 같은 수 sum
4) sum을 최대화로 갱신

for(int i = 0; i<N; i++){
    for(int j = 0; j<N; j++){
        
    }
}

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;
    static int ans = 0;

    static int[][] copyboard(){
        int[][] rst= new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                rst[i][j] = board[i][j];       
            }
        }
        return rst;
    }

    static int[] di= {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    static int[][] bomb(int si, int sj, int[][] copy){
        int size = board[si][sj];
        for(int d = 0; d<4; d++){
            for(int n = 0; n<size; n++){
                int ni = si + n*di[d];
                int nj = sj + n*dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    break;
                copy[ni][nj] = 0;
            }
        }
        return copy;
    }

    static int[][] pull(int[][] copy){
        for(int j = 0; j<N; j++){
            int[] A = new int[N];
            for(int i = 0; i<N; i++){
                A[i] = copy[N-1-i][j];
            }

            int zeros = 0;
            int[] temp = new int[N];
            for(int i = 0; i<N; i++){
                if(A[i] != 0){
                    temp[i-zeros] = A[i];
                }
                else{
                    zeros++;
                }
            }

            for(int i = 0; i<N; i++){
                copy[N-1-i][j] = temp[i];
            }
        }
        
        return copy;
    }

    static void maximize(int[][] copy){
        int sum = 0;
        for(int i = 0; i<N; i++){
            for(int j = 1; j<N; j++){
                if(copy[i][j-1] == 0)
                    continue;
                if(copy[i][j-1] == copy[i][j])
                    sum++;
            }
        }

        for(int j = 0; j<N; j++){
            for(int i = 1; i<N; i++){
                if(copy[i-1][j] == 0)
                    continue;
                if(copy[i-1][j] == copy[i][j])
                    sum++;
            }
        }

        ans = Math.max(ans, sum);
    }

    static void pa(int[] a){
        for(int i = 0; i<a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    static void pb(int[][] a){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.out.print(a[i][j]+" ");       
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int[][] copy = copyboard();
                copy = bomb(i,j,copy);
                copy = pull(copy);
                //pb(copy);
                maximize(copy);
            }
        }
        System.out.print(ans);
    }
}