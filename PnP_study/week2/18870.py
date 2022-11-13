
n = int(input())

arr = list(map(int,input().split()))
sorted_arr = sorted(set(arr))
check_dict = {}
cnt = 0

for i in range(len(sorted_arr)):
    if i ==0:
        check_dict={sorted_arr[i]:cnt}
        cnt += 1
    else:
        check_dict[sorted_arr[i]] = cnt
        cnt +=1





for elem in arr:
    print(check_dict[elem],end=' ')

