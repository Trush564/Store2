public class ClothingSize {
    //метод для обчислення розміру
    public static String calculateSize(Size size) {
        double chest = size.getChest();
        double waist = size.getWaist();
        double hips = size.getHips();

        if (chest <= 84 && waist <= 64 && hips <= 90) {
            return "XS";
        } else if (chest <= 88 && waist <= 68 && hips <= 94) {
            return "S";
        } else if (chest <= 92 && waist <= 72 && hips <= 98) {
            return "M";
        } else if (chest <= 96 && waist <= 76 && hips <= 102) {
            return "L";
        } else if (chest <= 100 && waist <= 80 && hips <= 106) {
            return "XL";
        } else {
            return "Розміру немає";
        }
    }
}

