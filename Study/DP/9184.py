# 신나는 함수 실행 DP try1


flag = 0

while True:
    a,b,c = map(int,input().split())

    memo = [
        [
            [
                1 for _ in range(20)
            ] for _ in range(20)
        ] for _ in range(20)
    ]

    # 세 변수 모두 -1 인경우의 종료 조건을 생성
    if a == -1 and b == -1 and c == -1:
        break
    if a > 20 or b > 20 or c > 20:
        a1 = a
        b1 = b
        c1 = c
        a = 20
        b = 20
        c = 20
        flag = 1
    if a < b and b < c :
        for i in range(a):
            for j in range(b):
                for k in range(c):
                    memo[i][j][k] = memo[i][j][k-1] + memo[i][j - 1][k - 1] - memo[i][j - 1][k]
    else:
        for i in range(a):
            for j in range(b):
                for k in range(c):
                    memo[i][j][k] = memo[i-1][j][k]  + memo[i-1][j-1][k] + memo[i-1][j][k-1] - memo[i-1][j-1][k-1]
    # flag로 나눈 이유는 20보다 큰경우 a,b,c를 20으로 바꿔주었기에 입력 값을 기억해둔 변수를 출력변수로 사용하기 위함
    if flag == 0:
        print(f"w({a},{b},{c}) = {memo[a-1][b-1][c-1]}")
    else:
        print(f"w({a1},{b1},{c1}) = {memo[a - 1][b - 1][c - 1]}")
