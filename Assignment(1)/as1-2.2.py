m = int(input())

# 오름차순으로 정렬된 수를 받는 리스트 생성
m_arr = list(map(int, input().split()))

n = int(input())

n_arr = list(map(int, input().split()))

k = int(input())

# k와 x+y 사이의 값을 측정
gap = 1000001
# x와 y이 사이의 값을 측정
var_gap = 100000000
# 정답으로 출력할 변수 저장
xans = 0
yans = 0
# 탐색을 위한 변수 선언
left = 0
right = n - 1

while 1:
    if left > m or right < 0:
        break

    else:
        # k보다 합이 큰 경우
        if k < m_arr[left] + n_arr[right]:
            right -= 1

        else:
            if gap > k - (m_arr[left] + n_arr[right]):
                gap = k - (m_arr[left] + n_arr[right])
                xans = m_arr[left]
                yans = n_arr[right]
                var_gap = abs(m_arr[left] - n_arr[right])
            # gap이 동일한 경우엔 var_gap을 확인해서 출력한 x,y를 정해야 한다.
            elif gap == k - (m_arr[left] + n_arr[right]):
                if var_gap > abs(m_arr[left] - n_arr[right]):
                    var_gap = abs(m_arr[left] - n_arr[right])
                    xans = m_arr[left]
                    yans = n_arr[right]

                # var_gap까지 동일한경우 다음 우선순위인 더 x 가 큰값으로 저장
                elif var_gap == abs(m_arr[left] - n_arr[right]):
                    if xans < m_arr[right]:
                        xans = m_arr[left]
                        yans = n_arr[right]
            left += 1

print(xans, yans)



