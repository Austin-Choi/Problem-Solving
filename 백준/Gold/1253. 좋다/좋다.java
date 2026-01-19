/*
걍 합 2중포문돌리고 set에 다넣고
in에 있는거 set에서 몇개나 있는지 세기?
-> 인덱스 조건때문에 걸림

투포인터로 하면 겹치는 합은 k로 고정해서 피하기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long[] in = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            long t = Long.parseLong(st.nextToken());
            in[i] = t;
        }

        int ans = 0;
        Arrays.sort(in);
        for(int k = 0; k<N; k++){
            int l = 0;
            int r = N-1;
            while(l<r){
                if(l == k) {
                    l++;
                    continue;
                }
                else if(r == k) {
                    r--;
                    continue;
                }

                long sum = in[l] + in[r];
                if(in[k] == sum) {
                    ans++;
                    break;
                }
                else if(in[k] > sum)
                    l++;
                else
                    r--;
            }
        }

        System.out.print(ans);
    }
}
