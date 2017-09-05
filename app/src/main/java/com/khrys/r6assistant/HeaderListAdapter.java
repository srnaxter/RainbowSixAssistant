package com.khrys.r6assistant;

/*
 * Created by Chrysler on 3/8/2017.
 * RainbowSixAssistant
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khrys.r6assistant.operators.Operator;
import com.khrys.r6assistant.operators.OperatorActivity;
import com.khrys.r6assistant.weapons.WeaponActivity;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

public class HeaderListAdapter extends RecyclerView.Adapter<HeaderListAdapter.MyViewHolder> implements StickyHeaderAdapter<HeaderListAdapter.HeaderHolder>
{
    private List<Integer> img;
    private List<String> txt;
    private LayoutInflater inflater;

    public HeaderListAdapter(ArrayList<Integer> img, ArrayList<String> txt)
    {
        this.img = img;
        this.txt = txt;
    }

    @Override
    public int getItemCount()
    {
        return txt.size();
    }

    private boolean isWeapon()
    {
        return getItemCount() > 60;
    }

    @Override
    public HeaderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_weapons, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeaderListAdapter.MyViewHolder holder, int position)
    {
        if(txt != null)
        {
            Integer imgdata = img.get(position);
            String txtdata = txt.get(position);
            holder.display(imgdata,txtdata);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView imageV;
        private final TextView textV;
        private final Context context;

        MyViewHolder(final View itemView)
        {
            super(itemView);

            imageV = itemView.findViewById(R.id.imageVarme);
            textV = itemView.findViewById(R.id.textVarme);
            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String typeExtra;
                    Intent newAct;

                    if(isWeapon())
                    {
                        newAct = new Intent(context, WeaponActivity.class);
                        typeExtra = "arme";
                    }
                    else
                    {
                        newAct = new Intent(context, OperatorActivity.class);
                        typeExtra = "operator";
                    }

                    newAct.putExtra(typeExtra, textV.getText().toString());
                    context.startActivity(newAct);

                }
            });
        }

        void display(Integer imgdata, String txtdata)
        {
            imageV.setImageResource(imgdata);
            textV.setText(txtdata);
        }
    }

    @Override
    public long getHeaderId(int position)
    {
        /*        Compte
                    Assault : 15
                    Pistols : 16
                    LMG : 4
                    SMG : 2
                    Sniper : 4
                    Shotgun : 14
                    Submachine : 15
                    TOTAL : 70
         */
        if(isWeapon())
        {
            if(position<15){
                return 0;
            }else if(position<31){
                return 15;
            }else if(position<35){
                return 31;
            }else if(position<37){
                return 35;
            }else if(position<41){
                return 37;
            }else if(position<55){
                return 41;
            }else if(position<70){
                return 55;
            }else{
                return 62;
            }
        }
        else
        {
            if(position<16){
                return 0;
            }else if(position<33){
                return 16;
            }else {
                return 0;
            }
        }

    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent)
    {
        final View view = inflater.inflate(R.layout.header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position)
    {
        int txtId = 0;
        if(isWeapon())
        {
            if(position<15){
                txtId = R.string.assault;
            }else if(position<31){
                txtId = R.string.pistol;
            }else if(position<35){
                txtId = R.string.lightmachine;
            }else if(position<37){
                txtId = R.string.machinepistol;
            }else if(position<41){
                txtId = R.string.marksmanrifle;
            }else if(position<55){
                txtId = R.string.shotgun;
            }else if(position<70){
                txtId = R.string.submachinegun;
            }
        }
        else
        {
            if(position<16){
                txtId = R.string.attackers;
            }else if(position<33){
                txtId = R.string.defenders;
            }
        }
        viewholder.header.setText(txtId);
    }

    class HeaderHolder extends RecyclerView.ViewHolder
    {
        TextView header;

        HeaderHolder(View itemView)
        {
            super(itemView);

            header = (TextView) itemView;
        }
    }
}