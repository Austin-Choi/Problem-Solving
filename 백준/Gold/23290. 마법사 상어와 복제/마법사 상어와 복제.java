// 물고기 2차원배열 (i,j,dir)
// 냄새 2차원배열
// 상어좌표 상어i, 상어j
/*
Info 객체 생성 대신 counting
-> board i j d 값을 이동/누적
 */
import java.util.*;
import java.io.*;
public class Main {
    static final int N = 4;
    static int[][][] board;
    //냄새 생기면 3, 한 사이클마다 -1씩감소 없으면 0
    static int[][] smell;
    static int M,S;
    //상어 이동용 우선순위 상좌하우
    static int[] sdi = {-1,0,1,0};
    static int[] sdj = {0,-1,0,1};
    //물고기 이동용 0~7
    static int[] di = {0,-1,-1,-1,0,1,1,1};
    static int[] dj = {-1,-1,0,1,1,1,0,-1};
    static int sharkI;
    static int sharkJ;
    static int[][][] nextBoard = new int[N][N][8];
    static int[][][] copyBoard = new int[N][N][8];
    // 0) 먼저 카피해서 갖고있기
    static void copymagic(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.arraycopy(board[i][j], 0, copyBoard[i][j], 0, 8);
            }
        }
    }

    /*
    1) 물고기 이동
    1칸 이동
    상어 true, 냄새 > 0, 경계out -> 불가능
    불가능하면 현재 dir에서 -d + 8 %8 해서 다시 시도(반시계)
    가능하면 이동하고 break;
    다음 물고기 ㄱㄱ
    ----------
    동시 이동이라 빈 nextBoard 써서
    움직인건 next ni nj에 더해주고
    못 움직인건 그대로 next ci cj에 더해줌
    마지막에 board = nextboard
     */
    static void fishmove(){
        //nextBoard 초기화
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                Arrays.fill(nextBoard[i][j], 0);
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int k = 0; k<8; k++){
                    int cnt = board[i][j][k];
                    if(cnt == 0)
                        continue;
                    boolean moved = false;
                    for(int d = 0; d<8; d++){
                        int nd = (k - d + 8)%8;
                        int ni = i + di[nd];
                        int nj = j + dj[nd];

                        if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                            continue;
                        if((ni == sharkI && nj == sharkJ) || smell[ni][nj] > 0)
                            continue;
                        nextBoard[ni][nj][nd] += cnt;
                        moved = true;
                        break;
                    }
                    if(!moved)
                        nextBoard[i][j][k] += cnt;
                }
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                System.arraycopy(nextBoard[i][j], 0, board[i][j], 0, 8);
            }
        }
    }
    /*
    2) 상어이동 ateFish, route (물고기수, 사전순 경로)
    -> 경로 += 선택방향 (1,2,3,4) * 10 ^ (3 - depth)

    static int[] sharkrst = {물고기수, 경로}
    -> 함수 호출 전에 main에서
    sharkrst {0, 9999}으로 초기화할 것

    상어이동은 DFS+백트래킹
    경계 벗어나면 return
    depth 3 되면
    ateFish 많으면 그걸로 sharkrst 교체해주고
    ateFish 같으면 sharkrst 경로 꺼내서 더 작을때만 현재 값으로 교체함
    -------
    물고기 존재 여부 eaten 추가로 써서
    물고기 있으면 임시로 거기 물고기 크기 더해주고 전부 없애줬다고 표시하고 dfs호출하고
    백트래킹할때 복원함
     */
    static int ateMax;
    static int routeMin;
    static boolean[][] eaten;

    static int fishSumAt(int i, int j){
        int sum = 0;
        for(int d = 0; d<8; d++)
            sum += board[i][j][d];
        return sum;
    }
    static void dfs(int si, int sj, int ateFish, int route, int depth){
        if(depth == 3){
            if(ateMax < ateFish || (ateMax == ateFish && routeMin > route)){
                ateMax = ateFish;
                routeMin = route;
            }
            return;
        }

        for(int d = 0; d<4; d++){
            int ni = si + sdi[d];
            int nj = sj + sdj[d];

            if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                continue;

            int fish = 0;
            boolean before = eaten[ni][nj];
            if(!eaten[ni][nj])
                fish = fishSumAt(ni,nj);

            eaten[ni][nj] = true;
            dfs(ni, nj, ateFish+fish, route*10 + d, depth+1);
            eaten[ni][nj] = before;
        }
    }

    /*
    2-2) 구한 sharkrst[1] 경로 따라가면서 (routeMin)
    -> 3자리 숫자 들어오면 int[] route로 바꿔주는 함수 만들기
    -> x/100 = [0], x -= [0]*100;
    x/10 = [1], x -= [1]*10;
    x = [2]
    return []
     */
    static void numToRoute(int route, int[] rst){
        rst[2] = route % 10;
        route /= 10;
        rst[1] = route % 10;
        route /= 10;
        rst[0] = route % 10;
    }

    /*
    isFish가 true였으면 smell = 3만들고
    isFish = false함
     */
    static void sharkMove(int[] route){
        for(int d = 0; d<3; d++){
            sharkI += sdi[route[d]];
            sharkJ += sdj[route[d]];

            int total = fishSumAt(sharkI, sharkJ);
            if(total > 0){
                Arrays.fill(board[sharkI][sharkJ], 0);
                smell[sharkI][sharkJ] = 3;
            }
        }
    }

    //3) smell 보드 모든 >0인거 다 smell -= 1
    static void smelloff(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(smell[i][j] > 0){
                    smell[i][j] -= 1;
                }
            }
        }
    }

    // 5) 마지막에 복제한거 붙이는 부분
    static void addmagic(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int d = 0; d<8; d++)
                    board[i][j][d] += copyBoard[i][j][d];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        board = new int[N][N][8];
        nextBoard = new int[N][N][8];
        copyBoard = new int[N][N][8];
        smell = new int[N][N];

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;
            board[i][j][dir]++;
        }

        st = new StringTokenizer(br.readLine());
        sharkI = Integer.parseInt(st.nextToken())-1;
        sharkJ = Integer.parseInt(st.nextToken())-1;


        for(int s = 0; s<S; s++){
            copymagic();
            fishmove();
            ateMax = 0;
            routeMin = 9999;
            eaten = new boolean[N][N];
            dfs(sharkI, sharkJ, 0,0,0);
            int route[] = new int[3];
            numToRoute(routeMin, route);
            sharkMove(route);
            smelloff();
            addmagic();
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int d = 0; d<8; d++){
                    ans += board[i][j][d];
                }
            }
        }

        System.out.println(ans);
    }
}
