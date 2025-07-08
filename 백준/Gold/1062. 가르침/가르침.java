import java.util.*;
import java.io.*;
public class Main {
    private static int N,K;
    private static int[] words;
    private static int base;
    private static int ans = 0;
    // 가능한 알파벳 마스크 (조합) dfs로 뽑기
    private static void dfs(int idx, int cnt, int mask){
        if(cnt == K-5){
            int readable = 0;
            for(int word : words){
                if((word & mask) == word)
                    readable++;
            }
            ans = Math.max(ans, readable);
            return;
        }
        
        for(int i = idx; i<26; i++){
            // a,n,t,i,c가 아니면
            if((base & (1<<i)) == 0){
                // mask 조합에 해당 알파벳을 추가함
                dfs(i+1, cnt+1, mask | (1<<i));
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K < 5){
            bw.write(0+"");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        // words에 각 단어의 알파벳 비트로 입력
        words = new int[N];
        for(int i = 0; i<N; i++){
            String word = br.readLine();
            int mask = 0;
            for(char c : word.toCharArray()){
                mask |= (1 << (c-'a'));
            }
            words[i] = mask;
        }
        
        // 기본 접두사 접미사에 있는 알파벳은 알아야 함
        base = 0;
        for(char c : "antic".toCharArray()){
            base |= (1 << (c-'a'));
        }
        
        dfs(0,0,base);
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
