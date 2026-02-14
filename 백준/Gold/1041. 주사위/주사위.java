/*
n^3 큰거 하나 만들려면
꼭지점 8개는 인접한 3면의 합이 최소가 되는거 -> 8 * 최소합

변 12 * (N-2)개는 인접한 2면의 합이 최소가 되는거 -> 12 * N-2 * 최소합

6*(N-2)*(N-2)개는 주사위 숫자 중 제일 작은거 -> 6*N-2*N-2 * 6개중 최소
다 합하고

저 조건에서 각 3면,2면,1면에서 제일 숫자
3면짜리에서 젤 큰거 * 4
2면짜리에서 젤 큰거 * 4 * N-2
1면짜리 * N-2 * N-2

3면에서 가장 큰 쪽을 한 면으로 몰아넣고 그 면을 안모이게하면 5면의 합이 최소가 될 듯
48 + 12*3 + 6*1 - (12 + 2*4*1 + 1*1*1)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] d = new int[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m1 = Integer.MAX_VALUE;
        for(int i =0; i<6; i++){
            d[i] = Integer.parseInt(st.nextToken());
            m1 = Math.min(m1, d[i]);
        }
        ArrayList<int[]> p3 = new ArrayList<>();
        //AED, ABD, ABC, ACE, FBD, FDE, FEC, FBC
        p3.add(new int[]{d[0], d[4], d[3]});
        p3.add(new int[]{d[0], d[1], d[3]});
        p3.add(new int[]{d[0], d[1], d[2]});
        p3.add(new int[]{d[0], d[2], d[4]});
        p3.add(new int[]{d[5], d[1], d[3]});
        p3.add(new int[]{d[5], d[3], d[4]});
        p3.add(new int[]{d[5], d[4], d[2]});
        p3.add(new int[]{d[5], d[2], d[1]});

        ArrayList<int[]> p2 = new ArrayList<>();
        // AE, AB, AC, AD, BD, DE, CE, CB, FB, FD, FE, FC
        p2.add(new int[]{d[0], d[4]});
        p2.add(new int[]{d[0], d[1]});
        p2.add(new int[]{d[0], d[2]});
        p2.add(new int[]{d[0], d[3]});

        p2.add(new int[]{d[1], d[3]});
        p2.add(new int[]{d[3], d[4]});
        p2.add(new int[]{d[4], d[2]});
        p2.add(new int[]{d[2], d[1]});

        p2.add(new int[]{d[5], d[1]});
        p2.add(new int[]{d[5], d[3]});
        p2.add(new int[]{d[5], d[4]});
        p2.add(new int[]{d[5], d[2]});

        int[] m3 = new int[3];
        int[] m2 = new int[2];
        int mm3 = Integer.MAX_VALUE;
        int mm2 = Integer.MAX_VALUE;
        for(int[] m : p3){
            int sum = 0;
            for(int i : m){
                sum += i;
            }
            if(mm3 > sum){
                m3 = m;
                mm3 = sum;
            }
        }
        for(int [] m : p2){
            int sum = 0;
            for(int i : m){
                sum+= i;
            }
            if(mm2 > sum){
                m2 = m;
                mm2 = sum;
            }
        }
        long ans;
        if(N == 1){
            ans = 0;
            int max = 0;
            for(int i : d){
                ans += i;
                max = Math.max(max, i);
            }
            ans -= max;
        }
        else{
            ans = (8L*mm3) + (12L*(N-2)*mm2) + (5L*(N-2)*(N-2)*m1);
            int mmm3 = 0;
            int mmm2 = 0;
            for(int i : m3)
                mmm3 = Math.max(mmm3, i);
            for(int i : m2)
                mmm2 = Math.max(mmm2, i);
            ans -= (4L*mmm3) + (4L*(N-2)*mmm2);
        }
        System.out.print(ans);
    }
}
