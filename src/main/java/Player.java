import DataBase.DataBase;

import java.util.Scanner;

public class Player {
private DataBase db;
private  String player1;
private  String player2;
private Scanner scanner=new Scanner(System.in);
    private Field field;
    public Player(){
        db=new DataBase();
        field=new Field( 3,3);
        registration();
        field.show();
    }

public void registration(){ // Запись ников игрока

    System.out.println("First player`s nickname:");
    player1 =scanner.nextLine().trim();
    System.out.println("Second player`s nickname:");
    player2=scanner.nextLine().trim();

    db.checkedReg(player1,player2); // проверка, есть ли игрок с таким ником в базе

}
    public void play(){
        while (true) {
            System.out.println(player1);
            while (!field.setCross(scanner.next(), scanner.next())) { // проверка на коректность ввода первого игрока
                System.out.println("Invalid input\nTry again");
            }
            if (field.checkWinnerCross()) {
                System.out.println(player1 +" won");    //если выиграл игрок № 1
                db.endGame(player1,player2);
                break;
            }
            System.out.println(player2);
            while (!field.setCircle(scanner.next(), scanner.next())) { // проверка на коректность ввода второго игрока
                System.out.println("Invalid input\nTry again");
            }
            if (field.checkWinnerCircle()) {
                System.out.println(player2+" won");         //если выиграл игрок № 2
                db.endGame(player2, player1);
                break;
            }
        }

    }
}
