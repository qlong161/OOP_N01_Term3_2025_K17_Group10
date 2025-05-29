Group 10
1. Nguyễn Hữu Quang Long
2. Bùi Việt Long
3. Lê Đức Thương

Xây dựng ứng dụng: Quản lý bán hàng cho tiểu thương nhỏ lẻ

Ứng dụng hướng tới hỗ trợ các tiểu thương buôn bán nhỏ lẻ trong việc:
  + Quản lý sản phẩm có trong kho
  + Ghi nhận thông tin khách hàng mua hàng trực tiếp hoặc đặt hàng online
  + Quản lý đơn hàng với các trạng thái (chưa xử lý, đang xử lý, đã xử lý)
  + Tính thu nhập theo ngày dựa trên đơn hàng đã hoàn tất 

Yêu cầu: 
  + Giao diện: Java Spring Boot.
  + Có chức năng quản lý đơn hàng, khách hàng, hàng tồn kho.

Chức năng:
Quản lý Người bán (Seller/User)
  + Nhập tên người bán khi khởi động ứng dụng.
  + Ghi nhận người bán để gắn vào hệ thống đơn hàng.

Quản lý đơn hàng:
  + Thêm, xóa, sửa đơn hàng.
  + Cập nhật trạng thái đơn hàng (chưa xử lý → đang xử lý → đã xử lý).
  + Hiển thị danh sách các đơn hàng theo trạng thái.
  + Xem danh sách đơn hàng trong ngày.
  + Có thể lọc theo mã đơn hàng, trạng thái đơn hàng, tên khách hàng, tên mặt hàng.
  + Tính tổng thu nhập trong ngày

Quản lý sản phẩm:
  + Thêm, xóa, sửa sản phẩm.
  + Cập nhật số lượng hàng tồn kho.
  + Quản lý số lượng tồn kho (stock):
    + Không cho phép tạo đơn hàng nếu sản phẩm đã hết hàng.
    + Có cảnh báo khi sản phẩm gần hết kho.
  + Lọc tìm theo mã sản phẩm, tên sản phẩm.
  + Kiểm tra hết hàng.

Quản lý khách hàng:
  + Gồm 2 loại khách:
    + Khách mua trực tiếp
    + Khách đặt hàng từ xa
  + Thêm, xóa, sửa khách hàng.
  + Tìm kiếm khách theo mã hoặc tên.
  + Hiển thị danh sách khách hàng hiện có.

Thống kê & Doanh thu:
  + Tính thu nhập trong ngày từ các đơn hàng đã xử lý.
  + Thống kê số lượng đơn hàng theo trạng thái.

Cần tạo các class liên quan đến:
  + Customer(khách hàng)
  + Product (Sản phẩm)
  + Order (Đơn hàng)

Khi xử lý trong bộ nhớ, dữ liệu cần lưu bằng các Collection như:
  + ArrayList cho các lớp Customer, Order.
  + Map cho Product.




