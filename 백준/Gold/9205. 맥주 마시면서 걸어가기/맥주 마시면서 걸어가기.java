/*
정점사이 거리 맨해튼으로 일단 구하고
50미터에 20병 들고 편의점에서 리필
-> (cost 1000 이하만 가능)
-> 정점 도달할수 있어야 다음 정점 가능
도착 여부 보는거니까 모든 간선 다 사용해서 목표지점 도달하는지 보기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T;
    //편의점 갯수
    static int N;
    static ArrayList<int[]> vertex;

    static int getCost(int[] p1, int[] p2){
        return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);
    }

    static boolean bfs(){
        boolean[] visited = new boolean[N+2];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        visited[0] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == N+1)
                return true;
            for(int n = 0; n<N+2; n++){
                if(visited[n])
                    continue;
                if(getCost(vertex.get(cur), vertex.get(n))<=1000){
                    visited[n] = true;
                    q.add(n);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            vertex = new ArrayList<>();

            for(int i = 0; i<N+2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int vi = Integer.parseInt(st.nextToken());
                int vj = Integer.parseInt(st.nextToken());
                vertex.add(new int[]{vi,vj});
            }

            if(bfs())
                sb.append("happy\n");
            else
                sb.append("sad\n");
        }
        System.out.println(sb);
    }
}
