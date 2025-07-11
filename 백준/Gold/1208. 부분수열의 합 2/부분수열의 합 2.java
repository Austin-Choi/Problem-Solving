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

        Collections.sort(rst);
        return rst;
    }

    private static int upperBound(List<Long> arr, long target){
        int l = 0;
        // 오른쪽 경계는 배열 밖을 의미해야 마지막 값을 포함 가능함
        int r = arr.size();

        while(l<r){
            int mid = (l+r)/2;
            long cur = arr.get(mid);
            if(cur <= target){
                l = mid+1;
            }
            else
                r = mid;
        }

        return l;
    }

    private static int lowerBound(List<Long> arr, long target){
        int l = 0;
        int r = arr.size();

        while(l<r){
            int mid = (l+r)/2;
            long cur = arr.get(mid);
            if(cur < target){
                l = mid+1;
            }
            else
                r = mid;
        }

        return l;
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
        List<Long> rss = getSubsetSum(rights);

        long ans = 0;
        // 같은 합이 여러개 있을 수 있음
        // upperBound - lowerBound
        // upperBound는 값을 포함하고 그 값이 나타나는 마지막 인덱스
        // -> left 옮기는 조건이 <=
        // lowerBound는 값이 나타나는 첫번째 인덱스
        // -> left 옮기는 조건이 <
        for(int i = 0; i<lss.size(); i++){
            long target = S - lss.get(i);
            ans += upperBound(rss, target) - lowerBound(rss, target);
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
