
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N, M, B;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int[][] il = new int[N][M];

        // 0~256층까지 다 할수도 있지만
        // 최소 최대값을 구해서 해도 됨
        int minH = Integer.MAX_VALUE;
        int maxH = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                il[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, il[i][j]);
                maxH = Math.max(maxH, il[i][j]);
            }
        }

        int ansH = 0;
        int ansT = Integer.MAX_VALUE;

        for(int h = minH; h <= maxH; h++) {
            int curTime = 0;
            int blocks = B;
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    int diff = il[i][j] - h;
                    // 현재 기준 높이보다 높을 때
                    if(diff > 0){
                        curTime += 2*diff;
                        blocks += diff;
                    }
                    // 현재 기준 높이보다 낮을 때
                    else if(diff < 0){
                        curTime += -1 * diff;
                        blocks += diff;
                    }
                }
            }
            //블록이 없으면 불가능
            if(blocks < 0)
                break;

            // 이부분이 잘못되었었음
            // curTime <= ansT는 블록을 제거하는 것보다 블록을 추가하는 것이
            // 시간이 덜 들기 때문에 < 이 아닌 <=로 표현함
            if(curTime <= ansT){
                ansT = curTime;
                ansH = h;
            }
        }

        sb.append(ansT)
                .append(" ")
                .append(ansH);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
