package WebService;

import java.util.ArrayList;


import android.content.Context;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import Classes.Const;
import Classes.Parametro;
import Classes.Utilidade;

public class WebService {
	private static String NAMESPACE = "http://tempuri.org/";
	private static String URL = "http://www.hs.correios.servicos.ws/Service_Busca.asmx";
	
	private static String URL1 = "http://www.hs.correios.servicos.ws/Service_Busca.asmx";
	private static String URL2 = "http://www.hs.correios.servicos.ws/Service_Busca.asmx";
	
	private static String SOAP_ACTION = "http://tempuri.org/";

	
	public static String Invoke_WS(String webMethName, Context ctx,
			ArrayList<Parametro> listaParametro ) {

		//URL = URL2;
		URL = Utilidade.GetPreference(Const.key_URL_WS, URL, ctx);
		
		SoapObject request = new SoapObject(NAMESPACE, webMethName);

		
		PropertyInfo ParamChave = new PropertyInfo();
		ParamChave.setName("sChave");
		ParamChave.setValue("%.~fs2016;");
		ParamChave.setType(String.class);
		request.addProperty(ParamChave);
	
		
		
		for (Parametro parametro : listaParametro) {
			
			PropertyInfo ParamPessoa = new PropertyInfo();
			ParamPessoa.setName(parametro.getNome());
			ParamPessoa.setValue(parametro.getValor());
			ParamPessoa.setType(String.class);
			request.addProperty(ParamPessoa);
		}
		
	
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		new MarshalBase64().register(envelope);
		
		envelope.implicitTypes = true;
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);

		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL, 30000);

		String sRetorno = "";

		try {
			androidHttpTransport.getServiceConnection().setRequestProperty(
					"Connection", "close");
			System.setProperty("http.keepAlive", "false");

			androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);

			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

			sRetorno = response.toString();

		} catch (Exception e) {

			sRetorno = e.getMessage();
			
			if(Utilidade.GetPreference(Const.key_URL_WS, URL, ctx).equals(URL1))
				Utilidade.SetPreferences(Const.key_URL_WS, URL2, ctx);
			else
				Utilidade.SetPreferences(Const.key_URL_WS, URL1, ctx);

		}

		return sRetorno;
	}	
		

}
