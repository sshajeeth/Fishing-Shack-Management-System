package Owner_page;

import javafx.beans.property.*;

public class Information_owner {
    private final StringProperty customerId;
    private final StringProperty customerName;
    private final StringProperty productId;
    private final StringProperty productName;
    private final StringProperty productQty;
    private final StringProperty totalPrice;


    public Information_owner(String customerId, String customerName, String productId, String productName, String productQty, String totalPrice){
        this.customerId=new SimpleStringProperty(customerId);
        this.customerName = new SimpleStringProperty(customerName);
        this.productId = new SimpleStringProperty(productId);
        this.productName = new SimpleStringProperty(productName);
        this.productQty = new SimpleStringProperty(productQty);
        this.totalPrice = new SimpleStringProperty(totalPrice) {
        };
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public StringProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getProductId() {
        return productId.get();
    }

    public StringProperty productIdProperty() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId.set(productId);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getProductQty() {
        return productQty.get();
    }

    public StringProperty productQtyProperty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty.set(productQty);
    }

    public String getTotalPrice() {
        return totalPrice.get();
    }

    public StringProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice.set(totalPrice);
    }


}
