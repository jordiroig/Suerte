package suerte.android.jordiroig.com.suerte;

import android.content.Intent;
import android.graphics.Color;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class ResultActivityTest {
    private ResultActivity activity2;
    private Integer valor;
    private Integer min;
    private Integer max;

    @Before
    public void setup() throws Exception
    {
        valor = 5;
        min = 1;
        max = 10;
    }

    @Test
    public void checkResult() throws Exception
    {
        Intent intent = new Intent(Robolectric.getShadowApplication().getApplicationContext(), ResultActivity.class);
        intent.putExtra("value", valor);
        intent.putExtra("min", min);
        intent.putExtra("max", max);
        ResultActivity activity = Robolectric.buildActivity(ResultActivity.class).withIntent(intent).create().get();
        ShadowActivity testActivity = Robolectric.shadowOf(activity);

        TextView correct = (TextView) testActivity.findViewById(R.id.correct_text);
        TextView result_text = (TextView) testActivity.findViewById(R.id.result_text);
        String number = correct.getText().toString().substring(correct.getText().toString().length() - 1);
        if(valor.toString().equals(number))
        {
            assertEquals(result_text.getCurrentTextColor(), Color.GREEN);
        }
        else
        {
            assertEquals(result_text.getCurrentTextColor(), Color.RED);
            assertEquals(result_text.getText().toString(), "Fallo...\nVuelve a intentarlo");
        }
    }
}
