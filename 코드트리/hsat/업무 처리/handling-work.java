/*
H <= 10, K <= 10, R <= 1000, 업무 번호 <= 100,000
long[][] works = new long[2^H][K]로 나타낼수 있고 
-> R이 1000으로 작아서 시뮬할 수 있으니까 queue 2차원 배열로 구성
좌측 말단 직원은 2^H - 2*H + 0 ~ + 2*H-1 임
홀수 번째 날짜에는 왼쪽 직원, 짝수에는 오른쪽 직원 -> 왼 오 위니까 후위순회 

*/
import java.util.*;
import java.io.*;

public class Main {
    static int H,K,R;
    // 직원들의 일 저장, 완전이진트리이므로 배열로 표현
    // 부서장 제외 트리 형태
    Queue<Integer>[][] works;

    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException {
        H = read();
        K = read();
        R = read();

        // 0 왼, 1 오
        int size = 1<<(H+1);
        Queue<Integer>[][] works = new ArrayDeque[size][2];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<2; j++){
                works[i][j] = new ArrayDeque<>();
            }
        }

        int leaf = 1<<H;
        // 말단 직원은 왼 오 없잖아 어떻게 처리하지
        // -> 일단 왼에 넣고 매번 0에서만 처리하기
        for(int i = leaf; i<size; i++){
            for(int k = 0; k<K; k++){
                works[i][0].add(read());
            }
        }

        // 각 하루마다 시뮬레이션
        long ans = 0;
        for(int r = 0; r<R; r++){
            int curSide = r % 2;

            // 모든 노드에 대해 처리
            for(int i = 1; i<size; i++){
                // 만약 말단 노드이면 처리할 쪽은 무조건 0
                int workSide = (i>=leaf) ? 0 : curSide;

                if(works[i][workSide].isEmpty())
                    continue;
                
                int curWork = works[i][workSide].poll();
                
                if(i == 1){
                    ans += curWork;
                }
                else{
                    // 부모한테 올리고 i%2쪽 자식의 일감 받기
                    works[i/2][i%2].add(curWork);
                }
            }
        }
        System.out.print(ans);
    }
}