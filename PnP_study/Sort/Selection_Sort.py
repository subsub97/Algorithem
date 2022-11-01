# 선택정렬(Selection_Sort)
# list 내부의 가장 작은 수를 찾아서 순서대로 정렬함
# list 내부의 가장 작은 수는 0과 같거나 크다고 가정
# O(n*n)의 수행시간을 가진다.


def selection_sort(arr):
    arr_size = len(arr)

    for i in range(arr_size):
        min_num = 99999
        for j in range(i,arr_size):
            if arr[j] <= min_num:
                min_num = arr[j]
                idx = j
        temp = arr[i] #정렬과정에서 value를 바꿔주기위한 변수
        arr[i] = arr[idx]
        arr[idx] = temp

    return arr
A = [1,10,2,5,7,9,4,3,6,8]
print(selection_sort(A))