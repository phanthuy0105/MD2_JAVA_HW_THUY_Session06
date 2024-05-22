package ra.exercise_session06;

import ra.learn_session06.Student;

import java.util.Scanner;

public class ProductImp {
    //Khởi tạo mảng gồm 1000 sinh viên để lưu trữ thông tin các sinh viên quản lý
    Product[] arrProducts = new Product[1000];
    //Biến lưu trữ chỉ số sinh viên nhỏ nhất chưa chứa dữ liêệu
    int currentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductImp product = new ProductImp();
        //In menu và thực hiện các chức năng theo menu
        do {
            System.out.println("**************MENU****************");
            System.out.println("1. Nhập thông tin n sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo giá");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Nhập sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    product.inputListProducts(scanner);
                    break;
                case 2:
                    product.displayListProducts();
                    break;
                case 3:
                    product.displayProfit();
                    product.displayListProductsFull();
                    break;
                case 4:
                    product.profitDecrease();
                    product.displayListProductsFull();
                    break;
                case 5:
                    product.quantityProductByPrice(scanner);
                    break;
                case 6:
                    product.searchByProductName(scanner);
                    break;
                case 7:
                    product.enterProduct(scanner);
                    product.displayListProductsFull();
                    break;
                case 8:
                    product.sellProduct(scanner);
                    product.displayListProductsFull();
                    break;
                case 9:
                    product.updateProductStatus(scanner);
                    product.displayListProductsFull();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-10");
            }
        } while (true);
    }

    // 1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)
    public void inputListProducts(Scanner scanner) {
        System.out.println("Nhập số sản phẩm cần nhập thông tin: ");
        int numberOfProducts = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfProducts; i++) {
            //1. Khởi tạo phần tử currentIndex là 1 đối tượng sản phẩm
            arrProducts[currentIndex] = new Product();
            //2. Gọi inputData() để nhập liệu
            arrProducts[currentIndex].inputData(scanner, arrProducts, currentIndex);
            //3. tăng currentIndex
            currentIndex++;
        }
    }

    // 2. Hiển thị thông tin các sản phẩm
    public void displayListProducts() {
        System.out.println("Thông tin sản phẩm chưa có lợi nhuận");
        for (int i = 0; i < currentIndex; i++) {
            arrProducts[i].displayData();
        }
    }

    // 3. Tính lợi nhuận các sản phẩm
    public void displayProfit() {
        System.out.println("Lợi nhuận sản phẩm");
        for (int i = 0; i < currentIndex; i++) {
            arrProducts[i].calProfit();
        }
    }

    public void displayListProductsFull() {
        System.out.println("Thông tin sản phẩm có lợi nhuận");
        for (int i = 0; i < currentIndex; i++) {
            arrProducts[i].displayDataFull();
        }
    }

    // 4. Sắp xếp sản phẩm theo lợi nhuận giảm dần
    public void profitDecrease() {
        System.out.println("Sắp xếp lợi nhuận giảm dần");
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrProducts[i].getProfit() < arrProducts[j].getProfit()) {
                    Product temp = arrProducts[i];
                    arrProducts[i] = arrProducts[j];
                    arrProducts[j] = temp;
                }
            }
        }
    }

    // 5. Thống kê các sản phẩm theo giá
    public void quantityProductByPrice(Scanner scanner) {
        System.out.println("Nhập vào số tiền:");
        int fromPrice = Integer.parseInt(scanner.nextLine());
        int toPrice = Integer.parseInt(scanner.nextLine());
        int count = 0;
        boolean isPrice = false;
        for (int i = 0; i < currentIndex; i++) {
            if (fromPrice <= arrProducts[i].getExportPrice() && toPrice >= arrProducts[i].getExportPrice()) {
                isPrice = true;
                count++;
            }
        }
        if (!isPrice) {
            System.err.println("Số tiền " + fromPrice + " đến " + toPrice + " không có sản phẩm nào");
        } else {
            System.out.println("Trong khoảng " + fromPrice + " đến " + toPrice + " có số lượng sản phẩm là: " + count);
        }
    }

    // 6. Tìm các sản phẩm theo tên sản phẩm
    public void searchByProductName(Scanner scanner) {
        System.out.println("Tìm kiếm theo tên sản phẩm: ");
        String name = scanner.nextLine();
        boolean isSearch = false;
        for (int i = 0; i < currentIndex; i++) {
            if (arrProducts[i].getProductName().contains(name)) {
                isSearch = true;
                arrProducts[i].displayDataFull();
                break;
            }
        }
        if (!isSearch) {
            System.err.println("Tên sản phẩm không tồn tại, vui lòng nhập lại");
        }
    }

    public int getIndexById(String productId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrProducts[i].getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    // 7. Nhập sản phẩm
    public void enterProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm: ");
        String enterProductId = scanner.nextLine();
        int indexStudentUpdate = getIndexById(enterProductId);
        boolean isInsert = false;
        if (indexStudentUpdate >= 0) {
            isInsert = true;
            System.out.println("Nhập vào số lương sản phẩm cần nhập: ");
            int quantityProduct = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < currentIndex; i++) {
                // TH1: Đã có Id
                if (arrProducts[i].getProductId().equals(enterProductId)) {
                    arrProducts[i].setQuantity(arrProducts[i].getQuantity() + quantityProduct);
                    break;
                }
            }
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
            // Chưa có Id
            System.out.println("Nhập số sản phẩm cần nhập dữ liệu:");
            int numberOfStudents = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numberOfStudents; i++) {
                //1. Khởi tạo phần tử currentIndex là 1 đối tượng sinh viên
                arrProducts[currentIndex] = new Product();
                //2. Gọi inputData() để nhập liệu
                arrProducts[currentIndex].inputData(scanner, arrProducts, currentIndex);
                //3. tăng currentIndex
                currentIndex++;
            }
        }
    }

    // 7. Bán sản phẩm
    public void sellProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm: ");
        String enterProductId = scanner.nextLine();
        int indexStudentUpdate = getIndexById(enterProductId);
        boolean isInsert = false;
        if (indexStudentUpdate >= 0) {
            isInsert = true;
            System.out.println("Nhập vào số lương sản phẩm cần bán: ");
            int quantityProduct = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < currentIndex; i++) {
                // TH1: Đã có Id
                if (arrProducts[i].getProductId().equals(enterProductId)) {
                    arrProducts[i].setQuantity(arrProducts[i].getQuantity() - quantityProduct);
                    break;
                }
            }
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    // 9. Cập nhật trạng thái sản phẩm
    public void updateProductStatus(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật trạng thái sản phẩm: ");
        String enterProductId = scanner.nextLine();
        int indexStudentUpdate = getIndexById(enterProductId);

        if (indexStudentUpdate >= 0) {
            System.out.println("Nhập vào trạng thái sản phẩm cần cập nhập:");
            arrProducts[indexStudentUpdate].setStatus(Boolean.parseBoolean(scanner.nextLine()));
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }
}
