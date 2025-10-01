/*
35분
처음과 끝이 이어지는 연속합 배열 만들고 정렬
투포인터로 a 처음(작음) b 끝(큼)에 포인터 두고
a+b == t
a와 b각각 연속되는거 세서 a+=연속, b-=연속
a+b<t -> l++
a+b > t -> r--
 */
import java.io.*;
import java.util.*;
public class Main {
    static int t;
    static int an, bn;
    static int[] ai;
    static int[] bi;

    static List<Integer> make(int[] arr){
        int N = arr.length;
        int[] temp = new int[2*N];
        for(int i = 0; i<2*N; i++){
            temp[i] = arr[i%N];
        }

        int[] prefix = new int[2*N+1];
        // 아무것도 안고른거 0이니까 1부터
        for(int i = 1; i<= 2*N; i++){
            prefix[i] = prefix[i-1] + temp[i-1];
        }

        List<Integer> rst = new ArrayList<>();

        for(int i = 1; i<N; i++){
            for(int j = 0; j<N; j++){
                rst.add(prefix[i+j]-prefix[j]);
            }
        }
        //모두고르기
        rst.add(prefix[N]-prefix[0]);
        //안고르기
        rst.add(0);
        Collections.sort(rst);
        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        an = Integer.parseInt(st.nextToken());
        bn = Integer.parseInt(st.nextToken());
        ai = new int[an];
        bi = new int[bn];
        for(int i = 0; i<an; i++){
            ai[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 0; i<bn; i++){
            bi[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> sa = make(ai);
        List<Integer> sb = make(bi);

        int l = 0;
        int r = sb.size() -1;
        long ans = 0;
        while(l < sa.size() && r >= 0){
            int cur = sa.get(l) + sb.get(r);
            if(cur == t){
                int ca = 1;
                int cb = 1;
                while(l+ca < sa.size()){
                    if(sa.get(l+ca).equals(sa.get(l)))
                        ca++;
                    else
                        break;
                }
                while(r-cb >= 0){
                    if(sb.get(r-cb).equals(sb.get(r)))
                        cb++;
                    else
                        break;
                }
                ans += (long) ca * cb;
                l += ca;
                r -= cb;
            }
            else if(cur < t){
                l++;
            }
            // cur > t
            else {
                r--;
            }
        }

        System.out.println(ans);
    }
}
