/*
결정문제 : 어느 길 너비 K를 고르면 경로 상의 모든 길보다 작거나 같은지를 리턴
간선 배열 하나 받고
Info start dest cost
board는 Info arraylist

이분탐색은
가능해지면 해당 인덱스를 답 저장, 더 큰 후보로 시도
l = mid + 1
불가능해지면 더 작은 후보로 시도
r = mid
 */
import java.io.*;
import java.util.*;
public class Main {
    static int N, M, S, E;
    static ArrayList<Info>[] board;
    static class Info{
        int dest;
        int cost;
        Info(int d, int c){
            this.dest = d;
            this.cost = c;
        }
    }

    static boolean can(int x){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        q.add(S);
        visited[S] = true;

        while(!q.isEmpty()){
            int cd = q.poll();

            if(cd == E)
                return true;

            for(Info i : board[cd]){
                if(!visited[i.dest] && i.cost >= x){
                    visited[i.dest] = true;
                    q.add(i.dest);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 0; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        int[] arr = new int[M];
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[m] = c;
            board[s].add(new Info(e,c));
            board[e].add(new Info(s,c));
        }

        Arrays.sort(arr);
        int l = 0;
        int r = M-1;
        int ans = 0;
        while(l <= r){
            int mid = (l+r)/2;
            if(can(arr[mid])) {
                ans = arr[mid];
                l = mid + 1;
            }
            else
                r = mid - 1;
        }

        System.out.println(ans);
    }
}