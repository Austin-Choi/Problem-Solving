/*
N-1번째에 최단경로가 갱신되는 그래프 만들기
벨만 포드는 입력 순서대로 N-1번 돌아서 갱신함
 */
public class Main {
    static int N,M;
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        N = 50;
        M = 49;
        sb.append(N).append(" ").append(M).append("\n");
        for(int m = M; m>=1; m--)
            sb.append(m).append(" ").append(m+1).append(" ").append(-1).append("\n");
        System.out.print(sb);
    }
}
