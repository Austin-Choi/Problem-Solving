import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        int len1 = arr1.length;
        int len2 = arr2.length;

        int[][] dp = new int[len2+1][len1+1];

        for(int i = 1; i<=len2; i++){
            for(int j = 1; j<=len1; j++){
                if(arr2[i-1] == arr1[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        int answer = dp[len2][len1];
        bw.write(answer+"\n");
        // 손으로 LCS 표를 그려보면 사선으로 진행되고 arr1 == arr2 문자가 같을때 stack에 넣고 len1--, len2--
        // 문자열을 찾을 때 만약 dp[i][j] == dp[i-1][j] (위쪽으로 같으면 세로줄은 입력 2이므로 len2--)
        // dp[i][j] == dp[i][j-1] (왼쪽과 같으면 가로줄은 입력 1이므로 len1--)
        Stack<Character> s = new Stack<>();
        while(len1 > 0 && len2 > 0){
            if(arr1[len1-1] == arr2[len2-1]){
                s.add(arr1[len1-1]);
                len1--;
                len2--;
            }
            else if(dp[len2-1][len1] == dp[len2][len1])
                len2--;
            else if(dp[len2][len1-1] == dp[len2][len1])
                len1--;
        }
        StringBuilder sb = new StringBuilder();
        while(!s.isEmpty()){
            sb.append(s.pop());
        }
        bw.write(sb+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
