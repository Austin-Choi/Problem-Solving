import java.util.*;
import java.io.*;

/*
ball list를 copy해서 move가 반환하고
원래 ball이랑 copy size가 다르면 충돌이 일어난 것
제일 늦은 시점 기록 -> ans

그리고 이동중에 충돌이 발생할 수도 있음. 
2초에 1칸씩 움직이는데 그냥 좌표 무한이니까 1초에 2칸씩 움직인다고 하고 계산하기
-> 그럼 시작좌표를 2배해서 입력

1
6
6 7 2 D
7 6 2 L
9 3 1 L
6 6 4 R
7 9 2 D
7 10 3 D

시간초과.. 파싱문제??
->int int니까 long으로 해서 <<32 shifting

*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static char rc() throws IOException{
        sst.nextToken();
        return (char) sst.sval.charAt(0);
    }

    static int T, N;
    static int[] ctoi = new int[128];
    //북동남서
    static int[] di = {1,0,-1,0};
    static int[] dj = {0,1,0,-1};
    // 파싱좌표, 번호, 무게, 방향
    // 좌표값 파싱해서 넣기 -> 두개이상 구슬 충돌하게 되면 하나만 남으므로
    // 그러면 이동하고나서 한 좌표에 여러 구슬 있는거 어떻게 표현하지???? 좌표가 무한인데
    // values로 접근? 자바는 멀티맵없을텐데
    // -> value를 list로
    // cn, cw, cd
    static Map<Long, int[]> ball;
    static long pack(int i, int j){
        return (((long)i) << 32) | (j & 0xffffffffL);
    }
    static int getI(long key){
        return (int)(key >> 32);
    }
    static int getJ(long key){
        return (int)key;
    }

    // 충돌여부 move에서 계산해야함
    static boolean coll;
    static Map<Long, int[]> move(){
        coll = false;

        Map<Long, int[]> winner = new HashMap<>();
        Map<Long, Integer> cnt = new HashMap<>();

        for(long key : ball.keySet()){

            int[] info = ball.get(key);

            int ci = getI(key);
            int cj = getJ(key);

            int cn = info[0];
            int cw = info[1];
            int cd = info[2];

            int ni = ci + di[cd];
            int nj = cj + dj[cd];

            // 범위 밖 제거
            if(ni < -2000 || ni > 2000 || nj < -2000 || nj > 2000)
                continue;

            long nKey = pack(ni, nj);

            cnt.put(nKey, cnt.getOrDefault(nKey, 0) + 1);

            if(!winner.containsKey(nKey)){
                winner.put(nKey, info);
            }
            else{
                int[] prev = winner.get(nKey);
                // 무게 우선, 같으면 번호 우선
                if(cw > prev[1] || (cw == prev[1] && cn > prev[0])){
                    winner.put(nKey, info);
                }
            }
        }

        for(int v : cnt.values()){
            if(v >= 2){
                coll = true;
                break;
            }
        }

        return winner;
    }

    public static void main(String[] args) throws IOException{
        T = read();
        ctoi['U'] = 0;
        ctoi['R'] = 1;
        ctoi['D'] = 2;
        ctoi['L'] = 3;
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int ans = -1;
            N = read();
            ball = new HashMap<>();

            for(int n = 1; n<=N; n++){
                int j = read()*2;
                int i = read()*2;
                int w= read();
                int d = ctoi[rc()];
                long key = pack(i, j);
                //cn, cw, cd
                ball.put(key, new int[]{n, w, d});
            }

            // [-1000,1000]이니까 4000으로 일단 함
            for(int t = 1; t<=4000; t++){
                if(ball.size() <= 1)
                    break;
                ball = move();
                if(coll)
                    ans = t;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}