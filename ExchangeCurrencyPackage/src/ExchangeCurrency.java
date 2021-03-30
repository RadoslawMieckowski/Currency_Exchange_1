import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/**
*@author Radosław Mieckowski
*/


public class ExchangeCurrency  extends JFrame{
    private final int FRAME_WIDTH=600;
    private final int FRAME_HEIGHT=250;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel addOrRemoveCurrencyPanel;
    private JPanel westPanel;
    private JLabel title;
    private static JComboBox combo1;
    private static JComboBox combo2;
    private JButton button;
    private JButton addCurrencyButton;
    private JButton removeCurrencyButton;
    private JButton loadButton;
    private FlowLayout manager;
    private Font font;
    private Offerts offerts;
    private static String baseCurrency;
    private static String quoteCurrency;
    private JFileChooser chooser;
    private static File choosedFile;

    public static File getChoosedFile() {
        return choosedFile;
    }

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
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        this.setBounds(screenWidth/2-FRAME_WIDTH/2, screenHeight/2-FRAME_HEIGHT/2, FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

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
        readCurrencies(combo1);
        combo1.addActionListener(event->{
            baseCurrency = combo1.getItemAt(combo1.getSelectedIndex()).toString();
            System.out.println(baseCurrency);
            //JOptionPane.showMessageDialog(null,"Currency must be chosen!","Error Message 1",JOptionPane.ERROR_MESSAGE);
        });

        panel4.add(combo2=new JComboBox<String>());
        readCurrencies(combo2);
        combo2.addActionListener(event->{
            quoteCurrency =combo2.getItemAt(combo2.getSelectedIndex()).toString();
            System.out.println(quoteCurrency);
            //JOptionPane.showMessageDialog(null,"Currency must be chosen!","Error Message 1",JOptionPane.ERROR_MESSAGE);
        });

        panel5=new JPanel(new GridLayout(2,2,5,5));
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

                if((baseCurrency==null || baseCurrency.equals("")) || (quoteCurrency==null)||quoteCurrency.equals("")) {//wywołanie metody na wartości null jest błędem!
                    JOptionPane.showMessageDialog(null,"Currencies must be chosen!","Error Message 1",JOptionPane.ERROR_MESSAGE);
                }
                else if(baseCurrency.equals(quoteCurrency))
                    {
                        JOptionPane.showMessageDialog(null,"You must choose two diffrent currencies!","Error Message 2",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        offerts= new Offerts();
                        this.setVisible(false);
                        offerts.setVisible(true);
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

        addOrRemoveCurrencyPanel=new JPanel(new GridLayout(2,1,0,5));
        addOrRemoveCurrencyPanel.add(addCurrencyButton=new JButton("Add Currency."));
        addCurrencyButton.addActionListener(event->{
            addCurrencyToCombo(combo1,combo2);
        });
        //System.out.println(addCurrencyButton.getPreferredSize().toString());
        addOrRemoveCurrencyPanel.add(removeCurrencyButton=new JButton("Remove Currency."));
        removeCurrencyButton.setToolTipText("Set Currency to remove in both combo boxes in the first combo box");
        removeCurrencyButton.addActionListener(event->{
            removeCurrencyFromCombo(combo1,combo2);
        });
        add(addOrRemoveCurrencyPanel,BorderLayout.EAST);

        westPanel=new JPanel();
        westPanel.add(loadButton=new JButton("Load quotations"));
        choosedFile=new File("src/ExchangeRates.txt");
        loadButton.addActionListener(event->{
            chooser=new JFileChooser();
            chooser.setCurrentDirectory(new File("./src"));
            chooser.setSelectedFile(new File("src/ExchangeRates.txt"));
            chooser.setFileFilter(new FileNameExtensionFilter("Text files","txt","csv","rtf","doc"));//nie działa
            int result=chooser.showOpenDialog(this);
            choosedFile=chooser.getSelectedFile();
            String fileName=choosedFile.getPath();
            System.out.println(fileName);
        });
        add(westPanel,BorderLayout.WEST);
    }
    private static void readCurrencies(JComboBox<String> combo){
        String currencyOut;
        try (Scanner scanner=new Scanner(new File("src\\Currencies.txt")))
        {
            while(scanner.hasNext()){
                currencyOut= scanner.nextLine();
                combo.addItem(currencyOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addCurrencyToCombo(JComboBox<String>combo1, JComboBox<String>combo2){
            String newCurrency = "";
            newCurrency = JOptionPane.showInputDialog(null,"Type your currency to add in the Input field","Currency adding",JOptionPane.PLAIN_MESSAGE);
            System.out.println(newCurrency);
            if(newCurrency==null){
                JOptionPane.showMessageDialog(null,"Input field can't be empty!","Error message nr.3",JOptionPane.ERROR_MESSAGE);
            }
            else if(newCurrency.equals("")){
                JOptionPane.showMessageDialog(null,"Input field can't be empty!","Error message nr.3",JOptionPane.ERROR_MESSAGE);
            }
            else{
                combo1.addItem(newCurrency);
                combo2.addItem(newCurrency);
                try(PrintWriter out=new PrintWriter("src\\Currencies.txt", String.valueOf(StandardCharsets.UTF_8))){
                    String text="";
                    for(int i=0;i<combo2.getItemCount();i++){
                        text=text+combo2.getItemAt(i)+"\n";
                    }
                    out.print(text);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
         }
         private static void removeCurrencyFromCombo(JComboBox<String>combo1, JComboBox<String>combo2){
            int index = combo1.getSelectedIndex();
             System.out.println(index);
            if(index!=0){
                combo1.removeItemAt(index);
                combo2.removeItemAt(index);
                try(PrintWriter out=new PrintWriter("src\\Currencies.txt", String.valueOf(StandardCharsets.UTF_8))){
                    String text="";
                    for(int i=0;i<combo2.getItemCount();i++){
                        text=text+combo2.getItemAt(i)+"\n";
                    }
                    out.print(text);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            else
                JOptionPane.showMessageDialog(null,"This field can't be removed!","Error message nr.4",JOptionPane.ERROR_MESSAGE);

         }

}
