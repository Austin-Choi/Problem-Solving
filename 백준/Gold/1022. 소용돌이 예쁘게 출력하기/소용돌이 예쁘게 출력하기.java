/*
시뮬레이션하면 터짐
..

오른쪽아래가 항상 1 9 25
0 1 2
(2*k+1)^2
 */
import java.util.*;
import java.io.*;
public class Main {
    static int value(int r, int c){
        // k는 레이어값
        int k = Math.max(Math.abs(r), Math.abs(c));
        int max = (2*k+1)*(2*k+1);
        int val;

        if(r==k)
            val = max - (k-c);
        else if(c==-k)
            val = max - 2*k - (k-r);
        else if(r == -k)
            val = max - 4*k - (c+k);
        else
            val = max - 6*k - (r+k);
        return val;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[4];
        for(int i = 0; i<4; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 공백계산용 최대값 구하기
        int maxVal = 0;
        for(int r=a[0]; r<=a[2]; r++){
            for(int c=a[1]; c<=a[3]; c++){
                maxVal = Math.max(maxVal, value(r,c));
            }
        }
        
        int w= String.valueOf(maxVal).length();

        StringBuilder sb = new StringBuilder();
        for(int r = a[0]; r<=a[2]; r++){
            for(int c= a[1]; c<=a[3]; c++){
                int val = value(r,c);
                sb.append(String.format("%"+w+"d", val)).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
