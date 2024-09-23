import java.io.*;
import java.util.StringTokenizer;

// 차수가 1이라는데
// 서브트리 1인거 찾으면 다르게나옴
// 리프노드 + 루트노드인가봄
// 루트노드에서 한개씩 쭉쭉쭉 가서 마지막에 m-1개의 리프노드 생성하기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n-m; i++){
            sb.append(i).append(" ").append(i+1).append("\n");
        }
        int temp = n-m;
        for(int i = temp+1; i<n; i++){
            sb.append(temp).append(" ").append(i).append("\n");
        }
        bw.write(sb+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
