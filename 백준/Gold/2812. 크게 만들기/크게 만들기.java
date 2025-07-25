import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int k = 0;
        Stack<Integer> s = new Stack<>();
        int i = 0;
        for(;i<N; i++){
            int cur = br.read()-'0';
            if(s.isEmpty())
                s.add(cur);
            else{
                while(!s.isEmpty() && cur > s.peek() && k<K) {
                    s.pop();
                    k++;
                }
                s.add(cur);
            }
        }

        // 만약 입력 내림차순이면 부족한 k개만큼 pop해야함
        // 4 3 2 1 -> 1 2 3 4이니까
        while(k<K){
            s.pop();
            k++;
        }

        StringBuilder sb = new StringBuilder();
        while(!s.isEmpty()){
            sb.append(s.pop());
        }
        sb.reverse();

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
