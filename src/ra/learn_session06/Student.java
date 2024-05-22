package ra.learn_session06;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Student {
    private String studentId;
    private String studentName;
    private int age;
    private String address;
    private String phone;

    public Student() {
    }

    public Student(String studentId, String studentName, int age, String address, String phone) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputData(Scanner scanner) {
        this.studentId = inputStudentId(scanner);
        //validate: tên sinh viên phải có từ 6-50 ký tự
        this.studentName = inputStudentName(scanner);
        System.out.println("Nhập vào tuổi sinh viên:");
        this.age = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào địa chỉ sinh viên:");
        this.address = scanner.nextLine();
        System.out.println("Nhập vào số điện thoại sinh viên:");
        this.phone = scanner.nextLine();
    }


    public String inputStudentId(Scanner scanner) {
        //Validate dữ liệu mã sinh viên: mã sinh viên gồm 5 ký tự, bắt đầu là SV
        String studentId;
        System.out.println("Nhập vào mã sinh viên:");
        do {
            //C1: Bắt theo regex
            String studentIdRegex = "SV[\\d]{3}";
            studentId = scanner.nextLine();
            if (Pattern.matches(studentIdRegex, studentId)) {
                break;
            } else {
                System.err.println("Mã sinh viên phải gồm 5 ký tự bắt đầu là SV,vui lòng nhập lại");
            }
            //C2: Bắt từng yêu cầu 1
//            if (this.studentId.length()==5){
//                if (this.studentId.startsWith("SV")){
//                    break;
//                }else{
//                    System.err.println("Mã sinh viên phải bắt đầu là SV, vui lòng nhập lại");
//                }
//            }else{
//                System.err.println("Mã sinh viên phải gồm 5 ký tự, vui lòng nhập lại");
//            }
        } while (true);
        return studentId;
    }

    public String inputStudentName(Scanner scanner) {
        System.out.println("Nhập vào tên sinh viên:");
        String studentName;
        do {
            //studentNameRegex = "[\\w]{6,50}"
            studentName = scanner.nextLine();
            if (studentName.length() >= 6 && studentName.length() <= 50) {
                return studentName;
            } else {
                System.err.println("Tên sinh viên gồm từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã sinh viên: %s - Tên sinh viên: %s - Tuổi: %d\n", this.studentId, this.studentName, this.age);
        System.out.printf("Địa chỉ: %s - Số điện thoại: %s\n", this.address, this.phone);
    }
}