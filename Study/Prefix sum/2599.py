# using Prefix Sum try 1
# 연속적인 며칠동안의 온도의 합이 가장 큰 값을 알아보자

import sys

n,m = map(int,input().split()) # 총 n일의 온도가 주어지고 최대를 구하고싶은 m일의 구간이 주어진다.
temperature_arr = list(map(int,sys.stdin.readline().split()))

sum_arr =[]
sum_num = 0

for num in temperature_arr:
    sum_num += num
    sum_arr.append(sum_num)

for i in range(n-m+1):
    if i == 0: # 첫 인덱스인 경우에는 누적합의 경우 맨앞 인덱스를 빼주지 않아아도 되기에 예외처리를 하였다.
        max_num = sum_arr[m-1] # 구간합을 구하기 위해서 배열의 맨앞 인덱스부터 비교를 하여 최대 값을 찾기 위함 m-1을 한 이유는 input값과 index값의 오차가 1 발생하기 때문
    else:
        if max_num < ( sum_arr[m-1 + i] - sum_arr[i-1] ): # m일 연속은 동적으로 정해지기 때문에 m 변수를 사용하고 for문의 i를 이용하여 하루씩 증가하면서 모든 날짜를 비교함 -1 을 하는 이유는 위 사유와 동일
            max_num = sum_arr[m-1 + i] - sum_arr[i-1]
print(max_num)