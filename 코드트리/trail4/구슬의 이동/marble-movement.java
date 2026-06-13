import java.util.*;
import java.io.*;

/*
10:20
1초에 모든 구슬 동시에 움직임
구슬 : 속도 빠를수록, 구슬 번호 높을수록
PriorityQueue<int[]>[][] board;

2 4 1 4
1 1 L 1
1 2 R 2
2 1 U 1
2 2 D 3
런타임에러
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

    static int N,M,T,K;
    // 북동남서
    static int[] ctoi = new int[128];
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static HashMap<Integer, int[]> ball = new HashMap<>();

    static void move(){
        HashMap<Integer, int[]> cBall = new HashMap<>();
        // [0]=번호,[1]=속도
        // !! 보드는 격자 계산용임. 업데이트해야하는건 ball map
        PriorityQueue<int[]>[][] copy = new PriorityQueue[N][N];
        for(int i= 0; i<N; i++){
            for(int j = 0; j<N; j++){
                copy[i][j] = new PriorityQueue<>((a,b)->{
                    if(a[1] != b[1])
                        return a[1] - b[1];
                    return a[0] - b[0];
                });
            }
        }

        for(int cn : ball.keySet()){
            int[] cur = ball.get(cn);
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];
            int cv = cur[3];

            // 주기성 이용해서 최적화해야함
            for(int v = 0; v<cv % (2*(N-1)); v++){
                int ni = ci + di[cd];
                int nj = cj + dj[cd];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N){
                    // 방향 바꾸는데 시간 안 듬
                    cd = (cd + 2)%4;
                    ni = ci + di[cd];
                    nj = cj + dj[cd];
                }
                ci = ni;
                cj = nj;
            }

            // 런타임에러  ci, cj에 음수값 들어옴
            // ball 업데이트안해서->cBall추가로 사용
            copy[ci][cj].add(new int[]{cn, cv, cd});
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                while(copy[i][j].size() > K){
                    copy[i][j].poll();
                }
                for(int[] cc : copy[i][j]){
                    int ccn = cc[0];
                    int ccv = cc[1];
                    int ccd = cc[2];
                    cBall.put(ccn, new int[]{i,j,ccd,ccv});
                }
            }
        }

        ball = cBall;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        T = read();
        K = read();
        // [0]=번호,[1]=속도
        //board = new PriorityQueue[N][N];

        ctoi['U'] = 0;
        ctoi['R'] = 1;
        ctoi['D'] = 2;
        ctoi['L'] = 3;

        for(int m = 1; m<=M; m++){
            int r = read()-1;
            int c = read()-1;
            int d = ctoi[rc()];
            int v = read();
            ball.put(m, new int[]{r,c,d,v});
        }

        for(int t = 0; t<T; t++){
            move();
        }
        System.out.print(ball.size());
    }
}