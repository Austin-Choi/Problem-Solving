import java.io.*;
/*
* 접는 부분에서 사이에 다른게 들어갈 수도 있음.
* 근데 연결 관계가 다른 연결이랑 교차하면 안됨
* -> 짝수는 짝수끼리 교차하면 안되고 홀수는 홀수끼리 교차하면안됨
*
* */
public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int T = read();
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            boolean rst = true;
            int N = read();
            int[] A = new int[N];
            for(int i = 0; i<N; i++){
                A[i] = read();
            }

            outer:
            for(int i = 0; i<N-1; i++){
                int a = Math.min(A[i], A[i+1]);
                int b = Math.max(A[i], A[i+1]);
                for(int j = i+2; j<N-1; j+=2){
                    int c = Math.min(A[j], A[j+1]);
                    int d = Math.max(A[j], A[j+1]);
                    // 같은 방향끼리 [a,b] [c,d] 겹치는지 보기
                    // a<b, c<d
                    // a,c,b,d
                    // c,a,d,b
                    if((a<c && c<b && b<d) || (c<a && a<d && d<b)){
                        rst = false;
                        break outer;
                    }
                }
            }
            if(rst)
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }
        System.out.print(sb);
    }
}
