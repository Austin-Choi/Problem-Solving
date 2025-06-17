import java.util.*;
import java.io.*;
import java.awt.Point;

class Info implements Comparable<Info>{
    int i;
    int j;
    int size;

    public Info(int i, int j, int size){
        this.i = i;
        this.j = j;
        this.size = size;
    }

    @Override
    public int compareTo(Info o){
        if(this.i == o.i){
            return this.j - o.j;
        }
        else
            return this.i - o.i;
    }
}

public class Main {
    private static final int[] di = {0,0,1,-1};
    private static final int[] dj = {1,-1,0,0};
    private static int[][] board;
    private static int[][] dist;
    private static int MAX_LIMIT;
    private static int timeSum = 0;
    private static int N;
    private static Info baby;
    private static void bfs(Info baby){
        Queue<Info> q = new ArrayDeque<>();
        dist = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], MAX_LIMIT);
        }
        dist[baby.i][baby.j] = 0;
        q.add(baby);

        while(!q.isEmpty()){
            Info cur = q.poll();
            int curI = cur.i;
            int curJ = cur.j;
            int curSize = cur.size;

            for(int i = 0; i<4; i++){
                int nextI = curI + di[i];
                int nextJ = curJ + dj[i];
                if(nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N || board[nextI][nextJ] > curSize)
                    continue;

                if(dist[nextI][nextJ] > dist[curI][curJ] + 1){
                    dist[nextI][nextJ] = dist[curI][curJ]+1;
                    q.add(new Info(nextI, nextJ, curSize));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        MAX_LIMIT = N*N+1;

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 9){
                    board[i][j] = 0;
                    baby = new Info(i,j,2);
                }
            }
        }
        boolean flag;
        int eatenCount = 0;

        while(true){
            flag = false;
            bfs(baby);
            PriorityQueue<Info> pq = new PriorityQueue<>();
            // 물고기들의 위치와 크기를 저장한 보드에서 위치만으로 조회해서
            // 지금 먹을 수 있는 애들만
            // 정렬함 가장 위 가장 왼쪽이 먼저 오게
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if((board[i][j] >0) && (board[i][j] < baby.size) && (dist[i][j] != MAX_LIMIT))
                        pq.add(new Info(i, j, board[i][j]));
                }
            }
            // bfs로 구한 dist중 pq에 들어있는 물고기 위치정보 중에서
            // 가장 작은 dist 구함
            int min = MAX_LIMIT;
            for(Info i : pq){
                if(dist[i.i][i.j] < min){
                    min = dist[i.i][i.j];
                }
            }
            // pq에서 하나씩 꺼내보면서
            // 해당 물고기의 dist가 min인지 확인하고
            // min이면 peek 하고
            // baby의 크기를 해당 물고기로 옮기고 문제 조건에 맞게 size를 갱신함
            // 그리고 break
            // min이 아니면 계속 poll
            while(!pq.isEmpty()) {
                Info cur = pq.peek();
                if (dist[cur.i][cur.j] == min) {
                    pq.poll();
                    board[cur.i][cur.j] = 0;
                    eatenCount++;
                    timeSum += dist[cur.i][cur.j];
                    flag = true;

                    if (eatenCount == baby.size) {
                        baby = new Info(cur.i, cur.j, baby.size + 1);
                        eatenCount = 0;
                    } else
                        baby = new Info(cur.i, cur.j, baby.size);
                    break;
                }
                else
                    pq.poll();
            }
            if(!flag)
                break;
        }

        bw.write(String.valueOf(timeSum));
        bw.flush();
        bw.close();
        br.close();
    }
}
