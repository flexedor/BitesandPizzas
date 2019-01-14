package com.example.fedor.bitesandpizzas;

import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    private String[] captions;
    private int[] imageIds;
    private Listener listener;

    interface Listener{
        void onClick(int position);
    }

    public CaptionedImagesAdapter(String[]captions,int[] imageIds){
        this.captions=captions;
        this.imageIds=imageIds;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
    private CardView cardView;
    public ViewHolder(CardView v){
        super(v);
        cardView=v;
    }
    }
    @Override
    public int getItemCount(){
        return captions.length;
        }
    public void setListener(Listener listener){
        this.listener=listener;
    }
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,parent,false);
        return new ViewHolder(cv);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final CardView cardView =holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView =(TextView)cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(position);
                }
            }
        });
    }
}
