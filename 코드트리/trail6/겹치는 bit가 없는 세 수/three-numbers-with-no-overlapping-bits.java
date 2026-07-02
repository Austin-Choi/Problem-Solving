import java.util.*;
import java.io.*;

/*
n개의 수 중에 서로 다른 3개 수 골랐을 때 세수의 최대합?
-> 구간 dp???
-> 그냥 완탐?  n이 최대 500
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        long[] arr = new long[N];
        for(int i = 0; i<N; i++){
            arr[i] = read();
        }

        // 2진수에서 각 자리가 하나도 안 겹쳐야함 
        // xor하면 같으면 0이고 다르면 1임
        // 공통 비트가 없으면 a&b == 0
        long ans = 0;
        for(int i = 0; i<N-2; i++){
            for(int j = i+1; j<N-1; j++){
                for(int k = j+1; k<N; k++){
                    long a = arr[i];
                    long b = arr[j];
                    long c = arr[k];

                    if(((a&b) == 0)&&((b&c) == 0)&&((a&c) == 0)){
                        ans = Math.max(ans, arr[i] + arr[j] + arr[k]);
                    }
                }
            }
        }
        System.out.print(ans);
    }   
}