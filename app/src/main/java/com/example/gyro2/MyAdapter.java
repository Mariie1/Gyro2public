package com.example.gyro2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] objects;
    private List<MyData> data;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView2; //da war mal final

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView2 = (TextView) view.findViewById(R.id.textView2);
        }

        public TextView getTextView() {
            return textView2;
        }
    }
    public MyAdapter(List<MyData> data) {
        this.data = data;
        /*Object strdata = data.getValue(); // Get the value from LiveData

        // Create a string array with the same size as the dataList
        String[] stringArray = new String[1];
        String dataString = strdata.toString();
        stringArray[0] = dataString;
        objects = stringArray;*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StringBuilder sb = new StringBuilder();

        if (data != null && position < data.size()) {
            for (int i = 0; i < data.size(); i++) {
                MyData myData = data.get(i);
                sb.append(myData.data);
                if (i < data.size() - 1) {
                    sb.append("\n");
                }
            }
        }
            holder.getTextView().setText(sb.toString());
        }
        //holder.itemView...


    @Override
    public int getItemCount() {
        //List<MyData> dataList = data.getValue();
        return data != null ? data.size() : 0;
    }
    public void setMyData(List<MyData> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}

