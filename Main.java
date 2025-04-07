import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner skan = new Scanner(System.in);
        String nazvaFaylu = "text.txt";
        boolean pratsyuye = true;
        while (pratsyuye) {
            System.out.println("Менюшка ༼ つ ◕_◕ ༽つ");
            System.out.println("1. Записати до файлу");
            System.out.println("2. Прочитати файл");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");
            int vibir = 0;
            try {
                vibir = Integer.parseInt(skan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Помилка вводу!");
                continue;
            }
            switch (vibir) {
                case 1:
                    zapysFile(nazvaFaylu, skan);
                    break;
                case 2:
                    procFile(nazvaFaylu);
                    break;
                case 3:
                    System.out.println("До побачення!");
                    pratsyuye = false;
                    break;
                default:
                    System.out.println("Невірний вибір!");
            }
        }
        skan.close();
    }
    private static void zapysFile(String nazvaFaylu, Scanner skan) {
        System.out.println("\nЗапис до файлу:");
        System.out.println("1. Додати в кінець");
        System.out.println("2. Переписати файл");
        System.out.print("Оберіть (1/2): ");
        int rezhym = 1;
        try {
            rezhym = Integer.parseInt(skan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Використовую додавання в кінець.");
        }
        boolean dodaty = (rezhym == 1);
        System.out.println("Вводьте текст. Для завершення введіть 'СТОП':");
        
        FileWriter fw = null;
        try {
            fw = new FileWriter(nazvaFaylu, dodaty);
            while (true) {
                String ryadok = skan.nextLine();
                if (ryadok.equals("СТОП")) {
                    break;
                }
                fw.write(ryadok + "\n");
            }
            System.out.println("Записано!");
        } catch (Exception e) {
            System.out.println("Помилка запису: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                System.out.println("Помилка закриття файлу: " + e.getMessage());
            }
        }
    }
    private static void procFile(String nazvaFaylu) {
        System.out.println("\nЗміст файлу:");
        FileReader fr = null;
        Scanner fileSkan = null;
        FileReader tempReader = null;
        
        try {
            fr = new FileReader(nazvaFaylu);
            fileSkan = new Scanner(fr);
            
            char[] buffer = new char[1024];
            int kilkist = 0;
            tempReader = new FileReader(nazvaFaylu);
            
            int charsRead;
            while ((charsRead = tempReader.read(buffer)) != -1) {
                for (int i = 0; i < charsRead; i++) {
                    if (buffer[i] == '\n') {
                        kilkist++;
                    }
                }
            }
            
            int nomer = 1;
            while (fileSkan.hasNextLine()) {
                String ryadok = fileSkan.nextLine();
                System.out.println(nomer + ": " + ryadok);
                nomer++;
            }
            
            if (nomer == 1) {
                System.out.println("Файл порожній!");
            } else {
                System.out.println("Загальна кількість рядків: " + kilkist);
            }
        } catch (Exception e) {
            System.out.println("Помилка читання: " + e.getMessage());
        } finally {
            try {
                if (tempReader != null) {
                    tempReader.close();
                }
                if (fileSkan != null) {
                    fileSkan.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                System.out.println("Помилка закриття файлу: " + e.getMessage());
            }
        }
    }
}
