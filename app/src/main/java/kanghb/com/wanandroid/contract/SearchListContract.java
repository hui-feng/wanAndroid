package kanghb.com.wanandroid.contract;

import kanghb.com.wanandroid.base.BasePresenter;
import kanghb.com.wanandroid.base.BaseView;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.model.bean.ArticleListBean;

/**
 * 创建时间：2018/11/13
 * 编写人：kanghb
 * 功能描述：
 */
public interface SearchListContract {
    interface View extends BaseView {
        void showSearchListArticles(ArticleListBean articleListBean);

        void showMoreSearchListArticles(ArticleListBean articleListBean);

        void showAddCollectArticle(int position, ArticleBean articleBean);

        void showCancelCollectArticle(int position, ArticleBean articleBean);

    }

    interface Presenter extends BasePresenter<View> {
        void getSearchListArticles(String key);

        void getMoreSearchListArticles(String key);

        void addCollectArticle(int position, ArticleBean articleBean);

        void cancelCollectArticle(int position, ArticleBean articleBean);
    }
}
