#입력
n, k = map(int, input().split())

A = [0 for _ in range(n)]
for i in range(n):
    A[i] = list(map(int, input().split()))


def findK(A, k):
	start = 0
	end = n*n-1
	while (start <= end):
		mid = (start + end) // 2
		row = mid // n
		col = mid % n
		num = A[row][col]

		if num == k:
			return [row, col]
		elif num > k:
			end = mid - 1
		elif num < k:
			start = mid + 1
	return [-1, -1]


result = findK(A, k)
print(f"({result[0]}, {result[1]})")

