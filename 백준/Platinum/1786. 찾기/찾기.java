import java.io.*;
import java.util.*;

public class Main {
    private static int[] setPrefixTable(String pattern){
        int m = pattern.length();
        int[] pi = new int[m];
        int prefixEnd = 0;

        for(int i = 1; i<m; i++){
            while(prefixEnd > 0 && pattern.charAt(i) != pattern.charAt(prefixEnd)){
                prefixEnd = pi[prefixEnd - 1];
            }

            // 문자가 같으면 prefixEnd를 증가시키고 pi[i]에 저장
            if(pattern.charAt(i) == pattern.charAt(prefixEnd)){
                prefixEnd++;
                pi[i] = prefixEnd;
            }
        }

        return pi;
    }

    private static List<Integer> kmp(String text, String pattern, int[] pi){
        List<Integer> rst = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        //패턴 위치
        int pos = 0;

        for(int i = 0; i<n; i++){
            //불일치 시 pos를 이전 일치 길이로 되돌림
            while(pos > 0 && text.charAt(i) != pattern.charAt(pos)){
                pos = pi[pos - 1];
            }

            // 문자가 일치하면 pos 증가
            if(text.charAt(i) == pattern.charAt(pos)){
                if(pos == m - 1){
                    // 패턴 찾음 인덱스 저장
                    // 1부터 시작하는 인덱스 형태로 저장
                    rst.add(i-m+2);
                    // 다음 매칭을 위해 다음 접두사 위치로 스킵
                    pos = pi[pos];
                }
                else {
                    pos++;
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String T = br.readLine();
        String P = br.readLine();

        int[] pi = setPrefixTable(P);
        List<Integer> results = kmp(T,P,pi);

        sb.append(results.size()).append("\n");
        for(int result : results){
            sb.append(result).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
