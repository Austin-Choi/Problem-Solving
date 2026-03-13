/*
공통 접두사가 가장 긴 단어들을 구하기
입력순서가 빠른 2개
단어 같으면 안됨

String배열로 받아서 원본 만들어두고
복사본으로 정렬하면 비슷한거끼리 붙어있게되고
-> 클래스로 묶음
-> 여기서 최대 공통 접두사 찾을 수 잇음
i랑 i+1 비교함
최대공통접두사 의 길이 최대값이 갱신되면 그걸로 하고
만약 길이가 같으면 입력 순서 고려해서 비교함
 */
import java.util.*;
import java.io.*;
public class Main {
    // 해시맵쓰면 같은 단어 idx 갱신되어버림
    static String[] input;
    static int N;
    static int getLCP(String a, String b){
        int len = Math.min(a.length(), b.length());
        int rst = 0;
        while(rst<len && a.charAt(rst) == b.charAt(rst))
            rst++;
        return rst;
    }
    static String[] origin;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new String[N];
        origin = new String[N];
        for(int i = 0; i<N; i++){
            String s = br.readLine();
            input[i] = s;
            origin[i] = s;
        }
        Arrays.sort(input);
        int maxlcp = 0;
        String lcp = null;

        // lcp 길이만 구함
        for(int i = 0; i<N-1; i++){
            String a = input[i];
            String b = input[i+1];
            int cur = getLCP(a,b);
            // 공통 접두사 반드시 존재해야함
            if(cur == 0)
                continue;
            if(maxlcp < cur){
                maxlcp = cur;
            }
        }

        // lcp 길이를 만족하는 lcp 후보 p를 구함
        // origin을 쭉 돌면서 해당 접두사를 가지는 2 단어 뽑음
        // 그 단어의 idx를 가지고 bestfst, bestsnd의 값을 갱신함
        // best는 출력용
        int b1 = Integer.MAX_VALUE;
        int b2 = Integer.MAX_VALUE;
        for(int i = 0; i<N-1; i++){
            if(getLCP(input[i], input[i+1]) == maxlcp){
                String p = input[i].substring(0, maxlcp);

                int fst = -1;
                int snd = -1;

                for(int j = 0; j<N; j++){
                    if(origin[j].startsWith(p)){
                        if(fst == -1)
                            fst = j;
                        else{
                            snd = j;
                            break;
                        }
                    }
                }

                if(fst < b1 || (fst == b1 && snd < b2)){
                    b1 = fst;
                    b2 = snd;
                }
            }
        }
        System.out.println(origin[b1]);
        System.out.print(origin[b2]);
    }
}
