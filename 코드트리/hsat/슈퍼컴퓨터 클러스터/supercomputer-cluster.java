/*
작은거 순서대로 정렬하고 
1 1 2 2 3 6 8 9 9 10
B가 엄청 커서 매번 순회할순없을거같고
같은거끼리 묶어서 처리 

최저 성능 mid를 B로 처리할 수 있는가?
1 ~ 10^9승
*/
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static long B;
    static int[] A;

    // 주어진 예산 B로 최저값을 X로 맞출 수 있는가?
    static boolean can(long X){
        long tot = 0;
        for(int i = 0; i<N; i++){
            if(X-(long) A[i] < 0)
                break;
            long cost = X-(long) A[i];
            cost *= cost;
            tot += cost;
            if(tot > B)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        long l = 0;
        long r = 2_000_000_000L;
        long ans = -1;
        while(l<=r){
            long mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                l = mid + 1;
            }
            else
                r = mid - 1;
        }

        System.out.print(ans);
    }
}