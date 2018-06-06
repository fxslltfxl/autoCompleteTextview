package fxs.free.com.autocompletetextview.demo;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import fxs.free.com.autocompletetextview.R;
import fxs.free.com.autocompletetextview.databinding.ItemBinding;

/**
 * @author panpan
 */
public class Adapter extends BaseAdapter implements Filterable {

    private List<UserVM> mList;
    private MainActivity mContext;
    private ArrayFilter mFilter;
    private List<UserVM> newValues;

    public Adapter(List<UserVM> mList, MainActivity mContext) {
        WeakReference weakReference = new WeakReference<>(mContext);
        this.mList = mList;
        this.newValues = mList;
        this.mContext = (MainActivity) weakReference.get();
    }

    @Override
    public int getCount() {
        return newValues.size();
    }

    @Override
    public UserVM getItem(int position) {
        return newValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemBinding binding;
        View item = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder(item);
            binding = viewHolder.getmViewDataBinding();
            convertView = viewHolder.getmViewDataBinding().getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemBinding) convertView.getTag();
        }
        binding.setVM(newValues.get(position));
        binding.executePendingBindings();
        
        binding.getRoot().setOnClickListener(view -> mContext.autoCompleteTextView.setText(String.format("name:%stel:%semail:%s", newValues.get(position).getName(), newValues.get(position).getTel(), newValues.get(position).getEmail())));

        binding.btnClick.setOnClickListener(v -> Toast.makeText(mContext, "拨打电话", Toast.LENGTH_LONG).show());

        binding.tvName.setOnClickListener(v -> mContext.autoCompleteTextView.setText(newValues.get(position).getName()));

        binding.ivCustom.setOnClickListener(view -> {
            mList.remove(newValues.get(position));
            newValues.remove(position);
            notifyDataSetChanged();
            binding.executePendingBindings();
        });
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    public class ViewHolder {
        ItemBinding mViewDataBinding;

        ViewHolder(View item) {
            mViewDataBinding = DataBindingUtil.bind(item);
        }

        ItemBinding getmViewDataBinding() {
            return mViewDataBinding;
        }
    }


    private class ArrayFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (prefix == null || prefix.length() == 0) {
                List<UserVM> list = mList;
                results.values = list;
                results.count = list.size();
            } else {
                String prefixString = prefix.toString().toLowerCase();
                ArrayList<UserVM> unfilteredValues = new ArrayList<>(mList);
                int count = unfilteredValues.size();
                newValues = new ArrayList<>(count);

                for (int i = 0; i < count; i++) {
                    UserVM pc = unfilteredValues.get(i);
                    if (pc != null) {

                        if (pc.getName() != null && pc.getName().startsWith(prefixString)) {
                            newValues.add(pc);
                        } else if (pc.getEmail() != null && pc.getEmail().startsWith(prefixString)) {
                            newValues.add(pc);
                        }
                    }
                }
                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            //noinspection unchecked
            newValues = (List<UserVM>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}