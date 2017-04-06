package hssports.rastreamento_correios;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import Adapter.RecyclerViewAdapter_Rast;
import Classes.Const;
import Classes.JSON;
import Classes.Objeto_Hist;
import Classes.Parametro;
import Classes.Utilidade;
import WebService.AsyncGeneric;
import WebService.IAsyncResponse;

public class Tela_resultado_consulta extends Activity {
    Context ctx;
    Button btnNotificar;
    int iQtdTentativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_resultado_consulta);
        ctx = this;
        String sCodigo = getIntent().getStringExtra(Const.EXTRA_Tela_Principal_Codigo);

      //  btnNotificar = (Button) findViewById(R.id.buttonNotificar);
    //   btnNotificar.setVisibility(View.GONE);
     //   btnNotificar.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View view) {

           // }
        //});

        iQtdTentativas = 0;
        Busca_WS(sCodigo);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void AlertDialogNotificar() {

    }

    public void Busca_WS(final String sCodigo) {
        ArrayList<Parametro> listaParametros = new ArrayList<Parametro>();
        listaParametros.add(new Parametro("sCod", sCodigo));
        AsyncGeneric asyncTask = new AsyncGeneric(listaParametros, "BuscaPorObjeto",
                ctx, new IAsyncResponse() {

            @Override
            public void processFinish(String output) {

                ArrayList<Objeto_Hist> lista_historico = new ArrayList<Objeto_Hist>();
                try {
                    lista_historico = (ArrayList<Objeto_Hist>) JSON.JsonToListObject(output, Objeto_Hist.class);
                } catch (Exception ee) {
                    String e = ee.getMessage();
                    if (iQtdTentativas <= 3)
                        Busca_WS(sCodigo);
                    else
                        Toast.makeText(ctx, "Não foi possivél pesquisar esse código, verifique sua conexão!", Toast.LENGTH_LONG).show();

                    iQtdTentativas++;

                }
                RecyclerView.Adapter mAdapter;
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                mRecyclerView.setHasFixedSize(true);
                GridLayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new RecyclerViewAdapter_Rast(lista_historico, ctx);


                mRecyclerView.setAdapter(mAdapter);
                boolean bObjetoEncontrado = true;

                for (Objeto_Hist objHist : lista_historico) {
                    try {
                        String sData = objHist.getDatahora();
                        String sAno = sData.substring(0, 4);
                        Integer iAno = Integer.parseInt(sAno);
                        if (iAno < 2000) {
                            bObjetoEncontrado = false;
                            break;
                        } else {
                            bObjetoEncontrado = true;
                            break;
                        }

                    } catch (Exception e) {

                        bObjetoEncontrado = false;
                    }
                }

                if (bObjetoEncontrado) {
                    ArrayList<String> listaUltimas = new ArrayList<String>();
                    listaUltimas = (ArrayList<String>) JSON.JsonToListObject(Utilidade.GetPreference(Const.key_Ultimas_Pesquisas, "[]", ctx), String.class);
                    boolean bTemnaLIsta = false;
                    for (String codigoGrav : listaUltimas) {
                        if (codigoGrav.equals(sCodigo)) {
                            bTemnaLIsta = true;
                            break;
                        }
                    }
                    if (!bTemnaLIsta) {
                        listaUltimas.add(sCodigo);
                        Utilidade.SetPreferences(Const.key_Ultimas_Pesquisas, JSON.ObjToJson(listaUltimas), ctx);
                    }
                }


            }
        }, true);
        asyncTask.execute("");
    }
}
