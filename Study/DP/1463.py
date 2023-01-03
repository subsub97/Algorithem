# DP try 3

x = int(input())

dp_arr = [
    0 for _ in range(x+1)
]
if x >= 4:
    dp_arr[0] = 0 # x가 1 인경우
    dp_arr[1] = 1 # x가 2 인경우
    dp_arr[2] = 1 # x가 3 인경우
    dp_arr[3] = 1
else: # x가 4보다 작은값인경우 인덱스 에러가 발생하는 것을 방지
    dp_arr = [0,0,1,1]
# 1일떄 1번이 아닌 0번이다...
for  i in range(4,x+1):
    dp_arr[i] = dp_arr[i-1] + 1 #-1해서 가능한 경우
    if i % 3 == 0:
        dp_arr[i] = min(dp_arr[i],dp_arr[i//3] + 1)
    if i % 2 == 0:
        dp_arr[i] = min(dp_arr[i],dp_arr[i//2] + 1)


print(dp_arr[x])