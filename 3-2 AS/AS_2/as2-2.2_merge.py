def merge_sort(arr):
    if len(arr) < 2:
        return arr

    mid = len(arr) // 2
    low_arr = merge_sort(arr[:mid])
    high_arr = merge_sort(arr[mid:])

    merged_arr = []
    l = h = 0
    while l < len(low_arr) and h < len(high_arr):
        if low_arr[l] < high_arr[h]:
            merged_arr.append(low_arr[l])
            l += 1
        else:
            merged_arr.append(high_arr[h])
            h += 1
    merged_arr += low_arr[l:]
    merged_arr += high_arr[h:]
    return merged_arr


n = int(input())

first_list = list(map(int, input().split()))
#딕셔너리로 나중에 index불러오기 위함
f_dict={}
idx_cnt = 1
for elem in first_list:
    f_dict[idx_cnt] = elem
    idx_cnt += 1

A = merge_sort(first_list)


k = int(input())
max_cnt = 0
check_listA = []

for i in range(n):
    cnt = 1
    check_listB = []
    for j in range(i + 1, n):
        if (A[j] - A[i]) <= k:
            cnt += 1
            if len(check_listB) == 0:
                check_listB.append(A[i])
            check_listB.append(A[j])
        else:
            break
    if cnt >= max_cnt:
        max_cnt = cnt
        check_listA = check_listB

final_list =[]

for key,value in f_dict.items():
    if value ==50:
        final_list.append(int(key))

final_list.sort()
for i in final_list:
    print(i)