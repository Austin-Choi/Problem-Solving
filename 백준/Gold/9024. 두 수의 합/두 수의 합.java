/*
서로 다른수니까 정렬하고
정렬하게되면 포인터가 증가할수록 두 수의 합은 증가하는 단조성을 띄기 때문에
두수의 합을 줄여주려면 r--
늘리려면 l++
하나 찾으면 다음 조합을 찾기 위해서 l++,r--
이분탐색을 적용할 수 있음

d = abs(지금 2개 합) - (k)라하고 
d랑 최소값 비교해서 갱신하면서 
d == 최소값이면 cnt++
만약 새 최소값 나오면 cnt=1
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T,N,K;
    static int[] inputs;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            inputs = new int[N];
            for(int i = 0; i<N; i++){
                inputs[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(inputs);
            int l = 0;
            int r = N-1;
            int min = Integer.MAX_VALUE;
            int cnt = 0;
            
            while(l<r){
                int d = Math.abs(inputs[l]+inputs[r]-K);
                if(min > d) {
                    cnt = 1;
                    min = d;
                }
                else if(min == d)
                    cnt++;
                if(inputs[l]+inputs[r] > K)
                    r--;
                else if (inputs[l]+inputs[r] < K)
                    l++;
                else{
                    l++;
                    r--;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
