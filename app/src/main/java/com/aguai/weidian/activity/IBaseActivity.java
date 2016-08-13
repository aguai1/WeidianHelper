package com.aguai.weidian.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Activity 接口
 * @author 阿怪
 * @version 1.0
 *
 */
public interface IBaseActivity {

	/**
	 * 绑定渲染视图的布局文件
	 * @return 布局文件资源id
	 */
	 int bindLayout();

	/**
	 * 初始化控件
	 */
	 void initView(final View view);
	/**
	 * 实务操作
	 */
	 void doBusiness(Context mContext);
	/**
	 * 处理传参
	 */
	 void initPrame(Bundle bundle);

}
