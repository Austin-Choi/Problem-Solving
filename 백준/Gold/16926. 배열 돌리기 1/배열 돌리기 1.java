/*
    NxM 배열 반시계 방향으로 돌림
    n,m을 동시에 2씩 빼서 둘중 하나라도 1이나 2가 나오면 스탑하는걸로..
    layer 갯수를 구함
    한번 반시계 방향을 돌려줄때마다는
    시작점을 왼쪽 위라 잡으면
    0,0부터 1,1 2,2 ... layersize, layersize로 간다.
    그러면 왼쪽 위를 기준으로 네 변을 돌면서 반시계방향으로 밀어주면 됨.
    temp로 첫번째를 저장해주고
    나머지를 한칸씩 밀어준다.
    그리고 마지막에 첫번째 저장한것을 더해주면서
    바깥에서 안으로 한칸씩 돌려준다.
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Layer의 갯수는
        // N,M중에 작은 것을 기준으로 계산해야함.
        // 코어의 모양은 음수가 될 수 없으므로
        // N,M에서 동시에 2를 계속 차감하게 되므로
        // 2로 나눈것
        int size = Math.min(N,M)/2;

        // 회전 수 만큼
        for(int rr = 0; rr<R; rr++){
            // layer 갯수 만큼
            for(int s = 0; s<size; s++){
                int tmp = board[s][s];
                // 위쪽 변 왼쪽으로 한칸씩 땡기기
                for(int idx = s; idx < M-s-1; idx++)
                    board[s][idx] = board[s][idx+1];
                // 왼쪽 변 아래쪽으로 한칸씩
                for(int idx = s; idx <N-s-1; idx++)
                    board[idx][M-1-s] = board[idx+1][M-1-s];
                // 아랫쪽 변 오른쪽으로 한칸씩
                for(int idx = M -1 - s; idx > s; idx--)
                    board[N-1-s][idx] = board[N-1-s][idx-1];
                // 오른쪽 변 위쪽으로 한칸씩
                for(int idx = N-1-s; idx>s; idx--)
                    board[idx][s] = board[idx-1][s];
                board[s+1][s] = tmp;
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                bw.write(board[i][j]+" ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
