/*
34분

각 정점별 최소 이동거리 플로이드-워셜로 구하기
k i j 순서 (중간, 시작, 도착)
N^3

그리고 K명의 친구들에 대해서 왕복 최대거리를 각 중간 도시마다 저장
maxTime[중간 도시] = 최대 거리
그리고 maxTime돌면서 최소 거리 구하고
그 거리인 중간 도시들 출력
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N, M, K;
    static final int ML = (1000 * 200 * 199) + 1;
    static int[][] board;
    static int[] friends;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i==j)
                    board[i][j] = 0;
                else
                    board[i][j] = ML;
            }
        }
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(board[s][e] != 0 && board[s][e] != ML)
                board[s][e] = Math.min(board[s][e], c);
            else
                board[s][e] = c;
        }

        K = Integer.parseInt(br.readLine());
        friends = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<K; i++){
            friends[i] = Integer.parseInt(st.nextToken());
        }

        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                for(int j = 1; j<=N; j++){
                    if(board[i][k] != ML && board[k][j] != ML)
                        board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
                }
            }
        }

        int[] maxTime = new int[N+1];

        for(int i = 1; i<=N; i++){
            int max = 0;
            for(int k = 0; k<K; k++){
                int dist = board[friends[k]][i] + board[i][friends[k]];
                max = Math.max(max, dist);
            }
            maxTime[i] = max;
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i<=N; i++){
            ans = Math.min(ans, maxTime[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            if(maxTime[i] == ans)
                sb.append(i).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
