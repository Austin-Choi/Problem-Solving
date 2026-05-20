import java.util.*;
import java.io.*;

/*
dfs + dp로 모든칸에 대해 dfs 하는 방식으로 풀어도 되긴한데
java는 재귀 오버헤드 최적화가 없어서 bfs로 최적화 시도하기??

경로가 있고 그래프로 생각한다면 
다음 칸이 커야만 갈 수 있음..
그럼 모든 칸을 노드로 보고 outdegree(나가는 방향 갯수)를 계산하면
위상정렬 느낌으로다가 
outdegree = 0인건 끝점 후보이므로 
역방향으로 queue써서 bfs돌린다.

최대 경로는 얼마나 멀리 뻗어가느냐이니까 
처리는 한번의 layer 크기만큼 묶어서 처리하고 
그 layer가 생성되는 깊이의 최대 깊이가 답이 됨.
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;
    static int[][] dp;
    static int[][] outDegree;
    //북동남서
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        dp = new int[N][N];
        outDegree = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int d = 0; d<4; d++){
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                        continue;
                    if(board[ni][nj] > board[i][j])
                        outDegree[i][j]++;
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            for(int j= 0; j<N; j++){
                if(outDegree[i][j] == 0)
                    q.add(new int[]{i,j});
            }
        }

        int ans = 0;

        while(!q.isEmpty()){
            int size = q.size();
            ans++;

            for(int s = 0; s<size; s++){
                int[] cur = q.poll();
                int ci= cur[0];
                int cj= cur[1];

                // 역방향이니까 현재보다 작은 값 찾기
                for(int d = 0; d<4; d++){
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                        continue;
                    if(board[ni][nj] >= board[ci][cj])
                        continue;
                    if(--outDegree[ni][nj] == 0){
                        q.add(new int[]{ni,nj});
                    }
                }
            }
        }

        System.out.print(ans);
    }
}