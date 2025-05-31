import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
map 갯수 쓰고 앞에 이름 상관없이 종류로 카운팅
조합 갯수 구하는 공식
각 옷 종류마다 독립적으로 일어나므로
곱셈 원리를 적용함
(각자 하나씩 입는 경우 + 이 종류를 안 입는 경우(상수 1로 고정))
을 옷 종류마다 곱해서
여기서는 아무것도 선택 안하는 건 안된다 함(알몸안돼)
그래서 마지막에 -1
 */
public class Main{
    private static int T;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());
        Map<String, Integer> m;
        for(int i = 0; i<T; i++){
            m = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            for(int j = 0; j<n; j++){
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String thing = st.nextToken();
                m.put(thing, m.getOrDefault(thing, 0)+1);
            }

            int ans = 1;
            for(Map.Entry<String, Integer> e : m.entrySet()){
                ans *= e.getValue()+1;
            }
            ans -= 1;
            sb.append(ans)
                    .append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
