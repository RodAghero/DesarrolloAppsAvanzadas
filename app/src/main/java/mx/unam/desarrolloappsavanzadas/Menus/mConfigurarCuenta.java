package mx.unam.desarrolloappsavanzadas.Menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import mx.unam.desarrolloappsavanzadas.Model.CuentaInstagram;
import mx.unam.desarrolloappsavanzadas.R;

public class mConfigurarCuenta extends AppCompatActivity {

    private Toolbar toolbar;
    public Button botonGuardarCuenta;

    private TextView visualizacionCuentaSeleccionada;
    private CuentaInstagram cuentaInstagramSeleccionada;
    private RadioButton radioButton01, radioButton02, radioButton03, radioButtonActual;
    private RadioGroup radioGroup;
    private int iRadio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_configurar_cuenta);

        toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.tvTituloAppbar)).setText("Configurar Cuenta");

        radioButton01 = (RadioButton) findViewById(R.id.opcion01);
        radioButton02 = (RadioButton) findViewById(R.id.opcion02);
        radioButton03 = (RadioButton) findViewById(R.id.opcion03);

        visualizacionCuentaSeleccionada = (TextView) findViewById(R.id.tvcuentaSeleccionada);
        botonGuardarCuenta = (Button) findViewById(R.id.botonGuardarCuenta);

        cuentaInstagramSeleccionada = CuentaInstagram.getItem(0);
        radioButton01.setText(cuentaInstagramSeleccionada.getNombreCompletoUsuario());
        cuentaInstagramSeleccionada = CuentaInstagram.getItem(1);
        radioButton02.setText(cuentaInstagramSeleccionada.getNombreCompletoUsuario());
        cuentaInstagramSeleccionada = CuentaInstagram.getItem(2);
        radioButton03.setText(cuentaInstagramSeleccionada.getNombreCompletoUsuario());

        radioGroup = (RadioGroup) findViewById(R.id.grupoRadios);

        seleccionPerfil();

        botonGuardarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuentaInstagram.seleccionUsuario = cuentaInstagramSeleccionada.getIdUsuario();
                Toast.makeText(mConfigurarCuenta.this, "Regresando a la pantalla principal", Toast.LENGTH_LONG).show();
                finish();
            }

        });

    }

    public void seleccionPerfil(){
        iRadio = radioGroup.getCheckedRadioButtonId();
        radioButtonActual = (RadioButton) findViewById(iRadio);
        iRadio = radioGroup.indexOfChild(radioButtonActual);
        cuentaInstagramSeleccionada = CuentaInstagram.getItem(iRadio);
        visualizacionCuentaSeleccionada.setText(cuentaInstagramSeleccionada.getNombreUsuario());
    }

    public void radioClicked(View view){
        seleccionPerfil();
    }


}
