# Quick_Sort를 구현해보자
# 램덤모듈 import
import random
import sys

'''
2단계 리스트(배열) A를 선택된 pivot을 기준으로 분할한다.
pivot보다 작거나 같은 수는 pivot의 왼쪽으,ㅡ
pivot보다 큰 수는 pivot의 오른쪽으로 이동하여 재 배열한다.
'''

'''
p_p = pivotpoint
피벗포인트는 left 보다 하나 작은 위치에서 시작한다.
A[i]가 설정된 Pivot보다 크다면 i를 1증가한다.
A[i]가 설정된 pibot보다 작거나 같으면 p_p를 1증가하고 A[p_p]와 a[i]를 교환 후 i 1증가
모든 원소들을 pivot과 비교 한 후 , pivotpoint를 1증가하고 A[p_p]와 A[right]랑교환
'''


def partition(A, left, right):
    # ramdom으로 피벗설정
    pivot_idx = random.randrange(left, right + 1)

    pivot = A[pivot_idx]
    # 비교를 위한 pivotpoint 생성
    pivotpoint = left - 1
    # 분할하기위한 정렬시작
    A[pivot_idx] = A[right]
    A[right] = pivot

    for i in range(left, right):
        if A[i] > pivot:
            continue
        else:
            pivotpoint += 1
            temp = A[pivotpoint]
            A[pivotpoint] = A[i]
            A[i] = temp
    pivotpoint += 1
    temp = A[pivotpoint]
    A[pivotpoint] = A[right]
    A[right] = temp
    return pivotpoint


def quick_sort(A, left, right):
    if left < right:
        pivotpoint = partition(A, left, right)
        quick_sort(A, left, pivotpoint - 1)
        quick_sort(A, pivotpoint + 1, right)

        return A
    else:
        return
    # pivotpoint는 항상 left보다 작아야한다.
    pivotpoint = left - 1
    # pivot 설정해주기 0이상 리스트A의 길이미만의 난수 생성
    pivot = right - 1
    # swap을 위한 변수 생성
    temp = 0


n = int(input())
A = list(map(int, input().split()))
m = int(input())
B = list(map(int, input().split()))

quick_sort(A, 0, n - 1)
quick_sort(B, 0, m - 1)

left = 0
right = 0

min_gap = (sys.maxsize)

while True:
    if left > n -1 or right > m-1:
        break

    if abs(A[left] - B[right]) < min_gap:
        min_gap = abs(A[left] - B[right])
        if A[left] >= B[right]:
            right += 1
        else:
            left += 1
    else:
        if A[left] >= B[right]:
            right += 1
        else:
            left += 1
print(min_gap)