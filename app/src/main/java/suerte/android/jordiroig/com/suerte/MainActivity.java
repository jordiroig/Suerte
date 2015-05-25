package suerte.android.jordiroig.com.suerte;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    Button main_button;
    EditText main_input;
    int min;
    int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definimos el intervalo de números para jugar
        min = 1;
        max = 10;

        //instanciamos los elementos de la vista y creamos un listener para el botón
        main_input = (EditText)findViewById(R.id.main_input);
        main_button = (Button)findViewById(R.id.main_button);
        main_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int targetId = v.getId();
        if(targetId == R.id.main_button) //No haria falta, ya que solo hemos creado un listener
        {
            String valor = main_input.getText().toString();
            if (valor.length() > 0) { //Comprobamos que se ha introducido algún valor
                int num = Integer.parseInt(main_input.getText().toString());
                if(num >= min && num <= max) { //Comprobamos que el valor está entre los parametros establecidos
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("value", main_input.getText());
                    intent.putExtra("max", max);
                    intent.putExtra("min", min);
                    startActivity(intent); //llamamos el segundo activity, pasandole el valor introducido y el intervalo
                    overridePendingTransition(R.anim.left_in, R.anim.left_out); //definimos una animación para la transición entre activities
                }
                else
                {   //Avisamos del error
                    Toast.makeText(getApplicationContext(), "Debe introducir un valor entre " + min + " y " + max, Toast.LENGTH_SHORT).show();
                }
            } else
            {   //Avisamos del error
                Toast.makeText(getApplicationContext(), "Debe introducir un valor", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
