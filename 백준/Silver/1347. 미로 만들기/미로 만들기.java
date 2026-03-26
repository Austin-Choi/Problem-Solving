import java.util.*;
import java.io.*;
public class Main {
    //남서북동
    static int[] di = {1,0,-1,0};
    static int[] dj = {0,-1,0,1};
    static int[] move(int ci, int cj, int cd, char cmd){
        if(cmd == 'R')
            return new int[]{ci, cj, (cd+1)%4};
        else if(cmd == 'L')
            return new int[]{ci, cj, (cd+3)%4};
        else
            return new int[]{ci+di[cd], cj+dj[cd], cd};
    }
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[] cmds = br.readLine().toCharArray();
        int si = 0, sj = 0, sd = 0;
        int minI = 0, minJ = 0;
        int maxI = 0, maxJ = 0;
        ArrayList<int[]> li = new ArrayList<>();
        li.add(new int[]{0,0});
        for(char c : cmds){
            int[] rst = move(si,sj,sd,c);
            si = rst[0];
            sj = rst[1];
            sd = rst[2];
            li.add(new int[]{si,sj});

            minI = Math.min(minI, si);
            maxI = Math.max(maxI, si);
            minJ = Math.min(minJ, sj);
            maxJ = Math.max(maxJ, sj);
        }
        //minI, minJ를 0,0으로 만드는 오프셋을 다 더해주고 그걸 기준으로
        //맵 전체 만들고 프린트
        int oi = -minI;
        int oj = -minJ;
        char[][] board = new char[maxI+oi+1][maxJ+oj+1];
        for(int i = 0; i<maxI+oi+1; i++){
            for(int j = 0; j<maxJ+oj+1; j++){
                board[i][j] = '#';
            }
        }

        for(int[] p : li){
            board[p[0]+oi][p[1]+oj] = '.';
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<maxI+oi+1; i++){
            for(int j = 0; j<maxJ+oj+1; j++){
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
