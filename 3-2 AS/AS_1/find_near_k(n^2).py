m = int(input())

# 오름차순으로 정렬된 수를 받는 리스트 생성
m_arr = list(map(int, input().split()))

n = int(input())

n_arr = list(map(int, input().split()))

k = int(input())

# 가장 가까운 수를 확인할 변수 check_num 생성
check_num = 1000000

for i in range(m):
    if i > k:
        break
    for j in range(n):
        sum_val = m_arr[i] + n_arr[j]
        distance = k - sum_val
        if sum_val > k:
            break
        if sum_val <= k and distance < check_num:
            # 배열 초기화
            min_arr = []
            min_arr.append(m_arr[i])
            min_arr.append(n_arr[j])
            check_num = distance
        elif sum_val <= k and distance == check_num:
            # x , y 의 차이를 확인 하기 위한 조건문 추가
            if abs(min_arr[0] - min_arr[1]) > abs(m_arr[i] - n_arr[j]):
                min_arr[0] = m_arr[i]
                min_arr[1] = n_arr[j]
            elif abs(min_arr[0] - min_arr[1]) == abs(m_arr[i] - n_arr[j]):
                if min_arr[0] < m_arr[i]:
                    min_arr[0] = m_arr[i]
                    min_arr[1] = n_arr[j]

for i in range(0, 2):
    print(min_arr[i], end=' ')