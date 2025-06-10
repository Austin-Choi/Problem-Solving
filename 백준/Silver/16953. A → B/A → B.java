import java.io.*;
import java.util.*;

public class Main {
    private static long B;
    private static Set<Long> visited;
    private static Map<Long, Long> dist;
    private static long calc(long num, int cmd){
        if(cmd == 0){
            return num*2L;
        }
        else
            return num*10L + 1;
    }

    private static void bfs(long num){
        Queue<Long> q = new ArrayDeque<>();
        q.add(num);
        visited.add(num);

        while(!q.isEmpty()){
            long cur = q.poll();

            for(int i = 0; i<2; i++){
                long next = calc(cur, i);
                if(next <= B) {
                    if(!visited.contains(next)) {
                        visited.add(next);
                        dist.put(next, dist.get(cur) + 1);
                        q.add(next);

                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        visited = new HashSet<>();
        dist = new HashMap<>();
        dist.put(A,0L);

        bfs(A);

        long ans = -1;
        if(dist.get(B) != null)
            ans = dist.get(B)+1;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}