package ra.exercise_session06;

import ra.learn_session06.Student;

import java.util.Scanner;

public class BookImp {
    //Khởi tạo mảng gồm 1000 sinh viên để lưu trữ thông tin các sinh viên quản lý
    Book[] arrBooks = new Book[1000];
    //Biến lưu trữ chỉ số sinh viên nhỏ nhất chưa chứa dữ liêệu
    int currentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookImp book = new BookImp();
        //In menu và thực hiện các chức năng theo menu
        do {
            System.out.println("**************MENU****************");
            System.out.println("1. Nhập thông tin n sách");
            System.out.println("2. Tính lợi nhuận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("5. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("6. Tìm sách theo tên sách");
            System.out.println("7. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("8. Thống kê số lượng sách theo tác giả");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    book.inputListBooks(scanner);
                    break;
                case 2:
                    book.displayInterest();
                    book.displayListBooks();
                    break;
                case 3:
                    book.displayListBooks();
                    break;
                case 4:
                    book.priceAscending();
                    book.displayListBooks();
                    break;
                case 5:
                    book.interestDecrease();
                    book.displayListBooks();
                    break;
                case 6:
                    book.searchByBookName(scanner);
                    break;
                case 7:
                    book.quantityBookByYearOfPublication(scanner);
                    break;
                case 8:
                    book.quantityBookByAuthor(scanner);
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-9");
            }
        } while (true);
    }

    // 1. Nhập thông tin n sách (n nhập từ bàn phím)
    public void inputListBooks(Scanner scanner) {
        System.out.println("Nhập số sách cần nhập thông tin: ");
        int numberOfBooks = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfBooks; i++) {
            //1. Khởi tạo phần tử currentIndex là 1 đối tượng sách
            arrBooks[currentIndex] = new Book();
            //2. Gọi inputData() để nhập liệu
            arrBooks[currentIndex].inputData(scanner, arrBooks, currentIndex);
            //3. tăng currentIndex
            currentIndex++;
        }
    }

    // 2. Tính lợi nhuận các sách
    public void displayInterest() {
        System.out.println("Lợi nhuận sách");
        for (int i = 0; i < currentIndex; i++) {
            arrBooks[i].interest();
        }
    }

    // 3. Hiển thị thông tin sách
    public void displayListBooks() {
        System.out.println("Thông tin sách");
        for (int i = 0; i < currentIndex; i++) {
            arrBooks[i].displayData();
        }
    }

    // 4. Sắp xếp sách theo giá bán tăng dần
    public void priceAscending() {
        System.out.println("Sắp xếp giá bán tăng dần");
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrBooks[i].getExportPrice() > arrBooks[j].getExportPrice()) {
                    Book temp = arrBooks[i];
                    arrBooks[i] = arrBooks[j];
                    arrBooks[j] = temp;
                }
            }
        }
    }

    // 5. Sắp xếp sách theo lợi nhuận giảm dần
    public void interestDecrease() {
        System.out.println("Sắp xếp lợi nhuận giảm dần");
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrBooks[i].getInterest() < arrBooks[j].getInterest()) {
                    Book temp = arrBooks[i];
                    arrBooks[i] = arrBooks[j];
                    arrBooks[j] = temp;
                }
            }
        }
    }

    // 6. Tìm sách theo tên sách (tên sách nhập từ bàn phím)
    public void searchByBookName(Scanner scanner) {
        System.out.println("Tìm kiếm theo tên sách: ");
        String name = scanner.nextLine();
        boolean isSearch = false;
        for (int i = 0; i < currentIndex; i++) {
            if (arrBooks[i].getBookName().contains(name)) {
                isSearch = true;
                arrBooks[i].displayData();
                break;
            }
        }
        if (!isSearch) {
            System.err.println("Tên sách không tồn tại, vui lòng nhập lại");
        }
    }

    // 7. Thống kê số lượng sách theo năm xuất bản
    public void quantityBookByYearOfPublication(Scanner scanner) {
        System.out.println("Nhập vào số năm:");
        int year = Integer.parseInt(scanner.nextLine());
        int count = 0;
        boolean isYear = false;
        for (int i = 0; i < currentIndex; i++) {
            if (arrBooks[i].getYear() == year) {
                isYear = true;
                count++;
            }
        }
        if (!isYear) {
            System.err.println("Năm " + year + " không có quyển sách nào");
        } else {
            System.out.println(year + " số lượng sách là: " + count);
        }
    }

    // 8. Thống kê số lượng sách theo tác giả
    public void quantityBookByAuthor(Scanner scanner) {
        System.out.println("Nhập vào tên tác giả:");
        String nameAuthor = scanner.nextLine();
        int count = 0;
        boolean isAuthor = false;
        for (int i = 0; i < currentIndex; i++) {
            if (arrBooks[i].getAuthor().equals(nameAuthor)) {
                isAuthor = true;
                count++;
            }
        }
        if (!isAuthor) {
            System.err.println("Tác giả " + nameAuthor + " không có quyển sách nào");
        } else {
            System.out.println(nameAuthor + " số lượng sách là: " + count);
        }
    }
}
