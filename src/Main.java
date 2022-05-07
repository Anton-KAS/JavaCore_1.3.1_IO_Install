import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static StringBuilder logText = new StringBuilder();

    public static void main(String[] args) {
        String installDirPath = "D://Games";


        if (install(installDirPath)) {
            logText.append("Все файлы созданы успешно").append("\n");
        } else {
            logText.append("Не все файйлы успешно созданы").append("\n");
        }
        try (FileOutputStream fos = new FileOutputStream(installDirPath + "/temp/temp.txt")) {
            byte[] bytes = logText.toString().getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean install(String installDirPath) {
        String[] rootDirs = {"src", "res", "savegames", "temp"};
        String[] srcDirs = {"main", "test"};
        String[] mainFiles = {"Main.java", "Utils.java"};
        String[] resDirs = {"drawables", "vectors", "icons"};
        String[] tempFiles = {"temp.txt"};
        int countAll = 0;
        int countDone = 0;

        // Проверка существования пути назначения
        File installDir = new File(installDirPath);
        if (!installDir.isDirectory()) {
            logText.append("директория не существует:\t").append(installDirPath).append("\n");
        }

        // В папке Games создайте несколько директорий: src, res, savegames, temp.
        for (String dir : rootDirs) {
            countAll++;
            if (makeDir(installDirPath + "/" + dir)) {
                countDone++;
            }
        }

        // В каталоге src создайте две директории: main, test.
        for (String dir : srcDirs) {
            countAll++;
            if (makeDir(installDirPath + "/src/" + dir)) {
                countDone++;
            }
        }

        // В подкаталоге main создайте два файла: Main.java, Utils.java.
        for (String file : mainFiles) {
            countAll++;
            if (makeFile(installDirPath + "/src/main/" + file)) {
                countDone++;
            }
        }

        // В каталог res создайте три директории: drawables, vectors, icons.
        for (String dir : resDirs) {
            countAll++;
            if (makeDir(installDirPath + "/res/" + dir)) {
                countDone++;
            }
        }

        // В директории temp создайте файл temp.txt.
        for (String file : tempFiles) {
            countAll++;
            if (makeFile(installDirPath + "/temp/" + file)) {
                countDone++;
            }
        }

        return countDone == countAll;
    }

    public static boolean makeDir(String dirPath) {
        File myDir = new File(dirPath);
        if (myDir.exists()) {
            logText.append("Каталог уже существует:\t").append(dirPath).append("\n");
            return true;
        }
        if (myDir.mkdir()) {
            logText.append("Каталог успешно создан:\t").append(dirPath).append("\n");
            return true;
        } else {
            logText.append("Каталог не создан:\t").append(dirPath).append("\n");
            return false;
        }
    }

    public static boolean makeFile(String filePath) {
        File myFile = new File(filePath);
        if (myFile.exists()) {
            logText.append("Файл уже существует:\t").append(filePath).append("\n");
            return true;
        }
        try {
            if (myFile.createNewFile()) {
                logText.append("Файл успешно создан:\t").append(filePath).append("\n");
                return true;
            }
        } catch (IOException ex) {
            logText.append("Файл не создан:\t").append(filePath).append("\n");
            logText.append(ex.getMessage()).append("\n");
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

}
