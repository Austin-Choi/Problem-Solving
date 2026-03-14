/*
pre 맨앞이 루트
in 그 루트값 기준 왼쪽이 왼쪽 sub, 오른쪽이 오른쪽 sub
build에서 파라미터는 각 순회결과 lr값 총 4개
여기서는 postorder니까 sb.append를 나중에 둠
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[] pre;
    static int[] inorder;
    static HashMap<Integer, Integer> inmap;
    static int N;
    static StringBuilder sb;
    static void build(int pl, int pr, int il, int ir){
        if(pl > pr || il > ir)
            return;

        int root = pre[pl];
        // inorder에서의 idx
        int rootIdx = inmap.get(root);
        // 왼쪽 서브트리의 크기
        int left = rootIdx - il;

        build(pl+1, pl+left, il, rootIdx-1);
        build(pl+left+1, pr, rootIdx+1, ir);
        sb.append(root).append(" ");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        // in에서 분할된 크기만큼의 맨앞이 루트고
        // in에서 분할 값으로 인덱스는 맵으로 찾기
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            pre = new int[N];
            inorder = new int[N];
            inmap = new HashMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++)
                pre[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                int a =  Integer.parseInt(st.nextToken());
                inorder[i] = a;
                inmap.put(a, i);
            }

            sb = new StringBuilder();
            build(0,N-1,0,N-1);
            System.out.println(sb);
        }
    }
}