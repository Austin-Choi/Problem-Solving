
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int[][] board;
    private static int[][] groupBoard; // 해당 칸이 속한 그룹의 번호
    private static int[] groupSize; // 그룹 번호에 해당하는 그룹 크기 저장
    private static int[] di = {0,0,1,-1};
    private static int[] dj = {1,-1,0,0};
    private static void bfs(Point start, int groupId){
        int num = 1;
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        groupBoard[start.x][start.y] = groupId;

        while(!q.isEmpty()){
            Point cur = q.poll();
            int cI = cur.x;
            int cJ = cur.y;

            for(int i = 0; i<4; i++){
                int nI = cI + di[i];
                int nJ = cJ + dj[i];

                if(nI > -1 && nJ > -1 && nI < N && nJ < M){
                    if(board[nI][nJ] == 0 && groupBoard[nI][nJ] == 0){
                        groupBoard[nI][nJ] = groupId;
                        num++;
                        q.add(new Point(nI, nJ));
                    }
                }
            }
        }
        groupSize[groupId] = num%10;
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        groupBoard = new int[N][M];
        groupSize = new int[1000001];

        for(int i = 0; i<N; i++){
            char[] tmps = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                board[i][j] = tmps[j] - '0';
            }
        }

        int id = 1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                // 보드 값이 0이고 아직 이 0에 대한 groupId가 지정되지 않았을 때
                if(board[i][j] == 0 && groupBoard[i][j] == 0){
                    bfs(new Point(i,j), id);
                    id++;
                }
            }
        }


        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                // 그룹 더할때 아주 큰 그룹이 이걸 둘러싸고 있으면
                // 중복해서 더해버리니까
                // Set 사용
                if(board[i][j] == 1){
                    Set<Integer> s = new HashSet<>();
                    int sum = 1;
                    for(int k = 0; k<4; k++){
                        int nI = i + di[k];
                        int nJ = j + dj[k];

                        if(nI > -1 && nJ > -1 && nI < N && nJ < M){
                            int groupId = groupBoard[nI][nJ];
                            if(!s.contains(groupId)){
                                s.add(groupId);
                                sum += groupSize[groupId];
                                sum %= 10;
                            }
                        }
                    }
                    board[i][j] = sum;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
