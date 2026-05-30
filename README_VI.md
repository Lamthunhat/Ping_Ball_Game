# Don't Drop It Adventure (Game Hứng Bóng)

Dự án game arcade 2D cổ điển được xây dựng bằng ngôn ngữ **Java**, sử dụng thư viện đồ họa **Swing** và **AWT**. Trò chơi có các tính năng như di chuyển mượt mà, vật lý va chạm, âm nhạc nền, hiệu ứng âm thanh, tính điểm, lưu điểm cao kỷ lục và quản lý số mạng chơi.

---

## 🎮 Cách chơi & Phím điều khiển

Mục tiêu của bạn là điều khiển nhân vật hứng quả lựu đạn (quả bóng) đang rơi và đẩy nó nảy ngược lên trên, không được để quả bóng rơi quá biên dưới của màn hình.

- **Phím Mũi tên Trái (Left Arrow)**: Di chuyển nhân vật sang bên trái.
- **Phím Mũi tên Phải (Right Arrow)**: Di chuyển nhân vật sang bên phải.
- **Phím P**: Tạm dừng (Pause) / Tiếp tục (Resume) trò chơi.
- **Phím Space (Dấu cách)**: Chơi lại từ đầu (Khi đã Game Over).
- **Phím ESC**: Quay trở lại Menu chính (Khi đang ở màn hình Game Over).

---

## 🛠️ Cấu trúc dự án

Dự án được tổ chức theo mô hình chuẩn **Model-View-Controller (MVC)** để quản lý mã nguồn dễ dàng:

```text
src/main/java/
├── main/
│   └── Main.java                 # Điểm khởi chạy chương trình (Main Entrypoint)
├── controllers/
│   ├── IGameController.java      # Giao diện định nghĩa các tác vụ điều khiển game
│   └── GameController.java       # Bộ điều khiển chính xử lý logic di chuyển, va chạm và cập nhật trạng thái game
├── models/
│   ├── GameConstants.java        # Nơi lưu trữ các hằng số cấu hình của game (kích thước, tốc độ)
│   ├── GameState.java            # Quản lý điểm số, điểm cao kỷ lục, trạng thái Game Over và Pause
│   ├── Ball.java                 # Quản lý thông tin quả bóng (tọa độ, kích thước, hướng di chuyển)
│   └── Paddle.java               # Quản lý thông tin thanh trượt/nhân vật (tọa độ, di chuyển trái/phải)
├── views/
│   ├── GameWindow.java           # Cửa sổ chính JFrame hiển thị các giao diện panels
│   ├── GamePanel.java            # Nơi vẽ đồ họa game, xử lý hoạt ảnh sprite của nhân vật và quả bóng
│   └── MenuPanel.java            # Giao diện menu chính khi khởi động game
└── utils/
    └── SoundPlayer.java          # Bộ quản lý âm thanh (phát nhạc nền lặp lại, phát âm thanh va chạm/thua cuộc)
```

### 📂 Thư mục Tài nguyên (`src/main/resources/`)
- `pic_transparent.png`: Tấm ảnh Sprite chứa các mảnh đồ họa của quả bóng và nhân vật.
- `opensound.wav`: Nhạc nền lúc bắt đầu và trong quá trình chơi game.
- `hit.wav`: Âm thanh phát ra khi hứng bóng thành công.
- `game_over.wav`: Âm thanh phát ra khi người chơi hết lượt chơi.

---

## 🚀 Hướng dẫn cài đặt và chạy Game

### Yêu cầu hệ thống
- Java Development Kit (JDK) 8 hoặc cao hơn.
- Apache Maven (khuyên dùng để tự động build và chạy).

### Cách 1: Chạy bằng Maven (Dòng lệnh)
1. Dọn dẹp và biên dịch dự án:
   ```bash
   mvn clean package
   ```
2. Chạy ứng dụng:
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```

### Cách 2: Biên dịch và chạy thủ công bằng lệnh Java (`javac` / `java`)
1. Biên dịch toàn bộ file mã nguồn:
   ```bash
   javac -encoding UTF-8 -d target/classes -sourcepath src/main/java src/main/java/main/Main.java
   ```
2. Sao chép thư mục tài nguyên âm thanh/hình ảnh vào thư mục classes đã biên dịch:
   * Trên Windows (PowerShell):
     ```powershell
     Copy-Item -Path "src/main/resources/*" -Destination "target/classes" -Force
     ```
3. Chạy file class chính:
   ```bash
   java -cp target/classes main.Main
   ```

---

## 📥 Hướng dẫn tải & chơi Game (Bản build sẵn .exe)

Dành cho những ai muốn chơi game ngay mà không cần cài đặt môi trường lập trình JDK hay tự biên dịch code:
1. Đi tới mục **Releases** ở phía bên phải của trang dự án này trên GitHub.
2. Tải về file `PingBallGame.zip` ở phiên bản mới nhất.
3. Giải nén file `.zip` vừa tải về trên máy tính của bạn.
4. Mở thư mục đã giải nén và nhấp đúp (double-click) vào file **`PingBallGame.exe`** để bắt đầu chơi game! (Máy tính của bạn hoàn toàn không cần cài đặt sẵn Java).
