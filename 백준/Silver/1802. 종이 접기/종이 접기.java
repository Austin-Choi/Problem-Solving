/*
가운데꺼 보고 양쪽 합이 1인지 보기 ?
재귀적으로 양쪽 합이 1인지 보기
 */
import java.io.*;
public class Main {
    static boolean fold(char[] input, int left, int right){
        if(left >= right)
            return true;

        int mid = (left + right) /2;
        for(int i = 1; i<= mid - left; i++){
            int l = mid-i;
            int r = mid+i;
            if(input[l] == input[r])
                return false;
        }

        return fold(input, left, mid-1) && fold(input, mid+1, right);
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            char[] input = br.readLine().toCharArray();
            if(fold(input, 0, input.length))
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}