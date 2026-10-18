/*
칸 최대 16개고 출력값 1000이라 32000 int 범위내

dfs에서 각 배터리 셀의 visited 상태 가지고 가야 함
만약 depth가 5라면 (각자 5칸을 선택했다면)
visited true인거 검사해서 겹치는 true가 2개일때만 합 보기
합 최대화

연결된 5칸짜리 펜타미노니까 가능한 모양 구해두고 비교하기
-> N*M 비트마스크

모든 칸이 시작점
*/
import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] board;
    
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    static Map<Integer, Integer> ss = new HashMap<>();

    // 1) 펜타미노 생성후 저장
    // Map에 mask, sum을 저장하면
    // N*M 비트마스크에서는 ㅡ,ㅡ 모양 2개여도 마스크값이 달라서 키의 유일성이 보장됨
    static void dfs(int ci, int cj, boolean[][] v, int depth, int mask, int sum){
        if(depth == 5){
            ss.put(mask, sum);
            return;
        }

        // 펜타미노 선택할때 현재 칸에서 이어질 필요는 없음 기존 칸 어딘가에 붙어있기만 하면됨
        for(int ni = 0; ni < N; ni++){
            for(int nj = 0; nj < M; nj++){
                if(v[ni][nj])
                    continue;

                boolean can = false;
                for(int d = 0; d<4; d++){
                    int nni = ni + di[d];
                    int nnj = nj + dj[d];
                    if(nni < 0 || nnj < 0 || nni >= N || nnj >= M)
                        continue;
                    if(v[nni][nnj]){
                        can = true;
                        break;
                    }
                }

                if(!can)
                    continue;

                v[ni][nj] = true;
                int nm = mask | (1 << (ni*M + nj));
                dfs(ni,nj, v, depth+1, nm, sum + board[ni][nj]);
                v[ni][nj] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                // 시작 셀 값 넣고 시작해야함
                boolean[][] visited = new boolean[N][M];
                visited[i][j] = true;
                dfs(i,j, visited, 1, 1 << (i*M + j), board[i][j]);
            }
        }

        int ans = -32001;
        // map의 모든 펜타미노 조합후 최대값 계산
        for(int m1 : ss.keySet()){
            for(int m2 : ss.keySet()){
                // if(m1 == m2)
                //     continue;
                
                int bc = Integer.bitCount(m1 & m2);
                if(bc == 2){
                    int curSum = ss.get(m1) + ss.get(m2);
                    ans = Math.max(ans, curSum);
                }
            }
        }

        System.out.print(ans);
    }
}