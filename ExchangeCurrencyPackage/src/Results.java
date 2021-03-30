import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
/**
*@author Radosław Mieckowski
*/
public class Results extends JFrame {

    private final int FRAME_WIDTH=600;
    private final int FRAME_HEIGHT=250;
    private JPanel panel1;
    private JLabel title;
    private Font font;
    private JButton button1;
    private JButton exitButton;
    private JPanel panel2;
    private JPanel boxpanel;
    private ExchangeCurrency ec;
    private JTable table;
    private TableModel model;
    private static TableRowSorter<TableModel> sorter;

    public static TableRowSorter<TableModel> getSorter() {
        return sorter;
    }

    public Results(){
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

        panel1=new JPanel(new GridLayout(3,1));
        panel1.add(new JLabel());
        title = new JLabel("Currency Exchange",SwingConstants.CENTER);
        title.setFont(font);
        panel1.add(title);
        panel1.add(new JLabel());
        add(panel1,BorderLayout.NORTH);

        table=new JTable(Mechanism.getCells(),Offerts.getColumnNames());
        model=table.getModel();
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        Mechanism.filter();
        add(new JScrollPane(table),BorderLayout.CENTER);

        panel2=new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
        panel2.add(button1=new JButton("Again!"));
        button1.addActionListener(event-> {
            ec=new ExchangeCurrency();
            this.setVisible(false);
            ec.setVisible(true);
            ExchangeCurrency.setBaseCurrency("");
            ExchangeCurrency.setQuoteCurrency("");
        });
        panel2.add(exitButton=new JButton("Exit."));
        exitButton.addActionListener(event->System.exit(0));
        boxpanel =new JPanel();
        boxpanel.add(new JLabel());
        boxpanel.add(panel2);
        add(boxpanel,BorderLayout.SOUTH);
    }
}
