# Greedy Try1
import sys
n = int(input())

wait_time = list(map(int,sys.stdin.readline().split()))

wait_time.sort() # 오름차순으로 정렬
total_wait = 0
each_wait = 0

for time in wait_time:
    each_wait += time
    total_wait += each_wait

print(total_wait)

# 시간이 적게 걸리는 순서대로 먼저 해결하는 방식으로 업무를 보는 방식으로 해결하자