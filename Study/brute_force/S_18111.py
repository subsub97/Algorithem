# Brute_force 18111 마인크래프트 try2
# 땅고르기 작업이 필요
# 땅 고르기 작업에 걸리는 최소 시간과 이경우의 땅의 높이를 구하라
# try 1은 모든 경우의 수를 다 보면서 앞으로 더 확인 하지 않아도 되는부분도 탐색 했지만 try2 에서는 그 부분은 스킵하는 코드로 수정
# line 56에 code 추가
import sys,time

n,m,b = map(int,input().split())
# 256까지 다 쌓으면서 찾아보기
temp_b = b
area = [
    list(map(int,sys.stdin.readline().split()))
    for _ in range(n)
]

height = 0

min_elapse_time = 100000000

while height <= 256:
    elapsed_time = 0
    add_block = 0
    use_block = 0
    flag = 0
    for i in range(n):
        for j in range(m):
            high = area[i][j]
            if high > height:
                add_block += high - height
                flag = 1
            else:
                use_block += height - high
    if flag == 0 and use_block > b:
        break
    if use_block > add_block + b:
        height += 1
        continue
    else:
        elapsed_time = add_block * 2 + use_block
        if elapsed_time <= min_elapse_time:
            min_elapse_time = elapsed_time
            max_height = height
    height += 1

print(min_elapse_time,max_height)
