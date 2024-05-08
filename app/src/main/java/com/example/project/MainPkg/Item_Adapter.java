package com.example.project.MainPkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.ArrayList;

public class Item_Adapter extends RecyclerView.Adapter<Item_Adapter.View_Holder> {
    Context context;

    ArrayList<Item_Modal> arrayList;

    Item_Adapter (Context context, ArrayList<Item_Modal> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_rows,parent,false);

        View_Holder viewHolder=new View_Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {

        holder.image.setImageResource(arrayList.get(position).img);
        holder.name.setText(arrayList.get(position).name);
        holder.amount.setText(arrayList.get(position).amount);


//click listener on complete row or anywhere in a row
//holder.itemView.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
        // int selectedPosition= holder.getAdapterPosition();
//        Toast.makeText(context, ""+arrayList.get(selectedPosition).name+"\n"+arrayList.get(selectedPosition).number, Toast.LENGTH_SHORT).show();
//    }
//});


//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int selectedPosition=holder.getAdapterPosition();
//                Intent intent=new Intent(context, Calendar.class);
//                intent.putExtra("image",arrayList.get(selectedPosition).img);
//                intent.putExtra("name", arrayList.get(selectedPosition).name);
//                intent.putExtra("amount",arrayList.get(selectedPosition).amount);
//                context.startActivity(intent);
//            }
//        });

        // long click listener
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//               int selectedPosition=holder.getAdapterPosition();
//                Toast.makeText(context, ""+arrayList.get(selectedPosition).name, Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });




    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }
    //Create a viewholder class that extends Recycler.ViewHolder
    public class View_Holder extends RecyclerView.ViewHolder{
        //Create Variables
        TextView name, amount;
        ImageView image;
        //Create Constructor
        public View_Holder(@NonNull View itemView) {
            super(itemView);
//get views in the item of the viewholder


            name=itemView.findViewById(R.id.item_name);
            amount=itemView.findViewById(R.id.item_amount);
            image=itemView.findViewById(R.id.image);

        }
    }


}
