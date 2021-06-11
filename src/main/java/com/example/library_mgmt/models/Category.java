package com.example.library_mgmt.models;

public class Category {
    private String category_name;
    private String shelfNo;
    private String floorNo;

    public Category(String category_name, String shelfNo, String floorNo) {
        this.category_name = category_name;
        this.shelfNo = shelfNo;
        this.floorNo = floorNo;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getShelfNo() {
        return shelfNo;
    }

    public void setShelfNo(String shelfNo) {
        this.shelfNo = shelfNo;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }


}
