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

        double[][] arr = new double[N][2];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            double w = Double.parseDouble(st.nextToken());
            double v = Double.parseDouble(st.nextToken());
            arr[i] = new double[]{w,v};
        }

        Arrays.sort(arr, (a,b)->
            Double.compare(b[1]/b[0] , a[1]/a[0])
        );

        double cur = 0;
        double val = 0;
        int idx = 0;
        while(idx < N && cur < M){
            if(cur + arr[idx][0] <= M){
                cur += arr[idx][0];
                val += arr[idx][1];
            }
            else{
                val += (M-cur)*(arr[idx][1] / arr[idx][0]);
                cur = M;
            }
            idx++;
        }
        System.out.printf("%.03f", val);
    }
}