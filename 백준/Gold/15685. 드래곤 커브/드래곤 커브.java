/*
1) 커브 그리기 (보드에 방문표시하기)
list<int[]>에 이제까지 생성한 드래곤 커브 정보 저장하고
동 북 서 남으로 주어지니까
최근거부터 새방향 = (원래 방향+1) % 4 해서 이동을 세대수 이하까지 하기 (0세대부터 시작)

2) 크기가 1*1 정사각형을 보면서 네 꼭지점이 모두 드래곤 커브의 일부인 것 갯수 출력하기
-> 이중for문 board 위에서
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static boolean[][] board = new boolean[101][101];
    static int[] di = {0,-1,0,1};
    static int[] dj = {1,0,-1,0};
    /*
    방향 초기값 넣고 세대만큼 방향 저장해서 list 재구성하고
    1번만 list대로 si,sj에서 시작해서 board에 방문표시함
     */
    static void makeCurve(int si, int sj, int sd, int g){
        ArrayList<Integer> dr = new ArrayList<>();
        dr.add(sd);
        for(int i = 1; i<=g; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = dr.size()-1; j>=0; j--){
                int dir = dr.get(j);
                temp.add((dir+1)%4);
            }
            dr.addAll(temp);
        }

        board[si][sj] = true;
        for(int dir : dr){
            int ni = si + di[dir];
            int nj = sj + dj[dir];
            board[ni][nj] = true;
            si = ni;
            sj = nj;
        }
    }
    /*

     */
    static int ans = 0;
    static void count(){
        for(int i = 0; i<100; i++){
            for(int j = 0; j<100; j++){
                if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1])
                    ans++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while(N-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            makeCurve(y,x,d,g);
        }
        count();
        System.out.print(ans);
    }
}
