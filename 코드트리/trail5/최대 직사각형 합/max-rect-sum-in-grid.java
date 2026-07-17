import java.util.*;
import java.io.*;

/*
그냥 2차원 ps 쓰면 터짐 N 4제곱이라
kadane 쓰기
맨위, 맨아래 열 고정하고 중간 kadane (최대 부분합 배열)
-> 이중 for문이 맨위 맨아래 열 고정 (세로 크기)
-> kadane (연속된 가로 크기)

kadane 
1차원 배열에서 
ans = 연속된 최대 합
cur = 연속된 현재 합
i = [0, N-1]
cur = Math.max(arr[i], cur + arr[i])로 현재에서 시작할지 전의 값에서 연속시킬지 
ans = Math.max(ans, cur)
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int kadane(int[] arr){
        int ans = -1000 * 300 * 300 -1;
        int cur = 0;
        for(int i = 0; i<arr.length; i++){
            cur = Math.max(arr[i], cur + arr[i]);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] arr = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                arr[i][j] = read();
            }
        }

        int ans = -1000 * 300 * 300 -1;
        int[] temp = new int[N];
        for(int t = 0; t<N; t++){
            Arrays.fill(temp, 0);

            for(int b = t; b<N; b++){
                for(int i = 0; i<N; i++){
                    temp[i] += arr[b][i];
                }    

                ans = Math.max(ans, kadane(temp));
            }
        }
        System.out.print(ans);
    }
}