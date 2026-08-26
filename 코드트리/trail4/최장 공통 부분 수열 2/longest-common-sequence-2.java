import java.util.*;
import java.io.*;

// 역추적 LCS 써야해서 dp방식 써야함

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int N = A.length;
        int M = B.length;
        int[][] dp = new int[N+1][M+1];
        dp[0][0] = 0;

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                // 같으면 lcs 하나 추가
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                // 다르면 두 방향중 큰쪽을 유지
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        ArrayList<Character> li = new ArrayList<>();
        int i = N;
        int j = M;
        while(i >0 && j>0){
            // 뒤에서부터 보면서 같으면 둘다 줄임
            if(A[i-1] == B[j-1]){
                li.add(A[i-1]);
                i--;
                j--;
            }
            else{
                // A의 마지막 문자를 제외한 경우가 더 길면 
                // A를 줄이기
                if(dp[i-1][j] > dp[i][j-1])
                    i--;
                // B 쪽이 길면 줄여서 맞추기
                else
                    j--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int ii = li.size()-1; ii>=0; ii--){
            sb.append(li.get(ii));
        }
        System.out.print(sb);
    }
}