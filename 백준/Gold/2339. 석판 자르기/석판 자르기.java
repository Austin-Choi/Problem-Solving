
/*
잘려진 석판은 두개의 석판이 나뉘어진 것이어야 함
-> 반쪽의 각자 불순물을 고르고 또 그 기준으로 반쪽 고르고 ..
-> 분할정복???

이전 자른방향 뭔지 알아야함-> 파라미터로
이전 보드 visited 상태 -> 파라미터로

1) 현재 결정체 0개 -> 0
2) 현재 결정체 1개 && 불순물 0개 -> 1
3) 절단
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    // 1 : 불순물, 2:결정체
    static int[][] board;
    static int[] count(int si, int sj, int ei, int ej){
        int[] rst = new int[2];
        for(int i = si; i<=ei; i++){
            for(int j = sj; j<=ej; j++){
                if(board[i][j] == 2)
                    rst[0]++;
                if(board[i][j] == 1)
                    rst[1]++;
            }
        }
        return rst;
    }

    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    // 0-> 가로, 1->세로
    // 해당 방향으로 (0,1) i,j 위치에서 자를수 있는지 bool
    static boolean canCut(int i, int j, int ssi, int ssj, int eei, int eej, int dir){
        int si = i;
        int sj = j;
        if(dir == 0){
            while(true){
                i += di[0];
                j += dj[0];
                if(i < ssi || j < ssj || i > eei || j > eej)
                    break;
                if(board[i][j] == 2)
                    return false;
            }
            while(true){
                si += di[1];
                sj += dj[1];
                if(si < ssi || sj < ssj || si > eei || sj > eej)
                    break;
                if(board[si][sj] == 2)
                    return false;
            }
        }
        else{
            while(true){
                i += di[2];
                j += dj[2];
                if(i < ssi || j < ssj || i > eei || j > eej)
                    break;
                if(board[i][j] == 2)
                    return false;
            }
            while(true){
                si += di[3];
                sj += dj[3];
                if(si < ssi || sj < ssj || si > eei || sj > eej)
                    break;
                if(board[si][sj] == 2)
                    return false;
            }
        }
        return true;
    }

    // sisj->eiej 덩어리를 파라미터 방향으로 잘랐을때 (0,1)
    // int[4][2] 반환
    // 두 덩어리 시작끝 반환
    static int[][] cut(int si, int sj, int ei, int ej, int i, int j, int dir){
        int[][] rst;
        if(dir == 0)
            rst = new int[][]{{si,sj}, {i-1,ej},{i+1,sj},{ei,ej}};
        else
            rst = new int[][]{{si,sj}, {ei, j-1},{si,j+1},{ei,ej}};
        return rst;
    }

    static int sol(int si,int sj, int ei, int ej, int prevDir){
        int[] cnt = count(si,sj,ei,ej);
        if(cnt[0] == 0)
            return 0;
        if(cnt[0] == 1 && cnt[1] == 0)
            return 1;

        int ans = 0;
        for(int i =si; i<=ei; i++){
            for(int j = sj; j<=ej; j++){
                if(board[i][j] == 1){
                    for(int dir = 0; dir<2; dir++){
                        if(dir == prevDir)
                            continue;
                        if(!canCut(i,j,si,sj,ei,ej,dir))
                            continue;
                        int[][] range = cut(si,sj,ei,ej,i,j,dir);
                        int a = sol(range[0][0], range[0][1], range[1][0], range[1][1], dir);
                        int b = sol(range[2][0], range[2][1], range[3][0], range[3][1], dir);

                        ans += a*b;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = sol(0,0,N-1,N-1, 0)+sol(0,0,N-1,N-1, 1);
        System.out.print(ans == 0 ? -1 : ans);
    }
}