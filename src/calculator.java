//Section 1.
// java Swing GUI calculator...
import java.awt.*;          //color ,font , borderlayout,griselayout.
import java.awt.event.*;    //button click ,mouese hover and keybord press
import java.util.Arrays;    //conver array into list and sorting
import javax.swing.*;       //main window ,text,buttonn ,container panel  import
import javax.swing.border.LineBorder;  //component ko charo taraf se border line lagane ke liye.

//Section 2.
//class and frame size define....
public class calculator { 
    int boardWidth = 360;
    int boardHeight = 540;

    //section 3.
      //  custom colour ke liye ...
    Color customDigit = new Color(44, 62, 80);      // Dark Blue-Gray
    Color customOperator = new Color(0, 184, 148);  // Teal Green
    Color customTop = new Color(230, 126, 34);      // Orange
    Color customBg = new Color(30, 42, 56);         // Navy Background
    Color customDisplay = new Color(25, 32, 45);    // Darker Navy
    Color customText = new Color(0, 255, 200);      // Cyan for display

    //section 4.
      //button list...
    String[] buttonValues = { 
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4","5", "6","-",
        "1", "2", "3","+",
        "0", "." ,"^", "="
    };
    String[] rightSymbols = {"÷","×","-","+","="};
    String[] topSymbols = {"AC","+/-","%"};

    //section 5.
    //frame and panel..
    JFrame frame = new JFrame("calculator"); //calculator display karne ke liye
    JLabel displayLabel = new JLabel(); // jaha number dikhe ga screen pe
    JPanel displayPanel = new JPanel(); // display and button dono ko  alag alag section me rakhne ke liye.
    JPanel buttonsPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;

    calculator(){
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(customBg);

        //Section 6.
        // Display setting
        displayLabel.setBackground(customDisplay);
        displayLabel.setForeground(customText);
        displayLabel.setFont(new Font("Consolas",Font.BOLD,60));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel,BorderLayout.NORTH);

        //Section 7.
        // Buttons layout
        buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));
        //button ko 5 row and 4 collum me grid ki tarahh lagaya gaya h.
        buttonsPanel.setBackground(customBg);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        frame.add(buttonsPanel);

        //section 8.
        //button creation with loop
        for(int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial",Font.BOLD,24));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBg));
            button.setOpaque(true);


            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customTop);
                button.setForeground(Color.white);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOperator);
                button.setForeground(Color.black);
            }
            else {
                button.setBackground(customDigit);
                button.setForeground(Color.white);
            }

            buttonsPanel.add(button);

            //section 9.
            // Action listener (same logic as your code)
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button =(JButton) e.getSource();
                    String buttonValue = button.getText();
                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if (buttonValue.equals("=")) {
                            if (A !=null) {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator.equals("+")) {
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                else if (operator.equals("-")){
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                else if (operator.equals("×")){
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                else if (operator.equals("÷")){
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                                clearAll();
                            }
                        }
                        else if ("+-×÷".contains(buttonValue)){
                            if (operator == null) {
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";   
                            }
                            operator = buttonValue;
                        }
                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        if (buttonValue.equals("AC")) {
                            clearAll();
                            displayLabel.setText("0");
                        }
                        else if (buttonValue.equals("+/-")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                        else if (buttonValue.equals("%")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }
                    else {
                        if (buttonValue.equals(".")) {
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }   
                        }
                        else if ("0123456789".contains(buttonValue)){
                            if(displayLabel.getText().equals("0")){
                                displayLabel.setText(buttonValue);
                            }
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
        }
        frame.setVisible(true);
    }

    //section 11.
    //main method
    public static void main(String[] args) {
        new calculator();
    }

    //section 10.
    //helper methods
    void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }
    String removeZeroDecimal(double numDisplay){
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
