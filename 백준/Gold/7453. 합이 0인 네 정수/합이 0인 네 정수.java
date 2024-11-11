import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static long[] A;
    private static long[] B;
    private static long[] C;
    private static long[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new long[n];
        B = new long[n];
        C = new long[n];
        D = new long[n];
        for(int i = 0; i<n; i++){
            String[] temp = br.readLine().split(" ");
            A[i] = Long.parseLong(temp[0]);
            B[i] = Long.parseLong(temp[1]);
            C[i] = Long.parseLong(temp[2]);
            D[i] = Long.parseLong(temp[3]);
        }

        long answer = 0L;
        // A+B 모든 경우의 수 구함
        long[] sumAB = new long[n*n];
        long[] sumCD = new long[n*n];
        int idx = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                sumAB[idx] = A[i]+B[j];
                sumCD[idx++] = C[i]+D[j];
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);
        // 투 포인터로 각 집합에서 0이 되는거 세주기
        int l = 0;
        int r = n*n-1;

        while(l < sumAB.length && r > -1){
            long a = sumAB[l];
            long b = sumCD[r];

            if(a + b == 0){
                // 일단 a,b 한개씩 있으므로 1개부터 시작
                long cntA = 1;
                long cntB = 1;
                // sumAB, sumCD는 중복된 값도 있음 (1+3, 2+2는 값은 달라도 합 결과는 같음)
                // 그래서 각자 중복된만큼 갯수 세주고 포인터 이동시키고
                // 두개 곱해서 answer에 더해줌
                while(l<sumAB.length-1 && sumAB[l+1] == a){
                    l++;
                    cntA++;
                }
                while (r > 0 && sumCD[r-1] == b){
                    r--;
                    cntB++;
                }

                answer += cntA * cntB;
                l++;
                r--;
            }
            else if(a + b < 0){
                l++;
            }
            else
                r--;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
