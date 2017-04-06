package hssports.rastreamento_correios;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Classes.Const;
import Classes.JSON;
import Classes.Utilidade;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Tela_principal extends Activity implements ZXingScannerView.ResultHandler {
    EditText edtTextCod;
    ImageButton btnPesquisar, btnPesquisarQR;
    Context ctx;
    ListView mainListView;
    ArrayAdapter<String> listAdapter;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        ctx = this;
        btnPesquisar = (ImageButton) findViewById(R.id.imageButtonPesquisa);
        btnPesquisarQR = (ImageButton) findViewById(R.id.imageButtonPesquisaQR);
        edtTextCod = (EditText) findViewById(R.id.editTextCodigo);
        edtTextCod.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mainListView = (ListView) findViewById(R.id.mainListView);


    /*    edtTextCod.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                if((edtTextCod.getText().toString().length() >= 2)&&(edtTextCod.getText().toString().length() <= 11))
                {
                    edtTextCod.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else
                {
                    edtTextCod.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                if(edtTextCod.getText().toString().length() == 13)
                {
                    btnPesquisar.setFocusable(true);
                    btnPesquisar.setFocusableInTouchMode(true);///add this line
                    btnPesquisar.requestFocus();
                }

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

*/

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTextCod.getText().toString().equals("")){
                    Toast.makeText(ctx, "Infome um código para consultar!", Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(Tela_principal.this, Tela_resultado_consulta.class);
                    intent.putExtra(Const.EXTRA_Tela_Principal_Codigo, edtTextCod.getText().toString());
                    startActivity(intent);
                    UltimasPesquisas();
                }
            }
        });

        btnPesquisarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    QrScanner();
                    UltimasPesquisas();
                } catch (Exception e) {
                }
            }
        });
        UltimasPesquisas();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void QrScanner() {


        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera
    }


    @Override
    public void handleResult(Result rawResult) {
        Intent intent1 = new Intent(Tela_principal.this, Tela_principal.class);
        startActivity(intent1);
        Intent intent = new Intent(Tela_principal.this, Tela_resultado_consulta.class);
        intent.putExtra(Const.EXTRA_Tela_Principal_Codigo, rawResult.getText());
        startActivity(intent);
        UltimasPesquisas();

    }

    public void UltimasPesquisas() {

        listAdapter = new ArrayAdapter<String>(ctx, R.layout.simplerow, (ArrayList<String>) JSON.JsonToListObject(Utilidade.GetPreference(Const.key_Ultimas_Pesquisas, "[]", ctx), String.class));
        mainListView.setAdapter(listAdapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String itemValue = (String) mainListView.getItemAtPosition(position);
                Intent intent = new Intent(ctx, Tela_resultado_consulta.class);
                intent.putExtra(Const.EXTRA_Tela_Principal_Codigo, itemValue);
                startActivity(intent);
                UltimasPesquisas();

            }

        });

        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertExcluir(i);
                return false;
            }
        });
    }

    private void alertExcluir(final int iPos) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);

        dialog.setTitle("Atenção")
                .setIcon(null)
                .setMessage("Deseja remover esse código do histórico?")
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        ArrayList<String> lista = new ArrayList<String>();
                        ArrayList<String> novalista = new ArrayList<String>();
                        String sCodSelecionado = (String) mainListView.getItemAtPosition(iPos);


                        lista = (ArrayList<String>) JSON.JsonToListObject(Utilidade.GetPreference(Const.key_Ultimas_Pesquisas, "[]", ctx), String.class);

                        for (String sItem : lista) {
                            if (!sItem.equals(sCodSelecionado))
                                novalista.add(sItem);
                        }
                        Utilidade.SetPreferences(Const.key_Ultimas_Pesquisas, JSON.ObjToJson(novalista), ctx);
                        UltimasPesquisas();
                    }
                }).show();
    }

}
