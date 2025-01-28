package com.example.Bill_Genretion_Sysyterm.Helper;

public class StockReport {
    private int pId;
    private String name;
    private int inventoryCount;
   public StockReport(){
    }

    public StockReport(int pId, String name, int inventoryCount) {
        this.pId = pId;
        this.name = name;
        this.inventoryCount = inventoryCount;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    @Override
    public String toString() {
       return pId+"\t"+name+"\t"+inventoryCount;
    }
}
