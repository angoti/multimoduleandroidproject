package professorangoti.com.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import professorangoti.com.gabaritoprovalistadealunos.ActivityPadrao;
import professorangoti.com.twittersearches.BuscaTwitterActivity;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //recupera o id do recurso de layout do miolo
        int layout_miolo = savedInstanceState.getInt("miolo");
        // recupera a instancia do LinearLayout onde será adicionado o miolo da tela
        LinearLayout conteudo_dinamico = (LinearLayout) findViewById(R.id.conteudo_dinamico);
        // infla a tela
        View miolo = getLayoutInflater().inflate(layout_miolo, conteudo_dinamico, false);
        // adiciona a view inflada na tela
        conteudo_dinamico.addView(miolo);

        dl = (DrawerLayout) findViewById(R.id.activity_base);
        t = new ActionBarDrawerToggle(this, dl, R.string.abrir, R.string.fechar);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(this);
    }

    // Responde a eventos do hamburger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    // Responde a eventos do menu da gaveta de navegação
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.conta:
                startActivity(new Intent(this, ActivityPadrao.class));
                break;
            case R.id.config:
                startActivity(new Intent(this, BuscaTwitterActivity.class));
                break;
            case R.id.carrinho:
                startActivity(new Intent(this, Tela2Activity.class));
                break;
            case R.id.teste:
                startActivity(new Intent(this, Tela3Activity.class));
                break;
            default:
                return true;
        }
        return false;
    }

}