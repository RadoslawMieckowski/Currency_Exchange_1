import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Person  {
    private String name;
    private String surName;
    private int  age;

    public Person(String name, String surName, int age) {
        this.name = name;
        this.surName = surName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        Person os1;
        Person os2;
        Person os3;
        Person os4;
        Person os5;
        Person os6;
        Person os7;
        Person os8;
        Person os9;
        Person os10;
        Person os11;
        Person os12;

        Person[][] people =
                {
                        {os1=new Person("Radek","Mieckowski",23), os2=new Person("Mateusz","Mieckowski",23), os3=new Person("Marek","Mieckowski",63), os3=new Person("Konrad","Mieckowski",33)},
                        {os4=new Person("Tobichi","Origami",17),os5= new Person("Tohka","Yatogami",17), new Person("Murasame","Reine",34), os6=new Person("Kotori","Itsuka",14)},
                        {os7=new Person("Yoshino","Yoshinon",9), os8=new Person("Kurumi"," Tokisaki",17), new Person("Horo","Dzia!",1000), os9=new Person("Dio","Brando",120)},
                        {os10=new Person("Joseph","Joestar",81),os11= new Person("Giorno","Giovanna",16), os12=new Person("Jotaro","Kujo",28), new Person("Yukino","Yukinoshita",18)}
                };
        String[] columns={"Imię","Nazwisko","wiek"};

        EventQueue.invokeLater(()->
        {
            JFrame myFrame=new JFrame();
            myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            myFrame.setSize(500,500);
            myFrame.setVisible(true);
            JPanel panel=new JPanel();
            JTable table=new JTable(people,columns);
            panel.add(table);
            myFrame.add(panel);
        });


//        for (Person[]row:people){
//            for (Person value:row)
//            {
//                System.out.println(Arrays.deepToString(people));
//            }
//        }
//String[][]imiona={
//        {"Radek","Mateusz","Marek"},
//        {"Radek","Marek","Mateusz"},
//        {"Marek","Mateusz","Radek"},
//};
//        System.out.println(Arrays.deepToString(imiona));

    }
}

