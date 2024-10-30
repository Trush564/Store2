
import java.util.Scanner;
public class Recommendation {
    private Scanner scanner;
    public Recommendation(Scanner scanner){
        this.scanner=scanner;
    }
    public boolean askRecommendation(){
        System.out.println("Хочете отримати рекомендацію?");
        int recommendationChoice=scanner.nextInt();
        return recommendationChoice==1;
    }
    public void recommendClothing(boolean isFormal, int temperature){

        if(temperature>40 || temperature<-10){
            System.out.println("Рекомендуємо залишатись вдома ");
        }
        if(isFormal){
            if(temperature>25){
                System.out.println("Рекомендуємо придбати легку вечірню сукню або легкий костюм");
            } else if(temperature>10){
                System.out.println("Рекомендуємо придбати костюм або сукню");
            }else{
                System.out.println("Рекомендуємо придбати теплий костюм ");
            }
        }else{
            if(temperature>25){
                System.out.println("Рекомендуємо шорти і футболку");
            }else if(temperature>10){
                System.out.println("Рекомендуємо штани і світшот");
            }else{
                System.out.println("Рекомендуємо штани та худі");
            }
        }
    }
}
