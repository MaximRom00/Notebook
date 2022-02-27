package Tasks.S2.notebook;

import java.time.LocalDateTime;
import java.util.Scanner;
// Класс проверяет входные данные на корректность
class CheckInput {
    private final static Scanner scanner = new Scanner(System.in);

    static String checkInputLine(){
        String inputLine = scanner.nextLine();
        while (!inputLine.matches(".{3,30}")){
            System.out.println("Ошибка, неверный ввод.");
            inputLine = scanner.nextLine();
        }
        return inputLine;
    }

    static String checkInputEmail(){
        String inputEmail = scanner.nextLine();
        while (!inputEmail.matches("^[\\w_-]{3,}@\\w{2,8}\\.\\w{2,6}$")){
            System.out.println("Ошибка, неверный ввод.");
            inputEmail = scanner.nextLine();
        }
     return inputEmail;
    }

    static LocalDateTime checkInputDateTime(){
        LocalDateTime localDateTime = null;
        System.out.println("1 - Текущая дата и время. \n2 - Указать дату и время.");
        switch (scanner.nextInt()){
            case 1 -> localDateTime = LocalDateTime.now();
            case 2 -> {

                System.out.println("Введите год:");
                int year = scanner.nextInt();
                while (!(year <= 2021 && year >= 2015)){
                    System.out.println("Неверный ввод. Год должен быть между 2015 и 2021");
                    year = scanner.nextInt();
                }

                System.out.println("Введите месяц:");
                int month = scanner.nextInt();
                while (!(month <= 12 && month >= 1)){
                    System.out.println("Неверный ввод. Месяц должен быть между 1 и 12");
                    month = scanner.nextInt();
                }

                System.out.println("Введите день недели:");
                int dayOfMonth = scanner.nextInt();
                while (!(dayOfMonth <= 31 && dayOfMonth >= 1)){
                    System.out.println("Неверный ввод. День должен быть между 1 и 31");
                    dayOfMonth = scanner.nextInt();
                }

                System.out.println("Введите час:");
                int hour = scanner.nextInt();
                while (!(hour < 24 && hour >= 0)){
                    System.out.println("Неверный ввод. Час должен быть между 0 и 24");
                    hour = scanner.nextInt();
                }

                System.out.println("Введите минуты:");
                int minute = scanner.nextInt();
                while (!(minute < 60 && minute >= 0)){
                    System.out.println("Неверный ввод. Минуты должны быть между 0 и 60");
                    minute = scanner.nextInt();
                }
                localDateTime = LocalDateTime.of(year,month,dayOfMonth,hour,minute);
            }
        }
        return localDateTime;
    }

    static String checkMessage(){
        String inputMessage = scanner.nextLine();
        while (!(inputMessage.matches(".{3,100}"))){
            System.out.println("Ошибка, неверный ввод.");
            inputMessage = scanner.nextLine();
        }
        return inputMessage;
    }
}
