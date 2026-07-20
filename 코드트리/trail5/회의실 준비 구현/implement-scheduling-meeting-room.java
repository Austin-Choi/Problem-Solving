import java.util.*;
import java.io.*;

/*
끝나는 시간 오름차순으로 정렬해서 다음 시작시간이 현재 끝나는 시간보다 작은 경우를 제외하면 진행 가능
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] arr = new int[N][2];
        for(int i =0 ; i<N; i++){
            arr[i] = new int[]{read(), read()};
        }

        Arrays.sort(arr, (a,b)->{
            if(a[1] != b[1])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        int ce = arr[0][1];
        int ans = 1;
        for(int i = 1; i<N; i++){
            if(arr[i][0] < ce)
                continue;
            ce = arr[i][1];
            ans++;
        }
        System.out.print(ans);
    }
}