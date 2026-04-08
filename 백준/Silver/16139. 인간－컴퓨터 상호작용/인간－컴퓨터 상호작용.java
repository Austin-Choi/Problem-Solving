import java.util.*;
import java.io.*;
/*
int[26][2000]
각 알파벳별로 빈도수 누적합으로 구해놓고
쿼리 들어오면 알파벳 고르고 거기 안에서 구간 보고 출력하기
 */

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        char[] in = br.readLine().toCharArray();
        int N = in.length;

        // 누적합 배열 쓸때는 off-by-one 문제 날 수 있어서
        // N+1로 만드는게 더 좋음.
        int[][] prefixSum = new int[N+1][26];

        for(int i = 1; i<=N; i++){
            for(int c = 0; c<26; c++){
                prefixSum[i][c] = prefixSum[i-1][c];
            }
            // char로 인덱스 접근할 수 있으니까 
            // 여기서 바로 더함
            prefixSum[i][in[i-1]-'a']++;
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int cIdx = c - 'a';
            sb.append(prefixSum[r+1][cIdx] - prefixSum[l][cIdx]).append("\n");
        }
        System.out.print(sb);
    }
}