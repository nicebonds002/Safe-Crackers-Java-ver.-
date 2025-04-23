package SafeCrackers;

import SafeCrackers.Console;
import javax.swing.text.*;
import java.awt.*;

public class Inspect{
    StyleContext sc = new StyleContext();
    public Inspect(String output, String quiz) throws BadLocationException {
        final Style redStyle = sc.addStyle("RED", null);
        redStyle.addAttribute(StyleConstants.Foreground, Color.red);
        redStyle.addAttribute(StyleConstants.FontSize,20);
        redStyle.addAttribute(StyleConstants.Family,"Arial Rounded MT Bold");

        final Style yellowStyle = sc.addStyle("YELLOW", null);
        yellowStyle.addAttribute(StyleConstants.Foreground, Color.yellow);
        yellowStyle.addAttribute(StyleConstants.FontSize,20);
        yellowStyle.addAttribute(StyleConstants.Family,"Arial Rounded MT Bold");

        final Style greenStyle = sc.addStyle("GREEN", null);
        greenStyle.addAttribute(StyleConstants.Foreground, Color.green);
        greenStyle.addAttribute(StyleConstants.FontSize,20);
        greenStyle.addAttribute(StyleConstants.Family,"Arial Rounded MT Bold");

        for(int i=quiz.length()-1; i>=0; i--){
            boolean didBreak = false;
            for(int j=quiz.length()-1; j>=0; j--){
                if(output.charAt(i)==quiz.charAt(i)){
                    String c = String.valueOf(output.charAt(i));
                    Console.document.insertString(0,c,greenStyle);
                    didBreak = true;
                    break;
                }
                if(output.charAt(i)==quiz.charAt(j)){
                    String c = String.valueOf(output.charAt(i));
                    Console.document.insertString(0,c,yellowStyle);
                    didBreak = true;
                    break;
                }
            }
            if(!didBreak){
                String c = String.valueOf(output.charAt(i));
                Console.document.insertString(0,c,redStyle);
            }
        }
        Console.document.replace(6,6,"", Console.style);
    }

}
