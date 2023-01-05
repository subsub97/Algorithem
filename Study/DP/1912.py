#연속합 DP try2
'''
try 2 try1에서는 2차원 배열을 사용했지만 이번에는 1차원 배열로 해결해보기
'''
n = int(input())

#문제에서 주어진 최저 값이 -1000 이기에 max를 그보다 작은 수로 설정한다면
# max값을 찾을때 놓치는 일이 없을 것이다.
max = -1001

# tuple로 한 이유는 입력받은 값이 변동될 일이 없기에 tuple로 했다.
#(솔직히 상관없음)
num_arr = tuple(map(int,input().split()))

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
            if max < memo[j]:
                max = memo[j]
print(max)


