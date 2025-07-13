import java.util.*;
import java.io.*;

class Point{
    int i;
    int j;

    public Point(int i, int j){
        this.i = i;
        this.j = j;
    }
}
public class Main {
    private static int[] di = {1,-1,0,0};
    private static int[] dj = {0,0,1,-1};
    private static int T, H,W;
    private static char[][] board;
    private static Set<Character> keys;
    private static int ans;
    private static boolean isAvailable(char c){
        if(c == '*')
            return false;
        if(Character.isUpperCase(c)){
            return keys.contains(Character.toLowerCase(c));
        }
        return true;
    }

    private static void bfs(){
        // 열쇠 셋에 변화 생겼는지
        boolean changed = true;

        while(changed){
            changed = false;
            Queue<Point> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[H][W];

            // 열쇠 셋 변화 생기면 열린 문 때문에 시작지점 바뀔 수 있음
            for(int i = 0; i<H; i++){
                for(int j = 0; j<W; j++){
                    if(i == 0 || j == 0 || i == H-1 || j == W-1){
                        if(!visited[i][j]){
                           if(isAvailable(board[i][j])){
                               q.add(new Point(i,j));
                               visited[i][j] = true;
                           }
                        }
                    }
                }
            }

            while(!q.isEmpty()){
                Point cur = q.poll();
                int ci = cur.i;
                int cj = cur.j;

                char c = board[ci][cj];

                if(c == '$'){
                    board[ci][cj] = '.';
                    ans += 1;
                }
                else if(Character.isLowerCase(c)){
                    if(!keys.contains(c)){
                        keys.add(c);
                        changed = true;
                    }
                    board[ci][cj] = '.';
                }

                for(int i = 0; i<4; i++){
                    int ni = ci + di[i];
                    int nj = cj + dj[i];

                    if(ni < 0 || nj < 0 || ni >= H || nj >= W)
                        continue;    

                    if(visited[ni][nj])
                        continue;

                    char nc = board[ni][nj];

                    if(nc == '*')
                        continue;

                    if(Character.isUpperCase(nc)
                            && !keys.contains(Character.toLowerCase(nc)))
                        continue;

                    q.add(new Point(ni, nj));
                    visited[ni][nj] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ans = 0;
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            board = new char[H][W];

            for(int i = 0; i<H; i++){
                char[] temp = br.readLine().toCharArray();
                for(int j = 0; j<W; j++){
                    board[i][j] = temp[j];
                }
            }

            keys = new HashSet<>();
            char[] temp = br.readLine().toCharArray();
            if(temp[0] != '0'){
                for(char c : temp){
                    keys.add(c);
                }
            }

            bfs();

            bw.write(String.valueOf(ans));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
