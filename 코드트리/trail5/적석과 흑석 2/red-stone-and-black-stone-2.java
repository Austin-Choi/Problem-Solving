import java.util.*;
import java.io.*;

/*
A는 오름차순으로 정렬하고 B는 [0] 오름차순으로 정렬

2 6 7 8 9
0,3 2,5 4,9 8,13

B 한번만 훑기 

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int C,N;

    public static void main(String[] args) throws IOException{
        C = read();
        N = read();
        int[] red = new int[C];
        int[][] black = new int[N][2];
        for(int i = 0; i<C; i++){
            red[i] = read();
        }
        Arrays.sort(red);
        for(int i = 0; i<N; i++){
            black[i] = new int[]{read(), read()};
        }
        Arrays.sort(black, Comparator.comparingInt(a->a[0]));

        int ans = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        int idx = 0;
        for(int i = 0; i<C; i++){
            int T = red[i];
            // idx<N 안하면 터짐
            while(idx < N && black[idx][0] <= T){
                q.add(black[idx++]);
            }
            
            // 매 실행마다 temp = peek말고 peek 자체로 접근해야 poll한뒤 그다음거 적용되고 함
            // -> 안그러면 무한루프걸림
            // 만료된 구간 먼저 버리기
            while(!q.isEmpty() && q.peek()[1] < T){
                q.poll();
            }
            // temp[0] <= T는 pq add조건에 의해 항상 참임
            if(!q.isEmpty()){
                q.poll();
                ans++;
            }
        }
        System.out.print(ans);
    }
}