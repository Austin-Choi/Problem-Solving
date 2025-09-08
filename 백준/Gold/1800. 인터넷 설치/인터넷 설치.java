/*
양방향이고

가격이 1~1000000이니까 이걸 leftright로 잡고
mid < cost의 상황이 K개 이하인가?

bfs
0-1bfs로 하면
간선비용 <= mid -> 가중치 0
간선비용 > mid -> 무료 혜택 소모, 가중치 1
-> 최소 몇개의 유로 간선을 밟아야 N번까지 도달하는가?
-> dist[N]이 K개 이하인지 boolean으로 출력

이분 탐색
can을 만족하면 더 줄여서 최소의 값을 탐색하고
-> r = mid - 1; (ans = mid)
can을 불만족하면 늘려서 가능한 최소의 값을 탐색해야 함
-> l = mid + 1;

주의점
가격이 1~백만인거지 실제 정답 범위는 
0~백만임 K개에서 다 해결될수도 있어서

그리고 ans는 -1 을 초기값으로 둬야 
아무것도 만족 못할때 -1을 출력함
 */
import java.util.*;
import java.io.*;
public class Main {
    static class Info{
        int dest;
        int cost;
        Info(int d, int c){
            this.dest = d;
            this.cost = c;
        }
    }
    static int N, P, K, ans;
    static ArrayList<Info>[] board;
    static boolean can(int x){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Deque<Integer> dq = new ArrayDeque<>();

        dist[1] = 0;
        dq.add(1);

        while(!dq.isEmpty()){
            int cur = dq.poll();

            for(Info i : board[cur]){
                int nn = i.dest;
                int nc = (i.cost > x) ? 1 : 0;
                if(dist[nn] > dist[cur] + nc){
                    dist[nn] = dist[cur] + nc;
                    if(nc == 0)
                        dq.addFirst(nn);
                    else
                        dq.addLast(nn);
                }
            }
        }

        return dist[N] <= K;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        for(int p = 0; p<P; p++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[s].add(new Info(e,c));
            board[e].add(new Info(s,c));
        }

        ans = -1;
        int l = 0;
        int r = 1_000_000;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }

        System.out.println(ans);
    }
}
