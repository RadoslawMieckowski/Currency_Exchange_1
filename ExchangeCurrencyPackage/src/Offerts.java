import javax.swing.*;
import java.awt.*;
/**
*@author Radosław Mieckowski
*/


public class Offerts extends JFrame {
    private final int FRAME_WIDTH=600;
    private final int FRAME_HEIGHT=250;
    private Font font;
    private JPanel panel1;
    private JLabel title;
    private JPanel choisePanel;
    private JComboBox combo;
    private JButton button;
    private JPanel boxpanel;
    private Results results;
    private int chosenAction;
    private Mechanism mech;
    private static String[] columnNames={"Offer number","currency pair","Purchase","Sale","Spread(b.p.)","Margin(b.p.)"};
    private JTable table;

    public static String[] getColumnNames() {
        return columnNames;
    }

    public Offerts(){
        setLayout(new BorderLayout());
        setTitle("Currency Exchange");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenWidth=screenSize.width;
        int screenHeight=screenSize.height;
        this.setBounds(screenWidth/2-FRAME_WIDTH/2, screenHeight/2-FRAME_HEIGHT/2, FRAME_WIDTH, FRAME_HEIGHT);

        font= new Font("SansSerif",Font.BOLD+Font.ITALIC,16);

        panel1=new JPanel(new GridLayout(3,1));
        panel1.add(new JLabel());
        title = new JLabel("Currency Exchange",SwingConstants.CENTER);
        title.setFont(font);
        panel1.add(title);
        panel1.add(new JLabel());
        add(panel1,BorderLayout.NORTH);

        mech=new Mechanism();
        table=new JTable(Mechanism.getCells(),columnNames);
        table.setAutoCreateRowSorter(true);
        add(new JScrollPane(table),BorderLayout.CENTER);

        combo=new JComboBox<String>();
        combo.addItem("Sell " + ExchangeCurrency.getQuoteCurrency() + " for " + ExchangeCurrency.getBaseCurrency());
        combo.addItem("Buy " + ExchangeCurrency.getQuoteCurrency() + " for " + ExchangeCurrency.getBaseCurrency());
        combo.addItem("Sell " + ExchangeCurrency.getBaseCurrency() + " for " + ExchangeCurrency.getQuoteCurrency());
        combo.addItem("Buy " + ExchangeCurrency.getBaseCurrency() + " for "+ ExchangeCurrency.getQuoteCurrency());
        combo.addActionListener(event->{
            chosenAction = combo.getSelectedIndex();
            System.out.println(chosenAction);
        });

        button=new JButton("Find the best offer!");
        button.addActionListener(event-> {
                switch (chosenAction){
                    case 0:
                    case 3:
                        System.out.println(chosenAction);
                        mech.findTheBestOfferMin();
                        break;
                    case 1:
                    case 2:
                        System.out.println(chosenAction);
                        mech.findTheBestOfferMax();
                        break;
                    default:
                        combo.setSelectedItem(0);
                        System.out.println(chosenAction);
                        mech.findTheBestOfferMin();
                }

            results= new Results();
             this.setVisible(false);
             results.setVisible(true);
          });

        boxpanel=new JPanel(new GridLayout(2,1));

         choisePanel=new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
         choisePanel.add(combo);
         choisePanel.add(button);
         boxpanel.add(choisePanel);
         boxpanel.add(new JLabel());
         add(boxpanel,BorderLayout.SOUTH);
 }
}
