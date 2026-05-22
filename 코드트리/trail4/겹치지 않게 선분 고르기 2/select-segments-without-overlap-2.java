import java.util.*;
import java.io.*;

/*
끝점 기준으로 정렬하고
dp[i] = i번째 선분까지 봤을때 겹치지 않게 뽑을 수 있는 최대 선분의 수
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        List<int[]> li = new ArrayList<>();
        for(int i = 0; i<N; i++){
            li.add(new int[]{read(), read()});
        }
        Collections.sort(li, (a,b)->{
            if(a[1] != b[1])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int ans = 1;
        int lastEnd = li.get(0)[1];
        for(int i = 1; i<N; i++){
            int s = li.get(i)[0];
            int e = li.get(i)[1];

            if(s > lastEnd){
                ans++;
                lastEnd = e;
            }
        }
        System.out.print(ans);
    }
}