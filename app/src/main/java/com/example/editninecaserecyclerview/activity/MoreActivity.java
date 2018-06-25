package com.example.editninecaserecyclerview.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.editninecaserecyclerview.R;
import com.example.editninecaserecyclerview.adapter.ApplyAdapter;
import com.example.editninecaserecyclerview.adapter.ApplyHeadAdapter;
import com.example.editninecaserecyclerview.callback.ItemDragHelperCallback;
import com.example.editninecaserecyclerview.interfaces.ApplyView;
import com.example.editninecaserecyclerview.interfaces.LoadApplyInterface;
import com.example.editninecaserecyclerview.model.ApplyTable;
import com.example.editninecaserecyclerview.util.LoadApplyImpl;
import com.example.editninecaserecyclerview.util.Logs;

import java.util.ArrayList;
import java.util.List;
/***
 * 更多应用编辑页面
 *
 * create by bthvi  2018/06/01
 */
public class MoreActivity extends Activity implements ApplyView {
    private RecyclerView mRv_head, mRv_all;
    private TextView mTv_edit;
    private Context context;
    //头部适配器
    private ApplyHeadAdapter headAdapter;
    //全部适配器
    private ApplyAdapter allAdapter;
    private LoadApplyInterface mPresenter;
    private List<ApplyTable> allTables;
    private List<ApplyTable> headTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        findView();
        initData();
    }

    private void initData() {
        allTables = new ArrayList<>();
        mPresenter = new LoadApplyImpl(this);
        mPresenter.lodeApplyRequest();
    }

    private void findView() {
        mRv_head = findViewById(R.id.mRv_head);
        mRv_all = findViewById(R.id.mRv_all);
        mTv_edit = findViewById(R.id.mTv_edit);
        mTv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (headAdapter.getEdit()){
                    mTv_edit.setText("完成");
                }else {
                    mTv_edit.setText("编辑");
                }
                headAdapter.setEdit(!headAdapter.getEdit());
                allAdapter.setEdit(!allAdapter.getEdit());
            }
        });
    }

    /**
     * 头部
     *
     * @param _headTables
     */
    @Override
    public void returnMineApply(List<ApplyTable> _headTables) {
        this.headTables = _headTables;
        Logs.I("headTables", headTables.toString());
        if (null == headAdapter) {
            /**设置头部数据适配器*/
            headAdapter = new ApplyHeadAdapter(this, headTables);
            mRv_head.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
            //DefaultItemAnimator()默认的RecyclerView动画实现类，如果产品需求没有特别复杂的动画要求，可以使用
            mRv_head.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画
            mRv_head.setAdapter(headAdapter);
            //加载数据完毕后 设置监听，拖拽移动事件 和 点击事件  把ItemDragHelperCallback设置给RecyclerView使用
            ItemDragHelperCallback callback = new ItemDragHelperCallback(headAdapter);
            //是一个工具类，可实现侧滑删除和拖拽移动,完成后数据的刷新（UI更新）由重写的ItemDragHelperCallback完成
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            //调用ItemTouchHelper的attachToRecyclerView方法建立联系
            touchHelper.attachToRecyclerView(mRv_head);
            headAdapter.setItemDragHelperCallback(callback);
        }
        headAdapter.addData(headTables);
        headAdapter.setOnItemClickListenerEdit(new ApplyAdapter.OnItemClickListenerEdit() {
            @Override
            public void onItemClick(View view, int position) {
                ApplyTable newsChannel = headAdapter.getAdapterData().get(position);
                for (int i = 0; i < allTables.size(); i++) {
                    if (newsChannel.getName().equals(allTables.get(i).getName())) {
                        allTables.get(i).setState(1);
                    }
                }
                allAdapter.notifyDataSetChanged();
                headTables.remove(position);
                headAdapter.notifyDataSetChanged();
                //进行添加或删除操作后，要更新的列表 进行存储
                mPresenter.onItemAddOrRemove((ArrayList<ApplyTable>) headTables, (ArrayList<ApplyTable>) allTables);
            }
        });
        headAdapter.setOnItemClickListener(new ApplyAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, String name) {
                Toast.makeText(MoreActivity.this,name,Toast.LENGTH_LONG).show();
            }
        } );
    }

    /**
     * 全部
     *
     * @param _allTables
     */
    @Override
    public void returnMoreNewsApply(List<ApplyTable> _allTables) {
        allTables.clear();
        allTables.addAll(_allTables);
        this.allTables = _allTables;
        if (null == allAdapter) {
            /**设置全部数据适配器*/
            mRv_all.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
            mRv_all.setItemAnimator(new DefaultItemAnimator());
            mRv_all.setAdapter(allAdapter);
            allAdapter = new ApplyAdapter(this, allTables);
            mRv_all.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
            mRv_all.setItemAnimator(new DefaultItemAnimator());
            mRv_all.setAdapter(allAdapter);
        }
        allAdapter.notifyDataSetChanged();
        allAdapter.setOnItemClickListenerEdit(new ApplyAdapter.OnItemClickListenerEdit() {
            @Override
            public void onItemClick(View view, int position) {
                    ApplyTable at = allTables.get(position);
                    at.setState(0);
                    headTables.add(new ApplyTable(at.getName(), at.getId(), at.getType(), at.getUrl(),
                            at.getIndex(), at.getFixed(), at.getImgRes(), 0));
                    headAdapter.notifyDataSetChanged();
                    allTables.get(position).setState(0);
                    allAdapter.notifyDataSetChanged();
                    //进行添加或删除操作后，要更新的列表 进行存储
                    mPresenter.onItemAddOrRemove((ArrayList<ApplyTable>) headTables, (ArrayList<ApplyTable>) allTables);
//                }
            }
        });
        allAdapter.setOnItemClickListener(new ApplyAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, String name) {
                Toast.makeText(MoreActivity.this,name,Toast.LENGTH_LONG).show();
            }
        } );
    }
}
