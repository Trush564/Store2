import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    //Змінна класу
    private Scanner scanner;
    private Recommendation recommendation;
    private List<Product> cart=new ArrayList<>();

    //Конструктор
    public Store(Scanner scanner) {
        this.scanner = scanner;
        this.recommendation = new Recommendation(scanner);
    }

    public void addClothing() {
        Product item = chooseClothingItem();
        if (item != null) {
            cart.add(item);
            item.confirmPurchase();
        }
    }

    public void addShoes() {
        Shoes item = chooseShoes();
        if (item != null) {
            cart.add(item);
            item.confirmPurchase();
        }
    }

    public void addAccessory() {
        Accessory item = chooseAccessory();
        if (item != null) {
            cart.add(item);
            item.confirmPurchase();
        }
    }

    public void showCart() throws  Cart {
        System.out.println("Ваше замовлення:");
        for (Product item : cart) {
            System.out.println(item.getDescription());
        }
        if (cart.isEmpty()){
            throw new Cart ("Кошик порожній");
        }
    }

    public void editProduct(Product product) {
        cart.remove(product);
        if (product instanceof Shoes) {
            addShoes();
        } else if (product instanceof Accessory) {
            addAccessory();
        } else if (product instanceof FormalClothing) {
            addClothing();
        } else {
            addClothing();
        }
    }
    public ClothingItem chooseClothingItemRecommendation(boolean isFormal){
        boolean wantRecommendation=recommendation.askRecommendation();
        if (wantRecommendation){
            System.out.println("Введіть температуру (°C):");
            int temperature = scanner.nextInt();
            recommendation.recommendClothing(isFormal, temperature);
        }

        if(isFormal){
            return chooseFormal();
        } else{
            return chooseClothingItem();
        }
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

    //  метод для перегляду доступних товарів
    public void displayAvailableProducts() {
        System.out.println("Доступні товари:");
        System.out.println("1. Одяг");
        System.out.println("2. Взуття");
        System.out.println("3. Аксесуари");
        System.out.println("4. Вихід");
    }

    // Метод для отримання товару
    public void handleProductSelection() {
        while (true) {
            displayAvailableProducts();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    chooseClothingItem();
                    break;
                case 2:
                    addShoes();
                    break;
                case 3:
                    addAccessory();
                    break;
                case 4:
                    System.out.println("Вихід ");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        }
    }
    public  List<Product>getCart(){
        return cart;
    }

    // Метод для відображення інформації про товар
    public void displayProductInfo(Product product) {
        System.out.println("Інформація про товар:");
        System.out.println(product.toString());
    }
    public Shoes chooseShoes() {
        System.out.println("Оберіть стать:");
        System.out.println("1. Чоловічий");
        System.out.println("2. Жіночий");
        int genderChoice = scanner.nextInt();
        String gender = (genderChoice == 1) ? "Чоловічий" : "Жіночий";

        System.out.println("Оберіть пору року:");
        System.out.println("1. Зима");
        System.out.println("2. Літо");
        System.out.println("3. Осінь/Весна");
        int seasonChoice = scanner.nextInt();
        String season = switch (seasonChoice) {
            case 1 -> "Зима";
            case 2 -> "Літо";
            case 3 -> "Осінь/Весна";
            default -> "Невідома пора року";
        };

        String type;
        if (genderChoice == 1) { // Чоловічий
            type = chooseMenShoes(season);
        } else { // Жіночий
            type = chooseWomenShoes(season);
        }

        System.out.println("Оберіть колір:");
        // Введіть логіку для вибору кольору
        System.out.println("1. Чорний");
        System.out.println("2. Коричневий");
        System.out.println("3. Сірий");
        System.out.println("4. Білий");
        int colorChoice = scanner.nextInt();
        String color = getColor(colorChoice);

        System.out.print("Введіть розмір (в см): ");
        double footSize = scanner.nextDouble();

        return new Shoes(gender, season, type, color, footSize);
    }

    private String chooseMenShoes(String season) {
        if (season.equals("Зима")) {
            System.out.println("Оберіть тип: 1. Чоботи 2. Черевики");
            int typeChoice = scanner.nextInt();
            return (typeChoice == 1) ? "Чоботи" : "Черевики";
        } else if (season.equals("Літо")) {
            System.out.println("Оберіть тип: 1. Кросівки 2. Сандалі");
            int typeChoice = scanner.nextInt();
            return (typeChoice == 1) ? "Кросівки" : "Сандалі";
        } else {
            System.out.println("Оберіть тип: 1. Кросівки 2. Черевики");
            int typeChoice = scanner.nextInt();
            return (typeChoice == 1) ? "Кросівки" : "Черевики";
        }
    }

    private String chooseWomenShoes(String season) {
        if (season.equals("Зима")) {
            System.out.println("Оберіть тип: 1. Чоботи 2. Ботильйони");
            int typeChoice = scanner.nextInt();
            return (typeChoice == 1) ? "Чоботи" : "Ботильйони";
        } else if (season.equals("Літо")) {
            System.out.println("Оберіть тип: 1. Кросівки 2. Босоніжки");
            int typeChoice = scanner.nextInt();
            return (typeChoice == 1) ? "Кросівки" : "Босоніжки";
        } else {
            System.out.println("Оберіть тип: 1. Кросівки 2. Черевики");
            int typeChoice = scanner.nextInt();
            return (typeChoice == 1) ? "Кросівки" : "Черевики";
        }
    }
    public Accessory chooseAccessory() {
        System.out.println("Оберіть стать:");
        System.out.println("1. Чоловічий");
        System.out.println("2. Жіночий");
        int genderChoice = scanner.nextInt();
        String gender = (genderChoice == 1) ? "Чоловічий" : "Жіночий";

        String accessoryType = "";
        if (genderChoice == 1) {
            System.out.println("Оберіть тип аксесуара:");
            System.out.println("1. Годинник");
            System.out.println("2. Ремінь");
            int typeChoice = scanner.nextInt();
            accessoryType = (typeChoice == 1) ? "Годинник" : "Ремінь";
        } else {
            System.out.println("Оберіть тип аксесуара:");
            System.out.println("1. Сумка");
            System.out.println("2. Кулон");
            int typeChoice = scanner.nextInt();
            accessoryType = (typeChoice == 1) ? "Сумка" : "Кулон";
        }

        // Вибір кольору
        System.out.println("Оберіть колір:");
        System.out.println("1. Чорний");
        System.out.println("2. Білий");
        System.out.println("3. Сірий");
        System.out.println("4. Коричневий");
        int colorChoice = scanner.nextInt();
        String color = getColor(colorChoice);

        // Створення нового аксесуару
        return new Accessory(gender, color, accessoryType);
    }


    private String getSeason(int choice) {
        switch (choice) {
            case 1: return "Зима";
            case 2: return "Літо";
            case 3: return "Весна";
            case 4: return "Осінь"; // Додано для повноти
            default: return "Невідома пора року";
        }
    }

    private String chooseShoeType(String gender, String season) {
        if (gender.equals("Чоловічий")) {
            if (season.equals("Зима")) {
                System.out.println("Оберіть тип: 1. Чоботи, 2. Черевики");
                int typeChoice = scanner.nextInt();
                return (typeChoice == 1) ? "Чоботи" : "Черевики";
            } else if (season.equals("Літо")) {
                System.out.println("Оберіть тип: 1. Кросівки, 2. Сандалі");
                int typeChoice = scanner.nextInt();
                return (typeChoice == 1) ? "Кросівки" : "Сандалі";
            }
        } else {
            if (season.equals("Зима")) {
                System.out.println("Оберіть тип: 1. Чоботи, 2. Ботильйони");
                int typeChoice = scanner.nextInt();
                return (typeChoice == 1) ? "Чоботи" : "Ботильйони";
            } else if (season.equals("Літо")) {
                System.out.println("Оберіть тип: 1. Кросівки, 2. Босоніжки");
                int typeChoice = scanner.nextInt();
                return (typeChoice == 1) ? "Кросівки" : "Босоніжки";
            }
        }
        return null;
    }

    private double calculateShoeSize(double footSize) {
        return Math.round(footSize * 1.5); // Множимо на 1.5
    }

}
