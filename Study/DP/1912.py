#연속합 DP try5
'''
try 2 try1에서는 2차원 배열을 사용했지만 이번에는 1차원 배열로 해결해보기
try 3 try2에서는 채울수있는 경우에는 모든 값을 num_arr에 추가 했지만
      이전 num_arr[j-1]값이 음수라면 최대가 될 수 없기에 음수가 나온다면
      바로 다음 값을 계산하도록 수정해보았다.
try 4 input()을 sys.stdin.readline()으로 바꿔보았다.
'''
import sys


n = int(input())

#문제에서 주어진 최저 값이 -1000 이기에 max를 그보다 작은 수로 설정한다면
# max값을 찾을때 놓치는 일이 없을 것이다.
max = -1001

# tuple로 한 이유는 입력받은 값이 변동될 일이 없기에 tuple로 했다.
#(솔직히 상관없음)
num_arr = tuple(map(int,sys.stdin.readline().split()))

# 값을 기억 저장할 DP 리스트 생성
memo = [
    0 for _ in range(n)
]

for i in range(n):
    for j in range(i,n):
        if j == i:
            memo[j] = num_arr[j]
            if max < memo[i]:
                max = memo[i]
        else:

            memo[j] = memo[j-1] + num_arr[j]
            if memo[j] < 0:
                break
            if max < memo[j]:
                max = memo[j]

print(max)


