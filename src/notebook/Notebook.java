package Tasks.S2.notebook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Notebook {
    private final List<Note> notes = new ArrayList<>();
    private final File file = new File("C:\\Users\\ххх\\IdeaProjects\\Repeat\\src\\Tasks\\S2\\resources\\files.txt");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void getNote(){
        try {
            Scanner noteScanner = new Scanner(file);
            noteScanner.useDelimiter("\\.\\s{2,}");
            while (noteScanner.hasNext()) {
                Scanner sc = noteScanner.useDelimiter("\\r?\\n+");
                while (sc.hasNext()) {
                    notes.add(new Note(sc.next(), LocalDateTime.parse(sc.next(), formatter), sc.next(), sc.next()));
                    sc.skip("\\r?\\n*");
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNote(){
        System.out.println("Введите тему заметки:");
        String topic = CheckInput.checkInputLine();

        System.out.println("Введите адрес email заметки:");
        String email = CheckInput.checkInputEmail();

        System.out.println("Введите сообщение заметки:");
        String message = CheckInput.checkMessage();

        System.out.println("Введите дату и время заметки:");
        LocalDateTime localDateTime = CheckInput.checkInputDateTime();

        notes.add(new Note(topic, localDateTime, email, message));
        writeToFile();
    }

    private void writeToFile(){
        try(FileWriter writer = new FileWriter(file)){
            for (int i = 0; i < notes.size(); i++) {
                writer.write(notes.get(i).getTopic() + "\n" + notes.get(i).getLocalDateTime().format(formatter) + "\n" + notes.get(i).getEmail()
                            + "\n" + notes.get(i).getMessage() + "\n\n");
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void print(){
        notes.forEach(System.out::println);
    }

    public void deleteNote(String deleteTopic){
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getTopic().equalsIgnoreCase(deleteTopic)){
                notes.remove(i);
            }
        }
        writeToFile();
    }

    public void searchByTopic(String searchTopic){
        List<Note> findList = new ArrayList<>();
        Pattern pattern = Pattern.compile("^" + searchTopic + "$", Pattern.CASE_INSENSITIVE);
        for (Note compareNote: notes){
            Matcher matcher = pattern.matcher(compareNote.getTopic());
            while (matcher.find()){
                findList.add(compareNote);
            }
        }
        if (findList.isEmpty()) System.out.println("Ничего не найдено.");
        else findList.forEach(System.out::println);
    }

    public void searchByMail(){
        String searchMail = CheckInput.checkInputEmail();
        List<Note> findList = new ArrayList<>();
        Pattern pattern = Pattern.compile("^" + searchMail + "$", Pattern.CASE_INSENSITIVE);
        for (Note compareNote: notes){
            Matcher matcher = pattern.matcher(compareNote.getEmail());
            while (matcher.find()){
                findList.add(compareNote);
            }
        }
        if (findList.isEmpty()) System.out.println("Ничего не найдено.");
        else findList.forEach(System.out::println);
    }

    public void searchByMessage(String searchMessage){
        List<Note> findList = notes.stream().filter(note -> note.getMessage().contains(searchMessage)).collect(Collectors.toList());
        if (findList.isEmpty()) System.out.println("Ничего не найдено.");
        else findList.forEach(System.out::println);
    }

    public void searchByDate(){
        System.out.println("Введите дату и время заметки:");
        LocalDateTime localDateTime = CheckInput.checkInputDateTime();
        for (Note note: notes){
            if (localDateTime.equals(note.getLocalDateTime())){
                System.out.println(note);
            }
        }
    }

    public void sortNote(){
        System.out.println("Сортировка заметок:\n1 - Сортировка по дате;\n2 - Сортировка по теме.");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1->{
                Comparator<Note> comparator = Comparator.comparing(Note::getLocalDateTime).thenComparing(Note::getTopic);
                notes.sort(comparator);
                notes.forEach(System.out::println);
            }
            case 2 -> {
                Comparator<Note> comparator = Comparator.comparing(Note::getTopic).thenComparing(Note::getLocalDateTime);
                notes.sort(comparator);
                notes.forEach(System.out::println);
            }
        }
    }
}


