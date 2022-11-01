m = int(input())

# 오름차순으로 정렬된 수를 받는 리스트 생성
m_arr = list(map(int, input().split()))

n = int(input())

n_arr = list(map(int, input().split()))

k = int(input())



def binarySearch(A,item,left,right):
    if left <= right:
        mid = (left + right)//2

        if item == A[mid]:
            return mid
        elif item < A[mid]:
            return binarySearch(A,item, left, mid-1)
        else:
            return binarySearch(A,item, mid + 1, right)
    else:
        a = A[right]
        return a , right
# 가장 가까운 수를 확인할 변수 check_num 생성
check_num = 100001

for i in range(m):
		if m_arr[i] <= k:
			# 이진탐색을 이용하여 k와 근접한 합 찾기
			num,right = binarySearch(n_arr,k - m_arr[i],0,n-1)
			if k - (num + m_arr[i]) < check_num:
					check_num = k - (num + m_arr[i])
					x = m_arr[i]
					y = n_arr[right]
					gap = abs(x-y)
			elif k - (num + m_arr[i]) == check_num:
					if gap > abs(m_arr[i] - num):
							x = m_arr[i]
							y = n_arr[right]
							gap = abs(x - y)
					elif gap == abs(m_arr[i] - num):
							if x < m_arr[i]:
									x = m_arr[i]
									y = n_arr[right]
print(x,y)



