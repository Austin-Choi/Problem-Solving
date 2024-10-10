
/*
N 한 변의 길이
M 파이어볼 숫자
K 이동 명령 횟수

행과 열은 0,0(실제)이 1,1로 입력됨.

이동은 fireball 클래스 내부에 구현하고
이동 후 board 업데이트
board 돌면서 2개 이상 있는 칸에서는 main문 밖에 구현된 divide로 fireball 정보 업데이트
그리고 divide는 Fireball[] 를 받아서 Fireball[]를 반환함

답은 board 다 돌면서 Fireball[] 읽어와서 m값 더하기

 */

import java.io.*;
import java.util.ArrayList;

class Fireball{
    int r; // 가로 위치(실제)
    int c; // 세로 위치(실제)
    int m; // 질량
    int s; // 속력
    int d; // 방향

    public Fireball(int r, int c, int m, int s, int d){
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }

    // size : 한 변의 크기
    /*
        7  0  1
        6     2
        5  4  3
    public static int[] di = {0, 1, 1, 1, 0, -1, -1, -1};
    public static int[] dj = {-1, -1, 0, 1, 1, 1, 0, -1};

    이동할때 격자 밖으로 나가면 소멸하는 히든 조건
     */
    // move 조건 잘 보기
    public void move(int size, int[] di, int[] dj){
        int newR = (this.r + dj[this.d]*this.s) % size;
        int newC = (this.c + di[this.d]*this.s) % size;
        
        if(newR > size-1)
            newR -= size;
        else if(newR < 0)
            newR += size;
        if(newC > size-1)
            newC -= size;
        else if(newC < 0)
            newC += size;

        this.r = newR;
        this.c = newC;
    }
}

public class Main {
    static int N;
    static int M;
    static int K;
    static ArrayList<Fireball>[][] board;
    public static int[] di = {0, 1, 1, 1, 0, -1, -1, -1};
    public static int[] dj = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static ArrayList divideBalls(ArrayList<Fireball> balls){
        int curR = balls.get(0).r; // 위치는 고정
        int curC = balls.get(0).c; // 위치는 고정
        int totalM = 0; // 질량
        int totalS = 0; // 속력
        int totalD = 0; // 방향
        int ballCount = balls.size();

        for(Fireball b : balls){
            totalM += b.m;
            totalS += b.s;
            totalD += b.d % 2;
        }
        // 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면 구분?
        // direction 모두 2로 나눈 결과 저장하고
        // directions 합이 ballcount 이거나 0이면 방향이 모두 같은것
        // 이외는 그렇지 않은 것
        int newM = totalM/5;
        int newS = totalS/ballCount;

        ArrayList<Fireball> result = new ArrayList<>();

//        String newD = "";
        if(newM != 0){
            if(totalD == ballCount || totalD == 0){
                result.add(new Fireball(curR, curC, newM, newS, 0));
                result.add(new Fireball(curR, curC, newM, newS, 2));
                result.add(new Fireball(curR, curC, newM, newS, 4));
                result.add(new Fireball(curR, curC, newM, newS, 6));

//                newD = "0246";
            }
            else{
                result.add(new Fireball(curR, curC, newM, newS, 1));
                result.add(new Fireball(curR, curC, newM, newS, 3));
                result.add(new Fireball(curR, curC, newM, newS, 5));
                result.add(new Fireball(curR, curC, newM, newS, 7));

//                newD = "1357";
            }
        }
//        System.out.println("new M:S:D -> "+newM+":"+newS+":"+newD);
        return result;
    }

    // 질량만 보여줌
    public static void printBoard(ArrayList<Fireball>[][] board){
        System.out.println("=================================================================================");
        for(int i = 0; i<board[0].length; i++){
            for (int j = 0; j<board[0].length; j++){
                System.out.print("[ i:"+i+" j:"+j+" => ");
                for(Fireball f : board[i][j]){
                    System.out.print(f.m+" ");
                }
                System.out.print(" ]");
            }
            System.out.println();
        }
        System.out.println("=================================================================================");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nmkInfo = br.readLine().split(" ");
        N = Integer.parseInt(nmkInfo[0]);
        M = Integer.parseInt(nmkInfo[1]);
        K = Integer.parseInt(nmkInfo[2]);

        board = new ArrayList[N][N];
        for(int i = 0; i<N; i++){
            for (int j = 0; j<N; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        while(M-->0){
            String[] inputFireball = br.readLine().split(" ");
            int tempR = Integer.parseInt(inputFireball[0]);
            int tempC = Integer.parseInt(inputFireball[1]);
            int tempM = Integer.parseInt(inputFireball[2]);
            int tempS = Integer.parseInt(inputFireball[3]);
            int tempD = Integer.parseInt(inputFireball[4]);
            board[tempR-1][tempC-1].add(new Fireball(tempR-1, tempC-1, tempM, tempS, tempD));
        }


        while(K-->0){
            // 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다. (정보만 업데이트)
            ArrayList<Fireball> ballPool = new ArrayList<>();
            for(int i = 0; i<N; i++){
                for (int j = 0; j<N; j++){
                    if(!board[i][j].isEmpty()){
                        for(Fireball f : board[i][j]){
                            f.move(N, di, dj);
                            ballPool.add(f);
                        }
                    }
                }
            }

            // 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다. (실제로 이동됨)
            board = new ArrayList[N][N];
            for(int i = 0; i<N; i++){
                for (int j = 0; j<N; j++){
                    board[i][j] = new ArrayList<>();
                }
            }

            for(Fireball f : ballPool){
                board[f.r][f.c].add(new Fireball(f.r, f.c, f.m, f.s, f.d));
            }
//            System.out.println("모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.");
//            printBoard(board);
            /*
                이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
                같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
                파이어볼은 4개의 파이어볼로 나누어진다.
             */
            for(int i = 0; i<N; i++){
                for (int j = 0; j<N; j++){
                    if(board[i][j].size()>=2){
                        board[i][j] = divideBalls(board[i][j]);
                    }
                }
            }

//            System.out.println("2개 이상의 파이어볼이 있는 칸에서는 4개로 나뉘어진다.");
//            printBoard(board);
        }

        // 마법사 상어가 이동을 K번 명령한 후, 남아있는 파이어볼 질량의 합을 구해보자.
        int totalM = 0;
        for(int i = 0; i<N; i++){
            for (int j = 0; j<N; j++){
                if(!board[i][j].isEmpty()){
                    for(Fireball b : board[i][j]){
                        totalM += b.m;
                    }
                }
            }
        }
        bw.write(totalM+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
