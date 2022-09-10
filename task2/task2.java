package task2;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class task2 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(args[0]);
        Scanner scanner = new Scanner(fileReader);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        while (scanner.hasNextLine()){
            arrayList.add(scanner.nextInt());
        }

        fileReader.close();

        Collections.sort(arrayList);

        int median = getMedian(arrayList);
        int res = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            res += Math.abs(arrayList.get(i) - median);
        }
        System.out.println(res);
    }

    public static int getMedian(ArrayList arrayList){
        int median;

        if (arrayList.size() % 2 == 1){
            return median = (int) arrayList.get(arrayList.size()/2);
        } else {
            return median = ((int) arrayList.get(arrayList.size()/2 - 1) + (int) arrayList.get(arrayList.size()/2)/2);
        }
    }
}





