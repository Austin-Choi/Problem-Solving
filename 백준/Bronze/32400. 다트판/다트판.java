import java.io.*;
import java.util.StringTokenizer;

/*
20 입력되면 해당 idx 값 기록
왼쪽은 0보다 작은 특이사항 1개
오른쪽은 19보다 작은 특이사항 1개
Math.abs랑 모듈러 연산 활용
 */
public class Main {
    public static void main(String[] args) throws IOException {
        double a = 0.0;
        double b = 10.5;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double[] board = new double[20];
        int idx20 = 0;
        for(int i = 0; i < 20; i++){
            double temp = Double.parseDouble(st.nextToken());
            board[i] = temp;
            if(temp == 20)
                idx20 = i;
        }
        // 왼쪽
        int left = idx20 -1;
        if(left < 0)
            left += 20;
        // 오른쪽
        int right = idx20 + 1;
        if(right > 19)
            right -= 20;
        a = (board[left] + board[right]+20.0)/3.0;

        if(a > b){
            bw.write("Alice");
        }
        else if(a < b)
            bw.write("Bob");
        else
            bw.write("Tie");
        bw.flush();
        bw.close();
        br.close();
    }
}
