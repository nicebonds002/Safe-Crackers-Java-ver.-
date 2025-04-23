package SafeCrackers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {
    public static void main(String args[]) throws FileNotFoundException {
        File setquiz = new File("setquiz.txt");
        Scanner myReader = new Scanner(setquiz);

        ArrayList<Integer> list = new ArrayList<Integer>();

        while(myReader.hasNextLine())
            list.add(myReader.nextInt());
        //int str = 123456;
        //String str2 = String.valueOf(str);
        System.out.println(list);
    }
}
