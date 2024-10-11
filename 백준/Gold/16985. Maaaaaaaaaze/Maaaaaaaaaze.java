import java.io.*;
import java.util.*;
/*
1. 0~20의 숫자로 (0,1,2,3은 1번 보드의 4가지 상태) 5 길이의 순열의 모든 경우의 수를 뽑아 시드값 만듬
2. 순서 정한대로 판 쌓아서 maze 생성
3. maze bfs 탐색
0은 참가자가 들어갈 수 없는 칸, 1은 참가자가 들어갈 수 있는 칸을 의미한다.!!!!!!!!!!

반복하며 최소값 출력. (12가 이상적으로 가능한 최소값이므로 12나오면 break)
 */

public class Main {
    public static int N = 5;
    // 면과 면 맞닿은 이동가능한 곳
    // 상 하 앞 뒤 좌 우
    public static int[] di = {1, -1, 0, 0, 0, 0};
    public static int[] dj = {0, 0, 1, -1, 0, 0};
    public static int[] dk = {0, 0, 0, 0, -1, 1};

    // 이동 비용이 1이므로 A* 안씀..
    // q대신 pq사용해서 cost가 최소가 되는 걸 먼저 나오게 해서
    // 휴리스틱 함수(ex 맨해튼 거리로 현재 위치에서 도착점까지 거리 구하기)값이랑
    // 시작점에서 현재 위치까지의 실제 이동 비용을 더해서
    // 이 값(expectedCost)이 가장 작은 경로를 우선 탐색함
    public static int bfs(int[][][] maze){
        // 시작과 끝이 1이어야함
        if (maze[0][0][0] == 0 || maze[4][4][4] == 0) {
            return -1;
        }

        boolean[][][] check = new boolean[N][N][N];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0, 0});
        check[0][0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int i = cur[0];
            int j = cur[1];
            int k = cur[2];
            int cost = cur[3];

            if(i==4 && j==4 && k==4)
                return cost;

            for(int ii = 0; ii<6; ii++){
                int nextI = i + di[ii];
                int nextJ = j + dj[ii];
                int nextK = k + dk[ii];

                if(nextI>=0 && nextJ>=0 && nextK>=0
                        && nextI<N && nextJ<N && nextK<N
                        && !check[nextI][nextJ][nextK]
                        && maze[nextI][nextJ][nextK] == 1){

                    check[nextI][nextJ][nextK] = true;
                    q.offer(new int[]{nextI, nextJ, nextK, cost+1});
                }
            }
        }
        // 도달 못함
        return -1;
    }

    // 보드 시계방향으로 한칸 돌리는 함수
    public static int[][] rotate(int[][] boardToRotate){
        int[][] temp = new int[N][N];
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                temp[j][4-i] = boardToRotate[i][j];
            }
        }
        return temp;
    }

    // 시드값 구할때 미리 돌린 상태까지 해서 20개를 가지고 5 길이의 순열 뽑는거 구해서
    // /4 해서 나오는게 보드 종류고
    // %4 해서 나오는게 몇번 시계방향으로 돌린 상태인지 판별

    // 0,1,2,3 은 0번 보드의 4가지 상태
    // 4,5,6,7 은 1번 보드이 4가지 상태
    // ...

    // 0, 1, 2, 3, 4 보드 놔야 하니까 maze 만들때 쓸 순열 경우의 수 시드값 구하기
    // 백트래킹
    public static List<List<Integer>> genSeed(){
        List<List<Integer>> result = new ArrayList<>();
        int[] numbers = new int[20];
        for(int i = 0; i<20; i++){
            numbers[i] = i;
        }
        bt(numbers, new ArrayList<>(), new boolean[N], result);
        return result;
    }
    public static void bt(int[] numbers, List<Integer> cur, boolean[] check, List<List<Integer>> result){
        if(cur.size() == N){
            result.add(new ArrayList<>(cur));
            return;
        }

        // 보드별로 5가지중 하나 고르고 
        for(int i = 0; i<N; i++){
            if(!check[i]){
                check[i] = true;
                // 상태 순회하면서 상태별로 뽑아줌
                for(int j = 0; j<4; j++){
                    int state = i * 4 + j;
                    cur.add(numbers[state]);
                    bt(numbers, cur, check, result);
                    cur.remove(cur.size()-1); // 마지막에 추가한 숫자 삭제
                }
                check[i] = false;
            }
        }

    }

    // 미로 시드 읽어와서
    // 돌려진 상태 확인하고
    // 상태에 맞게 미로 생성함
    public static int[][][] genMaze(List<Integer> seed, int[][][] input){
        int[][][] maze = new int[N][N][N];
        for(int i = 0; i<5; i++){
            int kind = seed.get(i) / 4;
            int rotateCount = seed.get(i) % 4;
            int[][] curBoard = input[kind];
            for(int j = 0; j<rotateCount; j++){
                curBoard = rotate(curBoard);
            }

            for(int k = 0; k<N; k++){
                maze[i][k] = Arrays.copyOf(curBoard[k], N);
            }
        }
        return maze;
    }

    // 미로 리스트 생성 함수
    public static List<int[][][]> genMazeList(int[][][] input, List<List<Integer>> seeds){
        List<int[][][]> result = new ArrayList<>();

        // seed대로 받아서
        // 각자 원래상태, 3번까지 돌린거 해서 저장
        for(List<Integer> seed : seeds){
            result.add(genMaze(seed, input));
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][][] input = new int[5][5][5];
        for(int i = 0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    input[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        List<int[][][]> mazeList = genMazeList(input, genSeed());

        int answer = Integer.MAX_VALUE;
        for(int[][][] cur : mazeList){
            int tmp = bfs(cur);
            // 0,0,0 에서 4,4,4까지 가는데 최소 길이는
            // 모든 이동가능하다고 했을때 12이다.
            // 아래로 4번 마지막 보드에서 오른쪽으로 4번 앞으로 4번 = 12
            if(tmp == 12){
                answer = tmp;
                break;
            }
            if(tmp != -1) {
                answer = Math.min(answer, tmp);
            }
        }

        if(answer == Integer.MAX_VALUE)
            bw.write("-1");
        else
            bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
