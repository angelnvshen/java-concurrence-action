<html>

<script>
    var ws = null;
    var target = "ws://localhost:8080/hello"

    function connection() {

        if ('WebSocket' in window) {
            ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(target);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }
    }

    function echo() {
        if (ws != null) {
            var message = document.getElementById('message').value;
            ws.send(message);
        } else {
            alert('WebSocket connection not established, please connect.');
        }
    }

    function disconnect() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
    }


</script>
<body>
<h2>Hello World!</h2>

<button id="connect" onclick="connection();">Connect</button>
<button id="disconnect" onclick="disconnect();">Disconnect</button>
<div>
    <textarea id="message" style="width: 350px">Here is a message!</textarea>
</div>
<div>
    <button id="echo" onclick="echo();">Echo message</button>
</div>
</body>
</html>
