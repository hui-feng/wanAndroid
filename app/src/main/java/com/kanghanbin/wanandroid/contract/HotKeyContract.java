package com.kanghanbin.wanandroid.contract;

import java.util.List;

import com.kanghanbin.wanandroid.base.BasePresenter;
import com.kanghanbin.wanandroid.base.BaseView;
import com.kanghanbin.wanandroid.model.bean.HotKey;
import com.kanghanbin.wanandroid.model.local.LocalHotKey;

/**
 * 创建时间：2018/11/12
 * 编写人：kanghb
 * 功能描述：
 */
public interface HotKeyContract {
    interface View extends BaseView {
        void showHotKeyResults(List<HotKey> hotKeyList);

        void showLocalHotKeys(List<LocalHotKey> localHotKeyList);

        void jumpToSearchListActivity();

        void showClearResult();
    }

    interface Presenter extends BasePresenter<View> {
        void getHotKeys();

        void getLoaclHotKeys();

        void addLocalHotKey(String data);

        void clearLocalHotKey();

    }
}
