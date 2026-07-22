import java.util.*;
import java.io.*;

/*
두 자연수의 합이 가장 큰 쌍은 오름차순 정렬해서 양쪽끝 더하기
투포인터

list에 num을 그대로 다 넣어버리면 터짐.
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] ns = new int[N][2];
        for(int i =0 ; i<N; i++){
            int cnt = read();
            int num = read();
            ns[i] = new int[]{cnt, num};
        }
        Arrays.sort(ns, (a,b)->{
            return a[1] - b[1];
        });

        int l = 0;
        int r = N-1;
        int ans = 0;
        // [0] = 갯수, [1] = 값
        while(l <= r){
            if(l == r){
                if(ns[l][0] >= 2)
                    ans = Math.max(ans, ns[l][1]*2);
                break;
            }
            // 최댓값 갱신은 한번만 하고
            ans = Math.max(ans, ns[l][1] + ns[r][1]);
            // 1대1 매칭이니까 둘중 cnt 최소값으로 빼줌
            int cnt = Math.min(ns[l][0], ns[r][0]);
            ns[l][0] -= cnt;
            ns[r][0] -= cnt;

            // 더이상 현재 pos에서 처리할거 없을때만 포인터 이동
            if(ns[l][0] == 0)
                l++;
            if(ns[r][0] == 0)
                r--;
        }
        System.out.print(ans);
    }
}