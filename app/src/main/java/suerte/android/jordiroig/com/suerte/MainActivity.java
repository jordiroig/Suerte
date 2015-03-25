package suerte.android.jordiroig.com.suerte;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button main_button;
    EditText main_input;
    int min;
    int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        min = 1;
        max = 10;
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
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Debe introducir un valor entre " + min + " y " + max, Toast.LENGTH_SHORT).show();
                }
            } else
            {
                Toast.makeText(getApplicationContext(), "Debe introducir un valor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
