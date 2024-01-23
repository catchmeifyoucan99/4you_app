//package com.example.musicapp.Activity;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import com.example.musicapp.Models.ListMusicModel;
//import com.example.musicapp.R;
//
//import java.util.ArrayList;
//
//public class ListMusic extends Activity {
//    ListView ds_nhac;
//    ImageButton rew,nex,paus;
//    int position=0;
//    TextView txt_dang_phat, sum_time,play_time,casi;
//    MediaPlayer mediaPlayer;
//    SeekBar sktime;
//
//    ArrayList<ListMusicModel> list_nhac;
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_bai_hat);
//
//        Intent intent = getIntent();
//        int s= intent.getIntExtra("so",1234);
//        String cs = intent.getStringExtra("ten");
//
//        ds_nhac         =   (ListView)     findViewById(R.id.nhac);
//        rew             =   (ImageButton)  findViewById(R.id.re);
//        paus            =   (ImageButton)  findViewById(R.id.pause);
//        nex             =   (ImageButton)  findViewById(R.id.next);
//        txt_dang_phat   =   (TextView)     findViewById(R.id.dang_phat);
//        sum_time        =   (TextView)     findViewById(R.id.time_out);
//        play_time       =   (TextView)     findViewById(R.id.time_play);
//        sktime          =   (SeekBar)      findViewById(R.id.seebar);
//        casi            =   (TextView)     findViewById(R.id.casi);
//
//        list_nhac       =   new ArrayList<>();
//
//        casi.setText("Ca sĩ: "+ cs);
//
//
//        switch (s){
//            case 0:
//                list_nhac.add(new list_nhac("Điều anh biết", time(R.raw.chidan_dieu_anh_biet),R.raw.chidan_dieu_anh_biet));
//                list_nhac.add(new list_nhac("Làm vợ anh nhé", time(R.raw.chidan_lam_vo_anh_nhe),R.raw.chidan_lam_vo_anh_nhe));
//                list_nhac.add(new list_nhac("Sự thật sau một lời hứa", time(R.raw.chidan_su_that_sau_mot_loi_hua),R.raw.chidan_su_that_sau_mot_loi_hua));
//                break;
//            case 1:
//                list_nhac.add(new list_nhac("Muộn rồi sao mà còn", time(R.raw.sontung_muon_roi_ma_sao_con),R.raw.sontung_muon_roi_ma_sao_con));
//                list_nhac.add(new list_nhac("Hãy trao cho anh", time(R.raw.sontung_hay_trao_cho_anh),R.raw.sontung_hay_trao_cho_anh));
//                list_nhac.add(new list_nhac("Có chắc yêu là đây", time(R.raw.sontung_co_chac_yeu_la_day),R.raw.sontung_co_chac_yeu_la_day));
//                break;
//        }
//
//        list_nhac_Adapter list_nhac_adapter = new list_nhac_Adapter(activity_list_bai_hat.this,R.layout.activity_view_list_nhac,list_nhac);
//        ds_nhac.setAdapter(list_nhac_adapter);
//
//        khoi_tao();
//        timesk();
//        mediaPlayer.start();
//        paus.setImageResource(R.drawable.pause);
//
//        ds_nhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                position=i;
//                if(mediaPlayer.isPlaying()){
//                    stop();
//                }
//                khoi_tao();
//                paus.setImageResource(R.drawable.pause);
//                mediaPlayer.start();
//            }
//        });
//
//        paus.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(mediaPlayer.isPlaying()){
//                    mediaPlayer.pause();
//                    paus.setImageResource(R.drawable.play);
//                }
//                else{
//                    mediaPlayer.start();
//                    paus.setImageResource(R.drawable.pause);
//                }
//            }
//        });
//
//        nex.setOnClickListener((view)->{
//            position++;
//            if(position>list_nhac.size()-1){
//                position=0;
//            }
//            stop();
//            khoi_tao();
//            paus.setImageResource(R.drawable.pause);
//            mediaPlayer.start();
//        });
//
//        rew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                position--;
//                if(position<0){
//                    position=list_nhac.size()-1;
//                }
//                stop();
//                khoi_tao();
//                paus.setImageResource(R.drawable.pause);
//                mediaPlayer.start();
//            }
//        });
//
//        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                mediaPlayer.seekTo(sktime.getProgress());
//            }
//        });
//    }
//    private void timesk(){
//        final Handler handler=new Handler();
//        boolean b=handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                @SuppressLint("SimpleDateFormat")SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
//                play_time.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
//                sktime.setProgress(mediaPlayer.getCurrentPosition());
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        position++;
//                        if(position> list_nhac.size()-1){
//                            position=0;
//                        }
//                        stop();
//                        khoi_tao();
//                        paus.setImageResource(R.drawable.pause);
//                        mediaPlayer.start();
//                        thoi_gian_play();
//                    }
//                });
//                handler.postDelayed(this,500);
//            }
//        },100);
//    }
//
//    private String time(int raw){
//        String t;
//        MediaPlayer player=new MediaPlayer();
//        player = MediaPlayer.create(activity_list_bai_hat.this,raw);
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat tg=new SimpleDateFormat("mm:ss");
//        t=tg.format(player.getDuration());
//        player.stop();
//        player.release();
//        return t;
//    }
//
//    private void thoi_gian_play(){
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat gioi = new SimpleDateFormat("mm:ss");
//        sum_time.setText(gioi.format(mediaPlayer.getDuration()));
//        sktime.setMax(mediaPlayer.getDuration());
//    }
//
//    @SuppressLint("SetTextI18n")
//    private void khoi_tao(){
//        mediaPlayer=MediaPlayer.create(activity_list_bai_hat.this, list_nhac.get(position).part);
//        txt_dang_phat.setText("Đang phát: " + list_nhac.get(position).ten);
//        thoi_gian_play();
//        paus.setImageResource(R.drawable.play);
//    }
//
//    private void stop(){
//        mediaPlayer.stop();
//    }
//
//    @Override
//    protected void onPause(){
//        mediaPlayer.stop();
//        super.onPause();
//    }
//}
