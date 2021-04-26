package com.example.mylogin;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Weight extends AppCompatActivity {
    VideoView vv;
    ImageView iv;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight);


        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new fragment_weight());
        fragmentTransaction.commit();

        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.replace(R.id.bottomFrameLayout, new fragment_cart()).commit();
        vv= findViewById(R.id.videoView2);
        //Video Uri
        Uri videoUri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.mh);

        //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
        vv.setMediaController(new MediaController(this));

        //VideoView가 보여줄 동영상의 경로 주소(Uri) 설정하기
        vv.setVideoURI(videoUri);

        //동영상을 읽어오는데 시간이 걸리므로..
        //비디오 로딩 준비가 끝났을 때 실행하도록..
        //리스너 설정
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //비디오 시작
                vv.start();
            }
        });

    }
    //화면에 안보일때...
    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시 정지
        if(vv!=null && vv.isPlaying()) vv.pause();
    }
    //액티비티가 메모리에서 사라질때..
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if(vv!=null) vv.stopPlayback();
    }
    //이미지뷰 눌리면 프래그먼트 변경하기
    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.weightIV:
                transaction.replace(R.id.frameLayout, new fragment_weight()).commitAllowingStateLoss();
                break;
            case R.id.cardioIV:
                transaction.replace(R.id.frameLayout, new fragment_cardio()).commitAllowingStateLoss();
                break;
            case R.id.partIV:
                transaction.replace(R.id.frameLayout, new fragment_part()).commitAllowingStateLoss();
                break;

        }
    }



}
