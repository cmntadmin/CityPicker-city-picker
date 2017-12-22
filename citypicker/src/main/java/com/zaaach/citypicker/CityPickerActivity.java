package com.zaaach.citypicker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zaaach.citypicker.adapter.CityListAdapter;
import com.zaaach.citypicker.adapter.ResultListAdapter;
import com.zaaach.citypicker.model.Area;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.utils.AreaUtil;
import com.zaaach.citypicker.utils.StringUtils;
import com.zaaach.citypicker.view.SideLetterBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author Bro0cL on 2016/12/16.
 */
public abstract class CityPickerActivity extends CheckPermissionsActivity implements View.OnClickListener, CheckPermissionsListener {
    public static final String KEY_PICKED_CITY = "picked_city";

    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private TextView cancelBtn;
    private ViewGroup emptyView;

    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<Area> mResultArea;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_activity_city_list);
        initData();
        initView();
        location();
    }


    public void initLocation(String location) {
        if (null != location && location.length() > 0) {
            mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
        } else {
            mCityAdapter.updateLocateState(LocateState.FAILED, null);
        }
    }

    private List<Area> getAreaByName(String name) {
        List<Area> cityListArea = getCitys();
        List<Area> areaListByName = new ArrayList<>();
        for (int i = 0; i < cityListArea.size(); i++) {
            if (cityListArea.get(i).getName().contains(name) || cityListArea.get(i).getPinyin().contains(name)) {
                areaListByName.add(cityListArea.get(i));
            }
        }
        return areaListByName;
    }

    private List<Area> getCitys() {
        List<City> provincesList = AreaUtil.getAllProvinces();
        List<Area> citysList = new ArrayList<>();
        for (int i = 0; i < provincesList.size(); i++) {
            citysList.add(provincesList.get(i).getArea());
        }
        Collections.sort(citysList, new CityComparator());
        return citysList;
    }


    private void initData() {
        List<Area> citysList = getCitys();
        mCityAdapter = new CityListAdapter(this, citysList);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(Area Area) {
                backWithData(Area);
            }

            @Override
            public void onLocateClick() {
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                requestPermissions(CityPickerActivity.this, neededPermissions, CityPickerActivity.this);
            }
        });

        mResultAdapter = new ResultListAdapter(this, null);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);

        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });

        searchBox = (EditText) findViewById(R.id.et_search);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    mResultArea = getAreaByName(keyword);
                    if (mResultArea == null || mResultArea.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(mResultArea);
                        Log.d("TAG", mResultArea.size() + "");
                    }
                }
            }
        });

        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                backWithData(mResultAdapter.getItem(position));
            }
        });

        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        cancelBtn = (TextView) findViewById(R.id.tv_search_cancel);

        clearBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    private void backWithData(Area city) {
        Intent data = new Intent();
        data.putExtra(KEY_PICKED_CITY, city);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_search_clear) {
            searchBox.setText("");
            clearBtn.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            mResultListView.setVisibility(View.GONE);
            mResultArea = null;
        } else if (i == R.id.tv_search_cancel) {
            finish();
        }
    }

    @Override
    public void onDenied(List<String> permissions) {
        Toast.makeText(this, "权限被禁用，请到设置里打开", Toast.LENGTH_SHORT).show();
        mCityAdapter.updateLocateState(LocateState.FAILED, null);
    }

    private class CityComparator implements Comparator<Area> {
        @Override
        public int compare(Area lhs, Area rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }


    public abstract void location();

}
