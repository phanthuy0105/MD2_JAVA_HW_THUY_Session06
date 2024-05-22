package ra.exercise_session06;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice, float profit, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProducts, int currentIndex) {
        // validate: Mã sản phẩm phải có từ không trùng lặp, gồm 4 ký tự
        this.productId = inputProductId(scanner, arrProducts, currentIndex);
        // validate: tên sản phẩm phải có từ không trùng lặp, gồm 6-50 ký tự
        this.productName = inputProductName(scanner, arrProducts, currentIndex);
        // validate: Giá nhập của sản phẩm có giá trị lớn hơn 0
        this.importPrice = inputImportPrice(scanner);
        // validate:  Giá xuất của sản phẩm, có giá trị lớn hơn ít nhất 30% so với giá nhập
        this.exportPrice = inputExportPrice(scanner, this.importPrice);
        // validate: Số lượng sản phẩm phải có giá trị lớn hơn 0
        this.quantity = inputQuantity(scanner);
        // description
        System.out.println("Nhập vào description sản phẩm");
        this.descriptions = scanner.nextLine();
        // description
        System.out.println("Nhập vào trạng thái sản phẩm");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    public String inputProductId(Scanner scanner, Product[] arrProducts, int currentIndex) {
        //validate: Mã sản phẩm phải có từ không trùng lặp, gồm 4 ký tự
        System.out.println("Nhập vào mã sản phẩm: ");
        String productId;
        do {
            productId = scanner.nextLine();
            //Kiểm tra duy nhất
            boolean isExist = false;
            for (int i = 0; i < currentIndex; i++) {
                if (arrProducts[i].getProductId().equals(productId)) {
                    isExist = true;
                    break;
                }
            }
            //C1: Bắt theo regex
            String productIdRegex = "[\\w]{4}";
            if (Pattern.matches(productIdRegex, productId)) {
                if (isExist) {
                    System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    break;
                }
            } else {
                System.err.println("Mã sản phẩm phải gồm 4 ký tự, vui lòng nhập lại");
            }

        } while (true);
        return productId;
    }

    public String inputProductName(Scanner scanner, Product[] arrProducts, int currentIndex) {
        //Validate dữ liệu tên sản phẩm: không trùng lặp, gồm 6-50 ký tự
        System.out.println("Nhập vào tên sản phẩm: ");
        String productName;
        do {
            productName = scanner.nextLine();
            //Kiểm tra duy nhất
            boolean isExist = false;
            for (int i = 0; i < currentIndex; i++) {
                if (arrProducts[i].getProductName().equals(productName)) {
                    isExist = true;
                    break;
                }
            }
            if (productName.length() >= 6 && productName.length() <= 50) {
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên sản phẩm gồm từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputImportPrice(Scanner scanner) {
        //Validate dữ liệu giá nhập: Giá nhập của sản phẩm có giá trị lớn hơn 0
        System.out.println("Giá nhập của sản phẩm: ");
        float importPrice;
        do {
            importPrice = Float.parseFloat(scanner.nextLine());
            if (importPrice > 0) {
                return importPrice;
            } else {
                System.err.println("Giá nhập của sản phẩm phải có giá trị lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputExportPrice(Scanner scanner, float importPrice) {
        //Validate dữ liệu giá nhập: Giá nhập của sản phẩm có giá trị lớn hơn 0
        System.out.println("Giá xuất của sản phẩm: ");
        float exportPrice;
        do {
            exportPrice = Float.parseFloat(scanner.nextLine());
            if (exportPrice >= (importPrice * 1.2)) {
                return exportPrice;
            } else {
                System.err.println(" Giá xuất của sản phẩm, có giá trị lớn hơn ít\n" +
                        "nhất 20% so với giá nhập, vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputQuantity(Scanner scanner) {
        //Validate dữ liệu số lượng: Số lượng sản phẩm có giá trị lớn hơn 0
        System.out.println("Số lượng sản phẩm: ");
        int quantity;
        do {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > 0) {
                return quantity;
            } else {
                System.err.println("Số lượng sản phẩm phải có giá trị lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public void calProfit() {
        this.profit = this.exportPrice - this.importPrice;
    }

    //Xây dựng phương thức cho phép hiển thị thông tin sản phẩm chưa lợi nhuận
    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá nhập: %.2f\n", this.productId, this.productName, this.importPrice);
        System.out.printf("Giá xuất: %.2f - Số lượng: %d - Mô tả: %s - Tình trạng: %s\n", this.exportPrice, this.quantity, this.descriptions, this.status ? "Đang bán" : "Không bán");
        System.out.printf("Lợi nhuận: %.2f\n", this.profit);
    }
    //Xây dựng phương thức cho phép hiển thị thông tin sản phẩm kèm lợi nhuận
    public void displayDataFull() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá nhập: %.2f\n", this.productId, this.productName, this.importPrice);
        System.out.printf("Giá xuất: %.2f - Số lượng: %d - Mô tả: %s - Tình trạng: %s\n", this.exportPrice, this.quantity, this.descriptions, this.status ? "Đang bán" : "Không bán");
        System.out.printf("Lợi nhuận: %.2f\n", this.profit);
    }
}
