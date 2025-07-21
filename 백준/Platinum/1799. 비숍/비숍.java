/*
i,j 좌표라고 하면 i+j % 2로 흑백 여부 나눌수 있고
흑칸끼리만, 백칸끼리만 각자 독립된 DFS 돌리면 됨
각자의 max를 구해서 더하면 정답

빈칸 포지션 리스트 만들어서
이 대각선이 이미 사용되었는지 여부를 visited 대용으로 쓰고

 */
import java.awt.Point;
import java.io.*;
import java.util.*;
public class Main {
    // 1시, 5시, 7시, 10시
    static int[] di = {1,-1,-1,1};
    static int[] dj = {1,1,-1,-1};

    // 어느 대각선을 썻는지 visited 배열 어떻게 표현하지???
    // 1시,7시는 pos를 추적해보면 항상 일정함
    // 0, 2, 4, 6, 8 => 2(N-1)의 범위
    // 0부터 2N-2를 포함해서 넣어야 하니 배열 실제 길이는 2N-1,
    // 배열 선언은 2N
    // 5시, 10시는 i-j
    // 0,4 1,3 2,2 3,1 4,0
    // -4 -2 0 2 4
    // 음수값이 나오므로 + N-1를 오프셋으로 붙여주면
    // 아까랑 똑같이 배열 크기는 2N이지만
    // 1시7시 -> i+j
    // 5시10시 -> i-j+N-1
    static boolean[] vi1;
    static boolean[] vi2;

    static int N;
    static int[][] board;
    static int whiteMax = 0;
    static int blackMax = 0;
    static ArrayList<Point> bpl = new ArrayList<>();
    static ArrayList<Point> wpl = new ArrayList<>();

    static void dfs(int idx, int depth, ArrayList<Point> list, boolean isBlack){
        if(idx == list.size()){
            if(isBlack){
                blackMax = Math.max(depth, blackMax);
                return;
            }
            else{
                whiteMax = Math.max(depth, whiteMax);
                return;
            }
        }

        int i = list.get(idx).x;
        int j = list.get(idx).y;

        // 지금 i,j가 속한 대각선 안 썻으면
        // 비숍을 놓고 백트래킹
        if(!vi1[i+j] && !vi2[i-j+N-1]){
            vi1[i+j] = true;
            vi2[i-j+N-1] = true;
            dfs(idx+1, depth+1, list, isBlack);
            vi1[i+j] = false;
            vi2[i-j+N-1] = false;
        }

        // 해당 칸에 비숍 안 놓는 경우
        dfs(idx+1, depth, list,isBlack);
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if(num == 1){
                    if((i+j)% 2 == 0)
                        wpl.add(new Point(i,j));
                    else
                        bpl.add(new Point(i,j));
                }
            }
        }
        vi1 = new boolean[2*N];
        vi2 = new boolean[2*N];

        dfs(0,0,wpl, false);
        dfs(0,0,bpl, true);

        bw.write(String.valueOf(whiteMax + blackMax));
        bw.flush();
        bw.close();
        br.close();
    }
}