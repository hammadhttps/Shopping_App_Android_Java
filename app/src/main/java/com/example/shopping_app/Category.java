package com.example.shopping_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
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

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        icon = in.readString();
        productCount = in.readInt();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public int getProductCount() {
        return productCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeInt(productCount);
    }
}
