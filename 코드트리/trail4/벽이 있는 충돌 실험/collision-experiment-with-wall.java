import java.util.*;
import java.io.*;

/*
충돌 판정을 하려면 v[i][j]에 개수만 저장하면 안 되고, 
이동 결과를 따로 저장한 뒤 cnt==1인 구슬만 살아남게
*/

public class Main {
    static StreamTokenizer sst =
            new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static char rc() throws IOException {
        sst.nextToken();
        return sst.sval.charAt(0);
    }

    // 북 동 남 서
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static int T, N, M;
    static Map<Character, Integer> ctoi = new HashMap<>();
    static ArrayList<int[]> ball;

    static int revDir(int d) {
        return (d + 2) % 4;
    }

    static void move() {
        int size = ball.size();

        int[] niArr = new int[size];
        int[] njArr = new int[size];
        int[] ndArr = new int[size];
        int[][] cnt = new int[N][N];

        // 이동
        for (int idx = 0; idx < size; idx++) {
            int[] b = ball.get(idx);

            int ci = b[0];
            int cj = b[1];
            int cd = b[2];

            int ni = ci + di[cd];
            int nj = cj + dj[cd];

            // 벽
            if (ni < 0 || nj < 0 || ni >= N || nj >= N) {
                ni = ci;
                nj = cj;
                cd = revDir(cd);
            }

            niArr[idx] = ni;
            njArr[idx] = nj;
            ndArr[idx] = cd;

            cnt[ni][nj]++;
        }

        ArrayList<int[]> next = new ArrayList<>();

        // 충돌 처리
        for (int idx = 0; idx < size; idx++) {
            int ni = niArr[idx];
            int nj = njArr[idx];
            if (cnt[ni][nj] == 1) 
                next.add(new int[]{ni, nj, ndArr[idx]});
            
        }

        ball = next;
    }

    public static void main(String[] args) throws Exception {
        ctoi.put('U', 0);
        ctoi.put('R', 1);
        ctoi.put('D', 2);
        ctoi.put('L', 3);
        T = read();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = read();
            M = read();
            ball = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                int r = read() - 1;
                int c = read() - 1;
                char d = rc();
                ball.add(new int[]{r,c,ctoi.get(d)});
            }

            for (int t = 0; t <=2 * N; t++) {
                move();
            }
            sb.append(ball.size()).append('\n');
        }
        System.out.print(sb);
    }
}