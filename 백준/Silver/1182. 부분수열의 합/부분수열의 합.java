import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static long S;
    public static long count = 0L;

    public static int[] l = null;

    public static void dfs(int depth, long sum){
        if(depth == N) {
            if (sum == S) {
                count++;
            }
            return;
        }

        // tree 의 전위순환 형태
        //다음 숫자 고르기
        dfs(depth+1, l[depth]+sum);
        //다음 숫자 안고르기
        dfs(depth+1, sum);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Long.parseLong(st.nextToken());
        l = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            l[i] = Integer.parseInt(st.nextToken());
        }
        //dfs에 안 고르기 항목이 있어서 공집합도 나오는데
        //이 경우 안 고르면 S가 0일때 만족하므로 S 가 0일때는 공집합의 경우를 고려해
        //count -1을 해줘야함
        dfs(0,0);

        if(S == 0)
            System.out.println(count - 1);
        else
            System.out.println(count);
    }
}
