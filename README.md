QUẢN LÝ BÁN HÀNG CHO TIỂU THƯƠNG NHỎ LẺ


![Spring Boot + Java Logo](https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/512px-Spring_Framework_Logo_2018.svg.png)



I. GIỚI THIỆU
Ứng dụng hướng đến việc đơn giản hóa công tác quản lý cho các tiểu thương nhỏ lẻ:
- Quản lý tồn kho
- Quản lý khách hàng trực tiếp và đặt hàng từ xa
- Quản lý đơn hàng theo trạng thái
- Tính thu nhập mỗi ngày
- Cảnh báo hàng gần hết kho
  
II. TÍNH NĂNG
- Tổng quan dashboard kèm thống kê
- Xác thực người dùng
- Quản lý danh mục sản phẩm
- Quản lý sản phẩm trong kho
- Nhập hàng và ghi nhận số lượng
- Xuất hàng và tính toán tổng tiền tự động
- Tạo báo cáo
- Xử lý phiên đăng nhập bằng token
- Trực quan hóa dữ liệu (có thể dùng biểu đồ)
- Xuất dữ liệu ra file Excel hoặc PDF
III. THIẾT KẾ CƠ SỞ DỮ LIỆU
1. User(Người dùng):
  | Trường     | Kiểu dữ liệu | Mô tả                    |
|------------|--------------|--------------------------|
| `userId`   | UUID         | Định danh người dùng     |
| `username` | String       | Tên đăng nhập            |
| `password` | String       | Mật khẩu                 |
| `email`    | String       | Email liên hệ            |
| `role`     | String       | Vai trò (`admin`/`seller`) |

2. Token:
| Trường   | Kiểu dữ liệu | Mô tả                        |
|----------|--------------|------------------------------|
| userId   | UUID         | Liên kết đến người dùng (FK) |
| token    | String       | Token phiên đăng nhập        |
| date     | Date         | Ngày tạo token               |


3. Category(Danh mục):
 | Trường     | Kiểu dữ liệu | Mô tả         |
|------------|--------------|---------------|
| categoryId | UUID         | Mã danh mục   |
| name       | String       | Tên danh mục  |


4. Product:  
| Trường     | Kiểu dữ liệu | Mô tả                         |
|------------|--------------|-------------------------------|
| pdId       | UUID         | Mã sản phẩm                   |
| pdName     | String       | Tên sản phẩm                  |
| pdPrice    | Decimal      | Giá bán                       |
| categoryId | UUID         | Liên kết danh mục (FK)        |
| pdInfo     | Text         | Mô tả sản phẩm                |
| pdQuantity | Integer      | Số lượng tồn kho              |

5. Import(Nhập hàng):
| Trường     | Kiểu dữ liệu | Mô tả                        |
|------------|--------------|------------------------------|
| ipId       | UUID         | Mã bản ghi nhập hàng         |
| pdId       | UUID         | Mã sản phẩm nhập (FK)        |
| pdPrice    | Decimal      | Giá nhập                     |
| pdQuantity | Integer      | Số lượng nhập                |
| userId     | UUID         | Người nhập (FK)              |
| date       | DateTime     | Ngày nhập hàng               |


6. Export(Xuất hàng):
| Trường        | Kiểu dữ liệu | Mô tả                                 |
|---------------|--------------|---------------------------------------|
| epId          | UUID         | Mã bản ghi xuất hàng                  |
| pdId          | UUID         | Mã sản phẩm xuất (FK)                |
| pdPrice       | Decimal      | Giá xuất                              |
| pdQuantity    | Integer      | Số lượng xuất                         |
| pdTotalPrice  | Decimal      | Tổng tiền = pdPrice * pdQuantity     |
| userId        | UUID         | Người xuất (FK)                      |
| date          | DateTime     | Ngày xuất hàng                        |


7. Report(Báo cáo):
| Trường   | Kiểu dữ liệu | Mô tả                     |
|----------|--------------|---------------------------|
| reportId | UUID         | Mã báo cáo                |
| userId   | UUID         | Người tạo báo cáo (FK)    |
| rpName   | String       | Tên báo cáo               |
| rpInfo   | Text         | Nội dung báo cáo          |


8. Customer:
| Trường     | Kiểu dữ liệu | Mô tả                              |
|------------|--------------|------------------------------------|
| customerId | UUID         | Mã khách hàng                      |
| name       | String       | Họ và tên khách hàng               |
| phone      | String       | Số điện thoại                      |
| address    | String       | Địa chỉ                            |
| type       | String       | Loại khách: trực tiếp / online     |


9.Order
| Trường      | Kiểu dữ liệu | Mô tả                                      |
|-------------|--------------|--------------------------------------------|
| orderId     | UUID         | Mã đơn hàng                                |
| customerId  | UUID         | Khách đặt hàng (FK)                        |
| productId   | UUID         | Sản phẩm trong đơn (FK)                    |
| userId      | UUID         | Người bán xử lý đơn hàng (FK)              |
| quantity    | Integer      | Số lượng đặt                               |
| price       | Decimal      | Giá bán đơn vị                             |
| totalPrice  | Decimal      | Thành tiền = quantity * price              |
| status      | String       | Trạng thái: chưa xử lý / đang / đã xử lý   |
| createdDate | DateTime     | Ngày tạo đơn                               |

III. CÔNG NGHỆ ĐÃ SỬ DỤNG:
1. Frontend: Engine dựng HTML, tích hợp trực tiếp trong Spring Boot
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
2. Backend: Framework chính để phát triển ứng dụng web (theo kiến trúc MVC)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
3. Database: CSDL quan hệ để lưu trữ đơn hàng, sản phẩm, khách hàng...
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
4. Testing: Dùng để kiểm thử đơn vị (unit test) trong Java
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=java&logoColor=white)
5. Build Tool: Quản lý thư viện và biên dịch dự án
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

IV. CẤU TRÚC DỰ ÁN
1. Kiến trúc tổng thể:
Ứng dụng được xây dựng theo mô hình MVC + phân tầng 3 lớp chuẩn:
[Client - Giao diện HTML/Thymeleaf]
       ↓
[Controller Layer] - Nhận request từ view
       ↓
[Service Layer] - Xử lý nghiệp vụ, logic tính toán
       ↓
[Repository Layer] - Truy xuất & cập nhật dữ liệu (bộ nhớ hoặc DB)
       ↓
[Data Models] - Các lớp như Product, Order, Customer

2. Các thành phần chính trong project:
| Thành phần          | Vai trò                                                                |
| ------------------- | ---------------------------------------------------------------------- |
| `Product`           | Lớp đại diện sản phẩm: mã, tên, giá, tồn kho, mô tả                    |
| `Customer`          | Lớp đại diện khách hàng: tên, loại khách (trực tiếp/online), liên hệ   |
| `Order`             | Lớp quản lý đơn hàng: sản phẩm, số lượng, khách, trạng thái, tổng tiền |
| `User`              | Người dùng hệ thống (người bán)                                        |
| `Import` / `Export` | Quản lý việc nhập và xuất sản phẩm                                     |
| `Report`            | Tổng hợp thống kê đơn hàng theo ngày / trạng thái                      |
| `OrderStatus` Enum  | Trạng thái đơn hàng (CHUA\_XU\_LY, DANG\_XU\_LY, DA\_XU\_LY)           |

3. Dòng xử lý đơn hàng:
Ví dụ: Tạo một đơn hàng mới
User nhập thông tin → Controller nhận dữ liệu → gọi Service xử lý:
  → kiểm tra tồn kho
  → tính tổng tiền
  → cập nhật trạng thái ban đầu (chưa xử lý)
  → lưu vào danh sách đơn hàng (ArrayList)

→ Trả kết quả về View (HTML/Thymeleaf)

4. Danh sách mô-đun chức năng chính:
| Mô-đun            | Chức năng chính                                  |
| ----------------- | ------------------------------------------------ |
| `Authentication`  | Ghi nhận người dùng (người bán)                  |
| `ProductManager`  | CRUD sản phẩm, kiểm tra tồn kho                  |
| `OrderManager`    | CRUD đơn hàng, lọc đơn hàng, cập nhật trạng thái |
| `CustomerManager` | CRUD khách hàng, phân loại khách                 |
| `Statistics`      | Tính tổng doanh thu theo ngày, lọc thống kê      |
| `AlertSystem`     | Cảnh báo khi sản phẩm sắp hết hàng               |

V. CẤU TRÚC THƯ MỤC
project-root/
├── .vscode/
│   └── settings.json                    # Cấu hình môi trường phát triển trong VS Code
│
├── myUI/
│   └── initial/
│       ├── dashboard.html
│       ├── product_form.html
│       ├── order_list.html
│       ├── customer_list.html
│       ├── style.css
│       ├── logo.png
│       └── notes.txt
│
├── images/                              # Ảnh minh họa sơ đồ, giao diện, báo cáo
│
├── Quản lý bán hàng cho tiểu thương nhỏ lẻ/
│   ├── .vscode/
│   │   └── settings.json
│   │
│   ├── Review/
│   │   ├── Selector.java
│   │   ├── Sequence.java
│   │   └── TestSequence.java
│   │
│   ├── bin/
│   │   ├── App.class
│   │   ├── Customer.class
│   │   ├── CustomerTest.class
│   │   ├── Order.class
│   │   ├── OrderTest.class
│   │   ├── Product.class
│   │   ├── TestProduct.class
│   │
│   ├── src/
│   │   ├── App.java
│   │   ├── Customer.java
│   │   ├── CustomerList.java
│   │   ├── Order.java
│   │   ├── OrderList.java
│   │   ├── Product.java
│   │   ├── ProductList.java
│   │   ├── User.java
│   │   ├── UserList.java
│   │   └── Menu.java
│   │
│   ├── test/
│   │   ├── CustomerTest.java
│   │   ├── CustomerListTest.java
│   │   ├── OrderTest.java
│   │   ├── OrderListTest.java
│   │   ├── ProductTest.java
│   │   └── UserListTest.java
│   │
│   └── README.md
│
├── mvnw                                   # Maven Wrapper script (Linux/Mac)
├── mvnw.cmd                               # Maven Wrapper script (Windows)
├── mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar              # Maven launcher
│       └── maven-wrapper.properties       # Cấu hình Maven Wrapper
│
├── pom.xml                                # Tệp cấu hình Maven chính (dependencies, plugins)
└── src/                                   # Cấu trúc Spring Boot chuẩn
    ├── main/
    │   ├── java/
    │   │   └── com/example/servingwebcontent/
    │   │       └── (các class Spring Boot controller, model, service…)
    │   └── resources/
    │       ├── templates/                 # Thymeleaf HTML
    │       ├── static/                    # CSS, JS, ảnh tĩnh
    │       └── application.properties     # Cấu hình Spring
    │
    └── test/
        └── java/
            └── com/example/servingwebcontent/
                └── (JUnit test class)

VI. MÔ HÌNH VÀ CHỨC NĂNG
1. Mô hình hệ thống – Kiến trúc MVC (Model - View - Controller)
Ứng dụng được xây dựng theo mô hình MVC, gồm 3 thành phần chính:
- Model (Dữ liệu)
Chứa các lớp đại diện cho thực thể trong hệ thống như:

Product – sản phẩm

Order – đơn hàng

Customer – khách hàng

User – người bán

- View (Giao diện)
Sử dụng Thymeleaf để hiển thị các trang HTML động như:

Trang danh sách đơn hàng

Trang thêm sản phẩm

Trang thống kê doanh thu

- Controller (Điều hướng & xử lý)
Nhận các yêu cầu từ người dùng (qua URL hoặc button)

Gọi Service để xử lý logic

Trả về kết quả tới View hoặc dữ liệu JSON (nếu dùng REST)

2. Chức năng hệ thống (theo từng mô-đun)
- Sản phẩm (Product)
Thêm, sửa, xóa sản phẩm

Kiểm tra tồn kho

Cảnh báo khi gần hết hàng

Lọc tìm theo mã hoặc tên sản phẩm

- Đơn hàng (Order)
Tạo, sửa, xóa đơn hàng

Cập nhật trạng thái: chưa xử lý → đang xử lý → đã xử lý

Lọc đơn theo:

Trạng thái

Ngày tạo

Khách hàng

Tên sản phẩm

Tính tổng tiền đơn hàng

Tính tổng thu nhập theo ngày

- Khách hàng (Customer)
Gồm 2 loại:

Khách mua trực tiếp

Khách đặt hàng từ xa

Quản lý thông tin, sửa, xóa

Lọc tìm theo tên hoặc mã

- Người bán (User)
Nhập tên người bán khi bắt đầu

Ghi nhận người tạo đơn để phân quyền/hiển thị báo cáo

- Thống kê (Report/Statistics)
Tổng hợp đơn hàng trong ngày

Thống kê đơn theo trạng thái

Tạo báo cáo (Excel hoặc PDF nếu có)

3. Luồng xử lý chức năng tiêu biểu
Ví dụ: Tạo đơn hàng

Người dùng chọn khách → chọn sản phẩm → nhập số lượng

Controller nhận request → kiểm tra tồn kho

Tính tổng tiền → lưu đơn hàng

Cập nhật lại tồn kho

Trả về View hiển thị đơn mới và thông báo thành công

VII. SƠ ĐỒ CLASS DIAGRAM
![Sơ đồ Class Diagram](https://github.com/user-attachments/assets/7dd3bc0b-8b56-4eea-a83b-9099b460a9c0)

VII. LƯU ĐỒ THUẬT TOÁN CỦA CHỨC NĂNG CHÍNH 
![Lưu đồ thuật toán của chức năng chính](https://github.com/qlong161/OOP_N01_Term3_2025_K17_Group10/blob/main/Images/s%C6%A1%20%C4%91%E1%BB%93%20oop.png?raw=true)

VIII. CHỨC NĂNG PROJECT


IX.CÁC THÀNH VIÊN TRONG NHÓM
1. Nguyễn Hữu Quang Long
2. Bùi Việt Long
3. Lê Đức Thương
X. PHÂN CHIA CÔNG VIỆC
| Thành viên            | Nhiệm vụ phụ trách                                                               |
| --------------------- | -------------------------------------------------------------------------------- |
| Nguyễn Hữu Quang Long | Viết phương thức lọc đơn hàng theo ngày với trạng thái `"Đã xử lý"`              |
| Bùi Việt Long         | Viết phương thức tính tổng tiền từ danh sách đơn hàng đã lọc                     |
| Lê Đức Thương         | Viết phương thức in danh sách đơn hàng và hoàn thiện kiểm thử chức năng tổng thể |
| Cả nhóm               | Gọi các chức năng con để kết hợp thành chức năng chính của hệ thống              |

XI.GIAO DIỆN






















