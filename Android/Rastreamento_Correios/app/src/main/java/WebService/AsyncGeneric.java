package WebService;

import java.util.ArrayList;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import Classes.Parametro;


public class AsyncGeneric extends AsyncTask<String, Object, String> {

	public IAsyncResponse delegate = null;
	public Context ctx;
	public ProgressDialog mprogressDialogGet;
	public String metodoWS = "";
	public ArrayList<Parametro> listaParametro;
	public boolean bExibeProgresDialog = true;

	public AsyncGeneric(ArrayList<Parametro> arraylistParametro,
			String nomeMetodoWS, Context context, IAsyncResponse asyncResponse,
			boolean bExibirProgress) {
		delegate = asyncResponse;
		ctx = context;
		bExibeProgresDialog = bExibirProgress;

		if (bExibeProgresDialog) {
			mprogressDialogGet = ProgressDialog.show(ctx, "Aguarde",
					"Baixando informações");
		}

		metodoWS = nomeMetodoWS;
		listaParametro = arraylistParametro;
	}


	@Override
	protected String doInBackground(String... params) {

		return WebService.Invoke_WS(metodoWS, ctx, listaParametro);

	}

	@Override
	protected void onPostExecute(String result) {
		if (bExibeProgresDialog)
			mprogressDialogGet.dismiss();
		delegate.processFinish(result);
	}

}