n = int(input())
arr = []
sum_val = 0
check_arr = []
mcheck_arr = []
many_arr = []
a_cnt = 0
b_cnt = 0
cnt = 1



max_cnt = 0
cnt = 0
m_cnt = 0

# 소수점 이하 첫째 자리 반올림
# 산술, 중앙, 최빈, 범위(최대 - 최소)
# -4000 ~ 4000 사이 수
for _ in range(n):
    arr.append(int(input()))

for _ in range(4001):
    mcheck_arr.append(0)
    check_arr.append(0)

for elem in arr:
    sum_val += elem
    if elem < 0:
        elem = elem * -1
        mcheck_arr[elem] += 1
    else:
        check_arr[elem] += 1

a_cnt = max(check_arr)
b_cnt = max(mcheck_arr)

if a_cnt == b_cnt:
    max_cnt = a_cnt
else:
    max_cnt = max(a_cnt,b_cnt)

arr.sort()

for i in range(n-1):
    if arr[i] == arr[i+1]:
        cnt += 1

    else:
        cnt = 1

    if max_cnt == cnt:
        many_arr.append(arr[i])

if len(arr) == 1:
    b = arr[0]

elif len(many_arr) < 2:
    b = many_arr[0]
else:
    b = many_arr[1]


if sum_val < 0:
    a = int(sum_val/5 - 0.5)
else:
    a = int(sum_val/n + 0.5)
print(a)
print(arr[n//2])
print(b)
print(arr[n-1] - arr[0])





