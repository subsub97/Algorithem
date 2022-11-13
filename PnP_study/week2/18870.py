
n = int(input())

arr = list(map(int,input().split()))
sorted_arr = sorted(arr)
check_dict = {}
cnt = 0

for i in range(n-1):
    if sorted_arr[i] == sorted_arr[i+1]:
        pass
    else:
        if len(check_dict) == 0:
            check_dict = {sorted_arr[i]:cnt}
            cnt += 1
        else:
            check_dict[sorted_arr[i]] = cnt
            cnt += 1
    if i == n-2:
        if sorted_arr[i] == sorted_arr[i+1]:
            check_dict[sorted_arr[i]] = cnt
        else:
            check_dict[sorted_arr[i]] = cnt
            check_dict[sorted_arr[i+1]] = cnt + 1



for elem in arr:
    print(check_dict[elem],end=' ')

