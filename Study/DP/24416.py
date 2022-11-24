

re_cnt = 0
dp_cnt = 0
def re_fibonacci(n):
    global re_cnt
    if n == 1 or n == 2:
        re_cnt += 1
        return 1
    else:
        return (re_fibonacci(n-1) + re_fibonacci(n-2))

def dp_fibonacci(n):
    global dp_cnt
    arr = [0 for _ in range(n+1)]
    arr[2] = 1
    arr[1] = 1
    for  i in range(3,n+1):
        dp_cnt += 1
        arr[i] = arr[i-1] + arr[i-2]
    return arr[n]

n = int(input())

re_fibonacci(n)
dp_fibonacci(n)
print(re_cnt,dp_cnt)
