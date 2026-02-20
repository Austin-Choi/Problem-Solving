/*
각 사람은 최소 1개, 최대 2개의 간선을 가질수 잇음
사이클 길이 mod K... 는 간선 1개짜리 함수그래프에만.
행렬 거듭제곱 -> i->j로 한번에 갈수 있으면 1
K번만에 갈수 있으면 A^K[i][j]
a로 시작해서 depth가 K일때 도착 정점이 b인지 여부 출력

여기선 도착 여부만 보니까 bool[][] 쓰고
경로 길이가 중요하다면 long[][] 써야함
 */
import java.util.*;
import java.io.*;
public class Main {
    //N < 200이니까 2차원 배열, 행렬로 풀수있을듯
    static int N, K,M;
    static boolean[][] board;
    static boolean[][] multiply(boolean[][] a, boolean[][] b){
        boolean[][] rst = new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int k = 0; k<N; k++){
                // 경로 없으면 볼필요없음
                if(!a[i][k])
                    continue;
                for(int j = 0; j<N; j++){
                    if(b[k][j] && !rst[i][j])
                        rst[i][j] = true;
                }
            }
        }
        return rst;
    }

    static boolean[][] makeBasic(){
        boolean[][] rst = new boolean[N][N];
        for(int i = 0; i<N; i++){
            rst[i][i] = true;
        }
        return rst;
    }
    static boolean[][] fastExp(int exp){
        boolean[][] rst = makeBasic();
        boolean[][] base = board;
        while(exp > 0){
            if(exp % 2 == 1)
                rst = multiply(rst, base);
            base = multiply(base, base);
            exp /= 2;
        }
        return rst;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new boolean[N][N];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            board[i][u] =  board[i][v] = true;
        }
        boolean[][] bb = fastExp(K);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            if(bb[u][v])
                sb.append("death\n");
            else
                sb.append("life\n");
        }
        System.out.print(sb);
    }
}
