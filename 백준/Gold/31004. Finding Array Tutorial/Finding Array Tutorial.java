/*
전체 종류 1,N으로 저장해놓고
1,1 1,2 ... 1,N
N,N N-1,N ... 1,N-1
종류 다른거 추가되면 1개 늘어남
-> 왼쪽에도 없던 종류고 오른쪽에도 없던 종류면 i번째는 고유값임
-> li-1 + 1 = li && ri+1 + 1 = ri
 */
import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int query(int l, int r) throws IOException{
        bw.write("? "+l+" "+r+"\n");
        bw.flush();
        return Integer.parseInt(br.readLine());
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int tot = query(1,N);

        int[] left = new int[N+1];
        //i+1이라서 N+2
        int[] right = new int[N+2];

        for(int i = 1; i<=N; i++) 
            left[i] = query(1, i);

        // 1,i=N이랑 i=1,N 겹치니까 빼줌
        right[1] = tot;
        for (int i = N; i>=2; i--) 
            right[i] = query(i, N);

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 1; i<=N; i++){
            if(left[i] == left[i-1]+1 && right[i] == right[i+1]+1)
                ans.add(i);
        }

        StringBuilder sb = new StringBuilder("! ");
        sb.append(ans.size());
        for(int n : ans)
            sb.append(" ").append(n);
        sb.append("\n");
        bw.write(sb.toString());
        bw.flush();
    }
}