package professorangoti.com.navigationdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Tela3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            savedInstanceState = new Bundle();
        savedInstanceState.putInt("miolo", R.layout.activity_tela3);
        super.onCreate(savedInstanceState);
    }
}
