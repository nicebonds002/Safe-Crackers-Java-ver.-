package SafeCrackers;

import SafeCrackers.Console;
import SafeCrackers.Inspect;
import SafeCrackers.Random;
import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SafeCrackers{
    File setquizs;
    Scanner myReader;
    static String quiz;
    
    ArrayList<String> list = new ArrayList<String>();

    public void starts(String filepath) throws FileNotFoundException {
        setquizs = new File(filepath);
        myReader = new Scanner(setquizs);
        while (myReader.hasNextLine())
            list.add(myReader.next());
        int word = (int)(Math.random()* list.size());
        System.out.println(list);
        quiz = list.get(word);
        System.out.println(quiz);
        for(int i=0; i < 12; i++){
            Console.btn[i].setEnabled(true);
        }

    }
    public void starts() {
        String quizes = Random.Random();
        this.quiz=quizes;
        System.out.println(quiz);
        for(int i=0; i < 12; i++){
            Console.btn[i].setEnabled(true);
        }
    }
    public void edit() throws IOException {
        ProcessBuilder pd = new ProcessBuilder("Notepad.exe","setquiz.txt");
        pd.start();
    }
    public void set(){
        String out = "";
        try{
            FileWriter setquiz = new FileWriter("setquiz.txt");
            setquiz.write(out);
            setquiz.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void check(String output) throws BadLocationException {

        int int_output = Integer.parseInt(output);
        int int_quiz = Integer.parseInt(quiz);

        if(int_output==int_quiz){
            System.out.println("You win");
            new Inspect(output,quiz);
            Console.timer.stop();
            Console.btn_start.setEnabled(true);
            Console.btn_edit.setEnabled(true);
            Console.btn_set.setEnabled(true);
            Console.btn_rand.setEnabled(true);
            for(int i=0; i < 12; i++){
                Console.btn[i].setEnabled(false);
            }

        }
        else{
            System.out.println("Try again");
            new Inspect(output,quiz);
        }
    }
}