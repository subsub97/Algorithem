#DP try1

n = int(input())

ans_arr = []

for i in range(n):
    x = int(input())
    if x <= 3:  # 초기값을 이용해야하는 수 보다 작은경우
        x = 3


    memo = [
        0 for _ in range(x + 1)
    ]

    memo[0] = 1 ; memo[1]=1;memo[2]=1;memo[3]=2;
    for j in range(4,x+1):
        memo[j] = memo[j-2] + memo[j-3]

    ans_arr.append(memo[x-1])
for num in ans_arr:
    print(num)