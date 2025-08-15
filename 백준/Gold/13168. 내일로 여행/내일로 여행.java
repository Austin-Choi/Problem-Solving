import java.util.*;
import java.io.*;
public class Main {
    static int N, R, M, K;
    static Map<String, Integer> city;
    static int[] plan;
    static int[][] normal;
    static int[][] tomorrow;
    static final int Limit = Integer.MAX_VALUE;
    static String[] free = {"Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun"};
    static String[] half = {"S-Train", "V-Train"};
    static int tomorrowCost(String type, int cost){
        for(String s : free){
            if(s.equals(type)){
                return 0;
            }
        }
        for(String s : half){
            if(s.equals(type))
                return cost / 2;
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        city = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            String temp = st.nextToken();
            city.put(temp, i);
        }
        M = Integer.parseInt(br.readLine());
        plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<M; i++){
            plan[i] = city.get(st.nextToken());
        }

        normal = new int[N][N];
        tomorrow = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(i==j){
                    normal[i][j] = 0;
                    tomorrow[i][j] = 0;
                }
                else{
                    normal[i][j] = Limit;
                    tomorrow[i][j] = Limit;
                }
            }
        }

        K = Integer.parseInt(br.readLine());
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            int s = city.get(st.nextToken());
            int e = city.get(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int c = tomorrowCost(t, cost * 2);
            normal[s][e] = Math.min(normal[s][e], cost * 2);
            normal[e][s] = normal[s][e];
            tomorrow[s][e] = Math.min(tomorrow[s][e], c);
            tomorrow[e][s] = tomorrow[s][e];
        }

        for(int k = 0; k<N; k++){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(normal[i][k] != Limit && normal[k][j] != Limit){
                        normal[i][j] = Math.min(normal[i][j], normal[i][k]+normal[k][j]);
                    }
                    if(tomorrow[i][k] != Limit && tomorrow[k][j] != Limit){
                        tomorrow[i][j] = Math.min(tomorrow[i][j], tomorrow[i][k]+tomorrow[k][j]);
                    }
                }
            }
        }

        int nt = 0;
        int tt = R * 2;

        for(int i = 1; i<M; i++){
            nt += normal[plan[i-1]][plan[i]];
            tt += tomorrow[plan[i-1]][plan[i]];
        }

        if(nt > tt)
            bw.write("Yes");
        else
            bw.write("No");
        bw.flush();
    }
}
