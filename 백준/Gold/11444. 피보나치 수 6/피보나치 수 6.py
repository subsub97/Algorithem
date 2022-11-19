import copy
n = int(input())

mode_num = 1000000007
# 행렬식을 이용한 풀이
arr =[[1,1],[1,0]]
def mult_matrix(arr,square_arr,odd_num):

    pre_square_arr = copy.deepcopy(square_arr)
    if odd_num ==1:
        square_arr[0][0] = ((pre_square_arr[0][0] * pre_square_arr[0][0]) + (pre_square_arr[0][1] * pre_square_arr[1][0])) % mode_num
        square_arr[0][1] = ((pre_square_arr[0][0] * pre_square_arr[0][1]) + (pre_square_arr[0][1] * pre_square_arr[1][1])) % mode_num
        square_arr[1][0] = ((pre_square_arr[1][0] * pre_square_arr[0][0]) + (pre_square_arr[1][1] * pre_square_arr[1][0])) % mode_num
        square_arr[1][1] = ((pre_square_arr[1][0] * pre_square_arr[0][1]) + (pre_square_arr[1][1] * pre_square_arr[1][1])) % mode_num
        # 홀수인경우 한번더 곱해주기
        pre_square_arr = copy.deepcopy(square_arr)

        square_arr[0][0] = ((arr[0][0]%mode_num)  * pre_square_arr[0][0]) + ((arr[0][1]%mode_num)  * pre_square_arr[1][0])
        square_arr[0][1] = ((arr[0][0]%mode_num)  * pre_square_arr[0][1]) + ((arr[0][1]%mode_num)  * pre_square_arr[1][1])
        square_arr[1][0] = ((arr[1][0]%mode_num)  * pre_square_arr[0][0]) + ((arr[1][1]%mode_num)  * pre_square_arr[1][0])
        square_arr[1][1] = ((arr[1][0] %mode_num) * pre_square_arr[0][1]) + ((arr[1][1]%mode_num)  * pre_square_arr[1][1])

        return square_arr
    else:
        square_arr[0][0] = ((pre_square_arr[0][0] * pre_square_arr[0][0]) + (
                    pre_square_arr[0][1] * pre_square_arr[1][0])) % mode_num
        square_arr[0][1] = ((pre_square_arr[0][0] * pre_square_arr[0][1]) + (
                    pre_square_arr[0][1] * pre_square_arr[1][1])) % mode_num
        square_arr[1][0] = ((pre_square_arr[1][0] * pre_square_arr[0][0]) + (
                    pre_square_arr[1][1] * pre_square_arr[1][0])) % mode_num
        square_arr[1][1] = ((pre_square_arr[1][0] * pre_square_arr[0][1]) + (
                    pre_square_arr[1][1] * pre_square_arr[1][1])) % mode_num
        return square_arr

def find_fibonacci_num(arr,n):
    if n == 1:
        square_arr =[[1,1],[1,0]]
        return square_arr
    else:
        square_arr = find_fibonacci_num(arr,n//2)
        if n % 2 != 0: #홀수인 경우
            # 덮어씌워지고 있다 덮어져서 더 큰 값이 나옴
             square_arr = mult_matrix(arr, square_arr,1)

             return square_arr
        else: # 짝수인 경우
            square_arr = mult_matrix(arr, square_arr, 0)
            return square_arr

a = find_fibonacci_num(arr,n)
answer = a[0][1] % mode_num
print(answer)


# else:
# sort_num = find_mode(A, num // 2)
# if num % 2 != 0:  # 홀수인 경우
#     return (sort_num * sort_num * A) % C
# else:  # 짝수인 경우
#     return (sort_num * sort_num) % C