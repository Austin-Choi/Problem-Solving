import java.io.*;

public class Main {
    static int MOD = 1000000007;
    static long[][] power(long[][] A, long exp){
        if (exp == 1)
            return A;

        long[][] half = power(A, exp/2);
        long[][] result = multiply(half, half);

        if(exp % 2 == 1)
            result = multiply(result, A);

        return result;
    }

    static long[][] multiply(long[][] A, long[][] B){
        long[][] rst = new long[A.length][B[0].length];

        for(int i = 0; i<rst.length; i++){
            for(int j = 0; j<rst[0].length; j++){
                for(int k = 0; k<A[0].length; k++){
                    rst[i][j] = (rst[i][j] + A[i][k]*B[k][j]) % MOD;
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long N = Long.parseLong(br.readLine());

        // 정보과학관 1 전산2 미래3 신양4 진리 5 한경직 6 학생 7 형남 8
        long[][] mat = new long[8][8];
        String s = "121323243435454656576878";
        char[] tmp = s.toCharArray();
        for(int i = 0; i<tmp.length; i+=2){
            int st = tmp[i]-'0';
            int ed = tmp[i+1]-'0';
            mat[st-1][ed-1] = 1;
            mat[ed-1][st-1] = 1;
        }

        long[][] rst = power(mat, N);

        long ans = rst[0][0];
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
