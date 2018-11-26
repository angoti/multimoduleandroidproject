package professorangoti.com.navigationdrawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;

public class EntradaActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            savedInstanceState = new Bundle();
        savedInstanceState.putInt("miolo", R.layout.activity_entrada);
        super.onCreate(savedInstanceState);
    }
}
