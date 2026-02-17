# [Gold I] 폴더 정리 (large) - 22861 

[문제 링크](https://www.acmicpc.net/problem/22861) 

### 성능 요약

메모리: 307336 KB, 시간: 1208 ms

### 분류

구현, 다이나믹 프로그래밍, 자료 구조, 그래프 이론, 문자열, 그래프 탐색, 트리, 시뮬레이션, 깊이 우선 탐색, 해시를 사용한 집합과 맵, 트리에서의 다이나믹 프로그래밍, 파싱

### 제출 일자

2026년 2월 17일 12:37:04

### 문제 설명

<p>이름이 <code>main</code> 폴더 안에 여러가지 파일과 폴더가 존재한다.</p>

<pre>main
 ├─ FolderA
 │    ├─ File1
 │    └─ File2
 └─ FolderB
       ├─ FolderC
       │   ├─ File4
       │   └─ File5
       ├─ File1
       └─ File3</pre>

<p>위 구조는 <code>main</code> 폴더의 하위 구조를 계층적으로 표시한 것이다. <code>FolderA</code>, <code>FolderB</code>, <code>FolderC</code>는 폴더이고 <code>File1</code>, <code>File2</code>, <code>File3</code>은 파일이다. 파일 이름이 같은 경우는 내용이 완전 동일한 파일이다.</p>

<p>한 폴더 안에 같은 이름을 가진 파일이 두 개 이상 존재할 수 없다. </p>

<p><code>main</code> 폴더의 하위 디렉토리에 같은 이름의 폴더가 두 개 이상 존재할 수 없다.</p>

<p>폴더 또는 파일을 옮겨 <code>main</code> 폴더를 정리하려고 한다.</p>

<p>예를 들어, <code>FolderB</code> 폴더 하위에 있는 두 개의 파일 <code>File1</code>, <code>File3</code>과 하나의 폴더 <code>FolderC</code>를 <code>FolderA</code> 폴더 하위에 옮기려고 한다.</p>

<p>File1 파일은 <code>FolderA</code> 폴더 안에 동일한 파일이 이미 존재하므로 덮어쓴다. <code>File3</code>은 동일한 파일이 없으므로 그대로 옮긴다. <code>FolderC</code> 폴더도 동일한 폴더가 없으므로 <code>FolderA</code> 폴더 하위에 옮긴다. <code>FolderB</code> 폴더 하위에 있는 폴더와 파일을 다 옮겼으므로 <code>FolderB</code>는 삭제한다.</p>

<p>아래는 <code>FolderB</code> 폴더를 <code>FolderA</code> 폴더 안에 옮긴 후 <code>main</code> 폴더의 하위 구조를 계층적으로 표시한 것이다.</p>

<pre>main
 └─ FolderA
      ├─ FolderC
      │ ├─ File4
      │ └─ File5
      ├─ File1
      ├─ File2
      └─ File3
</pre>

<p>이러한 과정을 통해 폴더를 정리하려고 한다. 폴더 정리 후 쿼리를 통하여 파일의 정보를 확인하려고 한다.</p>

### 입력 

 <p>첫 번째 줄에는 <code>main</code> 폴더 안에 있는 폴더의 총 개수 $N$과 파일의 총 개수 $M$이 공백으로 구분되어 주어진다.</p>

<p>두 번째 줄부터 $N + M + 1$ 번째까지 상위 폴더의 이름 $P$, 폴더 또는 파일의 이름 $F$, 폴더인지 아닌지 알려주는 $C$가 공백으로 구분되어 주어진다.</p>

<p>$C$의 값은 $F$가 폴더라면 1, 파일이라면 0으로 주어진다.</p>

<p>$N + M + 2$ 번째 줄에는 옮기는 횟수 $K$가 주어진다.</p>

<p>그 다음 줄부터 총 $K$ 줄에 걸쳐 폴더 경로 $A$와 폴더 경로 $B$가 공백으로 구분되어 주어진다.</p>

<p>옮기는 행위는 입력 순서대로 수행이 되어야 한다.</p>

<p>$A$ 하위에 있는 파일과 폴더들을 $B$ 하위에 옮기는 것이다. 이때, $A$는 $B$의 상위 폴더가 아님을 보장한다.</p>

<p>그 다음 줄에는 쿼리의 개수 $Q$가 주어진다.</p>

<p>그 다음 줄부터 $Q$개의 쿼리가 주어진다. 쿼리마다 <code>main</code>으로부터 폴더의 경로 정보가 들어온다. 예를 들어 <code>main</code> 폴더 안에 <code>FolderB</code>에 대한 쿼리가 들어온다면, <code>FolderB</code>의 경로인 <code><q>main/FolderB</q></code>로 주어진다. 반드시 폴더가 존재하는 경로로 주어짐을 보장한다.</p>

### 출력 

 <p>쿼리 순서대로 한 줄씩 폴더 하위에 있는 파일의 종류의 개수와 파일의 총 개수를 출력한다.</p>

<p>파일의 종류의 개수는 같은 파일이 여러개 있을 경우 하나로 계산한다. 파일의 총 개수는 같은 파일이 있더라도 하나로 계산하지 않는다.</p>

<p>예를 들어 이름이 <code>File1</code> 파일이 5개가 있을 경우 파일의 종류는 1 가지이고 파일의 총 개수는 5개이다.</p>

