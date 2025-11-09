import java.io.*;
import java.util.*;

public class Main {
    static int T,N,M;
    static long X,Y;
    static int[] arr;
    static boolean isValid(long a){
        return (X<=a) && (a<=Y);
    }
    static long arrToNum(int start){
        StringBuilder sb = new StringBuilder();
        for(int i = start; i<start+M; i++){
            sb.append(arr[i%N]);
        }
        return Long.parseLong(sb.toString());
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N];
            X = Long.parseLong(br.readLine().replaceAll(" ",""));
            Y = Long.parseLong(br.readLine().replaceAll(" ",""));
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;
            for(int i = 0; i<N; i++){
                if(isValid(arrToNum(i))){
                    ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}