
import java.util.Scanner;
public class G5_2447 {
    private static String[][] starGrid;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        starGrid = new String[n][n];

        int k = 0;
        // n이 3^k일때 k를 구하기
        while(n > 1){
            n /= 3;
            k++;
        }
        String[][] initGrid = {{"*","*","*"},{"*"," ","*"},{"*","*","*"}};


        for (int i = 1; i <= k; i++) {
//            System.out.println("현재의 init : "+initGrid.length);
            initGrid = printStar(initGrid,i);
            if(i == k) {
                for (int j = 0; j < initGrid.length; j++) {
                    for (int g = 0; g < initGrid.length; g++) {
                        System.out.print(initGrid[j][g] + " ");
                    }
                    System.out.println();

                }
            }
        }


    }
    public static String[][] printStar(String[][] preGrid,int cur_k){
        int k = (int)Math.pow((double)3,(double)cur_k)/3;
        int n = (int)Math.pow((double)3,(double)cur_k);
//        System.out.println("현재의 n " + n);
        String[][] newGrid = new String[n][n];


        // preGrid를 이용해서 전체적으로 그리기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i>=k && i < 2*k && j >= k && j < 2*k)  {
                    newGrid[i][j] = " ";
                }
                else{
                    if(preGrid[i%(n/3)][j%(n/3)] == "*"){
                        newGrid[i][j] = "*";
                    }
                    else{
                        newGrid[i][j] = " ";
                    }
                }

            }
        }
//        for (int i = 0; i< n; i++) {
//            for (int j = 0; j <n ; j++) {
//                System.out.print(newGrid[i][j] + " ");
//            }
//            System.out.println();
//
//        }
        return newGrid;
    }




}
