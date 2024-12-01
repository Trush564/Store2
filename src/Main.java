//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //Обєкт класу
        Scanner scanner = new Scanner(System.in);
        //Змінна для циклу покупок
        boolean shopping = true;
        //Обєкт класу Store
        Store store = new Store(scanner);
        Recommendation recommendation = new Recommendation(scanner);
        //Цикл покупок
        while (shopping) {
            System.out.println("Що бажаєте придбати?");
            System.out.println("1.Повсякденний одяг");
            System.out.println("2.Формальний одяг");
            System.out.println("3.Взуття");
            System.out.println("4.Аксесуар");
            System.out.println("5.Показати кошик");
            System.out.println("6.Вийти");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    store.addClothing();
                    boolean wantRecommendation = false;
                    System.out.println("Хочете отримати рекомендацію?");
                    System.out.println("1. Так");
                    System.out.println("2. Ні");
                    int recommendationChoice = scanner.nextInt();
                    wantRecommendation = (recommendationChoice == 1);

                    int temperature = 0;
                    if (wantRecommendation) {
                        System.out.println("Введіть температуру (°C):");
                        temperature = scanner.nextInt();
                    }
                    if (wantRecommendation) {
                        recommendation.recommendClothing(false, temperature);
                    }

                    ClothingItem clothingItem = store.chooseClothingItem();
                    if (clothingItem != null) {
                        boolean confirmed = store.confirmPurchase(clothingItem);
                        if (confirmed) {
                            System.out.println("Ваше замовлення: " + clothingItem);
                            System.out.println("Гарного дня!");
                        } else {
                            System.out.println("Операцію скасовано");
                        }
                    }
                    break;

                case 2:
                    store.addClothing();
                    FormalClothing formalItem = store.chooseFormal();
                    if (formalItem != null) {
                        boolean confirmed = store.FormalPurchase(formalItem);
                        if (confirmed) {
                            System.out.println("Ваше замовлення: " + formalItem);
                            System.out.println("Гарного дня!");
                        } else {
                            System.out.println("Операцію скасовано");
                        }
                    }
                    break;

                case 3:
                    store.addShoes();
                    break;

                case 4:
                    store.addAccessory();
                    break;

                case 5:
                    try {
                        store.showCart();
                        System.out.println("Бажаєте змінити товар? (1. Так, 2. Ні)");
                        if (scanner.nextInt() == 1) {
                            System.out.print("Введіть номер товару для зміни: ");
                            int index = scanner.nextInt();
                            store.editProduct(store.getCart().get(index - 1));
                    }}catch(Cart e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    shopping = false;
                    System.out.println("Дякуємо за покупки! Повертайтеся ще!");
                    break;

                default:
                    System.out.println("Невірний вибір.");
            }
        }
        scanner.close();
    }
}