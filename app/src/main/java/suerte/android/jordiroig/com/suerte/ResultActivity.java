package suerte.android.jordiroig.com.suerte;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Random;

public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //recogemos los valores de la pantalla anterior
        Intent intent = getIntent();
        int value = Integer.parseInt(intent.getExtras().get("value").toString());
        int min = Integer.parseInt(intent.getExtras().get("min").toString());
        int max = Integer.parseInt(intent.getExtras().get("max").toString());

        //generamos un valor aleatorio
        Random random = new Random();
        int num = random.nextInt((max - min) + 1) + min;

        //instanciamos los elementos de la vista
        TextView result = (TextView)findViewById(R.id.result_text);
        TextView received = (TextView)findViewById(R.id.received_text);
        TextView correct = (TextView)findViewById(R.id.correct_text);

        //seteamos los textos con los valores
        received.setText("El número introducido era: " + value);
        correct.setText("El número correcto era: " + num);

        if(num == value) //comprovamos si el jugador ha acertado
        {
            result.setText("Bien!!\nAcertado!!!!"); //seteamos el texto
            result.setTextColor(Color.GREEN);       //seteamos el color
        }
        else
        {
            result.setText("Fallo...\nVuelve a intentarlo");    //seteamos el texto
            result.setTextColor(Color.RED);                     //seteamos el color
        }
    }

    @Override
    public void onBackPressed() { //establecemos una animación cuando el jugador presiona el botón volver
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
