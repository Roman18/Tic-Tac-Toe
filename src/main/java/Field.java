import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Field {
    private final String[][] table;
    private final int row;
    private final int column;
    private List<String> listChecked = new ArrayList<>(); // хранение всех мест которые заняты либо Х или О

    public Field(int row, int column) {
        this.row = row;
        this.column = column;

        table = new String[this.row][this.column];
        create();
    }

    private void create() {
        for (int i = 0; i < table.length; i++) {
            Arrays.fill(table[i], " . ");
        }
    }

    public void show() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }

    public boolean setCross(String row, String column) {
        if (!row.matches("[\\d]") || !column.matches("[\\d]")) {
            return false;
        }
        int Row = Integer.parseInt(row) - 1; // что бы пользователь мог вводить привычные данные (1-3)
        int Column = Integer.parseInt(column) - 1; // что бы пользователь мог вводить привычные данные (1-3)
        if (Row < 0 || Row>=this.row || Column < 0  || Column>=this.column) {
            return false;
        }
        for (int i = 0; i < listChecked.size(); i++) {
                if (listChecked.get(i).equals(row + "|" + column)) {
return false;
            }
        }
        listChecked.add(row + "|" + column);
        table[Row][Column] = Items.getCROSS();
        show();
        return true;
    }

    public boolean setCircle(String row, String column) {
        if (!row.matches("[\\d]") || !column.matches("[\\d]")) {
            return false;
        }
        int Row = Integer.parseInt(row) - 1;
        int Column = Integer.parseInt(column) - 1;
        if (Row < 0 || Row>=this.row || Column < 0  || Column>=this.column) {
            return false;
        }
        for (int i = 0; i < listChecked.size(); i++) {
                if (listChecked.get(i).equals(row + "|" + column)) {
                    return false;
            }
        }
        listChecked.add(row + "|" + column);
        table[Row][Column] = Items.getCIRCLE();
        show();
        return true;
    }



    public boolean checkWinnerCross(){   //проверка на победителя Х игрока
        if (table[0][0].equals(" X ")&&table[1][1].equals(" X ")&&table[2][2].equals(" X ")){
            return true;
        }else if (table[0][2].equals(" X ")&&table[1][1].equals(" X ")&&table[2][0].equals(" X ")){
            return true;
        }
        if (table[0][0].equals(" X ")){
            if ((table[1][0].equals(" X ")&&table[2][0].equals(" X "))||(table[0][1].equals(" X ")&&table[0][2].equals(" X "))){
                return true;
            }
        }else if (table[1][1].equals(" X ")){
            if ((table[0][1].equals(" X ")&&table[2][1].equals(" X "))||(table[1][0].equals(" X ")&&table[1][2].equals(" X "))){
                return true;
            }
        }else if (table[2][2].equals(" X ")){
            if ((table[0][2].equals(" X ")&&table[1][2].equals(" X "))||(table[2][0].equals(" X ")&&table[2][1].equals(" X "))){
                return true;
            }
        }



        return false;
    }



    public boolean checkWinnerCircle(){  //проверка на победителя О игрока
        if (table[0][0].equals(" O ")&&table[1][1].equals(" O ")&&table[2][2].equals(" O ")){
            return true;
        }else if (table[0][2].equals(" O ")&&table[1][1].equals(" O ")&&table[2][0].equals(" O ")){
            return true;
        }
        if (table[0][0].equals(" O ")){
            if ((table[1][0].equals(" O ")&&table[2][0].equals(" O "))||(table[0][1].equals(" O ")&&table[0][2].equals(" O "))){
                return true;
            }
        }else if (table[1][1].equals(" O ")){
            if ((table[0][1].equals(" O ")&&table[2][1].equals(" O "))||(table[1][0].equals(" O ")&&table[1][2].equals(" O "))){
                return true;
            }
        }else if (table[2][2].equals(" O ")){
            if ((table[0][2].equals(" O ")&&table[1][2].equals(" O "))||(table[2][0].equals(" O ")&&table[2][1].equals(" O "))){
                return true;
            }
        }



        return false;
    }
}
