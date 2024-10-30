public class Shoes implements Product{
    private String gender;
    private String season;
    private String type;
    private String color;
    private double size;

    public Shoes(String gender, String season, String type, String color, double size) {
        this.gender = gender;
        this.season = season;
        this.type = type;
        this.color = color;
        this.size = size;
    }
    public String getGender(){
        return gender;
    }

    public String getSeason() {
        return season;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public double getSize() {
        return size;
    }
    @Override
    public String getDescription(){
        return "Взуття: " + type + " для " + gender + ", сезон: " + season + ", колір: " + color + ", розмір: " + size;
    }
    @Override
    public boolean confirmPurchase() {
        System.out.println("Ви хочете купити: " + getDescription());
        return true;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
