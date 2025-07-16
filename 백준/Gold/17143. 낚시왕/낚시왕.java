/*
왕 시작[0] 0 -> N+1 될때까지
오른쪽으로 한칸 왕 이동
상어 삭제 -> 함수
상어 이동 -> 함수

상어 삭제

낚시왕 이동 시간을 한 단계로 보고 for문 돌려서
낚시왕 이동 시간 하나마다
board에서 상어 삭제 함수 돌려주고
현재 상어 위치랑 정보 큐에 다 넣고 큐 빌때까지 poll해서 move 해준다음
겹쳐있는 상어 처리, board 갱신해야함

 */
import java.awt.Point;
import java.util.*;
import java.io.*;
class Shark implements Comparable<Shark>{
    int i;
    int j;
    int speed;
    int dir;
    int size;

    public Shark(int i, int j, int s, int d, int z){
        this.i = i;
        this.j = j;
        this.speed = s;
        this.dir = d;
        this.size = z;
    }

    @Override
    public int compareTo(Shark o){
        return o.size - this.size;
    }
}
public class Main {
    static int R,C,M;
    static Shark[][] board; // R, C
    // 북남동서 1<=d<=4
    static int[] di = {0,-1,1,0,0};
    static int[] dj = {0, 0,0,1,-1};
    static Queue<Point> sq;
    static int ans = 0;
    static int getOppositeDir(int d){
        if(d == 1)
            return 2;
        if(d == 2)
            return 1;
        if(d == 3)
            return 4;
        //if(d == 4)
        return 3;
    }
    // 상어 위치 큐 채우기
    // sq.add(new Point(i,j));
    static void fillSharkPosQ(){
        sq = new ArrayDeque<>();
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(board[i][j] != null)
                    sq.add(new Point(i,j));
            }
        }
    }

    // 왕이 있는 행의 모든 상어 리스트를 가져와서
    // i 좌표가 가장 작은 상어 board에서 제거
    static void fishing(int kingpos){
        PriorityQueue<Shark> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.i));
        for(int i = 0; i<R; i++){
            if(board[i][kingpos] != null)
                pq.add(board[i][kingpos]);
        }
        // 상어 없으면 낚시 안함
        if(pq.isEmpty())
            return;

        Shark target = pq.peek();
        ans += target.size;
        board[target.i][target.j] = null;
    }
    // 상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동
    // 겹치면 제일 큰 상어가 다 잡아먹음
    static int[] move(int i, int j, Shark[][] nb){
        Shark cur = board[i][j];
        int ni = i;
        int nj = j;
        int sp = cur.speed;
        int d = cur.dir;

        // 방향 바꿔서 왔다갔다하는건 쭉 펴서 2배의 거리로 왔다갔다한것과 같음
        // 주기성을 % 연산으로
        if (d == 1 || d == 2) {
            sp %= (R - 1) * 2;
        } else {
            sp %= (C - 1) * 2;
        }

        for(int s = 0; s < sp; s++){
            int ti = ni + di[d];
            int tj = nj + dj[d];

            if(ti < 0 || ti >= R || tj < 0 || tj >= C) {
                d = getOppositeDir(d);
                ti = ni + di[d];
                tj = nj + dj[d];
            }

            ni = ti;
            nj = tj;
        }

        Shark nShark = new Shark(ni, nj, sp, d, cur.size);

        if(nb[ni][nj] == null || nb[ni][nj].size < nShark.size)
            nb[ni][nj] = nShark;

        return new int[]{ni, nj};
    }

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[R][C];

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            board[i][j] = new Shark(i,j,s,d,z);
        }

        int kingPos = 0;
        for(; kingPos < C; kingPos++){
            fillSharkPosQ();
            fishing(kingPos);

            Shark[][] nb = new Shark[R][C];

            while(!sq.isEmpty()){
                Point cur = sq.poll();
                if(board[cur.x][cur.y] == null)
                    continue;
                move(cur.x, cur.y, nb);
            }

            board = nb;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
