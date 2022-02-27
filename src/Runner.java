package Tasks.S2;
/*
    Блокнот. Разработать консольное приложение, работающее с Заметками
в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
Общие пояснения к практическому заданию.

• В начале работы приложения данные должны считываться из файла, в конце работы – сохраняться в файл.
• У пользователя должна быть возможность найти запись по любому параметру или по группе параметров (группу параметров можно определить
самостоятельно), получить требуемые записи в отсортированном виде, найти записи, текстовое поле которой содержит определенное слово, а также добавить новую запись.
• Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных выражений.
• Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно добавляющий информацию.
 */
import Tasks.S2.notebook.Notebook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Notebook notebook = new Notebook();

        {
            notebook.getNote();
        }

        Scanner scanner = new Scanner(System.in);
        int choose = 1;

        while (choose != 0){
            try{
                System.out.println("1 - Печать всех заметок;\n2 - Добавить заметку;\n3 - Удалить заметку;\n4 - Отсортировать блокнот;\n" +
                        "5 - Найти заметку по теме;\n6 - Найти заметку по email;\n7 - Найти заметку по дате;" +
                        "\n8 - Найти заметку по сообщению;\n0 - Завершить работу.");
                choose = scanner.nextInt();
                switch (choose){
                    case 1 -> notebook.print();
                    case 2 -> notebook.addNote();
                    case 3 -> {
                        System.out.println("Введите тему для удаления заметки:");
                        notebook.deleteNote(scanner.next());
                    }
                    case 4 -> notebook.sortNote();

                    case 5 -> {
                        System.out.println("Введите тему для поиска заметки:");
                        notebook.searchByTopic(scanner.next());
                    }
                    case 6 -> {
                        System.out.println("Введите email для поиска заметки:");
                        notebook.searchByMail();
                    }
                    case 7 -> notebook.searchByDate();

                    case 8 -> {
                        System.out.println("Введите часть сообщения / сообщение для поиска заметки:");
                        notebook.searchByMessage(new Scanner(System.in).nextLine());
                    }
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Неверный ввод, повторите попытку.");
                scanner.next();
            }
        }
    }
}
