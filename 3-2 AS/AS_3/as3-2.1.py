'''
문 2) 다음과 같은 (1이상 2,000이하 정수)개의 행과 (1이상 2,000이하 정수)개의 열의 셀(cell)들로 이루어진  격자가 있다. 여기서 가장 아래 행은 행 1이고 가장 위의 행은 행 이며, 가장 왼쪽 열은 열 1이고 가장 오른쪽 열은 열 이다. 셀 는 번째 행과 번째 열의 셀을 나타낸다. 각 셀 에는 비용 이 주어진다. 아래 그림은  격자 예를 보여준다. 여기서  = 3, ...,  = 8이다.
입력: 첫 번째 줄에 과 이 주어진다. 다음 각 줄에 각 행(위로부터 아래로)의 셀의 비용이 주어진다.

출력:
경로의 최소 비용을 출력한다.
'''

# 행, 열을 입력 받는다.
n,m = map(int,input().split())

arr_2d = [
    list(map(int,input().split()))
    for _ in range(n)
]
# 최소비용 기록을 위한 배열 생성
dp_arr = [
    0 for _ in range(m)
]
min_cost = 0

#  n행 1열부터 1행 m열까지 확인해야한다.
for i in range(n-1,-1,-1): # 행을 관리하는 i는 n부터 시작한다.
    for j in range(m): # 열을 관리하는 j는 1부터 시작하며 올라간다.
        if j == 0 and i != 0: # 최좌측 열인 경우 왼쪽 대각위는 확인 하지 않는다. 꼭대기 행에서는 확인 안함
            dp_arr[j] = arr_2d[i-1][j] # 우측 상단 확인
            dp_arr[j+1] = arr_2d[i-1][j+1] # 상단 확인
        elif j == m-1 and i != 0: # 최우측 열인 경우는 우측상단은 확인 하지 않아도 된다. 꼭대기에서는 확인 안함
            dp_arr[j - 1] = arr_2d[ i- 1][j-1] # 왼쪽 상단 확인
            dp_arr[j] = arr_2d[i - 1][j] # 상단 확인
        elif i != 0:
            dp_arr[j - 1] = arr_2d[i - 1][j - 1]  # 왼쪽 상단 확인
            dp_arr[j] = arr_2d[i - 1][j]  # 상단 확인
            dp_arr[j + 1] = arr_2d[i - 1][j + 1]  # 상단 확인
        else:
            dp_arr[j] = arr_2d[n-1][j]
    min_cost += min(dp_arr)

print(min_cost)



