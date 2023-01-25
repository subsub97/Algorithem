# 1764 try1
import sys

n,m = map(int,sys.stdin.readline().strip().split())
# 듣도 못한 사람을 확인하기 위한 딕셔너리 생성
no_hear_dict = {}

# 듣도 보도 못한 사람을 관리할 list 생성
nerd_list = []
# 몇명이지 세울 count 변수 생성
cnt= 0

# 듣도 못한 명단 작성
for i in range(n):
    name = input()
    no_hear_dict[name] = True

# 보도 못한 사람들을 받으면서 바로 확인하자
for j in range(m):
    name = input()
    if no_hear_dict.get(name) == True:
        cnt += 1
        nerd_list.append(name)
# 사전순으로 출력을 위한 정렬
nerd_list.sort()

print(cnt)
for name in nerd_list:
    print(name)



