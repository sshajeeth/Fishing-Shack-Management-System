package Payment_page;

public class AddToCart {
   String productId1;
   String productName1;
    String productQty1;
    String productEach1;
   String totalPrice1;
    public static String totalAmount1;

    public String getTotalAmount1() {
        return totalAmount1;
    }

    public void setTotalAmount1(String totalAmount1)
    {
        this.totalAmount1 = totalAmount1;

    }

    public AddToCart(String productId1, String productName1, String productQty1, String productEach1, String totalPrice1) {
        this.productId1 = productId1;
        this.productName1 = productName1;
        this.productQty1 = productQty1;
        this.productEach1 = productEach1;
        this.totalPrice1 = totalPrice1;
    }

    public String getProductId1() {
        return productId1;
    }

    public void setProductId1(String productId1) {
        this.productId1 = productId1;
    }

    public String getProductName1() {
        return productName1;
    }

    public void setProductName1(String productName1) {
        this.productName1 = productName1;
    }

    public String getProductQty1() {
        return productQty1;
    }

    public void setProductQty1(String productQty1) {
        this.productQty1 = productQty1;
    }

    public String getProductEach1() {
        return productEach1;
    }

    public void setProductEach1(String productEach1) {
        this.productEach1 = productEach1;
    }

    public String getTotalPrice1() {
        return totalPrice1;
    }

    public void setTotalPrice1(String totalPrice1) {
        this.totalPrice1 = totalPrice1;
    }



}
