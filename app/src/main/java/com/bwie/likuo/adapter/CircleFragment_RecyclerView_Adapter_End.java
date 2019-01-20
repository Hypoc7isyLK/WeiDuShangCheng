package com.bwie.likuo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bwie.likuo.R;
import com.bwie.likuo.bean.CircleFragment_ShowCircle;
import com.bwie.likuo.util.StringUtils;
import com.bwie.likuo.util.recyclerview.SpacingItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * date:2018/12/8
 * author:李阔(淡意衬优柔)
 * function:
 */
public class CircleFragment_RecyclerView_Adapter_End extends RecyclerView.Adapter<CircleFragment_RecyclerView_Adapter_End.ViewHolder> {

    Context context;
    List<CircleFragment_ShowCircle> mResultBeans;



    public CircleFragment_RecyclerView_Adapter_End(Context context) {
        this.context = context;
        mResultBeans = new ArrayList<>();
    }

    public void reset(List<CircleFragment_ShowCircle> result) {
        mResultBeans.addAll(result);
        notifyDataSetChanged();
    }

    public void clear() {
        mResultBeans.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.circlefragment_recyclerview_item, null);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CircleFragment_ShowCircle circleFragment_showCircle = mResultBeans.get(i);
        viewHolder.circlefragment_recycle_sim.setImageURI(Uri.parse(mResultBeans.get(i).getHeadPic()));
        viewHolder.circlefragment_recycle_name.setText(mResultBeans.get(i).getContent());
        viewHolder.circlefragment_recycle_message.setText(mResultBeans.get(i).getNickName());
        //viewHolder.circlefragment_recycle_sim2.setImageURI(Uri.parse(mResultBeans.get(i).getImage()+""));
        //viewHolder.circlefragment_recycle_prisenum.setText(mResultBeans.get(i).getWhetherGreat() + "");
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        viewHolder.circlefragment_recycle_time.setText(simpleDateFormat.format(mResultBeans.get(i).getCreateTime()));
        //viewHolder.circlefragment_recycle_time.setText(simpleDateFormat.format(mResultBeans.get(i).getCreateTime()));


        if (StringUtils.isEmpty(circleFragment_showCircle.getImage())){
            viewHolder.gridView.setVisibility(View.GONE);
        }else{
            viewHolder.gridView.setVisibility(View.VISIBLE);
            String[] images = circleFragment_showCircle.getImage().split(",");

            //int imageCount = (int)(Math.random()*9)+1;
            int imageCount = images.length;

            int colNum;//列数
            if (imageCount == 1){
                colNum = 1;
            }else if (imageCount == 2||imageCount == 4){
                colNum = 2;
            }else {
                colNum = 3;
            }
            viewHolder.imageAdapter.clear();//清空
            /*for (int j = 0; j <imageCount ; j++) {
                viewHolder.imageAdapter.addAll(Arrays.asList(images));
            }*/
            viewHolder.imageAdapter.addAll(Arrays.asList(images));

            viewHolder.gridLayoutManager.setSpanCount(colNum);//设置列数


            viewHolder.imageAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView circlefragment_recycle_sim;
        private TextView circlefragment_recycle_name;
        private TextView circlefragment_recycle_time;
        private TextView circlefragment_recycle_message;
        //private SimpleDraweeView circlefragment_recycle_sim2;
        private ImageView circlefragment_recycle_prise;
        //private TextView circlefragment_recycle_prisenum;
        GridLayoutManager gridLayoutManager;
        RecyclerView gridView;
        ImageAdapter imageAdapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circlefragment_recycle_sim = itemView.findViewById(R.id.circlefragment_item_headPic);
            circlefragment_recycle_name = itemView.findViewById(R.id.circlefragment_item_content);
            circlefragment_recycle_time = itemView.findViewById(R.id.circlefragment_recycle_time);
            circlefragment_recycle_message = itemView.findViewById(R.id.circlefragment_item_nickName);
            //circlefragment_recycle_sim2 = itemView.findViewById(R.id.circlefragment_item_image);
            ///circlefragment_recycle_prisenum = itemView.findViewById(R.id.circlefragment_item_whetherGreat);
            //circlefragment_recycle_prise = itemView.findViewById(R.id.circlefragment_item_whetherGreat);



            gridView = itemView.findViewById(R.id.grid_view);
            imageAdapter = new ImageAdapter();
            int space = context.getResources().getDimensionPixelSize(R.dimen.dp_10);;//图片间距
            gridLayoutManager = new GridLayoutManager(context,3);
            gridView.addItemDecoration(new SpacingItemDecoration(space));
            gridView.setLayoutManager(gridLayoutManager);
            gridView.setAdapter(imageAdapter);
        }
    }
}
