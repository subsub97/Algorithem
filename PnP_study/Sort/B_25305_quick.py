# 백준 25305번

'''
2022 연세대학교 미래캠퍼스 슬기로운 코딩생활에 $N$명의 학생들이 응시했다.

이들 중 점수가 가장 높은 $k$명은 상을 받을 것이다. 이 때, 상을 받는 커트라인이 몇 점인지 구하라.

커트라인이란 상을 받는 사람들 중 점수가 가장 가장 낮은 사람의 점수를 말한다.

'''

# 내림차순 quick_sort를 이용하자
def quick_sort(list,start,end):
    if start >= end:
        return
    pivot = start
    left = start + 1
    right = end

    while(left <= right):
        while left <= end and list[left] >= list[pivot]:
            left += 1

        while right > start and list[right] <= list[pivot]:
            right -= 1
        if left > right:
            temp = list[right]
            list[right] = list[pivot]
            list[pivot] = temp
        else:
            temp = list[right]
            list[right] = list[left]
            list[left] = temp

    quick_sort(list,start,right - 1)
    quick_sort(list,right + 1,end)

# 입력
n,k = map(int,input().split())
score_arr = list(map(int,input().split()))
quick_sort(score_arr,0,n-1)


# 출력
print(score_arr[k-1])
