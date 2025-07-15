import java.io.*;
import java.util.*;
public class Main {
    static int N, K;
    static int[][] dist = new int[500001][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i<dist.length; i++){
            for(int j = 0; j<dist[i].length; j++){
                dist[i][j] = 500002;
            }
        }

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int t = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        // 시간 0은 짝수이므로 0으로 하고
        // 홀수가 아니니 이건 그대로 방문 X인 상태로 둠 
        dist[N][0] = 0;

        // 동생의 위치가 계속 바뀌어서
        // 일반적인 bfs처럼 목표 지점이 고정되어있지 않아서
        // while true 씀
        while (true){
            // 동생의 위치는 현재 시간에 따라 K에 대해 바뀌어야 함
            // K 자체가 갱신되면 안됨
            int bro = K + t*(t+1)/2;
            // 문제의 실패 조건
            if(bro > 500000) {
                bw.write("-1");
                break;
            }

            // 현재 형의 이동 시간 -> dist
            // 동생의 이동시간 -> t
            // 형과 동생의 시간 홀짝성이 일치해야 하고
            // 형이 먼저 도착해 있거나 동생이랑 같이 도착해야 만날 수 있음
            if(dist[bro][t%2] <= t){
                bw.write(t+"");
                break;
            }

            int size = q.size();
            // 한번에 큐 사이즈만큼 처리해서 시간 단계를 구분함
            for(int i = 0; i<size; i++){
                int c = q.poll();

                for(int next : new int[]{c+1, c-1, c*2}){
                    if(next < 0 || next > 500000)
                        continue;

                    if(dist[next][(t+1)%2] == 500002) {
                        //dist[next][(t+1)%2] = dist[c][t%2] + 1;
                        // bfs 단계별로 t를 관리할 때,
                        // 시간 변수 t를 기준으로 저장하는게 더 직관적이고 안정적
                        dist[next][(t+1)%2] = t + 1;
                        q.offer(next);
                    }
                }
            }
            t++;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
