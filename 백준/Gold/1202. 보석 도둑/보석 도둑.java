import java.util.*;
import java.io.*;
class Jewel implements Comparable<Jewel>{
    int m;
    int v;

    public Jewel(int m, int v){
        this.m = m;
        this.v = v;
    }

    @Override
    public int compareTo(Jewel o){
        return this.m - o.m;
    }
}

public class Main {
    private static int N, K;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ArrayList<Jewel> js = new ArrayList<>();
        ArrayList<Integer> bs = new ArrayList<>();

        for(int n = 0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            js.add(new Jewel(m,v));
        }

        for(int k = 0; k<K; k++){
            int c = Integer.parseInt(br.readLine());
            bs.add(c);
        }

        // 보석 배낭 둘다 작은 순으로 정렬해서
        Collections.sort(js);
        Collections.sort(bs);
        long ans = 0;

        // pq 하나를 모든 가방에 대해서 돌려씀
        // 한 pq에 지금 배낭 사이즈보다 작거나 같은 보석을 다넣음
        // 그중 가치 큰거 하나 뺌
        // 이제 다음 배낭은 이전배낭보다 크므로 이전 pq에 있는 보석들 유효함
        // 반복

        int j = 0;
        PriorityQueue<Jewel> tmp = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);
        for(int i = 0; i<K; i++){

            int bag = bs.get(i);

            while(j<N && bag >= js.get(j).m){
                tmp.add(js.get(j));
                j++;
            }

            if(!tmp.isEmpty()) {
                Jewel top = tmp.poll();
                ans += top.v;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
