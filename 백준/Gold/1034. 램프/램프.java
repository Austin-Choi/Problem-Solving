/*
각 행에 대해 0의 갯수 계산
K - 갯수 % 2 == 0, 갯수 <= K
-> 후보
map<String, Integer> => 패턴, 패턴빈도수
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> m = new HashMap<>();
        String[] board = new String[N];

        for(int i = 0; i<N; i++){
            board[i] = br.readLine();
        }

        int K = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            char[] tmp = board[i].toCharArray();
            int cnt = 0;
            for(int j = 0; j<M; j++){
                if(tmp[j] == '0')
                    cnt++;
            }
            if(cnt <= K && (K-cnt)%2 == 0){
                m.put(board[i], m.getOrDefault(board[i], 0)+1);
            }
        }

        int ans = 0;
        for(Map.Entry<String, Integer> e : m.entrySet()){
            ans = Math.max(ans, e.getValue());
        }

        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
