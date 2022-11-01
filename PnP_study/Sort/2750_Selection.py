n = int(input())

num_list = []
for _ in range(n):
    num_list.append(int(input()))


def selection_sort(arr):
    arr_size = len(arr)

    for i in range(arr_size):
        min_num = 99999
        for j in range(i, arr_size):
            if arr[j] <= min_num:
                min_num = arr[j]
                idx = j
        temp = arr[i]  # 정렬과정에서 value를 바꿔주기위한 변수
        arr[i] = arr[idx]
        arr[idx] = temp
        print(arr[i])


selection_sort(num_list)
