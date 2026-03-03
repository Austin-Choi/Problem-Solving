import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
100개 미만의 쿼리
영화를 볼때마다 쌓여진 순서 전부 변동
세그트리?
M번 위로 이동하므로 M+N 공간

Bit Indexed Tree(펜윅 트리)
-> prefix sum + 한칸 값 변경
-> tree[i] = i번 위치에서 끝나는 (i & -i) 크기의 구간합
-> -i = i 2진수로 나타내고 비트반전 후 +1한 것
 */
public class Main {
    static int[] tree;
    static int[] pos;
    static int size;
    static void update(int i, int diff){
        while(i<=size){
            tree[i] += diff;
            // 현재 값을 포함하는 더 큰 구간들로 이동
            i += (i & -i);
        }
    }

    static int sum(int i){
        int s = 0;
        while(i>0){
            s += tree[i];
            // 이미 더한 구간 제거, 그 이전 구간으로 이동
            i -= (i& -i);
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] q = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<m; i++){
                q[i] = Integer.parseInt(st.nextToken());
            }
            size = n + m;
            tree = new int[size+1];
            pos = new int[n+1];

            int top = m;
            //init
            //영화는 1부터 n까지
            for(int i = 1; i<=n; i++){
                pos[i] = m+i;
                update(pos[i], 1);
            }

            StringBuilder sb = new StringBuilder();
            for(int qq : q){
                // 위의 dvd 갯수세기
                sb.append(sum(pos[qq]-1)).append(" ");
                // dvd봄
                update(pos[qq], -1);
                // 지금 대상은 봣으니까 제일 위로감
                pos[qq] = top;
                // 제일 위에 놧으니까 1칸씩 미루기
                update(pos[qq],1);
                // 새로 올라온 영화가 기존 영화보다 위에 위치하게끔 조정
                top--;
            }
            System.out.print(sb);
        }
    }
}
