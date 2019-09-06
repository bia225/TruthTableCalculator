package truthtable;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TruthTable extends JFrame
{
    private JTextField text;
    private JTextField textfield [] = new JTextField [3];
    
    private final Font plain = new Font ("Serif",Font.PLAIN,30);
    
    private JRadioButton buttons1[] = new  JRadioButton [4];
    private JRadioButton buttons2[] = new  JRadioButton [4];
    
    private ButtonGroup bt1 = new ButtonGroup ();
    private ButtonGroup bt2 = new ButtonGroup ();
     
    private boolean values [][] = new boolean [4][4];
    private boolean answer[] = new boolean [4];
    
    private JButton operator [] = new  JButton [6];
    
    private boolean p[] = {true, true, false,false};
    private boolean notp[] = {false, false, true, true};
    private boolean q[] = {true, false, true, false};
    private boolean notq[] = {false, true, false, true};
   
    private boolean done = false;
    private String route = "";
    private short counter = 0;
    
    JPanel firstButtons;
    JPanel secondButtons;
    JPanel panel;
    
    public void instanciateValues()
    {
        for (int i = 0; i<values.length; i++)
        {
            values[0][i] = p[i];
            values[1][i] = !p[i];
            values[2][i] = q[i];
            values[3][i] = !q[i];
        }
    }
    
    public TruthTable ()
    {
        instanciateValues();
        JLabel lbls[] = new JLabel [3];
        JLabel request = new JLabel ("   Select the two propositions and then select the operator. Your request ---> ");
        lbls[0] = new JLabel("    First Input:      ");
        lbls[1] = new JLabel("    Second Input:");
        lbls[2] = new JLabel("    Answer:          ");
        
        JPanel labels[] = new JPanel [4];
        
        /* first text field*/ 
        text = new JTextField ("",12);   
        text.setHorizontalAlignment (JTextField.RIGHT);
        text.setFont(new Font ("Serif",Font.PLAIN, 18));
        text.setEditable(false);
        
        labels[0] = new JPanel ();
        labels[0].setLayout(new BorderLayout(10,10));
        labels[0].setFont(plain);
        labels[0].add(request, BorderLayout.WEST);
        labels[0].add(text, BorderLayout.CENTER);
        
        /* buttons p and !p */
        firstButtons = new JPanel ();
        firstButtons.setLayout(new GridLayout(2,1,10,10));
        firstButtons.setBorder(BorderFactory.createTitledBorder("First Proposition"));

        buttons1[0] = new  JRadioButton("p");
        buttons1[1] = new  JRadioButton("!p");
        buttons1[2] = new  JRadioButton("q");
        buttons1[3] = new  JRadioButton("!q");
        
        buttons1[0].setFont (plain);
        buttons1[1].setFont (plain);
        buttons1[2].setFont (plain);
        buttons1[3].setFont (plain);
        
        buttons1[0].addActionListener (new ButtonListener ());
        buttons1[1].addActionListener (new ButtonListener ());
        buttons1[2].addActionListener (new ButtonListener ());
        buttons1[3].addActionListener (new ButtonListener ());
        
        bt1.add(buttons1[0]);
        bt1.add(buttons1[1]);
        bt1.add(buttons1[2]);
        bt1.add(buttons1[3]);
        
        firstButtons.add(buttons1[0]);  
        firstButtons.add(buttons1[1]);    
        firstButtons.add(buttons1[2]);            
        firstButtons.add(buttons1[3]);
        
        /* buttons for the operators */
        JPanel operators = new JPanel();                          
        operators.setLayout(new GridLayout(3,2,10,10));
        String [] operatorText = {"AND","OR", "XOR", "IMPLIES", "BICONDITIONAL", "RESET"};
        
        for (int i = 0;i<operatorText.length; i++)
        {
            operator[i] = new  JButton(operatorText[i]);
            operator[i].setFont(plain);
            operator[i].addActionListener (new ButtonListener());
            operators.add(operator[i]);
        }
                                     
        /* buttons on the second part */
        secondButtons = new JPanel();
        secondButtons.setLayout(new  GridLayout(2,1,10,10));
        secondButtons.setBorder(BorderFactory.createTitledBorder("Second Proposition"));
        
        buttons2[0] = new  JRadioButton ("p");
        buttons2[1] = new  JRadioButton ("!p");
        buttons2[2] = new  JRadioButton ("q");
        buttons2[3] = new  JRadioButton ("!q");
                
        buttons2[0].setFont(plain);
        buttons2[1].setFont(plain);
        buttons2[2].setFont(plain);
        buttons2[3].setFont(plain);
        
        buttons2[0].addActionListener (new ButtonListener ());
        buttons2[1].addActionListener (new ButtonListener ());
        buttons2[2].addActionListener (new ButtonListener ());
        buttons2[3].addActionListener (new ButtonListener ());
        
        bt2.add(buttons2[0]);   
        bt2.add(buttons2[1]);
        bt2.add(buttons2[2]);                    
        bt2.add(buttons2[3]);
        
        secondButtons.add(buttons2[0]);
        secondButtons.add(buttons2[1]);
        secondButtons.add(buttons2[2]);
        secondButtons.add(buttons2[3]);
                       
        /* text panel*/
        JPanel textpan = new JPanel ();
        textpan.setLayout(new GridLayout (4,1,10,10));
        
        for (int i =0; i<textfield.length; i++)
        {
            textfield[i] = new JTextField ("",12);
            textfield[i].setEditable(false);
            textfield[i].setFont(plain);
            textfield[i].setHorizontalAlignment (JTextField.LEFT);
            
        }
        
        labels[1] = new JPanel ();
        labels[2] = new JPanel ();
        labels[3] = new JPanel ();
        
        labels[1].setLayout(new BorderLayout(10,10));
        labels[2].setLayout(new BorderLayout(10,10));
        labels[3].setLayout(new BorderLayout(10,10));
         
        labels[1].setFont(plain);      
        labels[2].setFont(plain);
        labels[3].setFont(plain);
                
        labels[1].add(lbls[0], BorderLayout.WEST);
        labels[2].add(lbls[1], BorderLayout.WEST);
        labels[3].add(lbls[2], BorderLayout.WEST);

        labels[1].add(textfield[0], BorderLayout.CENTER);
        labels[2].add(textfield[1], BorderLayout.CENTER);
        labels[3].add(textfield[2], BorderLayout.CENTER);
        
        textpan.add(labels[1]);
        textpan.add(labels[2]);       
        textpan.add(labels[3]);
                       
        /* adding to main panel */
        
        panel = new JPanel ();
        panel.setLayout(new BorderLayout(30,30));
        panel.add(labels[0], BorderLayout.NORTH);
        panel.add(firstButtons, BorderLayout.WEST);
        panel.add(operators, BorderLayout.EAST);
        panel.add(secondButtons, BorderLayout.CENTER);
        panel.add(textpan, BorderLayout.SOUTH);
                       
        this.setContentPane(panel);
        this.pack();
        this.setTitle ("Truth Table Calculator");
        this.setResizable(true);
    }    
    
    public class ButtonListener implements ActionListener 
    {
        public void print(String op)
        {
            if (done)
            {
                route+= op;
                for (int i =0; i<4; i++)
                    if (buttons2[i].isSelected())
                    {
                        route += buttons2[i].getText();
                        break;
                    }
                text.setText(route);
            }
        }

        public boolean check ()
        {
            int ii = -1, iii=-1;
            for (int i =0; i<4; i++)
            {
                if (buttons1[i].isSelected())
                    ii = i;
                if (buttons2[i].isSelected())
                    iii = i;
            }
            if (ii!=-1 && iii!=-1)
                return true;
            else 
            {
                JOptionPane.showMessageDialog(null, "Please check the missing button.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        
        public void clearTextField(int n, boolean [] arr)
        {
            String result = "";
            if (done)
            {
                for (int i =0; i<answer.length; i++)
                    result += answer[i] + "\t"; 
                textfield[0].setText(result);
                textfield[2].setText("");
            }
            result="";
            for (int i =0; i<p.length; i++)
                result += arr[i] + "\t"; 
            textfield[n].setText(result);
        }
        
        
        public void actionPerformed (ActionEvent evt)
        {
            Object source = evt.getSource ();
            if (source == buttons1[0])  
            {
                String result = "";
                for (int i =0; i<p.length; i++)
                    result += p[i] + "\t"; 
                textfield[0].setText(result);
            }
            else if (buttons1[1] == source) 
            {
                String result = "";
                for (int i =0; i<p.length; i++)
                    result += !p[i] + "\t";
                textfield[0].setText(result);
            }
            else if (buttons1[2] == source)
            {
                String result = "";
                for (int i =0; i<p.length; i++)
                    result += q[i] + "\t"; 
                textfield[0].setText(result);
            }
            else if (buttons1[3] == source)
            {
                String result = "";
                for (int i =0; i<p.length; i++)
                    result += !q[i] + "\t"; 
                textfield[0].setText(result);
            }
            else if (source == buttons2[0])
                clearTextField(1,p);
            else if (buttons2[1] == source)
                clearTextField(1,notp);
            else if (buttons2[2] == source)
                clearTextField(1,q);
            else if (buttons2[3] == source)
                clearTextField(1,notq);
            else if (source  == operator[5]) //when RESET is pressed
                Clear();
            else if (source == operator[0] && check()) //when AND is pressed
                Operation(" & ",0);
            else if (source == operator[1] && check()) //when OR is pressed
                Operation(" || ", 1);
            else if (source == operator[2] && check()) //when XOR is pressed
                Operation(" (+) ", 2);
            else if (source == operator[3] && check()) //when IMPLIES is pressed
                Operation(" -> ", 3);
            else if (source == operator[4] && check()) //when BICONDITIONAL is pressed
                Operation(" <-> ", 4);
        }
        
        
        public void Clear()
        {
            for (int i =0; i<3; i++)
                    textfield[i].setText("");
            bt1.clearSelection();
            bt2.clearSelection();
            text.setText("");
            route = "";
            for (int i =0; i<4; i++)
                buttons1[i].setEnabled(true);
            done = false;
            counter = 1;
            firstButtons.setVisible(true);
        }
        
        
        public void Operation(String opName, int flag)
        {
            String result = "";
            if (!done) 
            {
                route = "";
                done = true;
                int ii = -1, iii=-1;
                for (int i =0; i<4; i++)
                    if (buttons1[i].isSelected())
                    {
                        route = buttons1[i].getText();
                        break;
                    }
                print(opName);
                for (int i =0; i<4; i++)
                {
                    if (buttons1[i].isSelected())
                        ii = i;
                    if (buttons2[i].isSelected())
                        iii = i;
                }
                /* flags to know which operator we're working on*/ 
               if (flag == 0)
                    for (int i =0; i<4; i++)
                        answer[i] = values[ii][i] & values[iii][i];
                else if (flag == 1)
                    for (int i =0; i<4; i++)
                        answer[i] = values[ii][i] | values[iii][i];
                else if (flag == 2)
                    for (int i =0; i<4; i++)
                        answer[i] = values[ii][i] ^ values[iii][i];
                else if (flag == 3)
                    for (int i = 0; i<4; i++)
                        if (values[ii][i] && !values[iii][i])
                            answer[i] = false;
                        else
                            answer[i] = true;
                else if (flag == 4)
                    for (int i =0; i<4; i++)
                        if (values[ii][i] == values[iii][i])
                            answer[i] = true;
                        else
                            answer[i] = false;
                            
                for (int i =0; i<answer.length; i++)
                    result += answer[i] + "\t"; 
                textfield[2].setText(result);
    
            }

        }
    }

    public static void main (String []a) 
    {
         JFrame frame = new TruthTable ();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         frame.setLocation(300,150);
    }
}