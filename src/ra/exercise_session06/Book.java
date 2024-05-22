package ra.exercise_session06;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, String author, float interest, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.interest = interest;
        this.year = year;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputData(Scanner scanner, Book[] arrBooks, int currentIndex) {
        //validate: Mã sách phải có từ không trùng lặp
        this.bookId = inputBookId(scanner, arrBooks, currentIndex);
        //validate: tên sách phải có từ không trùng lặp, gồm 4 ký tự, bắt đầu là ký tự B
        this.bookName = inputBookName(scanner, arrBooks, currentIndex);
        //validate: Giá nhập của sách có giá trị lớn hơn 0
        this.importPrice = inputImportPrice(scanner);
        //validate:  Giá xuất của sách, có giá trị lớn hơn ít nhất 30% so với giá nhập
        this.exportPrice = inputExportPrice(scanner, this.importPrice);
        //validate: tên tác giả phải có từ 6-50 ký tự
        this.author = inputAuthor(scanner);
        //validate: năm sản xuất > 2000
        this.year = inputYear(scanner);
    }


    public String inputBookId(Scanner scanner, Book[] arrBooks, int currentIndex) {
        //validate: Mã sách phải có từ không trùng lặp
        System.out.println("Nhập vào mã sách: ");
        do {
            String bookId = scanner.nextLine();
            // Kiểm tra duy nhất
            boolean isExist = false;
            for (int i = 0; i < currentIndex; i++) {
                if (arrBooks[i].getBookId().equals(bookId)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Mã sách đã tồn tại, vui lòng nhập lại");
            } else {
                return bookId;
            }
        } while (true);
    }

    public String inputBookName(Scanner scanner, Book[] arrBooks, int currentIndex) {
        //Validate dữ liệu tên sách: không trùng lặp, gồm 4 ký tự, bắt đầu bằng B
        System.out.println("Nhập vào tên sách: ");
        String bookName;
        do {
            bookName = scanner.nextLine();
            //Kiểm tra duy nhất
            boolean isExist = false;
            for (int i = 0; i < currentIndex; i++) {
                if (arrBooks[i].getBookName().equals(bookName)) {
                    isExist = true;
                    break;
                }
            }
            //C1: Bắt theo regex
            String bookNameRegex = "B[\\d]{3}";
            if (Pattern.matches(bookNameRegex, bookName)) {
                if (isExist) {
                    System.err.println("Mã sách đã tồn tại, vui lòng nhập lại");
                } else {
                    break;
                }
            } else {
                System.err.println("Tên sách phải gồm 4 ký tự bắt đầu là B, vui lòng nhập lại");
            }
        } while (true);
        return bookName;
    }

    public float inputImportPrice(Scanner scanner) {
        //Validate dữ liệu giá nhập: Giá nhập của sách có giá trị lớn hơn 0
        System.out.println("Giá nhập của sách: ");
        float importPrice;
        do {
            importPrice = Float.parseFloat(scanner.nextLine());
            if (importPrice > 0) {
                return importPrice;
            } else {
                System.err.println("Giá nhập của sách phải có giá trị lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputExportPrice(Scanner scanner, float importPrice) {
        //Validate dữ liệu giá nhập: Giá nhập của sách có giá trị lớn hơn 0
        System.out.println("Giá xuất của sách: ");
        float exportPrice;
        do {
            exportPrice = Float.parseFloat(scanner.nextLine());
            if (exportPrice >= (importPrice * 1.3)) {
                return exportPrice;
            } else {
                System.err.println(" Giá xuất của sách có giá trị lớn hơn ít\n" +
                        "nhất 30% so với giá nhập, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputAuthor(Scanner scanner) {
        //Validate dữ liệu tên tác giả: có từ 6 đến 50 ký tự
        System.out.println("Nhập vào tên tác giả:");
        String authorBook;
        do {
            //studentNameRegex = "[\\w]{6,50}"
            authorBook = scanner.nextLine();
            if (authorBook.length() >= 6 && authorBook.length() <= 50) {
                return authorBook;
            } else {
                System.err.println("Tên tác giả gồm từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputYear(Scanner scanner) {
        //Validate dữ liệu năm: sau năm 2000
        System.out.println("Nhập vào năm sản xuất sách:");
        int yearBook;
        do {
            yearBook = Integer.parseInt(scanner.nextLine());
            if (yearBook > 2000) {
                break;
            } else {
                System.err.println("Xuất bản sau năm 2000, vui lòng nhập lại");
            }
        } while (true);
        return yearBook;
    }

    public void interest() {
        this.interest = this.exportPrice - this.importPrice;
    }

    public void displayData() {
        System.out.printf("Mã sách: %s - Tên sách: %s - Giá nhập: %.2f\n", this.bookId, this.bookName, this.importPrice);
        System.out.printf("Giá xuất: %.2f - Tác giả: %s - Năm: %d\n", this.exportPrice, this.author, this.year);
        System.out.printf("Lợi nhuận: %.2f\n", this.interest);
    }
}