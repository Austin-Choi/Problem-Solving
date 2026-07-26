import java.util.*;
import java.io.*;

/*
뒤에서부터 연속으로 다른 값 길이 보고
현재가 다르면 4로 바꿔주기로 하면 O(4N)
차분을 사용하면 O(N)

*/

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int ans = 0;
        // 뒤에서부터 하면 영향 안주니까
        for(int i = N-1; i>=0; i--){
            char cur = A[i];

            if(cur != B[i]){
                for(int j = i; j >= Math.max(0, i-3); j--){
                    if(A[j] == B[j])
                        break;
                    if(A[j] == 'G')
                        A[j] = 'H';
                    else
                        A[j] = 'G';
                }
                ans++;
            }
        }
        System.out.print(ans);
    }
}