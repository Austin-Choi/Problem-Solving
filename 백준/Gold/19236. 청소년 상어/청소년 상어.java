
/*
11:10 12:50
board = Fish[4][4]
Fish -> i,j,번호,방향
 */

import java.util.*;
import java.io.*;
public class Main {
    /*
    1 0 7
    2 9 6
    3 4 5
     */
    static int ans = 0;
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,-1,-1,-1,0,1,1,1};
    static Fish[][] board = new Fish[4][4];
    static class Fish{
        int i,j,num,dir;
        Fish(int i, int j, int num, int dir){
            this.i = i;
            this.j = j;
            this.num = num;
            this.dir = dir;
        }
    }
    // 상태복사용 fishboard
    static Fish[][] copy(Fish[][] src){
        Fish[][] copyBoard = new Fish[4][4];
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                Fish f = src[i][j];
                if(f == null)
                    copyBoard[i][j] = new Fish(i,j,-1,-1);
                else
                    copyBoard[i][j] = new Fish(i,j,f.num,f.dir);
            }
        }
        return copyBoard;
    }

    /*
    보드 빈칸은 num = -1, dir = -1
    2) 물고기 이동
    pq = { i , j , num , direction } 기준은 num
    숫자 작은거부터 시작함 1~16
    방향으로 한칸 진행함
    경계 넘으면 안됨
    int cn =
    int cd =
    while(true)
    -> 벽이나 상어가 있으면 안됨 (이후에 방향 +1 %8) if(nd == cd) break;
    -> 빈칸이면 숫자와 방향 그대로 빈칸 좌표로 이동 ,break
    -> 다른 물고기가 있으면 서로 번호만 바꿈 ,break
    (ex 1,1의1 7 이 0,2의15 5 랑 바꿔야 한다면 0,2{1 5} 가 되고 1,1{15 7}이 됨
     */
    static void move(Fish[][] curBoard, int sharki, int sharkj){
        for(int num = 1; num <= 16; num++){
            int fi = -1;
            int fj = -1;
            //현재 처리해야할 물고기 위치찾기
            for(int i = 0; i<4; i++){
                for(int j = 0; j<4; j++){
                    if(curBoard[i][j].num == num){
                        fi = i;
                        fj = j;
                        break;
                    }
                }
                if(fi != -1)
                    break;
            }
            if(fi == -1)
                continue;

            Fish cur = curBoard[fi][fj];
            int cd = cur.dir;

            for(int dd = 0; dd<8; dd++){
                int nd = (cd + dd) % 8;
                int ni = fi + di[nd];
                int nj = fj + dj[nd];

                if(ni < 0 || nj <0 || ni > 3 || nj > 3)
                    continue;
                if(ni == sharki && nj == sharkj)
                    continue;

                // 이동 가능
                if(curBoard[ni][nj].num == -1 && curBoard[ni][nj].dir == -1){
                    // 빈칸으로 이동
                    curBoard[ni][nj] = new Fish(ni,nj,cur.num,nd);
                    curBoard[fi][fj] = new Fish(fi,fj,-1,-1);
                    break;
                }
                else{
                    // 다른 물고기랑 번호와 방향 서로 바꾸기
                    Fish opposite = curBoard[ni][nj];
                    curBoard[ni][nj] = new Fish(ni,nj,cur.num, nd);
                    curBoard[fi][fj] = new Fish(fi,fj,opposite.num, opposite.dir);
                    break;
                }
            }
        }
    }
    /*
    1) 상어이동 dfs
    상어는 진행방향의 모든 칸을 고를 수 있음
    백트래킹 함수 파라미터는 (상어 현재 크기, 현재 방향)
    물고기 이동을 일단 하고
    copyboard 쓰기

    현재 진행 방향에서 8칸이 다 비어있으면 최대값 ans 갱신
     */

    static void bt(Fish[][] state, int sharki, int sharkj, int sharkSize, int curDir){
        if(sharkSize > ans)
            ans = sharkSize;

        Fish[][] movedBoard = copy(state);
        move(movedBoard, sharki, sharkj);

        boolean can = false;
        for(int s = 1; s<=3; s++){
            int ni = sharki + di[curDir] * s;
            int nj = sharkj + dj[curDir] * s;

            if(ni < 0 || nj <0 || ni > 3 || nj > 3)
                break;
            if(movedBoard[ni][nj].num == -1)
                continue;

            can = true;
            Fish[][] nextBoard = copy(movedBoard);

            int ate = nextBoard[ni][nj].num;
            int nd = nextBoard[ni][nj].dir;

            // 상어 이전칸은 빈칸으로
            nextBoard[sharki][sharkj] = new Fish(sharki, sharkj, -1, -1);
            // 먹은 칸 처리
            nextBoard[ni][nj] = new Fish(ni, nj, -1, -1);

            bt(nextBoard, ni, nj, sharkSize + ate, nd);
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i<4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<4; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                board[i][j] = new Fish(i,j,num,dir);
            }
        }

        int ss = board[0][0].num;
        int sd = board[0][0].dir;
        board[0][0].num = -1;
        board[0][0].dir = -1;

        bt(board, 0, 0, ss, sd);

        System.out.println(ans);
    }
}
