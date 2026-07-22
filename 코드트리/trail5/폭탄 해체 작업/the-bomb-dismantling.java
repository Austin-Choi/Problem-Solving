import java.util.*;
import java.io.*;

/*
우선순위큐에 시간제한 적은순, 같으면 가치 높은순으로 넣고
하나 해결할때마다 time++하고 해결 가능한 조건은 poll했을때 ct < bombtime([1])
------------
시간 제한 늦은거서부터 가치 최대인거만 제거하기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] bomb = new int[N][2];
        for(int i = 0; i<N; i++){
            bomb[i] = new int[]{read(), read()};
        }

        Arrays.sort(bomb, (a,b)->{
            return a[1] - b[1];
        });

        PriorityQueue<Integer> q = new PriorityQueue<>();
        int ans = 0;
        for(int[] b : bomb){
            int cs = b[0];
            int ct = b[1];

            ans += cs;
            q.add(cs);

            if(q.size() > ct)
                ans -= q.poll();
        }
        System.out.print(ans);
    }
}