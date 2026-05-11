import java.util.*;
import java.io.*;

/*
시작 돌 하나를 잡고 오른쪽, 아래쪽, 대각선 우상, 대각선 좌상
중복 최적화를 위해서 바로 전꺼 체크해봄. 입력에서 안들어온다고는 했지만..
*/

public class Main {
    static int[][] board = new int[19][19];
    static int N = 19;
    static int[] di = {0,1,-1,-1};
    static int[] dj = {1,0,1,-1};
   
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean found = false;
        StringBuilder sb = new StringBuilder();

        outer:
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] == 0)
                    continue;
                int curType = board[i][j];

                for(int d = 0; d<4; d++){
                    // 중복된 검사를 피하기 위해 
                    // 지금 방향 기준 한칸 전을 검사함
                    int pi = i - di[d];
                    int pj = j - dj[d];
                    // 인덱스 문제 없고 같은 색이면 중복되니까 안함
                    if(pi >= 0 && pj >= 0 && pi < N && pj < N && board[pi][pj] == curType)
                        continue;
                    
                    int cnt = 1;
                    int ni = i;
                    int nj = j;
                    while(true){
                        ni = ni + di[d];
                        nj = nj + dj[d];
                        if(ni < 0 || nj < 0 || ni >=N || nj >= N)
                            break;
                        if(board[ni][nj] != curType)
                            break;
                        cnt++;
                    }
                    if(cnt == 5){
                        found = true;
                        sb.append(curType).append("\n");
                        // 시작점이 i,j니까 그걸로 출력
                        sb.append(i + 2*di[d] + 1).append(" ").append(j + 2*dj[d] +1);
                        break outer;
                    }
                }
            }
        }
        if(!found)
            System.out.print(0);
        else
            System.out.print(sb);
    }
}