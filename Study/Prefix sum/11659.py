# 구간합 구하기  try 1

n,m = map(int,input().split())
num_arr = list(map(int,input().split())) # 숫자 배열 생성
sum_arr = []
sum_num = 0
for num in num_arr:
    sum_num += num
    sum_arr.append(sum_num)


for i in range(m):
    start , end = map(int,input().split())
    if start != 1:
        print(sum_arr[end-1] - sum_arr[start-2])
    else:
        print(sum_arr[end-1])

# 구간합이 0번째 인덱스부터 시작하는 경우 예외 처리를 만들어주었다.
# 다음엔 sys.stdin.readline 을 사용해보자