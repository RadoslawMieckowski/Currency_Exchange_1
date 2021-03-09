import javax.swing.*;
import java.awt.*;



public class ExchangeCurrency  extends JFrame{
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel title;
    private static JComboBox combo1;
    private static JComboBox combo2;
    private JButton button;
    private FlowLayout manager;
    private Font font;
    private Offerts offerts;
    private static String baseCurrency;
    private static String quoteCurrency;

    public static String getBaseCurrency() {
        return baseCurrency;
    }

    public static String getQuoteCurrency() {
        return quoteCurrency;
    }

    public static void setBaseCurrency(String baseCurrency) {
       ExchangeCurrency.baseCurrency = baseCurrency;
    }

    public static void setQuoteCurrency(String quoteCurrency) {
        ExchangeCurrency.quoteCurrency = quoteCurrency;
    }

    public ExchangeCurrency(){
        setLayout(new BorderLayout());
        setTitle("Currency Exchange");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,250);
       this.setBounds(350,250,600,250);


       font= new Font("SansSerif",Font.BOLD+Font.ITALIC,16);
        manager = new FlowLayout(FlowLayout.CENTER,40,0);

        panel1=new JPanel(new GridLayout(3,1));
        panel1.add(new JLabel());
        title = new JLabel("Currency Exchange",SwingConstants.CENTER);
        title.setFont(font);
        panel1.add(title);
        panel1.add(new JLabel());
        add(panel1,BorderLayout.NORTH);

        panel2=new JPanel(manager);
        panel2.add(new JLabel("Base currency"));
        panel2.add(new JLabel("Quote currency"));

        panel4=new JPanel(manager);
        panel4.add(combo1=new JComboBox<String>());

        combo1.addItem("");
        combo1.addItem("PLN");
        combo1.addItem("$");
        combo1.addItem("€");
        combo1.addItem("£");
        combo1.addItem("CAD");
        combo1.addItem("CHF");
        combo1.addItem("¥");

        combo1.addActionListener(event->{
            baseCurrency = combo1.getItemAt(combo1.getSelectedIndex()).toString();
            //System.out.println(baseCurrency);
        });

        panel4.add(combo2=new JComboBox<String>());

        combo2.addItem("");
        combo2.addItem("PLN");
        combo2.addItem("$");
        combo2.addItem("€");
        combo2.addItem("£");
        combo2.addItem("CAD");
        combo2.addItem("CHF");
        combo2.addItem("¥");
        combo2.addActionListener(event->{
           quoteCurrency =combo2.getItemAt(combo2.getSelectedIndex()).toString();
           //System.out.println(quoteCurrency);
           });

        panel5=new JPanel(new GridLayout(2,1));
        panel5.add(panel2);
        panel5.add(panel4);
        add(panel5,BorderLayout.CENTER);

        panel3=new JPanel(new GridLayout(3,3));
        panel3.add(new JLabel());
        panel3.add(new JLabel());
        panel3.add(new JLabel());
        panel3.add(new JLabel());
        panel3.add(button=new JButton("See exchange offerts!"));
        button.addActionListener(event-> {

                if((baseCurrency==""|| baseCurrency==null) || (quoteCurrency==""|| quoteCurrency==null)) {
                    JOptionPane.showMessageDialog(null,"Both currencies must be chosen!","Error Message 1",JOptionPane.ERROR_MESSAGE);

                }
                else{
                    if(baseCurrency==quoteCurrency){
                        JOptionPane.showMessageDialog(null,"You must choose two diffrent currencies!","Error Message 2",JOptionPane.ERROR_MESSAGE);

                    }
                    else{
                        offerts= new Offerts();
                        this.setVisible(false);
                        offerts.setVisible(true);

                    }
                }
            System.out.println("_____________");
            System.out.println(baseCurrency);
            System.out.println(quoteCurrency);
            System.out.println("-------------");

        });
        panel3.add(new JLabel());
        panel3.add(new JLabel());
        panel3.add(new JLabel());
        panel3.add(new JLabel());
        add(panel3,BorderLayout.SOUTH);


    }
}
