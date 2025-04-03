# [Gold III] Tree Isomorphism - 6998 

[문제 링크](https://www.acmicpc.net/problem/6998) 

### 성능 요약

메모리: 14068 KB, 시간: 100 ms

### 분류

그래프 이론, 그래프 탐색, 정렬, 트리, 트리 동형 사상

### 제출 일자

2025년 4월 3일 22:01:26

### 문제 설명

<p>Bilbo meets the love of his life, Oblib, but she seems eerily familiar and he worries that they are related. They both know their ancestry, but can’t just compare their ancestry trees because Bilbo knows his ancestor names in the male form, and Oblib knows hers in the female form, and the two forms are not related. Even if they were brother and sister, the names they know their ancestors by would all be different!</p>

<p>Bilbo and Oblib have written out their family trees. Your task is to determine if the two trees are isomorphic, i.e., if there is a one-to-one mapping between the two, ignoring the names. Assume that the trees are rooted, i.e., their roots are fixed. As an example, the two trees on the left below are not isomorphic to each other – there is no way to map the two children of a to the two children of x, since one child of a must have 2 children, but none of the children of x do.</p>

<table class="table table-bordered td-center th-center" style="width: 100%;">
	<tbody>
		<tr>
			<td><img alt="" src="https://upload.acmicpc.net/39024024-64b4-4d89-9c6e-da1377aca70e/-/preview/" style="width: 300px; height: 197px;"></td>
			<td><img alt="" src="https://upload.acmicpc.net/0f970e9e-596c-4a30-8fde-4a55f73f6d51/-/preview/" style="width: 444px; height: 192px;"></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<th>Non-isomorphic Trees</th>
			<th>Isomorphic Trees</th>
		</tr>
	</tfoot>
</table>

<p>On the other hand, the two trees on the right are isomorphic. The mapping is: a ↔ x (the roots must always be mapped to each other), b ↔ z, c ↔ y, g ↔ u, d ↔ w, e ↔ t, f ↔ v. There is one other isomorphism (that swaps the mappings for d and e).</p>

### 입력 

 <p>The first line in the test data file contains the number of test cases (< 100). For each such test case there are two lines, each describing a tree. An input line describes the tree by listing node labels encountered in a pre-order traversal, starting at the root. In other words, the trees are traversed from root to leaves and from left to right, and the label of every node along the way is output. After each set of children, there is a hash mark (’#’). All node labels and hash marks are separated by spaces. The example below shows the pre-order traversals for the two examples above.</p>

### 출력 

 <p>For each test case, you are to output “Isomorphic.” if the trees are isomorphic, or “Not isomorphic.” if not, followed by a newline.</p>

