import java.util.*;
import java.io.*;

/*
hsp로 구성되는데 주어지는 B가 내는 것을 어떻게 가공해야하지

i번째까지 제일 많이 등장한 종류의 갯수

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static char rc() throws IOException{
        sst.nextToken();
        return (char) sst.sval.charAt(0);
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] pp = new int[N+1];
        int[] ph = new int[N+1];
        int[] ps = new int[N+1];

        int[] sp = new int[N+1];
        int[] sh = new int[N+1];
        int[] ss = new int[N+1];

        // 입력
        char[] A = new char[N];
        for(int i = 0; i<N; i++){
            A[i] = rc();
        }

        //prefix 만들기 : 이전값은 무조건 복사하고 경우에따라 증가시키기
        for(int i = 1; i<=N; i++){
            char cur = A[i-1];

            pp[i] = pp[i-1];
            ph[i] = ph[i-1];
            ps[i] = ps[i-1];

            if(cur == 'P'){
                pp[i]++;
            }
            else if(cur == 'H'){
                ph[i]++;
            }
            else{
                ps[i]++;
            }
        }

        for(int i = N-1; i>=0; i--){
            char cur = A[i];

            sp[i] = sp[i+1];
            sh[i] = sh[i+1];
            ss[i] = ss[i+1];

            if(cur == 'P') 
                sp[i]++;
            else if(cur == 'H') 
                sh[i]++;
            else 
                ss[i]++;
        }

        int ans = 0;
        for(int i = 0; i<=N; i++){
            ans = Math.max(ans, (Math.max(pp[i], Math.max(ph[i], ps[i])) + Math.max(sp[i], Math.max(sh[i], ss[i]))));
        }
        System.out.print(ans);
    }
}