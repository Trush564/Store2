import java.util.Scanner;
public class Store {
    //Змінна класу
    private Scanner scanner;
    //Конструктор
    public Store(Scanner scanner) {
        this.scanner = scanner;
    }
    //Метод для вибору товару
    public ClothingItem chooseClothingItem() {
        String clothingType = null;
        String color = null;
        String sizeL = null;
        Size size = null; //Обєкт
        //Вибір типу одягу
        while (true) {
            System.out.println("Оберіть тип одягу:");
            System.out.println("1.Плаття");
            System.out.println("2.Футболка");
            System.out.println("3.Шорти");
            System.out.println("4.Штани");
            System.out.println("5.Спідниця");
            System.out.println("6.Світшот");
            System.out.println("7.Худі");
            int clothingChoice = scanner.nextInt();
            clothingType = getClothingType(clothingChoice);//отримуємо тип
            if (!clothingType.equals("Невідомий тип одягу")) {
                break;//якщо тип правильний то виходимо з циклу
            }
            System.out.println("Перепрошуємо, такого типу одягу немає");
        }
        //вибір кольору
        while (true) {
            System.out.println("Оберіть колір:");
            System.out.println("1.Чорний");
            System.out.println("2.Білий");
            System.out.println("3.Червоний");
            System.out.println("4.Коричневий");
            System.out.println("5.Синій");
            System.out.println("6.Сірий");
            int colorChoice = scanner.nextInt();
            color = getColor(colorChoice);//отримуємо колір
            if (!color.equals("Невідомий колір")) {
                break;//якщо колір правильний то виходимо
            }
            System.out.println("Перепрошуємо, вашого кольору немає");
        }
        //введення параметрів
        System.out.print("Введіть обхват грудей (см): ");
        double chest = scanner.nextDouble();

        System.out.print("Введіть обхват талії (см): ");
        double waist = scanner.nextDouble();

        System.out.print("Введіть обхват стегон (см): ");
        double hips = scanner.nextDouble();

        size = new Size(chest, waist, hips); //створюємо обєкт
        sizeL = ClothingSize.calculateSize(size); //обчислюємо розмір
        if (sizeL.equals("Розміру немає")) {
            System.out.println("Перепрошуємо, вашого розміру в магазині немає");
            return null;
        }

        size.printSize();
        return new ClothingItem(clothingType, color, sizeL);
    }
    //метод для підтвердження покупки
    public boolean confirmPurchase(ClothingItem clothingItem) {
        while (true) {
            System.out.println("Ви хочете купити: " + clothingItem);
            System.out.println("Все вірно?");
            System.out.println("1.Так");
            System.out.println("2.Ні");
            int confirmation = scanner.nextInt();
            if (confirmation == 1) {
                System.out.println("Дякуємо за покупку!");
                return true;
            } else {
                System.out.println("Що бажаєте змінити");
                System.out.println("1. Тип одягу");
                System.out.println("2. Колір");
                int changeChoice = scanner.nextInt();
                if (changeChoice == 1) {
                    clothingItem = chooseClothingItem();//змінюємо тип
                } else if (changeChoice == 2) {
                    clothingItem = changeColor(clothingItem);//змінюємо колір
                }
                if(clothingItem==null){
                    System.out.println("Операцію скасовано, немає відповідного товару");
                    return false;
                }
            }
        }
    }
    //метод длдя зміни кольору
    public ClothingItem changeColor(ClothingItem clothingItem) {
        while (true) {
            System.out.println("Оберіть колір:");
            System.out.println("1.Чорний");
            System.out.println("2.Білий");
            System.out.println("3.Червоний");
            System.out.println("4.Коричневий");
            System.out.println("5.Синій");
            System.out.println("6.Сірий");
            int colorChoice = scanner.nextInt();
            String color = getColor(colorChoice);
            if (!color.equals("Невідомий колір")) {
                return new ClothingItem(clothingItem.getType(), color, clothingItem.getSize());
            }
            System.out.println("Перепрошуємо, вашого кольору немає");
        }
    }
    //метод для отримання типу
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
    //метод для отримання кольору
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

