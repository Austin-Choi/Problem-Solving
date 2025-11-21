/*
대소문자구분
패턴 W 길이 g
원문 S, 길이 SL

패턴을 재조합해서 나오는 모든 형태들중에
원문에 부분 문자열로 나오는 경우의 수 세기

구성만 같으면 되니까
어떤 문자가 몇개로 이루어져있는지 패턴꺼 미리 계산해놓고
S g크기 구간(단어니까) 슬라이딩 윈도로 구성 같은지 보면서 세기

 */
import java.util.*;
import java.io.*;
public class Main {
    static int g;
    static int sl;
    static char[] W;
    static char[] S;

    static int[] freqW;

    static int[] setWordFreq(char[] w){
        int[] freq = new int[52];
        //65, 97
        for(int i = 0; i<g; i++){
            if(Character.isLowerCase(w[i]))
                freq[w[i]-'a'+26]++;
            else if(Character.isUpperCase(w[i]))
                freq[w[i]-'A']++;
        }
        return freq;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        g = Integer.parseInt(st.nextToken());
        sl = Integer.parseInt(st.nextToken());
        W = br.readLine().toCharArray();
        S = br.readLine().toCharArray();

        freqW = setWordFreq(W);
        int[] freqS = setWordFreq(S);

        int matches = 0;
        for(int i =0; i<52; i++){
            if(freqW[i] == freqS[i])
                matches++;
        }

        int ans = 0;
        if(matches == 52)
            ans++;

        for(int i = 1; i<= sl-g; i++){
            int out;
            if(Character.isLowerCase(S[i-1]))
                out = S[i-1]-'a'+26;
            else
                out = S[i-1]-'A';

            int in;
            if(Character.isLowerCase(S[i+g-1]))
                in = S[i+g-1]-'a'+26;
            else
                in = S[i+g-1]-'A';

            // 전까진 맞았는데 이제 매칭 깨질 예정임
            if(freqS[out] == freqW[out])
                matches--;
            //업뎃함
            freqS[out]--;
            // 업뎃하고 빈도수 같으면 매칭 맞는 갯수 1추가
            if(freqS[out] == freqW[out])
                matches++;

            if(freqS[in] == freqW[in])
                matches--;
            freqS[in]++;
            if(freqS[in] == freqW[in])
                matches++;

            if(matches == 52)
                ans++;
        }
        System.out.println(ans);
    }
}
