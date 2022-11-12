n = int(input())
arr = []
sum_val = 0
check_arr = []
mcheck_arr = []
cnt_arr = []
mcnt_arr = []

max_cnt = 0
cnt = 0
m_cnt = 0

# 소수점 이하 첫째 자리 반올림
# 산술, 중앙, 최빈, 범위(최대 - 최소)
# -4000 ~ 4000 사이 수
for _ in range(n):
    arr.append(int(input()))

for _ in range(4001):
    check_arr.append(0)
    mcheck_arr.append(0)

for elem in arr:
    sum_val += elem
    if elem < 0:
        elem = elem * -1
        mcheck_arr[elem] += 1
    else:
        check_arr[elem] += 1

# 최빈값 구하기
for i in range(4001):
    if check_arr[i] > max_cnt:
        max_cnt = check_arr[i]
        cnt = check_arr[i]
        cnt_arr = []
        cnt_arr.append(i)
    if mcheck_arr[i] > max_cnt:
        max_cnt = mcheck_arr[i]
        m_cnt  = mcheck_arr[i]
        mcnt_arr = []
        mcnt_arr.append(i)
    elif check_arr[i] == max_cnt:
            cnt_arr.append(i)
    elif mcheck_arr[i] == max_cnt:
            mcnt_arr.append(i)

if len(mcnt_arr) > 1:
    mcnt_arr.reverse()
# 중복 최빈값 잡기
if m_cnt == cnt:
    if len(mcnt_arr) != 0:
        if len(mcnt_arr) < 2:
            many_num = mcnt_arr[0] * -1
        else:
            many_num = mcnt_arr[1] * -1
    else:
        if len(cnt_arr) < 2:
            many_num = cnt_arr[0]
        else:
            many_num = cnt_arr[1]
elif m_cnt > cnt:
    if len(mcnt_arr) == 1:
        many_num = mcnt_arr[0] * -1
    else:
        many_num = mcnt_arr[1] * -1
else:
    if len(cnt_arr) == 1:
        many_num = cnt_arr[0]
    else:
        many_num = cnt_arr[1]




arr.sort()
if sum_val < 0:
    a = int(sum_val/5 - 0.5)
else:
    a = int(sum_val/n + 0.5)
print(a)
print(arr[n//2])
print(many_num)
print(arr[n-1] - arr[0])




