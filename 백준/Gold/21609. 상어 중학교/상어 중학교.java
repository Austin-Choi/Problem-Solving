/*
11:30 12:58
일반 블록 1~M, 검은색 -1, 무지개 0, 공백 -2

2) 그룹 탐색에서 무지개 블록 방문 처리
-> 무지개 그룹은 여러 그룹에서 공유될 수 있어서 전역 방문처리하면 안됨
 */
import java.awt.*;
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[][] board;
    static long ans;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static class Group implements Comparable<Group>{
        int basei;
        int basej;
        int rainbowCount;
        ArrayList<Point> li = new ArrayList<>();
        Group(){}

        public void addBlock(int i, int j){
            li.add(new Point(i,j));
        }

        @Override
        public int compareTo(Group o){
            if(this.li.size() == o.li.size()){
                if(this.rainbowCount == o.rainbowCount){
                    if(this.basei == o.basei)
                        return o.basej - this.basej;
                    else
                        return o.basei - this.basei;
                }
                else
                    return o.rainbowCount - this.rainbowCount;
            }
            else
                return o.li.size() - this.li.size();
        }
    }
    static class Block{
        int i;
        int j;
        int val;
        Block(int i, int j, int val){
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
    static boolean isVaild(int i, int j){
        return (i >= 0 && j >= 0 && i < N && j < N);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            /*
            1) 블록 그룹 찾기
            visited 계속 사용
            0,0부터 i,j로 진행하면서 visit 안한 블록 && -1이 아닌 블록들 기준으로 동서남북 뻗어감
            -> start 블록의 색이거나 0이면 확장 가능
            -> PriorityQueue<Group>에 저장하기 (Group : 기준i, 기준j, 좌표 리스트)
            -> 정렬기준 좌표리스트 길이 큰것, 무지개가 많은것, 기준j가 큰것, 기준i가 큰것
            -> maxGroup = pq<Group> poll;

            무지개 블록 방문처리 ->localvisited
            >0 블록 방문처리 -> visited
             */
            boolean[][] visited = new boolean[N][N];
            PriorityQueue<Group> pq = new PriorityQueue<>();
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(!visited[i][j] && board[i][j] > 0){

                        int color = board[i][j];
                        boolean[][] localVisited = new boolean[N][N];
                        Queue<Point> q = new ArrayDeque<>();
                        ArrayList<Point> list = new ArrayList<>();

                        q.add(new Point(i,j));
                        visited[i][j] = true;
                        list.add(new Point(i,j));
                        int rainbowCount = 0;
                        int basei = i;
                        int basej = j;

                        while (!q.isEmpty()){
                            Point cur = q.poll();
                            int ci = cur.x;
                            int cj = cur.y;

                            for(int d = 0; d<4; d++){
                                int ni = ci + di[d];
                                int nj = cj + dj[d];

                                if(!isVaild(ni,nj))
                                    continue;
                                if(board[ni][nj] == -1)
                                    continue;

                                if(board[ni][nj] == 0){
                                    if(!localVisited[ni][nj]){
                                        localVisited[ni][nj] = true;
                                        list.add(new Point(ni,nj));
                                        rainbowCount++;
                                        q.add(new Point(ni,nj));
                                    }
                                }
                                // 기준 블록은 일반 블록들 중 row, col 작은 것으로 갱신해야 함
                                else if(!visited[ni][nj] && board[ni][nj] == color){
                                    visited[ni][nj] = true;
                                    list.add(new Point(ni,nj));
                                    q.add(new Point(ni,nj));

                                    if(board[ni][nj]>0) {
                                        if (ni < basei || (ni == basei && nj < basej)) {
                                            basei = ni;
                                            basej = nj;
                                        }
                                    }
                                }
                            }
                        }
                        for (Point p : list){
                            if(board[p.x][p.y] == 0){
                                visited[p.x][p.y] = false;
                            }
                        }

                        if(list.size()>=2){
                            Group g = new Group();
                            g.li = list;
                            g.rainbowCount = rainbowCount;
                            g.basej = basej;
                            g.basei = basei;
                            pq.add(g);
                        }
                        else{
                            visited[i][j] = false;
                        }

                    }
                }
            }
            // 그룹없으면 끝
            if(pq.isEmpty())
                break;

            Group maxGroup = pq.poll();
            if(maxGroup.li.size() < 2)
                break;

            /*
            2) 1번의 좌표리스트 돌면서 공백으로 바꿔줌
            좌표리스트 크기의 제곱만큼 ans에 합산함
             */
            for(Point p : maxGroup.li){
                board[p.x][p.y] = -2;
            }
            ans += (long) maxGroup.li.size() * maxGroup.li.size();

            /*
            3) 중력 작용
            아래서부터 시작해서 열단위로 처리하고
            검은 블록이 나오면 벽으로 간주하고 그 위에 떨어질 블록이 있을수도 있으니
            나머지는 현재 base에 넣고 i,j는 공백처리해서 비워주고
            base를 한칸 위로 끌어올려주면서 처리하기
             */
            for(int j = 0; j<N; j++){
                int base = N-1;
                for(int i = N-1; i>=0; i--){
                    // 검은색 만나면 그 위부터 시작
                    if(board[i][j] == -1)
                        base = i -1;
                    else if(board[i][j] >= 0){
                        if(base != i){
                            board[base][j] = board[i][j];
                            board[i][j] = -2;
                        }
                        base--;
                    }
                }
            }

            /*
            4) 90도 반시계 보드 회전
            copyboard 하나 만들고
            for(int j = N-1; j>=0; j--){
                for(int i = 0; i<N; i++){
                    copy[N-1-j][i] = board[i][j];
            다 돌고 board = copy
             */
            int[][] copy = new int[N][N];
            for(int j = N-1; j>=0; j--){
                for(int i = 0; i<N; i++){
                    copy[N-1-j][i] = board[i][j];
                }
            }
            board = copy;

            // 5) 3번 내용 반복
            for(int j = 0; j<N; j++){
                int base = N-1;
                for(int i = N-1; i>=0; i--){
                    // 검은색 만나면 그 위부터 시작
                    if(board[i][j] == -1)
                        base = i -1;
                    else if(board[i][j] >= 0){
                        if(base != i){
                            board[base][j] = board[i][j];
                            board[i][j] = -2;
                        }
                        base--;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
