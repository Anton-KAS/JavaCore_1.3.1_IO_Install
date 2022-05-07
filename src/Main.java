import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String installDirPath = "D://Games";

        if (install(installDirPath)) {
            System.out.println("Установка прошла успешно");
        } else {
            System.out.println("Установка не прошла");
        }
    }

    public static boolean install(String installDirPath) {
        String[] rootDirs = {"src", "res", "savegames", "temp"};
        String[] srcDirs = {"main", "test"};
        String[] mainFiles = {"Main.java", "Utils.java"};
        String[] resDirs = {"drawables", "vectors", "icons"};
        String[] tempFiles = {"temp.txt"};

        // Проверка существования пути назначения
        File installDir = new File(installDirPath);
        if (!installDir.isDirectory()) {
            System.out.println("директория не существует: " + installDirPath);
            return false;
        }

        // В папке Games создайте несколько директорий: src, res, savegames, temp.
        for (String dir : rootDirs) {
            if (!makeDir(installDirPath + "/" + dir)) {
                return false;
            }
        }

        // В каталоге src создайте две директории: main, test.
        for (String dir : srcDirs) {
            if (!makeDir(installDirPath + "/src/" + dir)) {
                return false;
            }
        }

        // В подкаталоге main создайте два файла: Main.java, Utils.java.
        for (String file : mainFiles) {
            if (!makeFile(installDirPath + "/src/main/" + file)) {
                return false;
            }
        }

        // В каталог res создайте три директории: drawables, vectors, icons.
        for (String dir : resDirs) {
            if (!makeDir(installDirPath + "/res/" + dir)) {
                return false;
            }
        }

        // В директории temp создайте файл temp.txt.
        for (String file : tempFiles) {
            if (!makeFile(installDirPath + "/temp/" + file)) {
                return false;
            }
        }

        return true;
    }

    public static boolean makeDir(String dirPath) {
        File myDir = new File(dirPath);
        if (myDir.exists()) {
            System.out.println("Каталог уже существует: " + dirPath);
            return true;
        }
        if (myDir.mkdir()) {
            System.out.println("Каталог создан: " + dirPath);
            return true;
        } else {
            System.out.println("Каталог не создан: " + dirPath);
            return false;
        }
    }

    public static boolean makeFile(String filePath) {
        File myFile = new File(filePath);
        if (myFile.exists()) {
            System.out.println("Файл уже существует: " + myFile);
            return true;
        }
        try {
            if (myFile.createNewFile()) {
                System.out.println("Файл создан: " + filePath);
                return true;
            }
        } catch (IOException ex) {
            System.out.println("Файл не создан: " + filePath);
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

}
