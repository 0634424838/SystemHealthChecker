import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.InetAddress;

class SystemHealthChecker extends JFrame {

    private JLabel cpuLabel, memoryLabel, diskLabel, internetLabel;

    public SystemHealthChecker() {
        setTitle("System Health Checker");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel layout
        setLayout(new GridLayout(4, 1, 10, 10));

        cpuLabel = new JLabel("CPU Usage: (simulated) 35%");
        memoryLabel = new JLabel("Memory Usage: " + getMemoryUsage() + "%");
        diskLabel = new JLabel("Disk Usage: " + getDiskUsage() + "%");
        internetLabel = new JLabel("Internet Connected: " + (isInternetReachable() ? "Yes" : "No"));

        add(cpuLabel);
        add(memoryLabel);
        add(diskLabel);
        add(internetLabel);
    }

    private int getMemoryUsage() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;
        return (int) ((used * 100) / total);
    }

    private int getDiskUsage() {
        File root = new File("/");
        long total = root.getTotalSpace();
        long free = root.getFreeSpace();
        long used = total - free;
        return (int) ((used * 100) / total);
    }

    private boolean isInternetReachable() {
        try {
            InetAddress address = InetAddress.getByName("google.com");
            return address.isReachable(2000);
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SystemHealthChecker checker = new SystemHealthChecker();
            checker.setVisible(true);
        });
    }
}
