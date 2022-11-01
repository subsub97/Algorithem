# 버블정렬을 구현해보자
# 수를 정렬하기위해서 여러 정렬들이 존재하지만 가장 효율이 안좋은 정렬이라고한다.
'''
O(n^2) 으로 Big-O 표기법으로는 같지만
선택정렬의 경우 바깥쪽 for문의 loop이 끝나고
배열에서 교환이 발생하지만 버블정렬의 경우엔
내부 for문의 j가 증가할때마다 변경작업이
발생하기 때문에 더 비효율적이다.
'''

def bubble_sort(list):
    arr_size = len(list) # for문을 몇번 수행할지를 결정하기위한 변수
    for i in range(arr_size):
        for j in range(arr_size-i-1):
            if list[j] > list[j+1]:
                temp = list[j]
                list[j] = list[j+1]
                list[j+1] = temp

    for idx in range(arr_size):
        print(list[idx])

n = int(input())
A = []
for _ in range(n):
    A.append(int(input()))

bubble_sort(A)