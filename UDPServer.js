const dgram = require('dgram');
const server = dgram.createSocket('udp4');

server.on('error', (err) => {
  console.log(`server error:\n${err.stack}`);
  server.close();
});

server.on('message', (msg, rinfo) => {
  console.log(`server got: ${msg} from ${rinfo.address}:${rinfo.port}`);
  const jsonmsg = JSON.parse(msg);
  console.log(`my name is ${jsonmsg.name}`);
  console.log(`I live in (${jsonmsg.location.latitude},${jsonmsg.location.longitude})`);
});

server.on('listening',() => {
  const address = server.address();
  console.log(`server listening ${address.address}:${address.port}`);
});

server.bind(18181); // server listening 0.0.0.0:18181