import java.util.*;
import java.io.*;

/*
같은 날이 있으면 입실 이벤트 먼저 처리해야함
1. 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        // 이벤트 시각, 델타값, 사람 번호
        int[][] E = new int[2*N][3];
        for(int i = 0; i<N; i++){
            int a = read();
            int b = read();

            E[2*i] = new int[]{a, 1, i+1};
            E[2*i+1] = new int[]{b, -1, i+1};
        }

        Arrays.sort(E, (a,b)->{
            if(a[0] != b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });

        int nextRoom = 0;
        // 비어있는 방 번호
        PriorityQueue<Integer> q =new PriorityQueue<>();
        // i번 사람이 val 방 쓰는중
        int[] using = new int[N+1];
        Set<Integer> ans = new HashSet<>();

        for(int i= 0; i<2*N; i++){
            // 입실 발생
            if(E[i][1] == 1){
                int nRoom = 0;
                if(q.isEmpty()){
                    nRoom = nextRoom++;
                }
                else{
                    nRoom = q.poll();
                }
                // 사람을 방에 배정
                using[E[i][2]] = nRoom;
                ans.add(nRoom);
            }
            // 퇴실 발생
            else{
                q.add(using[E[i][2]]);
            }
        }
        System.out.print(ans.size());
    }
}