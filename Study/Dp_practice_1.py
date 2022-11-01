# Dp 다이나믹 프로그래밍을 이용한 피보나치 수열을 구해보자

# 메모이제이션을 위한 리스트 생성
memo = [0 for i in range(100)]

def dp(x):
    if x == 1: return 1;
    if x == 2 : return 1;
    if memo[x] != 0: return memo[x];
    memo[x] = dp(x-1) + dp(x-2);
    return memo[x]

print(dp(5))
