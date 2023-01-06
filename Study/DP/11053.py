# 가장 긴 증가하는 부분 수열 DP try2
'''
try1 틀렸습니다.
try2 current_num을 바꾸는 조건을 바꾸었다.
     이전에는 j가 증가할떄 마다 바꾸었는데 이러한 경우에
     중복된 값을 count하는 경우가 발생한다.
'''
import sys
n = int(input())

num_arr = list(map(int,sys.stdin.readline().split()))

# 가장 긴 증가하는 수열을 찾아보자

memo = [
    0 for _ in range(n)
]

max_cnt = 0

for i in range(n):
    current_num = num_arr[i]
    for j in range(i+1,n):
         if current_num < num_arr[j]:
             memo[i] += 1
             current_num = num_arr[j]
    if max_cnt < memo[i]:
        max_cnt = memo[i]

print(max_cnt + 1)
