/*
백트래킹으로 그래프 만들고 bfs로 도달 검증

기차가 이동하면서 선로를 놓는 방식으로 하는건 어떨까..
*/
import java.util.*;

class Solution {
    int N, M;
    int[][] grid;
    int[][] visited;
    int ans = 0;
    int remain = 0;

    // 비트마스크로 연결된 선로 표현
    static final int U = 1<<0, D = 1<<1, L = 1<<2, R = 1<<3;

    static final int[] TYPE = {
        0,
        L|R, U|D, U|D|L|R,
        U|L, U|R, D|R, D|L
    };

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int[] dirBit = {U,D,L,R};
    int[] opp = {1,0,3,2};

    // 현재 위치에 기차가 도착했을 때
    // 빈칸이면 선로를 놓고, 기존 선로면 그대로 이동
    void dfs(int x, int y, int d){
        if(x<0 || y<0 || x>=N || y>=M)
            return;
        if(grid[x][y] == -1)
            return;

        if(grid[x][y] > 0){
            move(x,y,d);
            return;
        }

        // 빈칸에 선로를 놓으면서 탐색
        for(int t=1; t<=7; t++){
            grid[x][y] = t;
            remain += (t==3 ? 2 : 1);

            move(x,y,d);

            remain -= (t==3 ? 2 : 1);
            grid[x][y] = 0;
        }
    }

    // 현재 선로에서 다음 위치로 이동
    void move(int x, int y, int d){
        int t = grid[x][y];
        int mask = TYPE[t];

        // 기차가 들어온 방향의 반대쪽이 연결되어 있어야 함
        int in = dirBit[opp[d]];
        if((mask & in) == 0)
            return;

        // 3번 선로는 가로와 세로를 별도로 방문 처리
        int axis = 1;
        if(t == 3)
            axis = (d==2 || d==3) ? 1 : 2;

        if((visited[x][y] & axis) != 0)
            return;

        int prev = visited[x][y];
        visited[x][y] |= axis;
        remain--;

        // 도착지까지 이동했고 모든 선로를 지나갔다면 성공
        if(x==N-1 && y==M-1){
            if(remain == 0)
                ans++;
        }
        else{
            int out;

            // 3번 선로는 들어온 방향으로 직진
            if(t == 3)
                out = dirBit[d];
            // 일반 선로는 들어온 쪽을 제외한 방향으로 진행
            else
                out = mask ^ in;

            for(int nd=0; nd<4; nd++){
                if((out & dirBit[nd]) != 0){
                    dfs(x+dx[nd], y+dy[nd], nd);
                    break;
                }
            }
        }

        remain++;
        visited[x][y] = prev;
    }

    public int solution(int[][] grid) {
        this.grid = grid;
        N = grid.length;
        M = grid[0].length;

        visited = new int[N][M];
        ans = 0;
        remain = 0;

        // 기존에 놓인 선로의 방문 횟수 계산
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(grid[i][j] > 0){
                    remain += (grid[i][j]==3 ? 2 : 1);
                }
            }
        }
        dfs(0,0,3);
        return ans;
    }
}