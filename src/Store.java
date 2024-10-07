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
                System.out.println("3. Розмір");
                int changeChoice = scanner.nextInt();
                String currentType = clothingItem.getType();
                String currentColor = clothingItem.getColor();
                if (changeChoice == 1) {
                    clothingItem = chooseClothingItem(); // змінюємо тип
                    if (clothingItem != null) {
                        clothingItem = new ClothingItem(clothingItem.getType(), currentColor, clothingItem.getSize());
                    }
                } else if (changeChoice == 2) {
                    ClothingItem newItem = changeColor(clothingItem); // змінюємо колір
                    if (newItem != null) {
                        clothingItem = new ClothingItem(currentType, newItem.getColor(), clothingItem.getSize());
                    }
                } else if (changeChoice == 3) {
                    // Введення нових параметрів для розміру
                    System.out.print("Введіть обхват грудей (см): ");
                    double chest = scanner.nextDouble();
                    System.out.print("Введіть обхват талії (см): ");
                    double waist = scanner.nextDouble();
                    System.out.print("Введіть обхват стегон (см): ");
                    double hips = scanner.nextDouble();

                    Size newSize = new Size(chest, waist, hips);
                    String newSizeLabel = ClothingSize.calculateSize(newSize);
                    if (!newSizeLabel.equals("Розміру немає")) {
                        clothingItem = new ClothingItem(currentType, currentColor, newSizeLabel);
                    } else {
                        System.out.println("Перепрошуємо, вашого розміру в магазині немає.");
                    }
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

    public FormalClothing chooseFormal() {
        String type=null;
        String color=null;
        String sizeL=null;
        String occasion=null;
        String fabric =null;
        while (true){
            System.out.println("Оберіть тип формального одягу:");
            System.out.println("1.Костюм");
            System.out.println("2.Вечірня сукня");
            int clothingChoice=scanner.nextInt();
            type=(clothingChoice==1)?"Костюм":"Вечірня сукня";
            break;
        }
        while(true){
            System.out.println("Оберіть колір:");
            System.out.println("1.Чорний");
            System.out.println("2.Білий");
            System.out.println("3.Червоний");
            System.out.println("4.РОжевий");
            System.out.println("5.Срібний");
            System.out.println("6.Золотий");
            int colorChoice=scanner.nextInt();
            color=getColor(colorChoice);
            if(!color.equals("Невідомий колір")){
                break;
            }
            System.out.println("Вибачте вашого кольору немає");
        }
        System.out.print("Введіть обхват грудей (см): ");
        double chest = scanner.nextDouble();

        System.out.print("Введіть обхват талії (см): ");
        double waist = scanner.nextDouble();

        System.out.print("Введіть обхват стегон (см): ");
        double hips = scanner.nextDouble();
        Size size=new Size(chest,waist,hips);
        sizeL=ClothingSize.calculateSize(size);

        if (sizeL.equals("Розміру немає")) {
            System.out.println("Перепрошуємо, вашого розміру в магазині немає");
            return null;
        }
        System.out.print("Введіть випадок (наприклад: весілля,день народження і тд): ");
        occasion = scanner.next();
        System.out.print("Введіть тканину: ");
        fabric = scanner.next();
        return new FormalClothing(type,color,sizeL,occasion,fabric);
    }
    public boolean FormalPurchase(FormalClothing formalItem){
        while (true) {
            System.out.println("Ви хочете купити: " + formalItem);
            System.out.println("Все вірно?");
            System.out.println("1. Так");
            System.out.println("2. Ні");
            int confirmation=scanner.nextInt();
            if (confirmation==1) {
                System.out.println("Дякуємо за покупку!");
                return true;
            } else {
                System.out.println("Операцію скасовано");
                return false;
            }
        }
    }
}

