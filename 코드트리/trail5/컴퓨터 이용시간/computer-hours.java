import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        // 이벤트 시각, 델타값, 사람 번호
        int[][] event = new int[2*N][3];
        // 사람이 차지하고 있는 컴터 번호 저장
        int[] ans = new int[N+1];
        Arrays.fill(ans, -1);
        for(int i = 0; i<N; i++){
            int a = read();
            int b = read();

            event[2*i] = new int[]{a, 1, i+1};
            event[2*i+1] = new int[]{b, -1, i+1}; 
        }

        // 종료 이벤트를 먼저 처리해야 컴퓨터 쓸수있음
        Arrays.sort(event, (a,b)->{
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });


        int cnt = 0;
        int next = 1;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 0; i<2*N; i++){
            // 컴터다씀
            if(event[i][1] == -1){
                q.add(ans[event[i][2]]);
            }
            else{
                int sNum;
                if(q.isEmpty()){
                    sNum = next++;
                }
                else{
                    sNum = q.poll();
                }
                ans[event[i][2]] = sNum;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            sb.append(ans[i]).append(" ");
        }
        System.out.print(sb);
    }
}