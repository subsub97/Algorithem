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
    b = temp_b
    flag = 0
    for i in range(n):
        for j in range(m):
            high = area[i][j]
            if high > height:
                # 높이가 높은 지층을 height과 같게 만들면서 inven을 채운다.
                # 먼저 이 작업을 하는 이유는 높은 것을 먼저 height로 맞춘다면 낮은 지층을 위로 쌓을때
                # inven에서 부족함이 발생할 경우 주어진 조건에서 height으로 평탄화 작업을 할 수 없음을 증명하기 위함
                while high != height:
                    high -= 1 #높은층 평탄화 작업
                    b += 1 # inven 증가
                    elapsed_time += 2
                    flag = 1


    for i in range(n):
        for j in range(m):
            high = area[i][j]
            if high < height:
                # 평탄화를 원하는 기준 height보다 낮은 층이라면 height까지 맞추준다.
                while high < height:
                    if b > 0: #inventory에 지층을 쌓기위한 층이 존재하면
                        high += 1
                        elapsed_time += 1
                        flag = 1
                    else:
                        break

    if flag ==1 and elapsed_time < min_elapse_time:
        min_elapse_time = elapsed_time
        height_list = [height]

    elif elapsed_time == min_elapse_time:
        height_list.append(height)
    else:
        break
    height += 1

print(min_elapse_time,max(height_list))
