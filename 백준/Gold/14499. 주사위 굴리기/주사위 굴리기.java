/*
  1
3 0 2
  4
  5

dice[][] -> 현재 바닥이 i일때 0,1,2,3(동서북남) 으로 이동하면 뭐가되는지 저장
-> 이렇게 하면 상태 전이수 고정되지 않음
dice[6] = 각 면에 적힌 현재 숫자

0)명령에 따라 현재 board에서의 위치, 명령이 경계 벗어나는지 체크
1)주사위 굴릴때 방향에 따라 현재 바닥칸 인덱스가 뭐가 되는지 알아내기 (d 배열로 처리)
2)현재 바닥칸의 인덱스가 x일때 상단 정수가 뭐가 되는지 알아내기
3)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[] d = new int[6];
    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};

    // 방향으로 굴럿을때 면에 저장된 값 이동
    static void roll(int dir){
        if(dir == 0){
            int t = d[0];
            d[0] = d[3];
            d[3] = d[5];
            d[5] = d[2];
            d[2] = t;
        }
        else if(dir == 1){
            int t = d[0];
            d[0] = d[2];
            d[2] = d[5];
            d[5] = d[3];
            d[3] = t;
        }
        else if(dir == 2){
            int t = d[0];
            d[0] = d[4];
            d[4] = d[5];
            d[5] = d[1];
            d[1] = t;
        }
        else{
            int t = d[0];
            d[0] = d[1];
            d[1] = d[5];
            d[5] = d[4];
            d[4] = t;
        }
    }

    static int N,M;
    static int si, sj;
    static int K;
    static int[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        si = Integer.parseInt(st.nextToken());
        sj = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 상단은 0, 바닥은 5
        for(int i = 0; i<K; i++){
            int dir = Integer.parseInt(st.nextToken())-1;
            int ni = si + di[dir];
            int nj = sj + dj[dir];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                continue;

            roll(dir);
            if(board[ni][nj] == 0){
                board[ni][nj] = d[5];
            }
            else{
                d[5] = board[ni][nj];
                board[ni][nj] = 0;
            }
            sb.append(d[0]).append("\n");
            si = ni;
            sj = nj;
        }
        System.out.print(sb);
    }
}
