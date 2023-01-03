#DP try1

n = int(input())

# 각 계단마다의 점수를 알고있는 리스트 생성
step = [
    int(input()) for _ in range(n)
]

memo = [ 0 for _ in range(n)]
# 연속으로 한칸을 올라가는지 체크하기위한 변수 cnt
memo[0] = step[0]
if n > 1:
    memo[1] = memo[0] + step[1]
for i in range(2,n):
    if i == 2:
        memo[i] = max(step[i - 1], step[i - 2]) + step[i]  # 3  번쨰 칸을 밟기 위해서는 1번쨰칸에서 2칸올라서 오는 경우와 2번째칸에서 한칸 올라오는 경우만 있다.
    else:
        memo[i] = max(memo[i-3] + step[i-1] , memo[i-2] )  + step[i]
        # i가 3인(4층) 경우에서 부터는 i번째 층에 도달하기 위해서 한칸 뛰어서 오는경우 두칸 뛰어서 오는 경우로 나뉘는데  이부분을 나누어 주었다.
        # memo[i-3] + step[i-1]로 한 이유는 세칸을 연속으로 한칸 -> 한칸 -> 한칸 이동 할 수 없기 때문에 2 -> 1 -> 1로 올라 가는 것과  비교하게 만들었다.
print(memo[n-1])