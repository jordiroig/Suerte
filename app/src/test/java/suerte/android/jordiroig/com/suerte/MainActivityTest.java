package suerte.android.jordiroig.com.suerte;

import android.content.Intent;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void clickingButton_shouldStartResultActivity2() throws Exception
    {
        result.setText(valor.toString());
        activity.findViewById(R.id.main_button).performClick();

        ShadowActivity shadowActivity = shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);

        assertThat(shadowActivity.findViewById(R.id.received_text)).isEqualTo(valor);
    }
}