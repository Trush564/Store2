public class ClothingItem implements Product{
    private String type;
    private String color;
    private String size;

    //конструктор
    public ClothingItem(String type, String color, String size) {
        this.type = type;
        this.color = color;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }
    //метод для виведення інфо про товар
    @Override
    public String getDescription() {
        return type + " кольору " + color + " розміру " + size;
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
