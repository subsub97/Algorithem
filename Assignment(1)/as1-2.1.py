m = int(input())

# 오름차순으로 정렬된 수를 받는 리스트 생성
m_arr = list(map(int, input().split()))

n = int(input())

n_arr = list(map(int, input().split()))

k = int(input())

gap = 1000001
var_gap = 1000000
xans = 10000000
yans = 10000000
cnt = 0

# 리스트 두개중 하나는 완전탐색을 돌리자
for i in range(m):
    start = 0
    end = n - 1
    # 나머지 한 리스트는 이진탐색을 이용하여 수행시간을 줄이자
    while start <= end:
        mid = (start + end) // 2
        # x + y 는 k 이하일 경우에만
        if m_arr[i]<= k:
            # 만약 찾는수와 합이 동일한 경우
            if k == (m_arr[i] + n_arr[mid]):

                # k값과 일치하는 합이 여러개인 경우대비
                if (cnt >= 1 and var_gap > abs(m_arr[i]-n_arr[mid])):
                    var_gap = abs(m_arr[i]-n_arr[mid])
                    xans = m_arr[i]
                    yans = n_arr[mid]
                elif var_gap == abs(m_arr[i]- n_arr[mid]):
                    if xans < m_arr[i]:
                        xans = m_arr[i]
                        yans = n_arr[mid]
                        break
                else:
                    if var_gap > abs(m_arr[i] - n_arr[mid]):
                        var_gap = abs(m_arr[i] - n_arr[mid])
                        xans = m_arr[i]
                        yans = n_arr[mid]
                        gap = 0
                        cnt += 1


            elif k > (m_arr[i] + n_arr[mid]):
                if gap == abs(k - (m_arr[i] + n_arr[mid])):
                    if var_gap > abs(m_arr[i] -n_arr[mid]):
                        var_gap = abs(m_arr[i] -n_arr[mid])
                        xans = m_arr[i]
                        yans = n_arr[mid]
                        start = mid + 1
                    elif var_gap == abs(m_arr[i] -n_arr[mid]):
                        if xans < m_arr[i]:
                            xans = m_arr[i]
                            yans = n_arr[mid]
                            start = mid + 1
                    else:
                        start = mid + 1
                elif gap > abs(k - (m_arr[i] + n_arr[mid])):
                    gap = abs(k - (m_arr[i] + n_arr[mid]))
                    xans = m_arr[i]
                    yans = n_arr[mid]
                    start = mid + 1
                else:
                    start = mid + 1
            else:
                if gap == abs(k - (m_arr[i] + n_arr[mid])):
                    if var_gap > abs(m_arr[i] -n_arr[mid]):
                        var_gap = abs(m_arr[i] -n_arr[mid])
                        xans = m_arr[i]
                        yans = n_arr[mid]
                        end = mid - 1
                    elif var_gap == abs(m_arr[i] - n_arr[mid]):
                        if xans < m_arr[i]:
                            xans = m_arr[i]
                            yans = n_arr[mid]
                            end = mid - 1
                elif gap > abs(k - (m_arr[i] + n_arr[mid])):
                    gap = abs(k - (m_arr[i] + n_arr[mid]))
                    xans = m_arr[i]
                    yans = n_arr[mid]
                    end = mid - 1
                else:
                    end = mid - 1
        else:
            break

print(xans, yans)






