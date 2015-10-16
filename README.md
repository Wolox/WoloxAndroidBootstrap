# Android Bootstrap

## Features
 - EndlessListFragment (uses SuperRecyclerView)
 - WoloxActivity and WoloxFragment
 - WoloxCallback
 - WoloxAdapter
 - Basic interceptors
 - Bitmap transformations

### EndlessListFragment example
```java
public class StringListFragment extends EndlessListFragment<String, StringAdapter.ViewHolder> {

    @Override
    protected RecyclerView.LayoutManager layoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected WoloxAdapter<String, StringAdapter.ViewHolder> adapter() {
        return new StringAdapter();
    }

    @Override
    protected Provider<Collection<String>> provider() {
        return new Provider<Collection<String>>() {
            @Override
            public void provide(int currentPage, int itemsPerPage, final Callback<Collection<String>> callback) {
                final List<String> ans = new ArrayList<>();
                ans.add("some cool text");
                ans.add("even cooler text");
                ans.add("this text is not that cool");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        callback.success(ans, null);
                    }
                }, 500); // 5000ms delay
            }
        };
    }

    @Override
    protected OnItemClickListener<String> onItemClickListener() {
        return null;
    }

    @Override
    protected int layout() {
        return R.layout.endless_list;
    }
}
```
### WoloxAdapter example
```java
public class StringAdapter extends WoloxAdapter<String, StringAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_string, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String current = get(i);
        viewHolder.text.setText(current);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.adapter_string_text);
        }
    }
}

```
