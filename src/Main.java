/* день недели = (день + код месяца + код года) % 7
  Пояснения
Код месяца

Коды месяца и года — это, пожалуй, самое трудное в формуле.

Код месяца нужно просто запомнить.

  1 — январь, октябрь;
  2 — май;
  3 — август;
  4 — февраль, март, ноябрь;
  5 — июнь;
  6 — декабрь, сентябрь;
  0 — апрель, июль.

Чтобы запоминать подобные нелогичные данные, проще всего прибегать к ассоциациям.
Код года

Код года в XXI веке рассчитывается по формуле:

код года = (6 + последние две цифры года + последние две цифры года / 4) % 7
  !! 2022 = (6 + 23 + 23/4) % 7

Оператор «/» означает неполное частное, то есть целую часть результата деления.

  2015 год: (6 + 15 + 15 / 4) % 7 = (6 + 15 + 3) % 7 = 24 % 7 = 4;
  2016 год: (6 + 16 + 16 / 4) % 7 = (6 + 16 + 4) % 7 = 26 % 7 = 5;
  2017 год: (6 + 17 + 17 / 4) % 7 = (6 + 17 + 4) % 7 = 27 % 7 = 6;
  2026 год: (6 + 26 + 26 / 4) % 7 = (6 + 26 + 6) % 7 = 38 % 7 = 3.

Старт отсчёта — выходные, то есть: 0 — суббота, 1 — воскресенье и так далее.
   */
public class Main {
    public static void main(String[] args) {
        int startX = firstDayOfWeekInMonth(1, 1) - 1;
        int startY = 0;

        int [][] calendar = new int[24][21];

        for (int month = 1; month <= 12; month++) {
            for (int j = 1; j <= daysInMonth(month); j++) {

                if (firstDayOfWeekInMonth(j, month) != 7){
                    calendar[startY][startX++] = j;
                }
                else {
                    calendar[startY++][startX] = j;
                    startX = startX - 6;
                }
            }
            switch (month) {
                case 1:
                    startY = 0;
                    startX = 6 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                case 2:
                    startY = 0;
                    startX = 13 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                case 3:
                    startY = 6;
                    startX = firstDayOfWeekInMonth(1, month + 1) - 1;
                    break;
                case 4:
                    startY = 6;
                    startX = 6 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                case 5:
                    startY = 6;
                    startX = 13 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                case 6:
                    startY = 12;
                    startX = firstDayOfWeekInMonth(1, month + 1) - 1;
                    break;
                case 7:
                    startY = 12;
                    startX = 6 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                case 8:
                    startY = 12;
                    startX = 13 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                case 9:
                    startY = 18;
                    startX = firstDayOfWeekInMonth(1, month + 1) - 1;
                    break;
                case 10:
                    startY = 18;
                    startX = 6 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                case 11:
                    startY = 18;
                    startX = 13 + firstDayOfWeekInMonth(1, month + 1);
                    break;
                default:
            }
        }
        System.out.println("      январь                 февраль                март");
        for (int i = 1; i <= 3; i++) {
            System.out.print("пн вт ср чт пт сб вс   ");
        }
        System.out.println();

        for (int i = 0; i <= 23; i++) {
            for (int j = 0; j <= 20; j++) {
                if (calendar[i][j] == 0)
                    System.out.print("   ");
                else if (calendar[i][j] < 10)
                    System.out.print(" " + calendar[i][j] + " ");
                else
                System.out.print(calendar[i][j] + " ");
                if ((j + 1) % 7 == 0)
                    System.out.print("  ");
            }
            System.out.println();
            switch (i) {
                case 6:
                    System.out.println("      апрель                 май                июнь");
                break;
                case 12:
                    System.out.println("      июль                 август                сентябрь");
                break;
                case 18:
                    System.out.println("      октябрь                 ноябрь                декабрь");
                break;
                default:
            }
            if ((i + 1) % 6 == 0) {
                for (int j = 1; j <= 3; j++) {
                    System.out.print("пн вт ср чт пт сб вс   ");
                }
                System.out.println();
            }

        }
    }

    public static int daysInMonth (int monthIn) {
        if (monthIn == 2)
            return 28;
        else if (monthIn == 4 || monthIn == 6 || monthIn == 9 || monthIn == 11) {
            return 30;
        }
        else
            return 31;
    }
    public static int firstDayOfWeekInMonth (int dataIn, int monthIn) {
        // день недели = (день + код месяца + код года) % 7
        int codeFromFormula = ( ( dataIn + codeMonth(monthIn) + (6 + 23 + 23/4) ) % 7);
        int codeDay = 0;
        switch (codeFromFormula) {
            case 0: codeDay = 6;
                break;
            case 1: codeDay = 7;
                break;
            case 2: codeDay = 1;
                break;
            case 3: codeDay = 2;
                break;
            case 4: codeDay = 3;
                break;
            case 5: codeDay = 4;
                break;
            case 6: codeDay = 5;
                break;
        }
        return codeDay;
    }
    public static int codeMonth (int month) {
        int code = 0;
        switch (month) {
            case 1: code = 1;
                break;
            case 2: code =  4;
                break;
            case 3: code =  4;
                break;
            case 4: code = 0;
                break;
            case 5: code = 2;
                break;
            case 6: code = 5;
                break;
            case 7: code =  0;
                break;
            case 8: code =  3;
                break;
            case 9: code = 6;
                break;
            case 10: code = 1;
                break;
            case 11: code = 4;
                break;
            case 12: code =  6;
                break;
        }
        return code;
    }

}