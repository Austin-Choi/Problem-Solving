import java.io.*;
/*
세그먼트 트리 문제의 본질
어떤 식으로 세그먼트 트리를 구성할건지?
어떤 식으로 쿼리를 날릴건지?

1. 사탕의 맛은 1~1000000까지 연속적이며 맛이 i인 사탕이 몇개 있는지를 저장하는 배열을 arr[i]라 함
세그먼트 트리의 한 노드의 구간 (a,b)는 맛이 a부터 b까지에 포함되는 사탕의 갯수를 의미함.

2-1. 세그먼트 트리의 완전 이진 트리 구조에서 L노드의 값이 k보다 크다면, L을 구성하는 사탕들 사이에 k번째로 맛있는 사탕이 존재한다는 의미.
2-2. L노드의 값을 tree[L]이라 했을 때, R에서 찾아야 하는 것은 k-tree[L]번째로 맛있는 사탕을 찾아야 함


 */

class SegmentTree {
    private long[] tree;
    private long[] arr;
    private int n = 1000000; // 맛 등급의 범위

    // 생성자 : 세그먼트 트리 초기화
    public SegmentTree() {
        this.tree = new long[4 * n]; // 세그먼트 트리 배열 초기화
        this.arr = new long[n+1]; // 맛 등급별 사탕 개수 저장 배열
    }

    // 특정 맛 등급의 사탕 개수를 업데이트하는 메서드
    public void update(int index, long value) {
        // 변화량을 계산하여 트리에 반영
        arr[index] += value;
        update(1, 1, n, index, value);
    }

    private void update(int node, int start, int end, int index, long diff) {
        if (index < start || index > end) return; // 범위 밖이면 무시
        tree[node] += diff; // 변화량 반영
        if (start != end) { // 리프 노드가 아닌 경우 자식 노드로 재귀적으로 진행
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index, diff);
            update(node * 2 + 1, mid + 1, end, index, diff);
        }
    }

    // k번째로 맛있는 사탕의 맛 등급을 찾는 메서드
    public int query(int k) {
        return query(1, 1, n, k);
    }

    private int query(int node, int start, int end, int k) {
        if (start == end) {
            return start; // 맛 등급 반환
        }
        int mid = (start + end) / 2;

        // 구간합을 이용한 이분탐색
        if (tree[node * 2] >= k) {
            // L노드에 k번째 사탕이 있는 경우, 왼쪽 자식 노드로 이동
            return query(node * 2, start, mid, k);
        } else {
            // 오른쪽 자식 노드로 이동, k에서 왼쪽 노드의 값을 뺌
            return query(node * 2 + 1, mid + 1, end, k - (int)tree[node * 2]);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        SegmentTree tree = new SegmentTree();
        // A : 1
        // B : 꺼낼 사탕 순위

        // A : 2
        // B : 넣을 사탕 맛 정도
        // C : 넣을 사탕 갯수
        for(int i = 0; i<n; i++){
            String[] temp = br.readLine().split("\\s");
            if(temp.length == 2){
                int rank = Integer.parseInt(temp[1]);
                int taste = tree.query(rank);
                bw.write(taste+"\n");
                // 사탕을 하나 빼고 나면 해당 등급의 사탕 갯수에서 1개를 빼줘야 함
                tree.update(taste, -1);
            }
            else {
                int rank = Integer.parseInt(temp[1]);
                long quantity = Long.parseLong(temp[2]);
                tree.update(rank, quantity);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
