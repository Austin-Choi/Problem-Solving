import java.io.*;
import java.util.StringTokenizer;

/*
직사각형 격자로 구성되어 있음.
각 격자의 셀은 서로 다른 높이로 구성됨
낮은 셀에 물이 차는게 걱정되서 물빼는기구를 설치하려함
기구 어디다가 설치해야 하는지 알아보기
만약 이 셀의 상하좌우의 셀이 모두 더 높다면 설치해야함.
가장자리거나 네 꼭지점에 있는 셀은 절대 설치할 일 없음.
 */
public class Main {
    // 상 하 좌 우
    public static int[] di = {-1, 1, 0, 0};
    public static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] board = new int[C][R];
        int[][] ans = new int[C][R];

        for(int i = 0; i<C; i++){
            String[] ls = br.readLine().split(" ");
            for(int j = 0; j<R; j++){
                int temp = Integer.parseInt(ls[j]);
                board[i][j] = temp;
            }
        }

        for(int i = 1; i<C-1; i++){
            for(int j = 1; j<R-1; j++){
                int nI = i;
                int nJ = j;
                int flags = 0;
                for(int k = 0; k<4; k++){
                    nI += di[k];
                    nJ += dj[k];
                    if(board[nI][nJ] > board[i][j])
                        flags++;
                    nI = i;
                    nJ = j;
                }
                if(flags == 4)
                    ans[i][j] = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<C; i++){
            for(int j = 0; j<R; j++){
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
