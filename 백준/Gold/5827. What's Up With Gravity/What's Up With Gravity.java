/*
중력은 항상 위->아래, 아래->위 2가지뿐임
바로 아래에 칸이 없으면 떨어짐
바로 아래에 칸이 있으면 -> 좌우, 중력뒤집기 가능

dist [i][j][2] = i,j칸에 중력 상태 k로 도착하는 최소 중력바꾸기 횟수
-> i,j의 중력방향 진행 다음칸은 #이어야 함
cost = 1 or 0 -> deque

추가 : 낙하도중 비팔로 만나도 성공으로 침
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static char[][] board;
    static int[] start, end;

    //현재 중력방향 맨 끝 위치 반환
    // 추가
    // -2,-2 : D도착
    // -1,-1 : 우주로떨어짐
    static int[] getEdgePos(int ci, int cj, boolean isDown){
        if(isDown){
            while(true){
                if(board[ci][cj]=='D')
                    return new int[]{-2,-2};
                if(ci == N-1)
                    return new int[]{-1,-1};
                if(board[ci+1][cj] == '#')
                    return new int[]{ci,cj};
                ci++;
            }
        }
        else{
            while(true){
                if(board[ci][cj]=='D')
                    return new int[]{-2,-2};
                if(ci == 0)
                    return new int[]{-1,-1};
                if(board[ci-1][cj] == '#')
                    return new int[]{ci,cj};
                ci--;
            }
        }
    }
    static int dijkstra(){
        // ci,cj,dist[i][j][2]
        // 중력은 상태값으로 dist에 추가
        Deque<int[]> q = new ArrayDeque<>();
        int[][][] dist = new int[N][M][2];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                Arrays.fill(dist[i][j], -1);
            }
        }
        // 버그 : 시작하자마자 중력적용해야함
        int[] s = getEdgePos(start[0], start[1], true);
        if(s[0] == -2)
            return 0;
        if(s[0] == -1)
            return -1;
        dist[s[0]][s[1]][0] = 0;
        q.add(new int[]{s[0], s[1], 0, 0});

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int ci = cur[0];
            int cj = cur[1];
            int cg = cur[2];
            int cc = cur[3];

            if(cc != dist[ci][cj][cg])
                continue;

            boolean isDown = (cg == 0);

            int bi = isDown ? ci + 1 : ci -1;
            if(bi >= 0 && bi < N && board[bi][cj] == '#'){
                int[] next = getEdgePos(ci,cj,!isDown);
                if(next[0] == -2)
                    return cc+1;
                if(next[0] != -1) {
                    if (dist[next[0]][next[1]][(cg + 1) % 2] == -1) {
                        dist[next[0]][next[1]][(cg + 1) % 2] = cc + 1;
                        q.addLast(new int[]{next[0], next[1], (cg + 1) % 2, cc + 1});
                    }
                }

                for(int d : new int[]{-1,1}){
                    int nj = cj + d;
                    if(nj >= 0 && nj < M){
                        if(board[ci][nj] != '#'){
                            next = getEdgePos(ci,nj,isDown);
                            if(next[0] == -2)
                                return cc;
                            if(next[0] != -1){
                                if(dist[next[0]][next[1]][cg] == -1){
                                    dist[next[0]][next[1]][cg] = cc;
                                    q.addFirst(new int[]{next[0],next[1],cg, cc});
                                }
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i<N; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                board[i][j] = temp[j];
                if(temp[j]=='C')
                    start = new int[]{i,j};
                if(temp[j]=='D')
                    end = new int[]{i,j};
            }
        }

        System.out.print(dijkstra());
    }
}
