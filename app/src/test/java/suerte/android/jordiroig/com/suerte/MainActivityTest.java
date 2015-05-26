package suerte.android.jordiroig.com.suerte;

import android.content.Intent;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class MainActivityTest {

    private MainActivity activity;

    private EditText result;
    private Integer valor;
    private Integer min;
    private Integer max;

    @Before
    public void setup() throws Exception
    {
        activity = Robolectric.setupActivity(MainActivity.class);
        result = (EditText) activity.findViewById(R.id.main_input);
        valor = 5;
        min = 1;
        max = 10;
    }

    @Test
    public void checkEditText() throws Exception
    {
        result.setText(valor.toString());
        assertThat(result.getText().toString()).isEqualTo(valor.toString());
    }

    @Test
    public void clickingButton_shouldStartResultActivity() throws Exception
    {
        result.setText(valor.toString());
        activity.findViewById(R.id.main_button).performClick();

        Intent expectedIntent = new Intent(activity, ResultActivity.class);
        expectedIntent.putExtra("value", valor);
        expectedIntent.putExtra("min", min);
        expectedIntent.putExtra("max", max);

        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }

    @Test
    public void clickingButton_shouldStartResultActivity2()
    {
        result.setText(valor.toString());
        activity.findViewById(R.id.main_button).performClick();

        ShadowActivity shadow = shadowOf(activity);
        Intent nextActivity = shadow.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(nextActivity);
        assertEquals(ResultActivity.class.getName(),
                shadowIntent.getComponent().getClassName());
    }

    @Test
    public void prova()
    {
        result.setText(valor.toString());
        activity.findViewById(R.id.main_button).performClick();

        ShadowActivity shadow = shadowOf(activity);
        Intent nextActivity = shadow.getNextStartedActivity();
        ResultActivity activity2 = Robolectric.buildActivity(ResultActivity.class).withIntent(nextActivity).create().get();

        ShadowActivity testActivity = Robolectric.shadowOf(activity2);

        Intent nextIntent = activity2.getIntent();
        String extra = nextIntent.getExtras().get("value").toString();

        assertEquals(extra,
                valor.toString());

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

    @Test
    public void prova2()
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