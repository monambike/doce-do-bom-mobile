package com.example.doce_do_bom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class inicioAdapter extends ArrayAdapter<Dados_Produto> implements Filterable {
    //Variáveis
    private final Context context;
    static public ArrayList<Dados_Produto> produtos;
    CustomFilter filter;
    ArrayList<Dados_Produto> filterList;

    public inicioAdapter(Context context, ArrayList<Dados_Produto> produtos) {
        super(context, R.layout.activity_iniciolista, produtos);
        this.context = context;
        this.produtos = produtos;
        this.filterList = produtos;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_iniciolista, parent, false);

        TextView txtNome = rowView.findViewById(R.id.ini_txtNome);
        TextView txtDescricao = rowView.findViewById(R.id.ini_txtDescricao);
        TextView txtPreco = rowView.findViewById(R.id.ini_txtPreco);
        final ImageView imgFavorito = rowView.findViewById(R.id.ini_imgFavorito);
        ImageView imgProduto = rowView.findViewById(R.id.ini_imgProduto);

        txtNome.setText(produtos.get(position).getsNome());
        txtDescricao.setText(produtos.get(position).getsDescricao());
        txtPreco.setText(produtos.get(position).getsPreco());
        imgProduto.setImageResource(produtos.get(position).intImagem);

        imgFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produtos.get(position).getbFavoritado() == false) {
                    imgFavorito.setImageResource(R.drawable.star_01);
                    produtos.get(position).setbFavoritado(true);
                }else if(produtos.get(position).getbFavoritado() == true) {
                    imgFavorito.setImageResource(R.drawable.star);
                    produtos.get(position).setbFavoritado(false);
                }
            }
        });

        return rowView;
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new CustomFilter();
        }

        return super.getFilter();
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();

                ArrayList<Dados_Produto> filters = new ArrayList<>();

                for(int i = 0; i < filterList.size();i++){
                    if(filterList.get(i).getsNome().toUpperCase().contains(constraint)){
                        Dados_Produto p = new Dados_Produto(filterList.get(i).getsNome(), filterList.get(i).getsDescricao(), filterList.get(i).getsPreco(), filterList.get(i).getbFavoritado(), filterList.get(i).getbDisponivel(), filterList.get(i).intImagem);
                        filters.add(p);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }else{
                results.count = filterList.size();
                results.values = filterList;
            }

            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            produtos = (ArrayList<Dados_Produto>) results.values;
            notifyDataSetChanged();
        }
    }
}