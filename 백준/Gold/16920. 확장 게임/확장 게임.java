
/*
동시 bfs
확장 방향 끝에 빈칸이 있지만 내 블럭이 가로막고 있으면
-> 갈수는 있지만 카운팅은 안해야함
for 4 direction 안에서
str배열의 길이만큼 di,dj 이용해서 한칸씩 갈수있는지 확인하고 진행하고 더이상 못가면
break하고 다른 direction 진행
-> 플레이어 1 2 3 ... 9 순서대로 진행
-> 초기 1의 위치를 다넣고 2,3,4 .. 도 그렇게
-> 각 방향 str 만큼 다했을때 큐에 끝점들 넣어줌

--------------------
동시 bfs로 하되
각 플레이어의 턴을 보장해서 bfs
-----------
끝점만 넣는거 아니고 확장한 모든 칸에서 bfs돌려야함
 */
import java.awt.*;
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,P;
    static int[] str;
    static char[][] board;
    static Queue<Point>[] ps;
    static int[] pc;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static void bfs(int player){
        Queue<Point> q = new ArrayDeque<>(ps[player]);
        // 넣고 새 좌표들을 위해 비워줘야함
        ps[player].clear();

        for(int n = 0; n<str[player]; n++){
            int size = q.size();
            if(size == 0)
                break;

            // q.isEmpty로 꺼내게 되면 다음턴에 해야할거까지 꺼내버림
            // -> 해결 : 처음 큐 size만큼만 꺼내야함
            for(int i = 0; i<size; i++){
                Point cur = q.poll();
                int ci = cur.x;
                int cj = cur.y;

                for(int d = 0; d<4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni < 0 || nj < 0 || ni >= N || nj >= M)
                        continue;
                    if (board[ni][nj] == '.') {
                        pc[player]++;
                        board[ni][nj] = (char) ('0' + player);
                        q.add(new Point(ni, nj));
                        ps[player].add(new Point(ni, nj));
                    }
                }
            }
        }
    }
    static boolean isEnd(){
        for(Queue<Point> q : ps){
            if(!q.isEmpty())
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        str = new int[P+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=P; i++){
            str[i] = Integer.parseInt(st.nextToken());
        }

        board = new char[N][M];
        ps = new Queue[P+1];
        pc = new int[P+1];
        for(int i = 0; i<=P; i++){
            ps[i] = new ArrayDeque<>();
        }

        for(int i = 0; i<N; i++){
            board[i] = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                if(board[i][j] != '.' && board[i][j] != '#'){
                    int cn = board[i][j] - '0';
                    ps[cn].add(new Point(i,j));
                    pc[cn]++;
                }
            }
        }
        while(true){
            boolean expanded = false;
            // 플레이어 턴만큼 bfs
            for(int player = 1; player <= P; player++){
                if(!ps[player].isEmpty()){
                    bfs(player);
                    expanded = true;
                }
            }
            if(!expanded)
                break;
        }
        for(int i = 1; i<=P; i++){
            System.out.print(pc[i]+" ");
        }
    }
}
