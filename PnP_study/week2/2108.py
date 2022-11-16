n = int(input())
arr = []
sum_val = 0
check_arr = []
mcheck_arr = []
many_arr = {}
a_cnt = 0
b_cnt = 0
cnt = 1
max_cnt = 1


# 소수점 이하 첫째 자리 반올림
# 산술, 중앙, 최빈, 범위(최대 - 최소)
# -4000 ~ 4000 사이 수
for _ in range(n):
    num = int(input())
    arr.append(num)
    sum_val += num


arr.sort()
for j in range(n+1):
    many_arr[j] = []


for i in range(1,n):
    if arr[i] == arr[i-1]:
        cnt += 1

    else:
        offset_num = int(arr[i-1] + 4000)
        many_arr[cnt].append(offset_num)
        if max_cnt < cnt: max_cnt = cnt
        cnt = 1
    if i == n-1:
        offset_num = int(arr[i-1] + 4000)
        many_arr[cnt].append(offset_num)
        if max_cnt < cnt: max_cnt = cnt


if len(arr) == 1:
    b = arr[0]

elif len(many_arr[max_cnt]) < 2:
    b = many_arr[max_cnt][0] - 4000
else:
    b = many_arr[max_cnt][1] - 4000


if sum_val < 0:
    a = int(sum_val/n - 0.5)
else:
    a = int(sum_val/n + 0.5)
print(a)
print(arr[n//2])
print(b)
print(arr[n-1] - arr[0])


