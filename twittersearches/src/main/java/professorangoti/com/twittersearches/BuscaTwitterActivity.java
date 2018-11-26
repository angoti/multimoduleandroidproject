package professorangoti.com.twittersearches;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class BuscaTwitterActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    // nome do arquivo XML de SharedPreferences que armazena as pesquisas salvas
    private static final String SEARCHES = "searches";
    private EditText queryEditText; // EditText onde o usuário digita uma consulta
    private EditText tagEditText; // EditText onde o usuário identifica uma consulta
    private ListView listaListView;
    private SharedPreferences savedSearches; // pesquisas favoritas do usuário
    private ArrayList<String> tags; // lista de identificadores das pesquisas salvas
    private ArrayAdapter<String> adapter; // vincula identificadores a ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter);

        // obtém referências para os EditText
        queryEditText = (EditText) findViewById(R.id.queryEditText);
        tagEditText = (EditText) findViewById(R.id.tagEditText);
        listaListView = (ListView)findViewById(R.id.list);

        // obtém os SharedPreferences que contêm as pesquisas salvas do usuário
        savedSearches = getSharedPreferences(SEARCHES, MODE_PRIVATE);

        // armazena os identificadores salvos em um ArrayList e, então, os ordena
        tags = new ArrayList<String>(savedSearches.getAll().keySet());
        Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);

        // cria ArrayAdapter e o utiliza para vincular os identificadores a ListView
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, tags);
        listaListView.setAdapter(adapter);

        // registra receptor para salvar uma pesquisa nova ou editada
        ImageButton saveButton = (ImageButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        // registra receptor que pesquisa quando o usuário toca em um identificador
        listaListView.setOnItemClickListener(this);

        // configura o receptor que permite ao usuário excluir ou editar uma pesquisa
        listaListView.setOnItemLongClickListener(this);
    }

    private void addTaggedSearch(String tag, String query) {
        // obtém um objeto SharedPreferences.Editor para armazenar novos pares
        // identificador-consulta
        SharedPreferences.Editor preferencesEditor = savedSearches.edit();
        preferencesEditor.putString(tag, query); // armazena pesquisa atual
        preferencesEditor.apply(); // armazena as preferências atualizadas

        // se o identificador é novo, adiciona-o, ordena tags e exibe a lista atualizada
        if (!tags.contains(tag)) {
            tags.add(tag); // adiciona o novo identificador
            Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);
            adapter.notifyDataSetChanged(); // vincula os identificadores a ListView
        }
    }

    @Override
    public void onClick(View v) {
        String query = queryEditText.getText().toString();
        String tag = tagEditText.getText().toString();

        if (!query.isEmpty() && !tag.isEmpty()) {
            // hide the virtual keyboard
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(tagEditText.getWindowToken(), 0);
            addTaggedSearch(tag, query); // add/update the search
            queryEditText.setText(""); // clear queryEditText
            tagEditText.setText(""); // clear tagEditText
            queryEditText.requestFocus(); // queryEditText gets focus
        } else // exibe mensagem solicitando que forneça uma consulta e um identificador
        {   // cria um novo AlertDialog Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(BuscaTwitterActivity.this);
            // configura o título da caixa de diálogo e a mensagem a ser exibida
            builder.setMessage(R.string.missingMessage);
            // fornece um botão OK que simplesmente remove a caixa de diálogo
            builder.setPositiveButton(R.string.OK, null);
            // cria AlertDialog a partir de AlertDialog.Builder
            AlertDialog errorDialog = builder.create();
            errorDialog.show(); // exibe a caixa de diálogo modal
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
