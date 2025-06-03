import java.util.*;
import java.io.*;

public class Main {
    private static int ans = 0;
    private static void z(int n, int r, int c){
        if(n == 0)
            return;

        // n은 단계니까 실제 다음 크기는 2의 n-1승
        int nextSize = (int)Math.pow(2, n-1);

        //어느 사분면인지 구해서 사분면의 첫 점 구하기
        if(r < nextSize && c < nextSize){
            z(n-1,r,c);
        }
        else if(r < nextSize && c >= nextSize){
            ans += (int)Math.pow(2, 2*(n-1));
            z(n-1, r, c - nextSize);
        }
        else if(r >= nextSize && c < nextSize){
            ans += (int)Math.pow(2, 2*(n-1)) * 2;
            z(n-1, r - nextSize, c);
        }
        else if(r >= nextSize && c >= nextSize){
            ans += (int)Math.pow(2, 2*(n-1)) * 3;
            z(n-1, r - nextSize, c - nextSize);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        z(N, r, c);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}