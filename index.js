var express = require('express');
var app = express();
app.set('port', (process.env.PORT || 5000));

var bodyParser = require("body-parser");
app.use(bodyParser.json());                            // soporte para codificar json
app.use(bodyParser.urlencoded( { extended:true } ));   // soporte para decodificar las uri

var firebase = require("firebase");
firebase.initializeApp({
  serviceAccount: "DesarrolloAppsAvanzadas-73837671a097.json",
  databaseURL: "https://desarrolloappsavanzadas.firebaseio.com"
});

// var FCM = require('fcm-push');

app.use(express.static(__dirname + '/public'));

// views is directory for all template files
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.get('/android', function(request, response) {
  response.render('pages/index');
});

// Método post
// https://safe-shelf-98430.herokuapp.com/
// recibe el token y el idUsuario
var tokenDevicesURI = "registrar-usuario";
app.post("/" + tokenDevicesURI, function(request, response) {
  var token = request.body.token;                            // id_dispositivo
  var idUsuario = request.body.idUsuario;                    // id_usuario


  var db = firebase.database();
  var tokenDevices = db.ref(tokenDevicesURI).push();    // PUSH para no sobre-escribir
  tokenDevices.set({
    token: token,
    idUsuario: idUsuario
  });

var path = tokenDevices.toString();

var pathSplit = path.split(tokenDevicesURI + "/");

var idAutoGenerado = pathSplit[1];

var respuesta = generarRespuestaAToken(db, idAutoGenerado);
response.setHeader("Content-Type", "application/json");
response.send(JSON.stringify(respuesta));
});

function generarRespuestaAToken(db, idAutoGenerado) {
  var respuesta = {};
  var usuario = "";
  var ref = db.ref("registrar-usuario");
  ref.on("child_added", function(snapshot, prevChildKey) {
    usuario = snapshot.val();

    respuesta = {
      id: idAutoGenerado,
      token: usuario.token,
      idUsuario: usuario.idUsuario
    };
  });
  return respuesta;
}


app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});
