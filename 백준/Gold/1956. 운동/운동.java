/*
정방 + 역방 그래프 만들고
모든 정점에 대해 정방 다익스트라로 하나씩 구하고
만약 현재 시작점에 대해 역방향이 존재한다면 그거 정점 하나 얹은게 돌아오는 것이 되고
이 문제에서는 그 돌아오는 사이클의 최소값 하나만 구하면 돼서 O(VE log V)로 가능
-> 그래프 밀도가 높아서 다익스트라 불리한듯?
 -> 플로이드가 유리함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[][] g;
    static int V,E;
    static final int INF = 400 * 10000 + 1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        g = new int[V+1][V+1];
        for(int i = 1; i<=V; i++){
            Arrays.fill(g[i], INF);
        }
        while(E-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            g[a][b] = c;
        }

        int ans = INF;
        // 반환점 k 찍고 돌아오기
        for(int k = 1; k<=V; k++){
            for(int i = 1; i<=V; i++){
                for(int j = 1; j<=V; j++){
                    if(g[i][k] != INF && g[k][j] != INF)
                        g[i][j] = Math.min(g[i][j], g[i][k]+g[k][j]);
                }
            }
        }
        for(int i = 1; i<=V; i++)
            ans = Math.min(ans, g[i][i]);
        System.out.print(ans == INF? -1:ans);
    }
}
