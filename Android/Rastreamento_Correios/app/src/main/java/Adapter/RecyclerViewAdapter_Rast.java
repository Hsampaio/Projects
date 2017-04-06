package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Classes.Objeto_Hist;
import hssports.rastreamento_correios.R;

/**
 * Created by Helton on 20/07/2016.
 */
public class RecyclerViewAdapter_Rast extends RecyclerView
        .Adapter<RecyclerViewAdapter_Rast
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Objeto_Hist> mDataset;
    private static MyClickListener myClickListener;
    Context ctx;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView txtLocal;
        TextView txtDescricao;
        TextView txtSituacao;
        TextView txtDataHora;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtLocal = (TextView) itemView.findViewById(R.id.textView_Local);
            txtDescricao = (TextView) itemView.findViewById(R.id.textView_descricao_card);
            txtSituacao = (TextView) itemView.findViewById(R.id.textView_Situacao);
            txtDataHora = (TextView) itemView.findViewById(R.id.textView_DataHora);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           // myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
       // this.myClickListener = myClickListener;
    }

    public RecyclerViewAdapter_Rast(ArrayList<Objeto_Hist> myDataset, Context context) {
        mDataset = myDataset;
        ctx = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {


        String sDataHora = "";
        try {
            String sData = mDataset.get(position).getDatahora();
            String sAno = sData.substring(0, 4);
            String sMes = sData.substring(5, 7);
            String sDia = sData.substring(8, 10);
            String sHora = sData.substring(11, 13);
            String sMinuto = sData.substring(14, 16);
            sDataHora = sDia+"/"+sMes+"/"+sAno+" - "+sHora+":"+sMinuto;
            if(sAno.equals("0001")) {
                sDataHora = "";

            }
            else
            {

            }
        } catch (Exception e) {
            sDataHora = mDataset.get(position).getDatahora();
            e.printStackTrace();
        }


        holder.txtLocal.setText(mDataset.get(position).getLocal());
        holder.txtDescricao.setText(mDataset.get(position).getDescricao());
        holder.txtSituacao.setText(mDataset.get(position).getSituacao());
        holder.txtDataHora.setText(sDataHora);

        if (mDataset.get(position).getDescricao().equals("")) {
            holder.txtDescricao.setVisibility(View.GONE);
        } else {
            holder.txtDescricao.setVisibility(View.VISIBLE);
        }

        if (String.valueOf(mDataset.get(position).getLocal()).equals("")) {
            holder.txtLocal.setVisibility(View.GONE);
        } else {
            holder.txtLocal.setVisibility(View.VISIBLE);
        }

        if (String.valueOf(mDataset.get(position).getSituacao()).equals("")) {
            holder.txtSituacao.setVisibility(View.GONE);
        } else {
            holder.txtSituacao.setVisibility(View.VISIBLE);
        }

        if (sDataHora.equals("")) {
            holder.txtDataHora.setVisibility(View.GONE);
        } else {
            holder.txtDataHora.setVisibility(View.VISIBLE);
        }

    }


    public void addItem(Objeto_Hist dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}

