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
-  Đề tài gợi ý: Ứng dụng quản lý sinh viên và ký túc xá
📝 Yêu cầu chức năng
Giao diện: Spring Boot (REST API hoặc kết hợp với Thymeleaf cho giao diện web MVC).

Chức năng quản lý sinh viên (Đối tượng 01):

Thêm, sửa, xóa sinh viên

Liệt kê thông tin sinh viên, lọc theo: lớp, giới tính, quê quán,...

Chức năng quản lý phòng ký túc xá (Đối tượng 02):

Thêm, sửa, xóa phòng

Chức năng gán sinh viên vào phòng:

Ví dụ: Gán sinh viên A vào phòng 101

Lưu trữ dữ liệu vào file nhị phân:

Sử dụng Java ObjectOutputStream và ObjectInputStream để đọc/ghi các đối tượng vào file.

Lưu dữ liệu trong bộ nhớ:

Sử dụng ArrayList để lưu danh sách sinh viên

Sử dụng HashMap<Integer, List<SinhVien>> để quản lý sinh viên theo phòng

🧾 Các đối tượng chính cần xây dựng (Class):
SinhVien: mã sinh viên, tên, giới tính, lớp, ngày sinh,...

Phong: mã phòng, tên phòng, sức chứa,...

QuanLyKTX: thực hiện thêm/sửa/xóa sinh viên và phòng, gán sinh viên vào phòng.

FileHandler: đọc/ghi danh sách sinh viên và phòng xuống file nhị phân.

✅ Các chức năng mở rộng (gợi ý tùy chọn):
Xuất báo cáo số sinh viên trong mỗi phòng

Lọc phòng chưa đủ sinh viên

Sắp xếp sinh viên theo tên hoặc mã
+------------------+           +-------------------+
|    SinhVien      |           |      Phong        |
+------------------+           +-------------------+
| - maSV: String   |           | - maPhong: String |
| - ten: String    |           | - sucChua: int    |
| - lop: String    |           | - tenPhong: String|
| - gioiTinh: String|          +-------------------+
| - ngaySinh: Date |
+------------------+

+--------------------------------+
|        QuanLyKTX               |
+--------------------------------+
| - dsSinhVien: List<SinhVien>  |
| - dsPhong: List<Phong>        |
| - phanBo: Map<String, List<SinhVien>> |
+--------------------------------+
| + themSinhVien()              |
| + xoaSinhVien()               |
| + ganSinhVienVaoPhong()       |
| + timKiemSinhVien()           |
+--------------------------------+

+-------------------------------+
|         FileHandler           |
+-------------------------------+
| + docFileSinhVien()           |
| + ghiFileSinhVien()           |
| + docFilePhong()              |
| + ghiFilePhong()              |
+-------------------------------+
User -> QuanLyKTX : ganSinhVienVaoPhong(maSV, maPhong)
QuanLyKTX -> dsSinhVien : timSinhVien(maSV)
QuanLyKTX -> dsPhong : timPhong(maPhong)
QuanLyKTX -> phanBo : themSVVaoDanhSachPhong()



