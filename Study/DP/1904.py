# DP try2

n = int(input())

dp_arr=[1,2,3]
cnt = 0
if n > 3:
    for i in range(3,n+1):
        dp_arr[cnt] = (dp_arr[(i-1)%3]+ (dp_arr[(i-2)%3])) % 15746

        cnt += 1
        if cnt == 3:
            cnt = 0
    print(dp_arr[cnt - 2] )
else:
    print(dp_arr[n-1])

# 예외처리를 해주지 않아서 오류가 발생했다. n이 3보다 작은경우 error가 발생했다.
