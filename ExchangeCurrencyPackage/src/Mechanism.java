import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
*@author Radosław Mieckowski
*/

public class Mechanism {

    private double purchase;
    private double sale;
    private int spread;
    private int margin;
    private static int minSpread;
    private static double minSale;
    private static double maxPurchase;
    private LinkedList<Integer> offertsNrs;
    private LinkedList<String>pairs;
    private LinkedList<Double>purchases;
    private LinkedList<Double>sales;
    private LinkedList<Integer>spreads;
    private LinkedList<Integer>margins;
    private LinkedList<LinkedList<? extends Object>> lists;
    private LinkedList<Integer>candidates;
    private static Object[][]cells;

    public static Object[][] getCells() {
        return cells;
    }

    public Mechanism() {
        try {
            offertsNrs=new LinkedList<>();
            pairs=new LinkedList<>();
            purchases=new LinkedList<>();
            sales=new LinkedList<>();
            spreads=new LinkedList<>();
            margins=new LinkedList<>();
            lists= new LinkedList<>();

            this.readAndConstruct();
            this.makeATable(lists);
            this.findMinSpread(spreads);

        } catch (IOException |NullPointerException |IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

//    public static void main (String[] args) {
//
//        Mechanism mech=new Mechanism();
//        mech.findTheBestOfferMin();
//        mech.findTheBestOfferMax();
//}
    public void readAndConstruct()throws IOException {
        Scanner scanner=new Scanner(new File("src\\ExchangeRates.txt"));
        scanner.useLocale(Locale.US);
        int i=0;
        while(scanner.hasNext()){
            purchase =scanner.nextDouble();
            sale =scanner.nextDouble();
            spread=(int)Math.round((sale - purchase)*10000);
            margin=scanner.nextInt();
            i++;
            pairs.add(ExchangeCurrency.getBaseCurrency()+"-"+ExchangeCurrency.getQuoteCurrency());
            purchases.add(purchase);
            sales.add(sale);
            spreads.add(spread);
            margins.add(margin);
        }

        for (int j=0;j<i;j++){
            offertsNrs.add(j+1);
        }
        lists.add(offertsNrs);
        lists.add(pairs);
        lists.add(purchases);
        lists.add(sales);
        lists.add(spreads);
        lists.add(margins);
/**Tests
        System.out.println(offertsNrs);
        System.out.println(pairs);
        System.out.println(purchases);
        System.out.println(sales);
        System.out.println(spreads);
        System.out.println(margins);
        System.out.println(lists);
        System.out.println(lists.get(2).get(1));
        System.out.println(lists.size());
        System.out.println(lists.get(0).size());
        scanner.close();
 */
    }
    public void makeATable(@NotNull LinkedList <LinkedList<? extends Object>>lists){
        cells =new Object[lists.get(0).size()][6];
        for(int i=0;i<lists.get(0).size();i++){
            for(int j=0;j<6;j++){
                cells[i][j]=lists.get(j).get(i);
            }
        }
        System.out.println(Arrays.deepToString(cells));
    }
    public void findMinSpread(@NotNull LinkedList<Integer>spreads){
        minSpread=spreads.get(0);
        for(int i=1;i<spreads.size();i++){
            if(minSpread>spreads.get(i))
                minSpread=spreads.get(i);
        }
        System.out.println(minSpread);
    }
    public void findTheBestOfferMin(){
        candidates=new LinkedList<>();
        boolean flag=true;
        for(int i=0;i<spreads.size();i++){
            if(spreads.get(i)==minSpread){
                candidates.add(i);
            }
        }
        System.out.println(candidates);
        if (candidates.size()==1) {
            System.out.println("filtruj po najlepszym spreadzie: "+minSpread);
            System.out.println("minSale: "+minSale);

        }
        else{
            for(int i=0;i<candidates.size();i++)
            {
                if(i!=candidates.get(i)) continue;
                else{
                    if(flag==true){
                        minSale=sales.get(i);
                        flag=false;
                    }
                    if((i+1)<candidates.size()){
                        if(minSale>sales.get(i+1)) {
                            minSale=sales.get(i+1);
                        }
                    }
                }
            }
            System.out.println("filtruj po najlepszym kursie "+ minSale);
        }
    }
    public void findTheBestOfferMax(){
        candidates=new LinkedList<>();
        boolean flag=true;
        for(int i=0;i<spreads.size();i++){
            if(spreads.get(i)==minSpread){
                candidates.add(i);
            }
        }
        System.out.println(candidates);
        if (candidates.size()==1) {
            System.out.println("filtruj po najlepszym spreadzie: "+minSpread);
            System.out.println("maxpurchase: "+maxPurchase);
        }
        else{
            for(int i=0;i<candidates.size();i++)
            {
                if(i!=candidates.get(i)) continue;
                else{
                    if(flag==true){
                        maxPurchase=purchases.get(i);
                        flag=false;
                    }
                    if((i+1)<candidates.size()){
                        if(maxPurchase<purchases.get(i+1)) {
                            maxPurchase=purchases.get(i+1);
                        }
                    }
                }
            }
            System.out.println("filtruj po najlepszym kursie "+ maxPurchase);
        }
    }
    public static void filter(){
        if(minSale==0.0 && maxPurchase==0.0){
            Results.getSorter().setRowFilter(
                    RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,minSpread,4));
        }
        else{
            if(minSale==0){
                Results.getSorter().setRowFilter(
                        RowFilter.andFilter(Arrays.asList(
                                RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,minSpread,4),
                                RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,maxPurchase,2))));
            }
            else {
                Results.getSorter().setRowFilter(
                        RowFilter.andFilter(Arrays.asList(
                                RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,minSpread,4),
                                RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,minSale,3))));
            }
        }

    }
}
