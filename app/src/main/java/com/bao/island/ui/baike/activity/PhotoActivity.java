package com.bao.island.ui.baike.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bao.island.R;
import com.bao.island.app.BaseActivity;
import com.bao.island.app.Constants;
import com.bao.island.model.Favor;
import com.bao.island.ui.adapter.PhotoAdapter;
import com.bao.island.util.ImageDownLoadCallBack;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoActivity extends BaseActivity {

    @BindView(R.id.vp_photo)
    ViewPager vpPhoto;

    List<String> imglist;
    int position;
    Toolbar toolbar;

    PhotoAdapter photoAdapter;
    MenuItem menuItem;
    String url;

    File currentFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        toolbar = getToolbar();
//        getToolbar().setTitle("图集");
//        menuItem = toolbarMenu.findItem(R.id.menu_photo_favor);
        imglist = getIntent().getStringArrayListExtra("imglist");

        url = imglist.get(position);
        position = getIntent().getIntExtra("position", 0);
        photoAdapter = new PhotoAdapter(getApplicationContext(), imglist);

        vpPhoto.setAdapter(photoAdapter);

        vpPhoto.setCurrentItem(position);

        vpPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                toolbar.setTitle(position + 1 + "/" + imglist.size());
                url = imglist.get(position);
//                menuItem = toolbarMenu.findItem(R.id.menu_photo_favor);
//                setLikeState();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

//                setLikeState();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        menuItem = menu.findItem(R.id.menu_photo_favor);
        setLikeState();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_photo_favor:
                if (menuItem.isChecked()) {
                    menuItem.setCheckable(false);
                    menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
                    DataSupport.deleteAll(Favor.class, "url = ?", url);
                } else {
                    menuItem.setChecked(true);
                    menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
                    Favor favor = new Favor();
                    favor.setUrl(url);
                    favor.setType(Constants.TYPE_IMG);
                    favor.save();
                }
                break;
            case R.id.menu_photo_share:
//                showShare();
                break;
            case R.id.menu_photo_save:
                final String fileName = url.substring(url.lastIndexOf("/"));

                Picasso.get().load(url).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        saveImageToGallery(getApplicationContext(), bitmap, fileName);
                        Toast.makeText(PhotoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Toast.makeText(PhotoActivity.this, "保存失败，请重试...", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                break;

        }
        return true;
    }


    private void setLikeState() {
        List<Favor> list = DataSupport.where("url = ?", url).find(Favor.class);
        System.out.println("list size is :" + list.size());
        if (list.size() != 0) {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
            menuItem.setChecked(true);
        } else {
            menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            menuItem.setChecked(false);

        }
    }

 /*   private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }*/



    private void saveImageToGallery(Context context, Bitmap bmp, String filename) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Islands");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        currentFile = new File(appDir, filename);
        if (currentFile.exists()){
            Toast.makeText(context, "图片已存在", Toast.LENGTH_SHORT).show();
            return;
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }

}
