# 신나는 함수 실행 DP try6
# 한번 구하면 다시 구해진수 이전은 다시 구하지 않는 방법으로 수정하자
memo = [
    [
        [
            0 for _ in range(21)
        ] for _ in range(21)
    ] for _ in range(21)
]
# 1 이 아닌 0으로 변경
def w(a,b,c):
    if a <= 0 or b <= 0 or c <= 0:
        return 1
    if a > 20 or b > 20 or c > 20:
        return w(20,20,20)
    if memo[a][b][c]:
        return memo[a][b][c]
    if a < b and b < c:
        memo[a][b][c] = w(a,b,c-1) + w(a,b-1,c-1) - w(a,b-1,c)
        return memo[a][b][c]
    memo[a][b][c] = w(a-1,b,c) + w(a-1,b-1,c) + w(a-1,b,c-1) - w(a-1,b-1,c-1)
    return memo[a][b][c]


while True:
    a,b,c = map(int,input().split())
    # 세 변수 모두 -1 인경우의 종료 조건을 생성
    if a == -1 and b == -1 and c == -1:
        break

    ans = w(a,b,c)

    print(f"w({a}, {b}, {c}) = {ans}")


