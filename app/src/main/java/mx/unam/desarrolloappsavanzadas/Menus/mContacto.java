package mx.unam.desarrolloappsavanzadas.Menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mx.unam.desarrolloappsavanzadas.R;
import mx.unam.desarrolloappsavanzadas.mail.SendMail;

public class mContacto extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextMessage;
    private Button botonEnviarComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_contacto);

        toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.tvTituloAppbar)).setText("Contacto");

        editTextName = (EditText) findViewById(R.id.inputNombre);
        editTextEmail = (EditText) findViewById(R.id.inputCorreo);
        editTextMessage = (EditText) findViewById(R.id.inputMensaje);

        botonEnviarComentario = (Button) findViewById(R.id.botonEnviarComentario);

    }

    public void sendEmail(View v){
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        SendMail enviaCorreo = new SendMail(this, email, name, message);
        enviaCorreo.execute();
    }




}
