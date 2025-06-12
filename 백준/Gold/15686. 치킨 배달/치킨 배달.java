
import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static boolean[] visited;
    private static ArrayList<Point> houses;
    private static ArrayList<Point> chickens;
    private static Point[] selectedCs;
    private static int minSum;

    private static int calc(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static void dfs(int idx, int depth){
        if(depth == M){
            int tmp = 0;
            for(Point h : houses) {
                int min = Integer.MAX_VALUE;
                for (Point c : selectedCs) {
                    min = Math.min(calc(h,c), min);
                }
                tmp += min;
            }
            minSum = Math.min(tmp, minSum);
            return;
        }

        for(int i = idx; i<chickens.size(); i++){
            selectedCs[depth] = chickens.get(i);
            dfs(i+1, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houses = new ArrayList<>();
        chickens = new ArrayList<>();

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1)
                    houses.add(new Point(i,j));
                else if(temp == 2)
                    chickens.add(new Point(i,j));
            }
        }
        visited = new boolean[chickens.size()];

        selectedCs = new Point[M];
        minSum = 9000000;
        dfs(0, 0);

        bw.write(String.valueOf(minSum));
        bw.flush();
        bw.close();
        br.close();
    }
}
