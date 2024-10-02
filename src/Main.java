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
            //Викликаємо метод для вибору товару
            ClothingItem clothingItem=store.chooseClothingItem();
            if (clothingItem==null){
                System.out.println("Будь ласка, спробуйте обрати вибрати інший тип одягу");
                continue;//Повертаємо до початку циклу
            }
            //Підтвердження покупки
            boolean confirmed =store.confirmPurchase(clothingItem);

            if(confirmed){
                //Якщо підтверджена
                System.out.println("Ваше замовлення"+clothingItem);
                System.out.println(" Гарного дня!");
            }else{
                System.out.println("Операція скасована");
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