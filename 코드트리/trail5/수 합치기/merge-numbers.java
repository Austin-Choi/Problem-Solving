import java.util.*;
import java.io.*;

/*
파일합치기와 달리 그건 인접한 2개만 가능하다는 조건때문에 
연속된 것에서 최적을 찾아야 해서 구간DP를 쓰지만
이건 아무거나 2개 할 수 있는거라 그리디 최적해가 보장됨.
-> 우선순위큐에 다 넣고 2개씩 뽑아서 합치고 다시넣고 1개될때까지 반복?
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        PriorityQueue<Integer> q  = new PriorityQueue<>();
        for(int i =0 ; i<N; i++)
            q.add(read());
        
        int ans = 0;
        while(q.size()>1){
            int fst = q.poll();
            int snd = q.poll();
            int cur = fst + snd;
            ans += cur;
            q.add(cur);
        }
        System.out.print(ans);
    }
}