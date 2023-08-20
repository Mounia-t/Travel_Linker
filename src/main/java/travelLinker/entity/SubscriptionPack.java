package travelLinker.entity;

public enum SubscriptionPack {
    Essentiel(100), 
    Extra(200),    
    Premium(300);  

    private int price;

    SubscriptionPack(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
