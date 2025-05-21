Group 10
1. Nguyễn Hữu Quang Long
2. Bùi Việt Long
3. Lê Đức Thương

Project: Quản lý bán hàng Online
Câu 1: Tiêu đề
Hệ thống quản lý bán hàng online

Câu 2: 
3 đối tượng chính:
1. Product 
- Mã sản phẩm
- Tên sản phẩm
- Giá bán
- Số lượng tồn

2. Customer 
- Mã khách hàng
- Họ tên
- Email

3. Order 
- Mã đơn hàng
- Khách hàng
- Danh sách sản phẩm
- Ngày đặt
- Tổng tiền
- Trạng thái
- Group 10
1. Nguyễn Hữu Quang Long
2. Bùi Việt Long
3. Lê Đức Thương

Project: Quản lý bán hàng Online
Câu 1: Tiêu đề
Hệ thống quản lý bán hàng online

Câu 2: 
3 đối tượng chính:
1. Product 
- Mã sản phẩm
- Tên sản phẩm
- Giá bán
- Số lượng tồn

2. Customer 
- Mã khách hàng
- Họ tên
- Email

3. Order 
- Mã đơn hàng
- Khách hàng

  NỘI DUNG 1:
*  Chức năng chính:
1. Quản lý Sản phẩm (Đối tượng 01)
 +Thêm sản phẩm mới

 +Sửa thông tin sản phẩm

 +Xóa sản phẩm

 +Liệt kê toàn bộ sản phẩm

 +Lọc sản phẩm theo danh mục, giá, tên, nhà sản xuất,…

2. Quản lý Đơn hàng (Đối tượng 02)
 +Thêm đơn hàng

 +Sửa đơn hàng (trước khi giao hàng)

 +Xóa đơn hàng
 
 +Liệt kê các đơn hàng

 +Lọc đơn hàng theo ngày, trạng thái, khách hàng,...

3. Gán Sản phẩm vào Đơn hàng
 +Chọn sản phẩm để thêm vào đơn hàng

 +Tính tổng tiền đơn hàng

 +Kiểm tra tồn kho trước khi thêm vào đơn hàng

4. Quản lý Khách hàng (Đối tượng 03)
 +Thêm, sửa, xóa thông tin khách hàng

 +Xem lịch sử mua hàng của từng khách hàng
- Danh sách sản phẩm
- Ngày đặt
- Tổng tiền
- Trạng thái
* Lưu trữ dữ liệu:
Dữ liệu được lưu xuống file nhị phân:

sanpham.dat

donhang.dat

khachhang.dat

Sử dụng các lớp xử lý lưu trữ:

SanPhamIO.java

DonHangIO.java

KhachHangIO.java
* Xử lý dữ liệu trong bộ nhớ:
-Dữ liệu được lưu trữ tạm thời trong các Collection:

ArrayList<SanPham>

ArrayList<DonHang>

Map<String, KhachHang>
* Tính năng mở rộng (Tuỳ chọn):
-Thống kê doanh thu theo ngày/tháng

Quản lý tồn kho

Gửi email xác nhận đơn hàng (nếu có thời gian tích hợp)

Tìm kiếm nâng cao

NỘI DUNG 2:
classDiagram
    class SanPham {
        -String maSP
        -String tenSP
        -double gia
        -int soLuong
        -String danhMuc
    }

    class DonHang {
        -String maDH
        -Date ngayDat
        -KhachHang khachHang
        -List<ChiTietDonHang> danhSachSP
        -String trangThai
    }


    class ChiTietDonHang {
        -SanPham sanPham
        -int soLuong
    }

    class KhachHang {
        -String maKH
        -String tenKH
        -String diaChi
        -String soDienThoai
    }

    SanPham --> ChiTietDonHang
    DonHang --> ChiTietDonHang
    DonHang --> KhachHang

  NỘI DUNG 3:
  1. Sequence Diagram – Đặt hàng
sequenceDiagram
    participant KhachHang
    participant GiaoDien
    participant HeThong
    participant FileLuuTru

    KhachHang->>GiaoDien: Chọn sản phẩm + số lượng
    GiaoDien->>HeThong: Tạo đơn hàng
    HeThong->>HeThong: Kiểm tra tồn kho
    HeThong->>FileLuuTru: Ghi đơn hàng mới
    FileLuuTru-->>HeThong: Xác nhận lưu
    HeThong-->>GiaoDien: Hiển thị thông báo thành công
2. Activity Diagram – Thêm sản phẩm

graph TD
    A[Bắt đầu] --> B[Nhập thông tin sản phẩm]
    B --> C[Kiểm tra dữ liệu]
    C -- Hợp lệ --> D[Lưu vào danh sách]
    C -- Không hợp lệ --> E[Thông báo lỗi]
    D --> F[Lưu xuống file]
    F --> G[Kết thúc]
    E --> B
3. Sequence Diagram – Xem đơn hàng theo khách hàng

sequenceDiagram
    participant NguoiDung
    participant GiaoDien
    participant HeThong
    participant FileLuuTru

    NguoiDung->>GiaoDien: Nhập mã khách hàng
    GiaoDien->>HeThong: Tìm đơn hàng theo khách
    HeThong->>FileLuuTru: Đọc đơn hàng
    FileLuuTru-->>HeThong: Trả về danh sách
    HeThong-->>GiaoDien: Hiển thị danh sách đơn hàng




