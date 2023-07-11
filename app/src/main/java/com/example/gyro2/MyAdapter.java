package com.example.gyro2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyData> data;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView2;

        public ViewHolder(View view) {
            super(view);
            textView2 = (TextView) view.findViewById(R.id.textView2);
        }

        public TextView getTextView() {
            return textView2;
        }
    }
    public MyAdapter(List<MyData> data) {
        this.data = data;
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
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }
    public void setMyData(List<MyData> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}

