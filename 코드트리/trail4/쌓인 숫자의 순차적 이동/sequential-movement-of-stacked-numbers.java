import java.util.*;
import java.io.*;

/*
각 칸 대표값 추적하는 2차원 배열
deque로 이루어진 2차원 보드 <Integer>
map<움직일 숫자, int[]{i,j, }>
출력할때는 위에 있는 숫자부터
pollLast + addFirst

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int[][] mBoard;
    static Deque<Integer>[][] board;
    static int N,M;
    // 숫자, i,j 좌표값 추적 (1~N*N 전부 추적)
    static Map<Integer, int[]> m = new HashMap<>();
    static int[][] m2;
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};

    static void move(int cur){
        // int ci = m.get(cur)[0];
        // int cj = m.get(cur)[1];
        int ci = m2[cur][0];
        int cj = m2[cur][1];

        //8방향중 제일 큰 값 존재하는 ni,nj를 mi,mj로 선정
        int max = 0;
        int mi = -1;
        int mj = -1;
        for(int d = 0; d<8; d++){
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                continue;
            if(mBoard[ni][nj] > max){
                max = mBoard[ni][nj];
                mi = ni;
                mj = nj;
            }
        }

        // 만약 선택된 숫자의 인접 8방향에 숫자없으면 움직이지 않음
        if(mi == -1)
            return;

        //cur가 존재하는 칸에서 
        // cur 위에있는거 전부 다 빼서 옮겨야 하는 칸으로 옮기고
        // 이때 대표값이 바뀐다면 mBoard[ni][nj] 값도 바꿔줌
        // 1) mi,mj로 옮길 cur포함한 cur위 숫자들 모음
        Deque<Integer> li = new ArrayDeque<>();
        while(true){
            int top = board[ci][cj].pollLast();
            if(top == cur){
                li.addFirst(top);
                break;
            }
            li.addFirst(top);
        }

        // cur+cur위 제거된 ci,cj의 mBoard 대표값 갱신
        Iterator it = board[ci][cj].iterator();
        int nmax = 0;
        while(it.hasNext()){
            nmax = Math.max(nmax, (int) it.next());
        }
        mBoard[ci][cj] = nmax;

        // cur+cur위 숫자들 mi,mj로 옮기고
        // mBoard mi,mj 대표값 갱신
        // m(숫자, i,j) 갱신
        while(!li.isEmpty()){
            int next = li.pollFirst();
            if(next > mBoard[mi][mj]){
                mBoard[mi][mj] = next;
            }
            board[mi][mj].addLast(next);
            //m.put(next, new int[]{mi,mj});
            m2[next] = new int[]{mi,mj};
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        mBoard = new int[N][N];
        board = new Deque[N][N];
        m2 = new int[N*N+1][2];
        for(int i= 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = new ArrayDeque<>();
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int n = read();
                mBoard[i][j] = n;
                board[i][j].addLast(n);
                //m.put(n, new int[]{i,j});
                m2[n] = new int[]{i,j};
            }
        }

        while(M-->0){
            int cur = read();
            move(cur);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j].isEmpty())
                    sb.append("None");
                else{
                    while(!board[i][j].isEmpty())
                        sb.append(board[i][j].pollLast()).append(" "); 
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}