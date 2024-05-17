
import java.io.File;
import java.util.Scanner;

public class Files {

    private static final long minFileSize = 1_048_576;

    public static void main(String[] args){

        int filesCount;

        System.out.println("Введите имя папки для поиска файлов:");
        final Scanner in = new Scanner(System.in);

        String directoryPath = in.nextLine(

        );

        final File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.out.println("Папка " + directoryPath + " не существует");
            return;
        }
        filesCount = listFilesFromDirectory(directory);

        if (filesCount == 0) {
            System.out.println("Файлов с длиной " + minFileSize + " байт и более не найдено");
        }
    }

    private static int listFilesFromDirectory(File directory){

        int filesCount = 0;

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    filesCount = filesCount + listFilesFromDirectory(file);
                } else {
                    if (file.length() >= minFileSize) {
                        filesCount++;
                        System.out.println(file.getAbsoluteFile() + " размер " + file.length());
                    }
                }
            }
        }
        return filesCount;
    }
}