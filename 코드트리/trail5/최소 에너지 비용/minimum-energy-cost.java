import java.util.*;
import java.io.*;

/*
현재 지점에서 얼마큼의 거리의 에너지를 채워야 최소가 될까
-> 지나온 지점들중 가장 싼 충전소에서 충전하기 
그리디 문제인듯 


*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] go = new int[N];
        int[] cost = new int[N];
        for(int i = 1; i<N; i++){
            go[i] = read();
        }

        int minCost = 1_000_001;
        long total = 0;
        for(int i = 0; i<N-1; i++){
            int cur = read();
            minCost = Math.min(minCost, cur);
            total += 1L * minCost * go[i+1];
        }
        System.out.print(total);
    }
}