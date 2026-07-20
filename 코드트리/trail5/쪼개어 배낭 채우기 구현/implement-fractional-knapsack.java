import java.util.*;
import java.io.*;

/*
가격/무게 내림차순으로 담기 
무게 1당 가격
*/

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{w,v};
        }

        Arrays.sort(arr, (a,b)->
            Double.compare((double)b[1]/b[0] , (double)a[1]/a[0])
        );

        int cw = 0;
        double val = 0;
        int idx = 0;
        while(idx < N && cw < M){
            if(cw + arr[idx][0] <= M){
                cw += arr[idx][0];
                val += arr[idx][1];
            }
            else{
                val += (M-cw)*((double)arr[idx][1] / arr[idx][0]);
                cw = M;
            }
            idx++;
        }
        System.out.printf("%.03f", val);
    }
}