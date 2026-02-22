package Model;

public class Category {
    private int id;
    private String name;
    private String icon;
    private int productCount;

    public Category(int id, String name, String icon, int productCount) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.productCount = productCount;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getIcon() { return icon; }
    public int getProductCount() { return productCount; }

}
