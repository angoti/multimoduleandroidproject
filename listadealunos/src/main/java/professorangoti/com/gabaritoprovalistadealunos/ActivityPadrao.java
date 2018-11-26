/*
Esta Activity demonstra a utilização de um ListView extendendo a classe Activity.
Desta forma o id da ListView pode ser qualquer id que você quiser nomear.
No arquivo padrao.xml temos a definição do id assim:

<ListView
        android:id="@+id/listview_listadealunos"

 */

package professorangoti.com.gabaritoprovalistadealunos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import professorangoti.com.gabaritoprovalistadealunos.model.Aluno;
import professorangoti.com.gabaritoprovalistadealunos.services.InterfaceDeServicos;
import professorangoti.com.gabaritoprovalistadealunos.services.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPadrao extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*if (savedInstanceState == null)
            savedInstanceState = new Bundle();
        savedInstanceState.putInt("miolo", R.layout.padrao);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.padrao);
        lista = (ListView) findViewById(R.id.listview_listadealunos);
        imprimeLista();
    }

    private void imprimeLista() {

        InterfaceDeServicos services = RetrofitService.getServico();
        Call<List<Aluno>> call = services.webserviceNotasDeAlunos();

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                List<Aluno> listaAlunosNotas = response.body();
                lista.setAdapter(new ListaAdapter(ActivityPadrao.this, listaAlunosNotas));
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Log.i("debug", t.getMessage());
            }
        });
    }
}
