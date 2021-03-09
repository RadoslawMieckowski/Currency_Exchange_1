import java.awt.*;


public class Main {



public static void main(String[] args) {






        EventQueue.invokeLater(()->
        {
            ExchangeCurrency exchange = new ExchangeCurrency();
            exchange.setVisible(true);
        });




//        EventQueue.invokeLater(()->
//        {
//            Offerts offerts= new Offerts();
//            offerts.setVisible(false);
//        });



//        EventQueue.invokeLater(()->{
//            Results results = new Results();
//            results.setVisible(false);
//    });

    }
}
