package poly.phone.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class XIcon {

    // Lấy icon từ thư mục java (cho các icon nút như accept.png)
    public static ImageIcon getIcon(String name) {
        URL url = XIcon.class.getResource("/poly/phone/icons/" + name);
        if (url != null) {
            return new ImageIcon(url);
        }
        System.err.println("❌ Không tìm thấy icon: " + name);
        return new ImageIcon(); // tránh null pointer
    }

    // Lấy hình ảnh từ resources (logo, avatar mặc định...)
    public static ImageIcon getImage(String name) {
        URL url = XIcon.class.getResource("/poly/phone/image/" + name);
        if (url != null) {
            return new ImageIcon(url);
        }
        System.err.println("❌ Không tìm thấy ảnh trong resources: " + name);
        return new ImageIcon(); // tránh null pointer
    }

    // Set icon vào JLabel từ đường dẫn file ngoài
    public static void setIcon(JLabel label, String path) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getImage() != null) {
            icon = getScaledIcon(icon, label.getWidth(), label.getHeight());
            label.setIcon(icon);
        } else {
            System.err.println("❌ Không thể load ảnh: " + path);
        }
    }

    // Set icon vào JLabel từ File
    public static void setIcon(JLabel label, File file) {
        setIcon(label, file.getAbsolutePath());
    }

    // Resize ảnh cho vừa khung JLabel
    public static ImageIcon getScaledIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        if (img == null) return icon;
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    // Sao chép file ảnh được chọn về thư mục lưu ảnh (ví dụ: "photos")
    public static File copyTo(File file, String dir) {
        File folder = new File(dir);
        if (!folder.exists()) folder.mkdirs();

        File newFile = new File(folder, file.getName());
        try {
            Path source = file.toPath();
            Path target = newFile.toPath();
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }
}



