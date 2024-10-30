public class Accessory implements Product{
    private String gender;
    private String color;
    private String type;

    public Accessory(String gender, String color, String type) {
        this.gender = gender;
        this.color = color;
        this.type = type;
    }
    @Override
    public String getDescription() {
        return "Аксесуар: " + type + " кольору " + color + " для " + gender;
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
