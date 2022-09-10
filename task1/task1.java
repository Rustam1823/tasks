package task1;

public class task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int i = 1;
        int[] arr = new int[m];

        do {
            for (int j = 0; j < m; j++) {
                if (i > n){
                    i = 1;
                }
                arr[j] = i;
                i++;
            }
            i = arr[m-1];
            System.out.print(arr[0]);
        } while (arr[m-1] != 1);
    }
}

