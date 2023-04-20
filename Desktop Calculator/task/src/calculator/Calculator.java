package calculator;

import javax.swing.*;

public class Calculator extends JFrame {

    public Calculator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");

        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setLayout(null);
        JTextField EquationTextField = new JTextField();
        EquationTextField.setName("EquationTextField");
        EquationTextField.setBounds(75, 20, 150, 30);
        add(EquationTextField);
        JButton Solve = new JButton("Solve");
        Solve.setName("Solve");
        Solve.setBounds(100, 300, 100, 30);
        add(Solve);
        Solve.addActionListener(e -> {

            String name = EquationTextField.getText();

            EquationTextField.setText(name+"="+result(name));


        });

        setVisible(true);

    }
    int result(String a) {
        String regex = "[\\D]+";
        String t = "[\\d]";
        String b="";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '+' || a.charAt(i) == '-' || a.charAt(i) == '*' || a.charAt(i) == '/' || a.charAt(i) == '.') {
                b = String.valueOf(a.charAt(i));
            }
        }
        System.out.println(b);
        String[]text = a.split(regex);
        int a1=Integer.parseInt(text[0]);
        int s1=Integer.parseInt(text[1]);


       int x= switch (b) {
            case "+"-> a1+s1;
            case "-"-> a1-s1;
            case "*"-> a1*s1;
            case "/"-> a1/s1;
            default->0 ;

        };
        return x;
    }
}
