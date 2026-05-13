import java.util.*;
import java.io.*;

/*
근데 조건이 2가지라서 단순히 전체 - 안되는것 음..
조건이 이번에는 and라서 직접 계산하는게 나을듯

어.. 근데 원형이면 계산할것도 없이 min(N,5)임 차가 2니까
가능한거 리스트로 구해서 set에 다 넣고 set보기?
*/

public class Main {
    static int N;
    static int M = 3;
    // 자리마다 가능한 숫자 pool list배열
    static Set<Integer>[][] pool = new Set[2][3];
    static Set<String> ss = new HashSet<>();

    // 3자리 set에 넣을때 파싱하는거
    static String parser(int a, int b, int c){
        return a + "," + b + "," + c;
    }
    static int[] A,B;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i =0; i<2; i++){
            for(int j = 0; j<3; j++){
                pool[i][j] = new HashSet<>();
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<3; i++){
            int cur = Integer.parseInt(st.nextToken());
            for(int d=-2; d<=2; d++){
                int nc = cur+d;
                nc = ((nc -1 + N) % N)+1;
                pool[0][i].add(nc);
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<3; i++){
            int cur = Integer.parseInt(st.nextToken());
            for(int d=-2; d<=2; d++){
                int nc = cur+d;
                nc = ((nc -1 + N) % N)+1;
                pool[1][i].add(nc);
            }
        }

        for(int i = 0; i<2; i++){
            for(int a : pool[i][0]){
                for(int b : pool[i][1]){
                    for(int c : pool[i][2]){
                        ss.add(parser(a,b,c));
                    }
                }
            }
        }

        System.out.print(ss.size());
    }
}