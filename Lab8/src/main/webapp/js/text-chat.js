var websocket = null;

function init() {
    websocket = new WebSocket('ws://localhost:8080/Lab8/text/chat');

    // Khi kết nối thành công
    websocket.onopen = function(resp) {
        console.log("onopen", resp);
    }

    // Khi nhận tin nhắn từ server
    websocket.onmessage = function(resp) {
        var message = resp.data;
        var html = document.getElementById('messages').innerHTML;

        document.getElementById('messages').innerHTML =
            `${html}<p>${message}</p>`;

        console.log("onmessage", resp.data);
    }

    // Khi xảy ra lỗi
    websocket.onerror = function(resp) {
        alert("An error occurred, closing application");
        console.log("onerror", resp);
    }

    // Khi kết nối bị đóng
    websocket.onclose = function(resp) {
        alert(resp.reason || "Goodbye");
        console.log("onclose", resp);
    }
}

// Gửi tin nhắn
function send() {
    var msg = document.getElementById("message").value;
    websocket.send(msg);
    document.getElementById("message").value = "";
}
