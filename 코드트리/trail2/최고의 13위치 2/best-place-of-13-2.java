/*
2차원 슬라이딩 윈도 합 
= ps[시작점+i크기-1][시작점+j크기-1] - ps[i시작점-1][j끝점] - ps[i끝점][j시작점-1] + ps[i시작점-1][j시작점-1]

아니면 그냥 3크기 행별 sum 구해놓고 
현재 위치 기준 안겹치는 최대값 고려하기
1) 같은 행이면 leftMax 
2) 다른 행이면 upMax
*/

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] board;
    static int[][] ps;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        ps = new int[N+1][N+1];

        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1*3 전처리
        for(int i = 1; i<=N; i++){
            int cur = board[i][1] + board[i][2] + board[i][3];
            ps[i][1] = cur;
            for(int j = 2; j<=N-2; j++){
                cur -= board[i][j-1];
                cur += board[i][j+2];                
                ps[i][j] = cur;
            }
        }

        int ans = 0;
        // 다른행, 위쪽에서의 최대
        int upMax = 0;
        for(int i = 1; i<=N; i++){
            // 같은행, 왼쪽에서부터의 최대
            int[] leftMax = new int[N+1];
            for(int j= 1; j<=N-2; j++){
                leftMax[j] = Math.max(j>=2 ? leftMax[j-1] : 0, ps[i][j]);
            }

            for(int j = 1; j<=N-2; j++){
                // 1. 두 윈도가 서로 다른 행에 있을땐 위쪽기준 max값에 현재값 더한거 비교
                ans = Math.max(ans, upMax + ps[i][j]);
                // 2. 두 윈도가 같은 행에 있을땐 왼쪽기준 max값에 현재값 더한 거 비교
                if(j >= 4){
                    ans = Math.max(ans, leftMax[j-3] + ps[i][j]);
                }
            }

            // 행이 다를때 쓸 위쪽기준 최대값 갱신
            // -> 한 행 통째로 sum값 죄다넣고 최대로
            for(int j = 1; j<=N-2; j++){
                upMax = Math.max(upMax, ps[i][j]);
            }
        }
        
        System.out.print(ans);
    }
}