import java.util.*;
import java.io.*;

/*
kadane 알고리즘 쓰기
-> cur, ans
-> cur = 현재까지의 최대 합
-> ans = 전체 최대 합
-> ans 초깃값 잡을때 가능한 최대 최저값 잡아야함 0 아님
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int cur = 0;
        int ans = -1000 * 100_000;
        for(int i= 0; i<N; i++){
            int num = read();
            cur = Math.max(num, cur + num);
            ans = Math.max(ans, cur);
        }
        System.out.print(ans);
    }
}