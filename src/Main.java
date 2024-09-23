//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean shopping = true;

        while (shopping) {
            System.out.println("Оберіть тип одягу:");
            System.out.println("1.Плаття");
            System.out.println("2.Футболка");
            System.out.println("3.Шорти");
            System.out.println("4.Штани");
            System.out.println("5.Спідниця");
            System.out.println("6.Світшот");
            System.out.println("7.Худі");
            int clothingChoice = scanner.nextInt();
            String clothingType = getClothingType(clothingChoice);

            System.out.println("Оберіть колір:");
            System.out.println("1.Чорний");
            System.out.println("2.Білий");
            System.out.println("3.Червоний");
            System.out.println("4.Коричневий");
            System.out.println("5.Синій");
            System.out.println("6.Сірий");
            int colorChoice = scanner.nextInt();
            String color =getColor(colorChoice);

            System.out.print("Введіть обхват грудей (см): ");
            double chest = scanner.nextDouble();

            System.out.print("Введіть обхват талії (см): ");
            double waist = scanner.nextDouble();

            System.out.print("Введіть обхват стегон (см): ");
            double hips = scanner.nextDouble();

            Size size = new Size(chest, waist, hips);
            String sizeL = ClothingSize.calculateSize(size);

            ClothingItem clothingItem = new ClothingItem(clothingType, color, sizeL);

            System.out.println("Ви хочете купити: " + clothingItem);
            System.out.println("Все вірно?");
            System.out.println("1.Так");
            System.out.println("2.Ні");
            int a = scanner.nextInt();
            if (a == 1) {
                System.out.println("Дякуємо за покупку!");
            } else {
                System.out.println("Операція скасована. Дякуємо за відвідування нашого магазину!");
                shopping = false;
            }

            if (a == 1) {
                System.out.println("Чи бажаєте продовжити покупки?");
                System.out.println("1.Так");
                System.out.println("2.Ні");
                int b = scanner.nextInt();
                if (b != 1) {
                    shopping = false;
                    System.out.println("Дякуємо за покупки! Гарного дня!");
                }
            }
        }
    }
    public static String getClothingType(int choice) {
        switch (choice) {
            case 1:
                return "Плаття";
            case 2:
                return "Футболка";
            case 3:
                return "Шорти";
            case 4:
                return "Штани";
            case 5:
                return "Спідниця";
            case 6:
                return "Світшот";
            case 7:
                return "Худі";
            default:
                return "Невідомий тип одягу";
        }
    }
    public static String getColor(int choice) {
        switch (choice) {
            case 1:
                return "Чорний";
            case 2:
                return "Білий";
            case 3:
                return "Червоний";
            case 4:
                return "Коричневий";
            case 5:
                return "Синій";
            case 6:
                return "Сірий";
            default:
                return "Невідомий колір";
        }
    }
}