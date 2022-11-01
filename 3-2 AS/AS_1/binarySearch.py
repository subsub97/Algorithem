# 재귀적 이진탐색 코드
def r_binarySearch(A, item, left, right, mid):
    if left <= right:
        mid = (left + right) // 2
        if item == A[mid]:
            return mid
        elif item < A[mid]:
            return r_binarySearch(A, item, left, mid - 1, mid)
        else:
            return r_binarySearch(A, item, mid + 1, right, mid)
    else:
        tmpmid = mid
        return mid


A = [1, 3, 5, 7, 9]
print(r_binarySearch(A, 2, 0, 4, 2))

# 비재귀적 이진탐색 코드
# tmpmid 는 만약 찾는 값이 없다면 근처 가장 가까운 값을 찾기 위함
def binarySearch(item,A):
    tmpmid = 0
    start = 0
    end = len(A) - 1
    while start <= end:
        mid = (start + end) // 2
        if A[mid] == item:
            tmpmid = mid
            return mid
        elif A[mid] < item:
            start = mid + 1
            tmpmid = mid
        else:
            end = mid - 1
            tmpmid = mid
    return tmpmid

print(binarySearch(5,A))

