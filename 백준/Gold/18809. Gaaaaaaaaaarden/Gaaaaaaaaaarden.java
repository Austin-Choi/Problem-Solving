/*
1) 배양액 시작점들 조합 구하기
Point[]로 놓을수 있는 위치 인덱스 해싱
-> 전체에서 G+R개 뽑음
-> G+R개에서 G개만 뽑음 (나머지는 R)

2) 빨강, 초록 배약액 퍼뜨리기
동시 bfs
스테이지별로 꽃 갯수 갱신해야하니까
queue 안에 i,j,isGreen,time으로 해서 time이 bfs단계
greentime 2차원, redtime 2차원 배열 놓고
현재 ci,cj가 flowers에서 true면 못퍼져나가니까 진행 x
color isGreen 보고 ni nj dist 갱신하고 바로
반대 time dist[][] 체크해서 같은지 보고
같으면 flowers true
아닐때만 큐에 넣음
 */
import java.awt.Point;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main {
    static int N,M,G,R;
    static int[][] board;

    static List<Point> cand = new ArrayList<>();

    static void chooseGR(int idx, int cnt, List<Integer> chosen){
        if(cnt == G+R){
            chooseG(0,0,chosen,new boolean[chosen.size()]);
            return;
        }
        if (idx == cand.size())
            return;

        chosen.add(idx);
        chooseGR(idx+1, cnt+1, chosen);
        chosen.remove(chosen.size()-1);

        chooseGR(idx+1, cnt, chosen);
    }

    static int ans = 0;
    static void chooseG(int idx, int cnt, List<Integer> chosen, boolean[] isGreen){
        if(cnt == G){
            List<Integer> red = new ArrayList<>();
            List<Integer> green = new ArrayList<>();
            for(int i = 0; i<chosen.size(); i++){
                if(isGreen[i])
                    green.add(chosen.get(i));
                else 
                    red.add(chosen.get(i));
            }
            ans = Math.max(ans, bfs(green, red));
            return;
        }
        
        if(idx == chosen.size())
            return;

        isGreen[idx] = true;
        chooseG(idx+1, cnt+1, chosen, isGreen);
        isGreen[idx] = false;
        
        chooseG(idx+1, cnt, chosen, isGreen);
    }

    static class Info{
        int i;
        int j;
        boolean isGreen;
        int time;
        Info(int i, int j, boolean isGreen, int time){
            this.i = i;
            this.j = j;
            this.isGreen = isGreen;
            this.time = time;
        }
    }
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};

    // 시간 기준으로 레벨 관리
    // gt, rt에서 미방문 -1, 시작점 0
    static int bfs(List<Integer> green, List<Integer> red){
        Queue<Info> q = new ArrayDeque<>();
        int[][] gt = new int[N][M];
        for(int i =0; i<N; i++){
            Arrays.fill(gt[i], -1);
        }
        int[][] rt = new int[N][M];
        for(int i = 0; i<N; i++){
            Arrays.fill(rt[i], -1);
        }
        boolean[][] flowers = new boolean[N][M];

        for(int gg : green){
            Point g = cand.get(gg);
            q.add(new Info(g.x, g.y, true, 0));
            gt[g.x][g.y] = 0;
        }
        for(int rr : red){
            Point r = cand.get(rr);
            q.add(new Info(r.x, r.y, false, 0));
            rt[r.x][r.y] = 0;
        }

        while(!q.isEmpty()){
            Info cur = q.poll();
            int ci = cur.i;
            int cj = cur.j;
            boolean isGreen = cur.isGreen;
            int ct = cur.time;

            if(flowers[ci][cj])
                continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if(board[ni][nj] == 0)
                    continue;

                // 도달했을때 바로 꽃이 되는지 체크해야 함
                if(isGreen){
                    if(gt[ni][nj] == -1){
                        gt[ni][nj] = ct+1;
                        if(rt[ni][nj] == ct+1)
                            flowers[ni][nj] = true;
                        else
                            q.add(new Info(ni, nj, isGreen, ct+1));
                    }
                    else
                        continue;
                }
                else{
                    if(rt[ni][nj] == -1){
                        rt[ni][nj] = ct+1;
                        if(gt[ni][nj] == ct+1)
                            flowers[ni][nj] = true;
                        else
                            q.add(new Info(ni, nj, isGreen, ct+1));
                    }
                }
            }
        }

        int rst = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(flowers[i][j])
                    rst++;
            }
        }
        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 2){
                    cand.add(new Point(i,j));
                }
            }
        }

        chooseGR(0,0, new ArrayList<>());
        System.out.println(ans);
    }
}
