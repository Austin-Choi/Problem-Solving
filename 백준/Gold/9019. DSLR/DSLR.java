import java.io.*;
import java.util.*;


public class Main {
    private static int A;
    private static int B;
    private static boolean[] visited;
    private static String[] dist;

    private static int calc(int n, int cmd){
        if(cmd == 0){
            return (n*2) % 10000;
        }
        else if(cmd == 1){
            if(n == 0)
                return 9999;
            return n-1;
        }
        else if(cmd == 2){
            return (n%1000) * 10 + n/1000;
        }
        else
            return (n%10) * 1000 + n/10;
    }
    private static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            char[] cmds = {'D','S','L','R'};

            for(int i = 0; i<4; i++){
                int next = calc(cur, i);

                if(!visited[next]){
                    visited[next] = true;
                    dist[next] = dist[cur] + cmds[i];
                    q.add(next);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            dist = new String[10000];
            Arrays.fill(dist, "");
            visited = new boolean[10000];
            bfs(A);
            bw.write(dist[B]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
