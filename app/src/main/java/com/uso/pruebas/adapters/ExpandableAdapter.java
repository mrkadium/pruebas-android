package com.uso.pruebas.adapters;

import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uso.pruebas.R;
import com.uso.pruebas.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.ExpandableVH> implements Filterable {
    List<User> listUser; //filtered values
    List<User> listAllUser; //all the values

    public ExpandableAdapter(List<User> listUser) {
        this.listUser = listUser;
        this.listAllUser = new ArrayList<>(listUser);
    }

    @NonNull
    @Override
    public ExpandableVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_user, parent, false);
        return new ExpandableVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExpandableVH holder, int position) {
        User user = listUser.get(position);
        holder.getNameUser().setText(user.getName());
        holder.getAgeUser().setText(""+user.getAge());
        holder.getLinearUser().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(holder.getLinearUser(), new AutoTransition());
                holder.getExpandableUser().setVisibility(
                        holder.getExpandableUser().getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        //RUNS ON A BACKGROUND THREAD
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<User> filteredList = new ArrayList<>();

            if(charSequence.toString().isEmpty()) //if the search text is empty
                filteredList.addAll(listAllUser); //add all the users
            else{
                for(User user: listAllUser){ //loop through all the users
                    //if the user value contains the search text
                    if(user.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(user); //add the user to the list
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        //RUNS ON A UI THREAD
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listUser.clear();
            listUser.addAll((Collection<? extends User>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ExpandableVH extends RecyclerView.ViewHolder{
        LinearLayout linearUser;
        LinearLayout expandableUser;
        TextView nameUser;
        TextView ageUser;

        public ExpandableVH(@NonNull View itemView) {
            super(itemView);
            linearUser = itemView.findViewById(R.id.linearUser);
            expandableUser = itemView.findViewById(R.id.expadableUser);
            nameUser = itemView.findViewById(R.id.nameUser);
            ageUser = itemView.findViewById(R.id.ageUser);
        }

        public LinearLayout getLinearUser() {
            return linearUser;
        }

        public LinearLayout getExpandableUser() {
            return expandableUser;
        }

        public TextView getNameUser() {
            return nameUser;
        }

        public TextView getAgeUser() {
            return ageUser;
        }
    }
}
