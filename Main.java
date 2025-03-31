import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner skan = new Scanner(System.in);
        String nazvaFaylu = "text.txt";
        boolean pratsyuye = true;
        int vibir = 0;
        int rezhim = 1;
        boolean dodaty = true;
        String ryadok = "";
        int nomer = 1;
        File fayl = new File(nazvaFaylu);
        FileWriter fw = null;
        Scanner fileSkan = null;

        while (pratsyuye) {
            System.out.println("\nМенюшка ༼ つ ◕_◕ ༽つ");
            System.out.println("1. Записати до файлу");
            System.out.println("2. Прочитати файл");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");

            try {
                vibir = Integer.parseInt(skan.nextLine());
            } catch (Exception e) {
                System.out.println("Помилка вводу!");
                continue;
            }

            if (vibir == 1) {
                System.out.println("\nЗапис до файлу:");
                System.out.println("1. Додати в кінець");
                System.out.println("2. Переписати файл");
                System.out.print("Оберіть (1/2): ");

                try {
                    rezhim = Integer.parseInt(skan.nextLine());
                } catch (Exception e) {
                    rezhim = 1;
                    System.out.println("Використовую додавання в кінець.");
                }

                dodaty = (rezhim == 1);

                System.out.println("Вводьте текст. Для завершення введіть 'СТОП':");

                try {
                    fw = new FileWriter(nazvaFaylu, dodaty);

                    while (true) {
                        ryadok = skan.nextLine();
                        if (ryadok.equals("СТОП")) {
                            break;
                        }
                        fw.write(ryadok + "\n");
                    }

                    fw.close();
                    System.out.println("Записано!");

                } catch (Exception e) {
                    System.out.println("Помилка запису: " + e.getMessage());
                }
            }
            else if (vibir == 2) {
                // Читання файлу
                System.out.println("\nЗміст файлу:");

                try {
                    fayl = new File(nazvaFaylu);
                    if (!fayl.exists()) {
                        System.out.println("Файл ще не створено!");
                        continue;
                    }

                    fileSkan = new Scanner(fayl);
                    nomer = 1;

                    while (fileSkan.hasNextLine()) {
                        ryadok = fileSkan.nextLine();
                        System.out.println(nomer + ": " + ryadok);
                        nomer++;
                    }

                    fileSkan.close();

                    if (nomer == 1) {
                        System.out.println("Файл порожній!");
                    }

                } catch (Exception e) {
                    System.out.println("Помилка читання: " + e.getMessage());
                }
            }
            else if (vibir == 3) {
                System.out.println("До побачення!");
                pratsyuye = false;
            }
            else {
                System.out.println("Невірний вибір!");
            }
        }

        skan.close();
    }
}