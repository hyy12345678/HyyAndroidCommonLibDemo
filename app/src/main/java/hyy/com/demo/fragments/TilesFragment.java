package hyy.com.demo.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.hyy.android.common.view.tiles.TilesViewGroup;



import hyy.com.demo.util.ScreenUtil;
import hyy.com.dropdownlistviewdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TilesFragment extends Fragment {

    TilesViewGroup tilesViewGroup;

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment AutoExtVGFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TilesFragment newInstance() {
        TilesFragment fragment = new TilesFragment();

        return fragment;
    }

    public TilesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tiles, container, false);

        tilesViewGroup = view.findViewById(R.id.tiles_group);

        int screenWidth = ScreenUtil.getScreenWidth(getContext());
        int normalWidth = screenWidth / 2;
        int normalHeight = ScreenUtil.dip2px(getContext(), 50f);


        View tileView1 = generateTileView(getContext(), "xixi1", R.color.hyy_commlib_red, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi1",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView2 = generateTileView(getContext(), "xixi2", R.color.hyy_commlib_blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi2",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView3 = generateTileView(getContext(), "xixi3", R.color.hyy_commlib_green, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi3",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView4 = generateTileView(getContext(), "xixi4", R.color.hyy_commlib_yellow, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi4",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView5 = generateTileView(getContext(), "xixi5", R.color.hyy_commlib_red, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi5",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView6 = generateTileView(getContext(), "xixi6", R.color.hyy_commlib_blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi6",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView7 = generateTileView(getContext(), "xixi7", R.color.hyy_commlib_orange, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi7",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView8 = generateTileView(getContext(), "xixi8", R.color.hyy_commlib_red, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi8",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView9 = generateTileView(getContext(), "xixi9", R.color.hyy_commlib_blue, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi9",Toast.LENGTH_SHORT).show();
            }
        });
        View tileView10 = generateTileView(getContext(), "xixi10", R.color.hyy_commlib_green, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"xixi10",Toast.LENGTH_SHORT).show();
            }
        });


        tilesViewGroup.addView(tileView1, normalWidth, normalHeight);
        tilesViewGroup.addView(tileView2, normalWidth, normalHeight * 2);
        tilesViewGroup.addView(tileView3, normalWidth, normalHeight);
        tilesViewGroup.addView(tileView4, normalWidth * 2, normalHeight);
        tilesViewGroup.addView(tileView5, normalWidth, normalHeight * 2);
        tilesViewGroup.addView(tileView6, normalWidth, normalHeight);
        tilesViewGroup.addView(tileView7, normalWidth, normalHeight);

        tilesViewGroup.addView(tileView8, normalWidth, normalHeight);
        tilesViewGroup.addView(tileView9, normalWidth, normalHeight * 2);
        tilesViewGroup.addView(tileView10, normalWidth, normalHeight);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private View generateTileView(Context context, String title, int bgColor, View.OnClickListener listener) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_tile, null);
        view.setBackgroundColor(context.getResources().getColor(bgColor));
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(title);

        if(null != listener){
            view.setOnClickListener(listener);
        }

        return view;
    }

}
