package kanghb.com.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.text.HtmlCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.model.bean.ArticleBean;
import kanghb.com.wanandroid.util.GlideUtil;

/**
 * 创建时间：2018/11/19
 * 编写人：kanghb
 * 功能描述：
 */
public class WechatListAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {
    public WechatListAdapter(int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.tv_title, HtmlCompat.fromHtml(item.getTitle(), HtmlCompat.FROM_HTML_MODE_LEGACY))
                .setText(R.id.tv_date, "时间：" + item.getNiceDate())
                .setImageResource(R.id.iv_wechat_collect, item.isCollect() ? R.mipmap.icon_collect_yes : R.mipmap.icon_collect_no);

        helper.addOnClickListener(R.id.iv_wechat_collect);
    }
}
