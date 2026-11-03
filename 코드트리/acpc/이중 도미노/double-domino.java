/*
변경은 최대 1번이므로 완전탐색으로 푸는건 맞는데 횟수를 줄여야함
변경되면 변경된 부분 빼고 나머지 부분을 기존에 처리한 전처리 데이터를 재사용하면 되는데 
N의 제곱근만큼의 batch 크기를 잡아서 전처리를 하면 가능함


suffixScore[i] = i부터 시작했고 끝까지 갔을때 발생하는 점수
suffixForce[i] = i블록에서 종료했을때의 누적 F
prefixMax[i] = 처음부터 i까지의 이전 도미노들의 높이 합을 뺀 최댓값
-> 이후 이걸로 최초 점수 발생 위치 이분탐색 가능 prefix는 단조증가하므로 정렬상태
blockSum[i] = 처음부터 i까지에서 점수가 발생하지 않았다면 더해야할 F

*/
import java.util.*;
import java.io.*;

public class Main {

    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    // B: 제곱근 전처리 batch 크기
    static int N, B;
    // 원본 도미노 입력 배열 D: 코디, rD: 친구
    static int[] D, rD;

    static class BlockData{
        // suffixscore
        int[] ss;
        // suffixforce
        long[] sf;
        // prefixmax
        long[] pm;
        // blocksum
        long[] bs;
    
        BlockData(int n, int blockCount){
            ss = new int[n];
            sf = new long[n];
            pm = new long[n];
            bs = new long[blockCount];
        }
    }

    static BlockData preProcess(int[] A){
        int blockCount = (N+B-1) / B;
        BlockData bd = new BlockData(N, blockCount);

        for(int b = 0; b<blockCount; b++){
            // 현재 블럭 시작점
            int L = b*B;
            // 현재 블럭 끝점
            int R = Math.min(N-1, L-1+B);

            // 처음부터 i까지 점수가 발생하지 않았다면 더해야할 F
            for(int i = L; i<=R; i++){
                bd.bs[b] += A[i];
            }

            // 처음부터 i까지의 이전 도미노들의 높이 합을 뺀 최댓값
            long sum = 0;
            long max = Long.MIN_VALUE;
            for(int i = L; i<=R; i++){
                long val = (long) A[i] - sum;
                max = Math.max(max, val);
                bd.pm[i] = max;
                sum += A[i];
            }

            // suffixScore i부터 시작했고 끝까지 갔을때 발생하는 점수,
            // suffixForce i블록에서 종료했을때의 누적 F
            for(int i = L; i<=R; i++){
                long F = A[i];
                int score = 0;

                for(int j = i+1; j<=R; j++){
                    // 다음 도미노가 누적된 힘보다 낮아야 넘어뜨릴수 있음.
                    if(A[j] < F){
                        F += A[j];
                    }
                    else{
                        score++;
                        F = A[j];
                    }
                }

                bd.ss[i] = score;
                bd.sf[i] = F;
            }
        }

        return bd;
    }

    // !! : 이미 처리한 [0]고려해서 누적합 처리해야함

    static long calc(int[] A, BlockData bd, int i, int j){
        long F = 0;
        long score = 0;

        int blockCount = (N+B-1)/B;

        for(int b = 0; b<blockCount; b++){
            int L = b*B;
            int R = Math.min(N-1, L+B-1);

            // 해당 블록에 교환된 도미노가 있는지 확인하기
            boolean hasChanged = (L<=i && i<=R) || (L<=j && j<=R);
            
            // 첫번째 블록 또는 교환된 블록 처리
            if(b == 0 || hasChanged){
                for(int k = L; k<=R; k++){
                    if(k == 0){
                        F = A[k];
                        continue;
                    }

                    if(A[k] < F)
                        F += A[k];
                    else{
                        score++;
                        F = A[k];
                    }
                }
            }
            // 점수 발생 없이 블록 전체 통과
            else if(bd.pm[R] < F){
                F += bd.bs[b];
            }
            // 블록 내부에서 점수 발생
            else{
                // prefixMax[k] >= F 를 만족하는 최초 위치 이분탐색
                int left = L;
                int right = R;
                while(left < right){
                    int mid = (left+right)/2;
                    if(bd.pm[mid] >= F)
                        right = mid;
                    else 
                        left = mid+1;
                }
                int k = left;
                // k에서 멈추면서 1점 획득하고 k부터 다시 시작한 이후의 점수 추가
                score += 1 + bd.ss[k];
                // 블록 끝에서의 누적 힘
                F = bd.sf[k];
            }
        }
        return score;
    }

    static int[] swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        return A;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        B = (int) Math.sqrt((double) N);
        D = new int[N];
        rD = new int[N];
        
        for(int i = 0; i<N; i++){
            int cur = read();
            D[i] = cur;
            rD[N-1-i] = cur;
        }

        BlockData forward = preProcess(D);
        BlockData backward = preProcess(rD);

        long ans = calc(D, forward, -1, -1) + calc(rD, backward, -1, -1);

        for(int i = 0; i<N; i++){
            for(int j = i+1; j<N; j++){
                // 같은 높이면 교환 의미가 없음.
                if(D[i] == D[j])
                    continue;

                D = swap(D, i, j);
                rD = swap(rD, N-1-i, N-1-j);

                ans = Math.max(ans, calc(D, forward, i, j) + calc(rD, backward, N-1-i, N-1-j));

                D = swap(D, i, j);
                rD = swap(rD, N-1-i, N-1-j);
            }
        }
        System.out.print(ans);
    }
}