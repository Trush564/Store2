public class ClothingItem {
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
    public String toString() {
        return type + " кольору " + color + " розміру " + size;
    }
}
