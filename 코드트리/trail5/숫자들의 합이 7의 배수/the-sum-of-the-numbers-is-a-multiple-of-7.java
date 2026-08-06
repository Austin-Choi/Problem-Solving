import java.util.*;
import java.io.*;

/*
연속하게 고른 수의 합이 7의 배수가 되려면
구간값 [i,j] -> pre[j] - pre[i-1]
만약 이때 합의 나머지값이 같으면 그 구간은 7의 배수
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();

        int[] fst = new int[7];
        int[] last = new int[7];
        Arrays.fill(fst, -1);
        Arrays.fill(last, -1);
        // 처음부터 구간이 시작되는 답을 찾으려면 0은 0으로 
        fst[0] = 0;
        last[0] = 0;

        long sum = 0;
        for(int i = 1; i<=N; i++){
            sum += read();
            int m = (int)(sum % 7);
            if(fst[m] == -1){
                fst[m] = i;
                last[m] = i;
            }
            else
                last[m] = Math.max(last[m], i);
        }

        int ans = 0;
        for(int i = 0; i<7; i++){
            if(fst[i] == -1)
                continue;
            ans = Math.max(ans, last[i] - fst[i]);
        }
        System.out.print(ans);
    }
}