import java.util.*;
import java.io.*;
/*
cctv의 i,j 위치, 종류를 저장하는 int[cctv갯수][3]배열
1) cctv 종류에 따른 가능한 방향에서 최소 사각지대 상태의 board 방문 상태를 유지하고 다음 cctv로 넘어가야함
-> 백트래킹?
2)copyBoard 써서 상태 유지
 */
public class Main {
    static ArrayList<int[]> cctvs = new ArrayList<>();
    static int N,M;
    static int[][] board;
    static int ans = 0;
    // cctv가 감시할수 있는 공간은 7로 함
    static void dfs(int idx, int[][] board){
        if(cctvs.size() == idx){
            int cnt = 0;
            for(int i = 0; i<N; i++){
                for(int j= 0; j<M; j++){
                    if(board[i][j] == 0)
                        cnt++;
                }
            }
            ans = Math.min(ans, cnt);
            return;
        }

        int[] cctv = cctvs.get(idx);
        int si = cctv[0];
        int sj = cctv[1];
        int type = cctv[2];

        for(ArrayList<Integer> dirs : ranges[type]){
            int[][] copy = copyBoard(board);
            // dirs 전체가 한 조합
            for(int d : dirs){
                copy = see(si,sj,d,copy);
            }
            dfs(idx+1, copy);
        }
    }
    // 종류와 위치에 따른 board 방문표시해서 리턴해주는 거
    static int[][] see(int si,int sj,int d,int[][] copy){
        while(true){
            int ni = si + di[d];
            int nj = sj + dj[d];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                break;
            if(copy[ni][nj] == 6){
                break;
            }
            if(copy[ni][nj] == 0)
                copy[ni][nj] = 7;
            si = ni;
            sj = nj;
        }
        return copy;
    }
    // 전처리후 배열로접근
    static ArrayList<ArrayList<Integer>>[] ranges = new ArrayList[6];
    static void initRanges(){
        for(int i = 1; i<6; i++){
            ranges[i] = new ArrayList<>();
        }

        ranges[1].add(new ArrayList<>(List.of(0)));
        ranges[1].add(new ArrayList<>(List.of(1)));
        ranges[1].add(new ArrayList<>(List.of(2)));
        ranges[1].add(new ArrayList<>(List.of(3)));

        ranges[2].add(new ArrayList<>(List.of(0, 1)));
        ranges[2].add(new ArrayList<>(List.of(2, 3)));

        ranges[3].add(new ArrayList<>(List.of(0, 2)));
        ranges[3].add(new ArrayList<>(List.of(0, 3)));
        ranges[3].add(new ArrayList<>(List.of(1, 3)));
        ranges[3].add(new ArrayList<>(List.of(1, 2)));

        ranges[4].add(new ArrayList<>(List.of(0, 1, 2)));
        ranges[4].add(new ArrayList<>(List.of(0, 1, 3)));
        ranges[4].add(new ArrayList<>(List.of(0, 2, 3)));
        ranges[4].add(new ArrayList<>(List.of(1, 2, 3)));

        ranges[5].add(new ArrayList<>(List.of(0, 1, 2, 3)));
    }
    // 동 서 북 남
    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};

    static int[][] copyBoard(int[][] board){
        int[][] c = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                c[i][j] = board[i][j];
            }
        }
        return c;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        initRanges();
        board = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                int cur = Integer.parseInt(st.nextToken());
                board[i][j] = cur;
                if(cur > 0 && cur != 6){
                    cctvs.add(new int[]{i,j,cur});
                }
            }
        }

        ans = N*M+2;
        dfs(0,board);
        System.out.print(ans);
    }
}
