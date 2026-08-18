import java.util.*;
import java.io.*;

/*
그냥 부분문자열 깨지는 시점 찾아도 될거같음 탐색 1번에
-> N이 20만이라 선형 탐색으로는 오래걸릴것임 

순서상에서 언제 부분문자열이 되는 시점이 최초로 깨지는지
그전에는 항상 부분문자열임 포함하고 있으니까 
-> 일단 남은 문자 갯수가 B보다 적으면 절대 될 수 없음
-> r = A길이 - B길이

*/

public class Main {
    static char[] A;
    static char[] B;
    //deleteTime
    static int[] dt;

    static boolean can(int X){
        int j= 0;
        for(int i = 0; i<A.length; i++){
            if(dt[i] > X && A[i] == B[j]){
                j++;
                if(j == B.length)
                    return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        dt = new int[A.length];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<A.length; i++){
            int pos = Integer.parseInt(st.nextToken())-1;
            dt[pos] = i+1;
        }

        if(!can(0)){
            System.out.print(0);
            return;
        }

        int l = 0;
        int r = A.length - B.length;
        int ans = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                l = mid +1;
            }
            else
                r = mid -1;
        }
        System.out.print(ans+1);
    }
}