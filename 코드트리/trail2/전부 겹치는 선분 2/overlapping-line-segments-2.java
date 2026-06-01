import java.util.*;
import java.io.*;

/*
l,r 갱신하는 문제긴한데 초견에는 매번 하나 골라서 스킵하고 똑같이 갱신하면
N^2 나올거 같은데 음.

prefix suffix 패턴 사용하면
미리 i까지의 l값, r값 구해두고 
O(1)에 하나 빼고 계산한 l,r 값 구하기

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] A;

    public static void main(String[] args) throws IOException{
        N = read();
        A = new int[N][2];
        for(int i = 0; i<N; i++){
            A[i] = new int[]{read(), read()};
        }

        // pml = [0, i] 까지 봤을때 maxL
        // sml = [i, N-1] 까지 봤을때 maxl
        int[] pMaxL = new int[N];
        pMaxL[0] = A[0][0];
        for(int i = 1; i<N; i++){
            pMaxL[i] = Math.max(pMaxL[i-1], A[i][0]);
        }

        int[] pMinR = new int[N];
        pMinR[0] = A[0][1];
        for(int i = 1; i<N; i++){
            pMinR[i] = Math.min(pMinR[i-1], A[i][1]);
        }

        int[] sMaxL = new int[N];
        sMaxL[N-1] = A[N-1][0];
        for(int i = N-2; i>=0; i--){
            sMaxL[i] = Math.max(sMaxL[i+1], A[i][0]);
        }

        int[] sMinR = new int[N];
        sMinR[N-1] = A[N-1][1];
        for(int i = N-2; i>=0; i--){
            sMinR[i] = Math.min(sMinR[i+1], A[i][1]);
        }

        int l = -1;
        int r = 101;
        boolean found = false;
        // 제외할 선분 idx = i 
        for(int i= 0; i<N; i++){
            if(i == 0){
                l = sMaxL[1];
                r = sMinR[1];
            }
            else if(i == N-1){
                l = pMaxL[N-2];
                r = pMinR[N-2];
            }
            else{
                l = Math.max(pMaxL[i-1], sMaxL[i+1]);
                r = Math.min(pMinR[i-1], sMinR[i+1]);
            }

            if(l<=r){
                found = true;
                break;
            }
        }

        if(found)
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}