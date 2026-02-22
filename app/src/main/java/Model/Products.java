package Model;

public class Products {



        private int id;
        private String name;
        private double price;
        private String description;
        private String imageUrl;
        private int categoryId;

        public Products(int id, String name, double price, String description,
                       String imageUrl, int categoryId) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
            this.imageUrl = imageUrl;
            this.categoryId = categoryId;
        }

        // Getters
        public int getId() { return id; }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public String getDescription() { return description; }
        public String getImageUrl() { return imageUrl; }
        public int getCategoryId() { return categoryId; }
}
