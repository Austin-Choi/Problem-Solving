import java.util.*;
import java.io.*;

/*
8 4
4 1 9 10 5 7 4 10
9 4 10 5

B 원소별로 A에서의 위치 전처리하고
2 (0,6) (3,7) 4
B에서 하나만 뺏을때 증가수열이 되는 경우의 수 구하기?
-> 이러면 최악의 경우 O(NM)될수도

pre[i] = B[0~i]를 A에 매칭할때 B[i]가 매칭되는 가장 앞쪽 위치
suf[i] = B[i~M-1]를 A에 매칭할때 B[i]가 매칭되는 가장 뒤쪽 위치
-> 극단 위치로만으로도 삭제 가능 여부 판단 가능
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++)
            A[i] = read();
        int[] B = new int[M];
        for(int i = 0; i<M; i++)
            B[i] = read();
        
        int[] pre = new int[M];
        Arrays.fill(pre, -1);
        int j = 0;
        for(int i = 0; i<N && j<M; i++){
            if(A[i] == B[j]){
                pre[j] = i;
                j++;
            }
        }
        
        int[] suf = new int[M];
        Arrays.fill(suf, -1);
        j = M-1;
        for(int i = N-1; i>=0 && j>=0; i--){
            if(A[i] == B[j]){
                suf[j] = i;
                j--;
            }
        }

        //맨앞과 맨뒤 따로 처리해야함
        long ans = 0;
        for(int i = 0; i < M; i++){
            if(i == 0){
                if(suf[1] != -1)
                    ans++;
            }
            else if(i == M-1){
                if(pre[M-2] != -1)
                    ans++;
            }
            else{
                if(pre[i-1] != -1 && suf[i+1] != -1 && pre[i-1] < suf[i+1])
                    ans++;
            }
        }
        System.out.print(ans);
    }
}