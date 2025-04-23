package SafeCrackers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Random {
    public static String Random(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String quizes = "";
        for(int i = 0; i < 6; i++){
            quizes += numbers.get(i).toString();
        }
        return quizes;
    }
}