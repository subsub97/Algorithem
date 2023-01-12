#DP 포도주시식 try1

n = int(input())

g_quantitys = [
    int(input())
    for _ in range(n)
]

memo = [
    0 for _ in range(n)
]

for i in range(n):
    if i == 0:
        memo[i] = g_quantitys[0]
    elif i == 1:
        memo[i] = memo[i-1] + g_quantitys[i]
    elif i == 2:
        memo[i] = max(memo[i-1], memo[i-2]+g_quantitys[i] ,g_quantitys[i-1]+g_quantitys[i])
    else:
        memo[i] = max(memo[i-3] + g_quantitys[i-1] + g_quantitys[i] , memo[i-2] + g_quantitys[i])

print(max(memo))