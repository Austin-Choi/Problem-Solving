import java.util.*;
import java.io.*;

public class Main {
    private static int N, C;
    private static List<Long> getSubsetSums(int[] li){
        List<Long> l = new ArrayList<>();
        int total = 1 << li.length;

        // i -> 어떤 원소를 선택할지 표현하는 정수
        for(int i =0; i<total; i++){
            long sum = 0;

            for(int j = 0; j<li.length; j++){
                if((i & (1 << j)) != 0){
                    sum += li[j];
                }
            }

            l.add(sum);
        }

        Collections.sort(l);
        return l;
    }

    private static int upperBound(List<Long> li, long t){
        int l = 0;
        int r = li.size();
        while(l < r){
            int m = (l+r)/2;
            if(li.get(m) <= t){
                l = m + 1;
            }
            else
                r = m;
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int half = N/2;
        int[] lefts = new int[half];
        int[] rights = new int[N-(half)];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<half; i++){
            lefts[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i<N-half; i++){
            rights[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> leftSS = getSubsetSums(lefts);
        List<Long> rightSS = getSubsetSums(rights);

        long ans = 0;
        for(int i = 0; i<leftSS.size(); i++){
            long target = C-leftSS.get(i);
            ans += upperBound(rightSS, target);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
