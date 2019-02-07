var net = require('net');

const writeData = (socket, data) => {
  var success = !socket.write(data);
  if (!success) {
    (function (socket, data) {
      socket.once('drain', function () {
        writeData(socket, data);
      });
    })(socket, data);
  }
}

var server = net.createServer((client) => {
  console.log('Client connection : ');
  console.log(`local = ${client.localAddress} : ${client.localPort}`);
  console.log(`remote = ${client.remoteAddress} : ${client.remotePort}`);
  client.setEncoding('utf8');

  client.on('data', (data) => {
    console.log('Received data from client on port %d: %s', client.remotePort, data.toString());
    console.log('Bytes received: ' + client.bytesRead);
    writeData(client, 'Server Received: ' + data.toString());
    console.log('Bytes sent: ' + client.bytesWritten);
  });
  client.on('end', () => {
    console.log('Client disconnected');
    server.getConnections((err, count) => {
      console.log('Remaining Connections: ' + count);
    });
  });
  client.on('error', (err) => {
    console.log('Socket Error: ', JSON.stringify(err));
  });
  client.on('timeout', () => {
    console.log('Socket Timed out');
  });
});

server.listen(9999, () => {
  console.log('Server listening: ' + JSON.stringify(server.address()));
  server.on('close', () => {
    console.log('Server Terminated');
  });
  server.on('error', (err) => {
    console.log('Server Error: ', JSON.stringify(err));
  });
})