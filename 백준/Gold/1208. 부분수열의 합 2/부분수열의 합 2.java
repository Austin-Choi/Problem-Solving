import java.io.*;
import java.util.*;

public class Main {
    private static int N,S;

    private static List<Long> getSubsetSum(int[] arr){
        List<Long> rst = new ArrayList<>();
        int total = 1 << arr.length;

        for(int i = 0; i<total; i++){
            long sum = 0;
            for(int j = 0; j<arr.length; j++){
                if((i & (1 << j))!=0)
                    sum += arr[j];
            }
            rst.add(sum);
        }

        return rst;
    }
    private static Map<Long, Integer> getSubsetSumMap(int[] arr){
        Map<Long, Integer> map = new HashMap<>();
        int total = 1 << arr.length;

        for(int i = 0; i<total; i++){
            long sum = 0;
            for(int j = 0; j<arr.length; j++){
                if((i & (1 << j))!=0)
                    sum += arr[j];
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return map;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] lefts = new int[N/2];
        int[] rights = new int[N - (N/2)];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N/2; i++){
            lefts[i] = Integer.parseInt(st.nextToken());
        }
        for(int i= 0; i<N-(N/2); i++){
            rights[i] = Integer.parseInt(st.nextToken());
        }

        List<Long> lss = getSubsetSum(lefts);
        Map<Long, Integer> rss = getSubsetSumMap(rights);

        // map counting
        // 값의 빈도수를 빠르게 저장/조회
        // 중복 처리 용이, 정렬 불필요, 탐색 O(1), 저장 O(n)
        long ans = 0;
        for(int i = 0; i<lss.size(); i++){
            long target = S - lss.get(i);
            ans += rss.getOrDefault(target, 0);
        }
        // 공집합의 합 0은 빼야함
        if(S == 0)
            ans--;

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}