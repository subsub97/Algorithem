# Greedy try1

n,k = map(int,input().split())

# 각 동전의 크기별로 정리된 배열 생성
coin_arr = [
    int(input())
    for _ in range(n)
]
cnt =0
num = -1 #파이썬 slicing을 이용하기위한 변수

while True:
    if k - coin_arr[num] >= 0:
        k -= coin_arr[num]
        cnt += 1
    else:
        num -= 1
        if k == 0:
            break
print(cnt)




