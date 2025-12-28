/*
cell[][] -> 칸이 유효한가?
down[][] -> 아래 경계 존재하는가
right[][] -> 오른쪽 경계 존재하는가

행 = 2M+1, 열 = 3N+1

occ 써서 이미 차지하고 있는 칸을 건너뛰면서 j를 계산해서 입력받아야함
합치기 정보 저장하는 in에서 열의 수는 j값의 등장 최대값이고

경계 down, right 전부 true로 해놓고 in읽으면서
경계 지울거 지움
현재 -> r,c
아래쪽 -> (r -> r+rs-1, c -> c + cs) 만큼 지우기
위쪽 -> (r->r+rs, c->c+cs-1) 만큼 지우기

줄이 공백으로 끝나면 삭제해야함,...
 */
import java.util.*;
import java.io.*;
public class Main {
    static int M, N;
    static boolean[][] cell;
    static boolean[][] down;
    static boolean[][] right;
    // 이미 사용된칸
    static boolean[][] occ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean fst = true;

        while(true){
            M = Integer.parseInt(br.readLine());
            if(M == 0)
                break;

            if(!fst)
                sb.append("\n");
            fst = false;

            List<int[]> in = new ArrayList<>();
            N = 0;
            // 합치기 정보 없는칸은?
            occ = new boolean[M][9];
            for(int i = 0; i<M; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int j = 0;

                for(int k = 0; k<n; k++){
                    while(occ[i][j])
                        j++;

                    int rs = Integer.parseInt(st.nextToken());
                    int cs = Integer.parseInt(st.nextToken());
                    in.add(new int[]{i, j, rs, cs});

                    for(int x = i; x<i+rs; x++){
                        for(int y = j; y < j+cs; y++){
                            occ[x][y] = true;
                        }
                    }
                    j += cs;
                }
                N = Math.max(N, j);
            }

            cell = new boolean[M][N];
            down = new boolean[M][N];
            right = new boolean[M][N];

            // 초기화
            for(int i = 0; i<M; i++){
                for(int j = 0; j<N; j++){
                    down[i][j] = true;
                    right[i][j] = true;
                }
            }

            for(int[] info : in){
                int r = info[0];
                int c = info[1];
                int rs = info[2];
                int cs = info[3];

                // 각 인덱스는 항상 true긴함
                cell[r][c] = true;

                // 아래쪽 경계 지우기
                for(int i = r; i<r+rs-1; i++){
                    for(int j = c; j < c + cs; j++){
                        down[i][j] = false;
                    }
                }

                // 오른쪽 경계 지우기
                for(int i = r; i<r+rs; i++){
                    for(int j = c; j < c + cs-1; j++){
                        right[i][j] = false;
                    }
                }
            }

            //print
            // 처음이거나 아래 경계 살아있으면 --
            // 아니면 뚫어놓기
            for(int i = 0; i<=M; i++){
                StringBuilder data = new StringBuilder();
                for(int j = 0; j<N; j++){
                    if(i == 0 || down[i-1][j])
                        data.append(" --");
                    else
                        data.append("   ");
                }
                while(data.length() > 0 && data.charAt(data.length()-1) == ' ')
                    data.deleteCharAt(data.length()-1);
                sb.append(data).append("\n");

                if(i == M)
                    break;

                // 맨 왼쪽이거나 오른쪽 경계 살아있으면 |
                // 아니면 뚫기
                data = new StringBuilder();
                for(int j = 0; j<N; j++){
                    if(j == 0 || right[i][j-1])
                        data.append("|");
                    else
                        data.append(" ");

                    // 칸 정보 살아있으면 1-based로 입력
                    if(cell[i][j])
                        data.append(i+1).append(j+1);
                    else
                        data.append("  ");
                }
                // 맨오른쪽출력
                data.append("|");

                while(data.length() > 0 && data.charAt(data.length()-1) == ' ')
                    data.deleteCharAt(data.length()-1);
                sb.append(data).append("\n");
            }

        }
        System.out.println(sb);
    }
}