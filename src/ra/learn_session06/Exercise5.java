package ra.learn_session06;

import java.util.Scanner;

public class Exercise5 {
    //Khởi tạo mảng gồm 1000 sinh viên để lưu trữ thông tin các sinh viên quản lý
    Student[] arrStudents = new Student[1000];
    //Biến lưu trữ chỉ số sinh viên nhỏ nhất chưa chứa dữ liêệu
    int currentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exercise5 bt5 = new Exercise5();
        //In menu và thực hiện các chức năng theo menu
        do {
            System.out.println("**************MENU****************");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới n sinh viên");
            System.out.println("3. Cập nhật sinh viên theo mã sinh viên");
            System.out.println("4. Xóa sinh viên theo mã sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    bt5.displayListStudent();
                    break;
                case 2:
                    bt5.inputListStudent(scanner);
                    break;
                case 3:
                    bt5.updateStudent(scanner);
                    break;
                case 4:
                    bt5.deleteStudent(scanner);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (true);
    }

    public void displayListStudent() {
        System.out.println("DANH SÁCH SINH VIÊN ĐANG QUẢN LÝ:");
        for (int i = 0; i < currentIndex; i++) {
            arrStudents[i].displayData();
        }
    }

    public void inputListStudent(Scanner scanner) {
        System.out.println("Nhập số sinh viên cần nhập dữ liệu:");
        int numberOfStudents = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfStudents; i++) {
            //1. Khởi tạo phần tử currentIndex là 1 đối tượng sinh viên
            arrStudents[currentIndex] = new Student();
            //2. Gọi inputData() để nhập liệu
            arrStudents[currentIndex].inputData(scanner);
            //3. tăng currentIndex
            currentIndex++;
        }
    }

    public void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật thông tin:");
        String studentIdUpdate = scanner.nextLine();
        //1. Tìm phần tử cần cập nhât trong mảng (chỉ số phần tử cần cập nhật)
        int indexStudentUpdate = getIndexById(studentIdUpdate);
        //2. Nếu tồn tại thực hiện cập nhật sinh viên (trừ mã sinh viên)
        if (indexStudentUpdate >= 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên sinh viên");
                System.out.println("2. Cập nhật tuổi sinh viên");
                System.out.println("3. Cập nhật địa chỉ sinh viên");
                System.out.println("4. Cập nhật số điện thoại sinh viên");
                System.out.println("5. Thoát");
                System.out.print("Lưa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên sinh viên cần cập nhập:");
                        arrStudents[indexStudentUpdate].setStudentName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào tuổi cần cập nhật:");
                        arrStudents[indexStudentUpdate].setAge(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.println("Nhập vào địa chỉ cần cập nhật:");
                        arrStudents[indexStudentUpdate].setAddress(scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Nhập vào số điện thoại cập nhật:");
                        arrStudents[indexStudentUpdate].setPhone(scanner.nextLine());
                        break;
                    case 5:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1-5");
                }
            } while (isExit);
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
    }

    public int getIndexById(String studentId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrStudents[i].getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần xóa:");
        String studentIdDelete = scanner.nextLine();
        int indexStudentDelete = getIndexById(studentIdDelete);
        if (indexStudentDelete >= 0) {
            for (int i = indexStudentDelete; i < currentIndex - 1; i++) {
                arrStudents[i] = arrStudents[i + 1];
            }
            arrStudents[currentIndex - 1] = null;
            currentIndex--;
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
    }
}