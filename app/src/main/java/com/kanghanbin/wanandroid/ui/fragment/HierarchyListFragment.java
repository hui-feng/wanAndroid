package com.kanghanbin.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kanghanbin.wanandroid.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.kanghanbin.wanandroid.base.BaseListFragment;
import com.kanghanbin.wanandroid.contract.HierarchyDetailListContract;
import com.kanghanbin.wanandroid.model.bean.ArticleBean;
import com.kanghanbin.wanandroid.model.bean.ArticleListBean;
import com.kanghanbin.wanandroid.presenter.HierarchyDetailListPresenter;
import com.kanghanbin.wanandroid.ui.adapter.HierarchyDetailAdapter;
import com.kanghanbin.wanandroid.util.IntentUtil;

import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM1;
import static com.kanghanbin.wanandroid.util.Constant.ARG_PARAM2;

/**
 * 创建时间：2018/11/5
 * 编写人：kanghb
 * 功能描述：
 */
public class HierarchyListFragment extends BaseListFragment<HierarchyDetailListPresenter> implements HierarchyDetailListContract.View,BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private int cid;
    private List<ArticleBean> articleBeanList;
    private HierarchyDetailAdapter adapter;
    private int currentPage;

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        Bundle bundle = getArguments();
        cid = bundle.getInt(ARG_PARAM1);
        articleBeanList = new ArrayList<>();
        adapter = new HierarchyDetailAdapter(R.layout.item_list_hierarchy, articleBeanList);
        adapter.setOnItemClickListener(this);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(adapter);
        setAuto();

    }


    public static HierarchyListFragment newInstance(int param1, String param2) {
        HierarchyListFragment fragment = new HierarchyListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HierarchyDetailListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_hierarchy;
    }


    private void setAuto() {
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPage = 0;
                mPresenter.getRefreshHierarchyArticleList(currentPage, cid);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPage++;
                mPresenter.getMoreHierarchyArticleList(currentPage, cid);
            }
        });

    }

    @Override
    public void showRefreshHierarchyArticleList(ArticleListBean articleListBean) {
        smartRefreshLayout.finishRefresh();
        articleBeanList.clear();
        articleBeanList.addAll(articleListBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreHierarchyArticleList(ArticleListBean articleListBean) {
        if (articleListBean.getDatas().size() == 0) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
            currentPage--;
        }else {
            smartRefreshLayout.finishLoadMore();
        }
        articleBeanList.addAll(articleListBean.getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity,view,getString(R.string.shareView));
        ArticleBean articleBean = articleBeanList.get(position);
        IntentUtil.startArticleDetailActivity(mContext,activityOptionsCompat,
                articleBean.getId(),articleBean.getTitle(),articleBean.getLink(),articleBean.isCollect(),false);
    }
}
