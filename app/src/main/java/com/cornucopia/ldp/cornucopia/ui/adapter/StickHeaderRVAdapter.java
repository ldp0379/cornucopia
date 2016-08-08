package com.cornucopia.ldp.cornucopia.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cornucopia.ldp.cornucopia.R;
import com.cornucopia.ldp.cornucopia.response.model.StickHeaderModel;

import java.util.List;

/**
 * 顶部悬浮RV - adapter
 * Created by ldp on 16/8/5.
 */
public class StickHeaderRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // RecyclerView 的第一个item，肯定是展示StickyLayout的.
    public static final int FIRST_STICKY_VIEW = 1;
    // RecyclerView 除了第一个item以外，要展示StickyLayout的.
    public static final int HAS_STICKY_VIEW = 2;
    // RecyclerView 的不展示StickyLayout的item.
    public static final int NONE_STICKY_VIEW = 3;

    private Context context;
    private List<StickHeaderModel> stickyExampleModels;

    public StickHeaderRVAdapter(Context context, List<StickHeaderModel> recyclerViewModels) {
        this.context = context;
        this.stickyExampleModels = recyclerViewModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sticky_header_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof RecyclerViewHolder) {
            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;
            if (position % 2 == 0) {
                recyclerViewHolder.rlContentWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.bg_color_1));
            } else {
                recyclerViewHolder.rlContentWrapper.setBackgroundColor(
                        ContextCompat.getColor(context, R.color.bg_color_2));
            }

            StickHeaderModel stickyExampleModel = stickyExampleModels.get(position);
            recyclerViewHolder.tvName.setText(stickyExampleModel.name);
            recyclerViewHolder.tvGender.setText(stickyExampleModel.gender);
            recyclerViewHolder.tvProfession.setText(stickyExampleModel.profession);

            if (position == 0) {
                recyclerViewHolder.tvStickyHeader.setVisibility(View.VISIBLE);
                recyclerViewHolder.tvStickyHeader.setText(stickyExampleModel.sticky);
                // 第一个item的吸顶信息肯定是展示的，并且标记tag为FIRST_STICKY_VIEW
                recyclerViewHolder.itemView.setTag(FIRST_STICKY_VIEW);
            } else {
                // 之后的item都会和前一个item要展示的吸顶信息进行比较，不相同就展示，并且标记tag为HAS_STICKY_VIEW
                if (!TextUtils.equals(stickyExampleModel.sticky, stickyExampleModels.get(position - 1).sticky)) {
                    recyclerViewHolder.tvStickyHeader.setVisibility(View.VISIBLE);
                    recyclerViewHolder.tvStickyHeader.setText(stickyExampleModel.sticky);
                    recyclerViewHolder.itemView.setTag(HAS_STICKY_VIEW);
                } else {
                    // 相同就不展示，并且标记tag为NONE_STICKY_VIEW
                    recyclerViewHolder.tvStickyHeader.setVisibility(View.GONE);
                    recyclerViewHolder.itemView.setTag(NONE_STICKY_VIEW);
                }
            }
            // ContentDescription 用来记录并获取要吸顶展示的信息
            recyclerViewHolder.itemView.setContentDescription(stickyExampleModel.sticky);
        }
    }

    @Override
    public int getItemCount() {
        return stickyExampleModels == null ? 0 : stickyExampleModels.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView tvStickyHeader;
        public RelativeLayout rlContentWrapper;
        public TextView tvName;
        public TextView tvGender;
        public TextView tvProfession;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            tvStickyHeader = (TextView) itemView.findViewById(R.id.tv_sticky_header_view);
            rlContentWrapper = (RelativeLayout) itemView.findViewById(R.id.rl_content_wrapper);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvGender = (TextView) itemView.findViewById(R.id.tv_gender);
            tvProfession = (TextView) itemView.findViewById(R.id.tv_profession);
        }
    }
}
