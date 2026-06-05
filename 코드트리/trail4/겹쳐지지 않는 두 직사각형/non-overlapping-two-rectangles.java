import java.util.*;
import java.io.*;

/*
기준점 두개 잡고 그걸 왼쪽 위로 하는 가능한 모든 직사각형엥 대해 합 비교해보기
-> 둘이 겹침 판정을 어떻게?
-> 겹침 판정을 먼저하고 합 각자 계산해가지고 돌리기

누적합 전처리해가지고 직사각형 합 빨리 계산하기?
-> si,sj 세로 =r, 가로=c
-> psum[si+r][sj+c] - psum[si][sj+c] - psum[si+r][sj] + psum[si][sj];
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M;
    // 1-based index
    static int[][] board;
    static int[][] psum;

    //a가 왼쪽위 , b가 오른쪽아래라고 가정
    static boolean isOverlap(int sia, int sja, int ra, int ca, int sib, int sjb, int rb, int cb){
        int aTop = sia;
        int aBottom = sia + ra - 1;
        int aLeft = sja;
        int aRight = sja + ca - 1;

        int bTop = sib;
        int bBottom = sib + rb - 1;
        int bLeft = sjb;
        int bRight = sjb + cb - 1;

        if(aBottom < bTop) 
            return false;
        if(bBottom < aTop) 
            return false;
        if(aRight < bLeft) 
            return false;
        if(bRight < aLeft) 
            return false;

        return true;
    }

    static int getRectSum(int si, int sj, int r, int c){
        // idxerror
        int ei = si + r -1;
        int ej = sj + c -1;
        return psum[ei][ej] - psum[si-1][ej] - psum[ei][sj-1] + psum[si-1][sj-1];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N+1][M+1];
        psum = new int[N+1][M+1];

        for(int i =1; i<=N; i++){
            for(int j=1; j<=M; j++){
                board[i][j] = read();
            }
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                psum[i][j] = psum[i-1][j] + psum[i][j-1] - psum[i-1][j-1] + board[i][j];
            }
        }

        int ans = -(1000 * 5 * 5) -1;
        for(int ai = 1; ai<=N; ai++){
            for(int aj = 1; aj<=M; aj++){
                for(int bi = 1; bi<=N; bi++){
                    for(int bj = 1; bj<=M; bj++){
                        if(ai == bi && aj == bj)
                            continue;

                        for(int ra = 1; ai + ra - 1 <= N; ra++){
                            for(int ca = 1; aj + ca - 1 <= M; ca++){

                                for(int rb = 1; bi + rb - 1 <= N; rb++){
                                    for(int cb = 1; bj + cb - 1 <= M; cb++){

                                        if(!isOverlap(
                                            ai, aj, ra, ca,
                                            bi, bj, rb, cb
                                        )){
                                            ans = Math.max(
                                                ans,
                                                getRectSum(ai, aj, ra, ca)
                                            + getRectSum(bi, bj, rb, cb)
                                            );
                                        }
                                    }
                                }

                            }
                        }

                    }//-->ai,aj,bi,bj(시작점잡기)
                }
            }
        }
        System.out.print(ans);
    }
}