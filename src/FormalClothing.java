import java.util.Objects;
public class FormalClothing extends ClothingItem {
    String occasion; //яка подія
    String fabric; //тканина

    public FormalClothing(String type, String color, String size, String occasion, String fabric) {
        super(type, color, size);
        this.occasion = occasion;
        this.fabric = fabric;
    }

    @Override
    public String toString() {
        return super.toString()+", випадок:"+occasion+", тканина:"+ fabric;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null) return false;
        if(!super.equals(obj)) return false;
        FormalClothing that=(FormalClothing) obj;
        return occasion.equals(that.occasion)&& fabric.equals(that.fabric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),occasion,fabric);
    }
}
