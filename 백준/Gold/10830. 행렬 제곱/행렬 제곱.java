
import java.util.*;
import java.io.*;

public class Main {

    private static int[][] matrix;
    private static int N;
    private static long B;

    public static int[][] multiply(int[][] m1, int [][] m2){
        int[][] result = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int sum = 0;
                for(int k = 0; k<N; k++){
                    sum += (m1[i][k] * m2[k][j]) % 1000;
                }
                result[i][j] = sum % 1000;
            }
        }
        return result;
    }

    public static int[][] modulo(int[][] m1){
        int[][] result = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                result[i][j] = m1[i][j] % 1000;
            }
        }
        return result;
    }

    public static int[][] pow(int[][] m1, long b){
        if(b == 1)
            return modulo(m1);

        int[][] half = pow(m1, b/2);
        int[][] result = multiply(half, half);

        if(b%2 == 1)
            result = multiply(result, m1);

        return result;
    }

    public static StringBuilder print(int[][] m1){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(m1[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] ans = pow(matrix, B);
        bw.write(print(ans).toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
