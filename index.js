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

var FCM = require('fcm-push');

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
  var idFoto = request.body.idFoto;                          // id_foto


  var db = firebase.database();
  var tokenDevices = db.ref(tokenDevicesURI).push();    // PUSH para no sobre-escribir
  tokenDevices.set({
    token: token,
    idUsuario: idUsuario,
    idFoto: idFoto
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
      idUsuario: usuario.idUsuario,
      idFoto: usuario.idFoto
    };
  });
  return respuesta;
}

// Método get
// https://safe-shelf-98430.herokuapp.com/like-hueso
// Recibe el id y el idUsuario
app.get("/like-hueso/:id/:idUsuario", function(request, response){  // Con : preparamos para recibir datos
  var id      = request.params.id;             // con params recibimos los datos que vienen en una url
  var idUsuario  = request.params.idUsuario;

  var db = firebase.database();               // inicializa el objeto db
  var ref = db.ref("registrar-usuario/" + id);     // recibe la url con el trozo que trae el id autogenerado

  var usuario = "";
  var respuesta = {};

  ref.on("value", function(snapshot) {
      console.log(snapshot.val());
      usuario = snapshot.val();
      var mensaje = idUsuario + " dio un hueso";

      enviarNotificacion(usuario.token, mensaje);    // manda llamar la notificacion

      respuesta = {                                  // define los datos a enviar
        id: id,
        token: usuario.token,
        idUsuario: usuario.idUsuario
      };

      response.send(JSON.stringify(respuesta));

    }, function (errorObject) {               // si se produce algún error enviarmos los datos vacíos
      console.log("The read failed: " + errorObject.code);   // conservamos el console.log en caso de error

      respuesta = {
        id: "",
        token: "",
        idUsuario: ""
      };

      response.send(JSON.stringify(respuesta));

    });

});

function enviarNotificacion(tokenDestinatario, mensaje) {
	var serverKey = 'AIzaSyA3LqaTFfn0ZYgpYdZb0SCP7V8XCBkVhbQ';
	var fcm = new FCM(serverKey);

	var message = {
	    to: tokenDestinatario, // required
	    collapse_key: '',
	    data: {},
	    notification: {
	        title: 'Notificacion desde Servidor',
	        body: mensaje,
	        icon: "dog_bone_48",
	        sound: "default",
	        color: "#00BCD4"
	    }
	};

	fcm.send(message, function(err, response){
	    if (err) {
	        console.log("Something has gone wrong!");
	    } else {
	        console.log("Successfully sent with response: ", response);
	    }
	});
}


/*
function enviarNotificacion(tokenDestinatario, mensaje) {
  var serverKey = 'AIzaSyA3LqaTFfn0ZYgpYdZb0SCP7V8XCBkVhbQ';
  var fcm = new FCM(serverKey);

  var message = {
    to: tokenDestinatario,
    collapse_key: '',
    data: {},
    notification: {
      title: 'Notificacion desde servidor',
      body: mensaje,
      icon: "dog_bone_48",
      sound: "default",
      color: "#00BCD4"
    }
  };

  fcm.send(message, function(err, response) {
    if (err) {
      console.log("Something has gone wrong");
    } else {
      console.log("Successfully sent with response: ", response);
    }
  });

}
*/


app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});
