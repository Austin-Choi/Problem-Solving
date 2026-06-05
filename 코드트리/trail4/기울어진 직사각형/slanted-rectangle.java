import java.util.*;
import java.io.*;

/*
숫자가 양수니까 최대한 많이 순환하면 됨
-> 그렇진 않음 한쪽에 ㅈㄴ큰 숫자 몰려있을수도 있음

순환 방향대로 대각 4개 di,dj 설정하고 ninj가 invalid 하면 다음 dir 넘어가면 됨
처음 위치로 돌아오면 sum 그만하고 sum누적된거 리턴
-> visited 쓰기?

맨 아랫줄만 해보면 됨 순환형이라 거기가 최대임 
각 방향으로 최대 1번은 움직여야 하니까 꼭지점은 제외하고 순회해야 함 
-> 마주보는 변 길이만 같게 해야해서 전체탐색해야함 
-> si,sj N^2 * l1,l2 N^2 * 4 -> O(N^4)?
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int[] di = {-1,-1,1,1};
    static int[] dj = {1,-1,-1,1};
    static int N;
    static int[][] board;

    static int getSum(int si, int sj, int l1, int l2){
        int ci = si;
        int cj = sj;
        int sum = 0;

        // 최대로 가는게 아니라 각 방향마다 길이별로 다 다르게 해서 탐색해야한다고..
        // 근데 마주보는 변 끼리는 길이가 같아야 함.
        // l1 l2 파라미터로 빼기
        for(int d = 0; d<4; d++){
            for(int a = 0; a<((d % 2 == 0) ? l1 : l2); a++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                // if(ni == si && nj == sj)
                //     return sum; -> 마지막에
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    return 0;
                sum += board[ni][nj];
                ci = ni;
                cj = nj;
            }
        }
        if(ci == si && cj == sj)
            return sum;
        return 0;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int l1 = 1; l1<N; l1++){
                    for(int l2 = 1; l2<N; l2++){
                        ans = Math.max(ans, getSum(i,j,l1,l2));
                    }
                }
            }
        }
        System.out.print(ans);
    }
}