package Utils;

import java.util.concurrent.ConcurrentHashMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Lưu request & response vào từng luồng (thread) riêng biệt.
 * Dùng để truy cập nhanh trong các helper như CookieUtils, SessionUtils.
 */
public class RRSharer {
    private static final ConcurrentHashMap<Long, HttpServletRequest> reqs = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, HttpServletResponse> resps = new ConcurrentHashMap<>();

    // Lưu request và response hiện tại vào luồng
    public static void add(HttpServletRequest req, HttpServletResponse resp) {
        long id = Thread.currentThread().getId();
        reqs.put(id, req);
        resps.put(id, resp);
    }

    // Gỡ request và response khỏi luồng khi xong xử lý
    public static void remove() {
        long id = Thread.currentThread().getId();
        reqs.remove(id);
        resps.remove(id);
    }

    // Lấy request từ luồng hiện tại
    public static HttpServletRequest request() {
        return reqs.get(Thread.currentThread().getId());
    }

    // Lấy response từ luồng hiện tại
    public static HttpServletResponse response() {
        return resps.get(Thread.currentThread().getId());
    }
}

