package com.davifantasia.unevengrid.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.davifantasia.unevengrid.AlternatingThreeCellsItemDetailsActivity;
import com.davifantasia.unevengrid.R;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mNewsList.
 */
public class AlternatingThreeCellsAdapter extends RecyclerView.Adapter<AlternatingThreeCellsAdapter.ViewHolder> {
//    private static final String TAG = AlternatingThreeCellsAdapter.class.getSimpleName();

    private static final int TYPE_EVEN = 0;
    private static final int TYPE_ODD = 1;

    private List<String> mItems;
    private int mItemsCount;
    /**
     * Remainder of mNewsList.size % 3
     */
    private int mItemsCountRemainder;

    private Activity mActivity;

    public AlternatingThreeCellsAdapter(Activity activity, List<String> items) {
        mActivity = activity;
        mItems = items;
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // SINGLE item in a row.
        private final RelativeLayout mSingleItemRL;
        private final TextView mSingleItemTV;
        // DOUBLE items in a row.
        private final RelativeLayout mDoubleItemsRL;
        private final RelativeLayout mDoubleItemsRL1;
        private final TextView mDoubleItemsTV1;
        private final RelativeLayout mDoubleItemsRL2;
        private final TextView mDoubleItemsTV2;
        // TRIPLE items in a row.
        private final LinearLayout mTripleItemsLL;
        private final RelativeLayout mTripleItemsRL1;
        private final TextView mTripleItemsTV1;
        private final RelativeLayout mTripleItemsRL2;
        private final TextView mTripleItemsTV2;
        private final RelativeLayout mTripleItemsRL3;
        private final TextView mTripleItemsTV3;

        public ViewHolder(View v) {
            super(v);

            mSingleItemRL = (RelativeLayout) v.findViewById(R.id.single_item_relative_layout);
            mSingleItemTV = (TextView) v.findViewById(R.id.single_item_text_view);

            mDoubleItemsRL = (RelativeLayout) v.findViewById(R.id.double_items_relative_layout);
            mDoubleItemsRL1 = (RelativeLayout) v.findViewById(R.id.double_items_relative_layout_1);
            mDoubleItemsTV1 = (TextView) v.findViewById(R.id.double_items_text_view_1);
            mDoubleItemsRL2 = (RelativeLayout) v.findViewById(R.id.double_items_relative_layout_2);
            mDoubleItemsTV2 = (TextView) v.findViewById(R.id.double_items_text_view_2);

            mTripleItemsLL = (LinearLayout) v.findViewById(R.id.triple_items_linear_layout);
            mTripleItemsRL1 = (RelativeLayout) v.findViewById(R.id.triple_items_relative_layout_1);
            mTripleItemsTV1 = (TextView) v.findViewById(R.id.triple_items_text_view_1);
            mTripleItemsRL2 = (RelativeLayout) v.findViewById(R.id.triple_items_relative_layout_2);
            mTripleItemsTV2 = (TextView) v.findViewById(R.id.triple_items_text_view_2);
            mTripleItemsRL3 = (RelativeLayout) v.findViewById(R.id.triple_items_relative_layout_3);
            mTripleItemsTV3 = (TextView) v.findViewById(R.id.triple_items_text_view_3);

        }

        public RelativeLayout getSingleItemRL() {
            return mSingleItemRL;
        }

        public TextView getSingleItemTV() {
            return mSingleItemTV;
        }

        public RelativeLayout getDoubleItemsRL() {
            return mDoubleItemsRL;
        }

        public RelativeLayout getDoubleItemsRL1() {
            return mDoubleItemsRL1;
        }

        public TextView getDoubleItemsTV1() {
            return mDoubleItemsTV1;
        }

        public RelativeLayout getDoubleItemsRL2() {
            return mDoubleItemsRL2;
        }

        public TextView getDoubleItemsTV2() {
            return mDoubleItemsTV2;
        }

        public LinearLayout getTripleItemsLL() {
            return mTripleItemsLL;
        }

        public RelativeLayout getTripleItemsRL1() {
            return mTripleItemsRL1;
        }

        public TextView getTripleItemsTV1() {
            return mTripleItemsTV1;
        }

        public RelativeLayout getTripleItemsRL2() {
            return mTripleItemsRL2;
        }

        public TextView getTripleItemsTV2() {
            return mTripleItemsTV2;
        }

        public RelativeLayout getTripleItemsRL3() {
            return mTripleItemsRL3;
        }

        public TextView getTripleItemsTV3() {
            return mTripleItemsTV3;
        }
    }

    // Create new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v;

        if (viewType == TYPE_EVEN)
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.alternating_three_cells_items_group_even, viewGroup, false);
        else
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.alternating_three_cells_item_group_odd, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the toolbar_spinner manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        int position1 = position * 3;
        int position2 = (position * 3) + 1;
        int position3 = (position * 3) + 2;

        if ((position1 + 3) == (mItemsCount * 3)) {
            // Last item in list.
            switch (mItemsCountRemainder) {
                case 0: // There are 3 items on the last row, so no remainder.
                    setTripleItems(viewHolder, position1, position2, position3);
                    break;

                case 1: // There's 1 item left.
                    setSingleItem(viewHolder, position1);
                    break;

                case 2: // There are 2 items left.
                    setDoubleItems(viewHolder, position1, position2);
                    break;
            }
        } else {
            setTripleItems(viewHolder, position1, position2, position3);
        }
    }

    private void setSingleItem(ViewHolder viewHolder, int position1) {
        // Hide items 2 and 3.
        viewHolder.getSingleItemRL().setVisibility(View.VISIBLE);
        viewHolder.getDoubleItemsRL().setVisibility(View.GONE);
        viewHolder.getTripleItemsLL().setVisibility(View.GONE);

        viewHolder.getSingleItemTV().setText(mItems.get(position1));
        setOnClick(viewHolder.getSingleItemRL(), position1);
    }

    private void setDoubleItems(ViewHolder viewHolder, int position1, int position2) {
        // Hide items 2 and 3.
        viewHolder.getSingleItemRL().setVisibility(View.GONE);
        viewHolder.getDoubleItemsRL().setVisibility(View.VISIBLE);
        viewHolder.getTripleItemsLL().setVisibility(View.GONE);

        viewHolder.getDoubleItemsTV1().setText(mItems.get(position1));
        setOnClick(viewHolder.getDoubleItemsRL1(), position1);

        viewHolder.getDoubleItemsTV2().setText(mItems.get(position2));
        setOnClick(viewHolder.getDoubleItemsRL2(), position2);
    }

    private void setTripleItems(ViewHolder viewHolder, int position1, int position2, int position3) {
        // Hide items 2 and 3.
        viewHolder.getSingleItemRL().setVisibility(View.GONE);
        viewHolder.getDoubleItemsRL().setVisibility(View.GONE);
        viewHolder.getTripleItemsLL().setVisibility(View.VISIBLE);

        viewHolder.getTripleItemsTV1().setText(mItems.get(position1));
        setOnClick(viewHolder.getTripleItemsRL1(), position1);

        viewHolder.getTripleItemsTV2().setText(mItems.get(position2));
        setOnClick(viewHolder.getTripleItemsRL2(), position2);

        viewHolder.getTripleItemsTV3().setText(mItems.get(position3));
        setOnClick(viewHolder.getTripleItemsRL3(), position3);
    }

    private void setOnClick(View view, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, AlternatingThreeCellsItemDetailsActivity.class);
                intent.putExtra(AlternatingThreeCellsItemDetailsActivity.TITLE, mItems.get(position));
                mActivity.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the toolbar_spinner manager)
    @Override
    public int getItemCount() {
        computeItemsCount();
        return mItemsCount;
    }

    private void computeItemsCount() {
        mItemsCount = mItems.size() / 3;
        mItemsCountRemainder = mItems.size() % 3;
        if (mItemsCountRemainder > 0)
            mItemsCount++;
    }

    // With the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
            return TYPE_EVEN;
        else
            return TYPE_ODD;
    }

    public void setData(List<String> items) {
        mItems = items;
        notifyDataSetChanged();
    }

}
