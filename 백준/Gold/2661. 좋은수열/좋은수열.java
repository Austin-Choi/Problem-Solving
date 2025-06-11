
import java.io.*;

public class Main {
    private static int N;

    private static int[] out;
    private static StringBuilder sb;
    private static boolean found = false;

    private static boolean isBadSeq(int depth){
        boolean bad;
        // 길이 1부터 비교해야됨 부분수열이니까
        for(int len = 1; len <= depth /2; len++){
            bad = true;
            for(int i = 0; i<len; i++){
                if(out[depth -1 -i] != out[depth -1 - len - i]){
                    bad = false;
                    break;
                }
            }
            if (bad)
                return true;
        }
        return false;
    }

    private static void dfs(int depth){
        if(found || isBadSeq(depth))
            return;

        if(depth == N){
            for(int o : out){
                sb.append(o);
            }
            found = true;
            return;
        }

        for(int i = 1; i<=3; i++){
            out[depth] = i;
            dfs(depth + 1);
            if(found)
                return;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        out = new int[N];
        sb = new StringBuilder();

        out[0] = 1;
        dfs(1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
