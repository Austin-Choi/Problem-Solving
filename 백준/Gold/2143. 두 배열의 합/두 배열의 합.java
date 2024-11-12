import java.io.*;
import java.util.*;

public class Main {
    private static long T;
    private static int m;
    private static int n;
    private static long[] A;
    private static long[] B;
    private static List<Long> sumA;
    private static List<Long> sumB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Long.parseLong(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new long[m];
        sumA = new ArrayList<>();
        for(int i = 0; i<m; i++){
            A[i] = Long.parseLong(st.nextToken());
        }
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = i; j < m; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        B = new long[n];
        sumB = new ArrayList<>();
        B[0] = 0;
        for (int i = 0; i<n; i++){
            B[i] = Long.parseLong(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += B[j];
                sumB.add(sum);
            }
        }

        Collections.sort(sumA);
        Collections.sort(sumB);
        int l = 0;
        int r = sumB.size()-1;
        long answer = 0;

        while(l < sumA.size() && r >= 0){
            long a = sumA.get(l);
            long b = sumB.get(r);

            if(a+b == T){
                long cntA = 0;
                while(l < sumA.size() && sumA.get(l) == a){
                    cntA++;
                    l++;
                }
                long cntB = 0;
                while(r >= 0 && sumB.get(r) == b){
                    cntB++;
                    r--;
                }
                answer += cntA * cntB;
            }
            else if(a+b < T){
                l++;
            }
            else
                r--;
        }

        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
