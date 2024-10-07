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
        //Цикл покупок
        while (shopping) {
            System.out.println("1.Купити повсякденний одяг");
            System.out.println("2. КУпити формальний одяг ");
            int choice=scanner.nextInt();
            if (choice == 1) {
                //Викликаємо метод для вибору товару
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
            }else if (choice==2){
                FormalClothing formalItem=store.chooseFormal();
                if(formalItem!=null){
                    boolean confirmed=store.confirmPurchase(formalItem);
                    if(confirmed){
                        System.out.println("Ваше замовлення:"+formalItem);
                        System.out.println(" Гарного дня!");
                    }else {
                        System.out.println("Операцію скасовано");
                    }
                }
            }

            //Чи хоче кор продовжити покупки
            System.out.println("Бажаєте ще щось купити?");
            System.out.println("1.Так");
            System.out.println("2.Ні");
            int continueShopping = scanner.nextInt();
            if(continueShopping!=1){
                //Якщо хоче завершити
                System.out.println("Гарного дня");
                shopping=false;
            }
        }
    }
}