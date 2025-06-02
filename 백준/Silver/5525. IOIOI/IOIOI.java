
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        String input = br.readLine();

        int sum = 0;
        int rank = 0;
        int i = 1;

        while(i < M-1){
            if(input.charAt(i) == 'O'
                && input.charAt(i-1) == 'I'
                && input.charAt(i+1) == 'I'){
                rank++;
                if(rank == N){
                    sum++;
                    // IOIOI 에서 IOI가 2번 카운팅될수 있게 줄임
                    rank--;
                }
                // 다음 패턴 탐색
                i += 2;
            }
            // 패턴에 일치하지 않을 경우 
            else {
                rank = 0;
                i++;
            }
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}
