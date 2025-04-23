package SafeCrackers;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import static SafeCrackers.SafeCrackers.quiz;

public class Console extends JFrame implements ActionListener{
    static String[] btnString = {"7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "Reset", "0", "Enter"};
    static Button[] btn = new Button[12];
    static JButton btn_start, btn_edit, btn_set, btn_rand;
    static TextField input_text;

    protected static JLabel countdownLabel;
    protected static JLabel countLabel;
    static JLabel quizLabel;
    protected static Timer timer;
    protected int second;
    protected int minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");
    protected static int n;

    String count;

    static JTextPane output_text;
    static Font f = new Font("Arial Rounded MT Bold",Font.PLAIN,20);
    static JPanel TLPanel, TRPanel, BLPanel, BRPanel;
    static JSplitPane pane1, pane2, mainLine;
    static DefaultStyledDocument document;
    static Style style;
    static StyleContext sc;
    public Console(){
        TLPanel = new JPanel(new GridLayout(2,1,1,1));
        TLPanel.setBackground(Color.white);
        TRPanel = new JPanel(new GridLayout(2,1,1,1));
        BLPanel = new JPanel(new GridLayout(4,1,1,1));
        BRPanel = new JPanel();

        pane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,TLPanel,BLPanel);
        pane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,TRPanel,BRPanel);
        mainLine = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pane1,pane2);

        BRPanel.setLayout(new GridLayout(4,3));

        for(int i=0; i < 12; i++) {
            btn[i] = new Button(btnString[i]);
            btn[i].addActionListener(this);
            btn[i].setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,25));
            btn[i].setBackground(new Color(246,239,229));
            BRPanel.add(btn[i]);
        }

        btn_start = new JButton("Start");
        btn_start.setFont(f);
        btn_start.addActionListener(this);
        btn_start.setBackground(new Color(236, 255, 255));
        btn_edit = new JButton("Edit");
        btn_edit.setFont(f);
        btn_edit.addActionListener(this);
        btn_edit.setBackground(new Color(255, 250, 252));
        btn_set = new JButton("Set a quiz");
        btn_set.setFont(f);
        btn_set.addActionListener(this);
        btn_set.setBackground(new Color(244, 252, 255));
        btn_rand = new JButton("Start(random)");
        btn_rand.setBackground(new Color(248, 251, 253));
        btn_rand.setFont(f);
        btn_rand.addActionListener(this);

        BLPanel.add(btn_start);
        BLPanel.add(btn_edit);
        BLPanel.add(btn_set);
        BLPanel.add(btn_rand);

        countdownLabel = new JLabel("");
        countdownLabel.setBounds(300, 230, 200, 100);
        countdownLabel.setHorizontalAlignment(JLabel.LEFT);
        countdownLabel.setFont(f);
        countdownLabel.setText("00:30");
        second = 30;
        minute = 0;

        TLPanel.add(countdownLabel);

        countLabel = new JLabel("");
        countLabel.setBounds(300, 230, 200, 100);
        countLabel.setHorizontalAlignment(JLabel.RIGHT);
        countLabel.setFont(f);
        countLabel.setText("0");
        countLabel.setForeground(Color.RED);
        n = 0;
        TLPanel.add(countLabel);

        quiz = quiz;
        quizLabel = new JLabel("000000");
        quizLabel.setBounds(200, 230, 200, 100);
        quizLabel.setHorizontalAlignment(JLabel.LEFT);
        quizLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,10));
        quizLabel.setForeground(Color.WHITE);
        TLPanel.add(quizLabel);

        document = new DefaultStyledDocument();
        sc = new StyleContext();
        style = sc.addStyle("WHITE", null);
        StyleConstants.setForeground(style, Color.GRAY);
        StyleConstants.setBackground(style, Color.BLACK);
        StyleConstants.setFontSize(style,20);
        StyleConstants.setFontFamily(style,"Arial Rounded MT Bold");
        StyleConstants.setBold(style,false);
        output_text = new JTextPane(document);
        output_text.setBackground(Color.BLACK);
        output_text.setFont(f);
        document.setCharacterAttributes(0,6,style,true);
        output_text.setText("000000");

        TRPanel.add(output_text);

        input_text = new TextField(4);
        input_text.setForeground(Color.RED);
        input_text.setBackground(Color.BLACK);
        input_text.setFont(f);
        input_text.setEditable(false);
        TRPanel.add(input_text);

        setContentPane(mainLine);
        setTitle("SAFE CRACKERS GAME");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }
    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        SafeCrackers s = new SafeCrackers();
        if (str.equals("Enter")) {
            String output = input_text.getText();
            //output_text.setText(output);
            ///System.out.println(output);
            try {
                s.check(output);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            n = n + 1;
            String count = String.valueOf(n);
            countLabel.setText(count);
            for (int i = 0; i < 12; i++) {
                Console.btn[i].setEnabled(true);
            }
        } else if (str.equals("Set a quiz")) {
            s.set();
        } else if (str.equals("Start")) {
            quizLabel.setForeground(Color.WHITE);
            input_text.setText("");
            output_text.setText("000000");
            btn_start.setEnabled(false);
            btn_edit.setEnabled(false);
            btn_set.setEnabled(false);
            btn_rand.setEnabled(false);
            try {
                s.starts("setquiz.txt");

                if (countdownLabel.getText() == "00:30") {
                    countdownTimer();
                    timer.start();
                } else {
                    countdownLabel.setText("00:30");
                    second = 30;
                    minute = 0;
                    countdownTimer();
                    timer.start();
                }
                countLabel.setText("0");
                n = 0;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (str.equals("Start(random)")) {
            quizLabel.setForeground(Color.WHITE);
            input_text.setText("");
            output_text.setText("000000");
            btn_start.setEnabled(false);
            btn_edit.setEnabled(false);
            btn_set.setEnabled(false);
            btn_rand.setEnabled(false);
            ///s.start();
            s.starts();
            if (countdownLabel.getText() == "00:30") {
                countdownTimer();
                timer.start();
            } else {
                countdownLabel.setText("00:30");
                second = 30;
                minute = 0;
                countdownTimer();
                timer.start();
            }
            countLabel.setText("0");
            n = 0;
        } else if (str.equals("Edit")) {
            try {
                s.edit();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (str.equals("Reset")) {
            input_text.setText("");
            System.out.println("Reset");
        } else {
            if (input_text.getText().length() > 5) {
                for (int i = 0; i < 11; i++) {
                    Console.btn[i].setEnabled(false);
                }
                Console.btn[11].setEnabled(true);
            }
            else if (input_text.getText().length() < 5) {
                input_text.setText(input_text.getText() + str);
                Console.btn[11].setEnabled(false);
            }
            else{
                input_text.setText(input_text.getText() + str);
                for (int i = 0; i < 11; i++) {
                    Console.btn[i].setEnabled(false);
                }
                Console.btn[11].setEnabled(true);
            }
        }
    }
    public static void main(String args[]){
        new Console();
        Lock obj = new Locks();
        obj.LockStart();
    }
    public void countdownTimer() {

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                second--;
                ddSecond = dFormat.format(second);
                ddMinute = dFormat.format(minute);
                countdownLabel.setText(ddMinute + ":" + ddSecond);

                if(second==-1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);
                    countdownLabel.setText(ddMinute + ":" + ddSecond);
                }
                if(minute==0 && second==0) {
                    timer.stop();
                    btn_start.setEnabled(true);
                    btn_edit.setEnabled(true);
                    btn_set.setEnabled(true);
                    btn_rand.setEnabled(true);
                    for(int i=0; i < 12; i++){
                        Console.btn[i].setEnabled(false);
                    }
                    try{
                        Thread.sleep(3000);
                        quizLabel.setText("Password : "+quiz);
                        quizLabel.setForeground(Color.BLACK);

                    } catch(InterruptedException be){
                        System.out.println(be);
                    }
                }
            }
        });
    }
}	