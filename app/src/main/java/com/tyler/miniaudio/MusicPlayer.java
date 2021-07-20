package com.tyler.miniaudio;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class MusicPlayer extends Activity {

    //MediaPlayer SetUp:
    private static MediaPlayer mediaPlayer;
    public static Random randomNumberGenerator = new Random();// For shuffling songs
    public static Handler myHandler = new Handler();
    public static int shuffleOn = 0; //variable to choose whether to shuffle Music or not
    public static int currentSong = 0;
    public static float volume = 1;
    //For Thread work
    //volatile means that our threads always access the
    //most up to date version of the variable
    public static volatile Thread seekBarProgression;
    public static volatile Boolean isMusicPlaying = true;
    public static MusicPlayer musicPlayer;
    public static Context context;

    public static boolean isMediaPlayerAlive() {
        return (mediaPlayer != null);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Log.d(MainBottomNavActivity.TAG, "mmm" + context);
    }


    public MusicPlayer() {
        setUpMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void stopMusic() {
        if (mediaPlayer == null)
            return;
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public static MusicPlayer getInstance() {
        return musicPlayer;
    }

    public void setUpMediaPlayer() {
        moveSeekBarWhilePlayingMusic();
    }

    //////////// Function for changing songs
    public static void nextSong() {

        if (FloatingViewService.serviceAlive)
            FloatingViewService.getInstance().playButton.setImageResource(FloatingViewService.getInstance().savedPausedDrawableID);

        if (currentSong + 1 != FloatingViewService._songs.size()) { //We are not final track
//                                Log.d(MainBottomNavActivity.TAG, "Next Song");
            if (shuffleOn == 0) {
                ++currentSong;
            } else if (shuffleOn == 1) {
                currentSong = randomNumberGenerator.nextInt(FloatingViewService._songs.size());
            }
            //if shuffle == 3, we just leave the currentSong alone
        } else { //We are on final track
            if (shuffleOn == 1) {
                currentSong = randomNumberGenerator.nextInt(FloatingViewService._songs.size());
            } else if (shuffleOn == 0) {
                currentSong = 0;
                return; // stop playing since we got to the last track
            }
        }

        Log.d(MainBottomNavActivity.TAG, "inside nextSong");
        musicPlayerSongChange(currentSong);
    }

    public static void prevSong() {
        if (FloatingViewService.serviceAlive)
            FloatingViewService.getInstance().playButton.setImageResource(FloatingViewService.getInstance().savedPausedDrawableID);

        if (currentSong != 0) { //We are not final track
//                                Log.d(MainBottomNavActivity.TAG, "Next Song");
            if (shuffleOn == 0) {
                --currentSong;
            } else if (shuffleOn == 1) {
                currentSong = randomNumberGenerator.nextInt(FloatingViewService._songs.size());
            }
            //if shuffle == 3, we just leave the currentSong alone
        } else { //We are on final track
            if (shuffleOn == 1) {
                currentSong = randomNumberGenerator.nextInt(FloatingViewService._songs.size());
            } else {
                currentSong = 0;
                return; // stop playing since we got to the last track
            }
        }
        musicPlayerSongChange(currentSong);
    }

    public static boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public static void playPause() {
        Log.d(MainBottomNavActivity.TAG, "playPause Start");
        if (mediaPlayer != null) {
            Log.d(MainBottomNavActivity.TAG, "playPause not null");
            if (mediaPlayer.isPlaying()) {

                Log.d(MainBottomNavActivity.TAG, "playPause pausing");
                mediaPlayer.pause();
                if (FloatingViewService.serviceAlive) {
                    FloatingViewService.getInstance().playButton.setImageResource(
                            FloatingViewService.getInstance().savedPlayDrawableID);
                }
            } else {


                Log.d(MainBottomNavActivity.TAG, "playPause playing");
                mediaPlayer.start();
                if (FloatingViewService.serviceAlive) {
                    FloatingViewService.getInstance().playButton.setImageResource(
                            FloatingViewService.getInstance().savedPausedDrawableID);

                }
            }
        } else {
            musicPlayerSongChange(currentSong);
            if (FloatingViewService.serviceAlive) {

                FloatingViewService.getInstance().playButton.setImageResource(
                        FloatingViewService.getInstance().savedPausedDrawableID);

            }
        }

    }

    public static void shuffleSongs() {
        SavedPreferences savedPreferences = SavedPreferences.getInstance();
        Boolean lightModeOn = savedPreferences.get(SavedPreferences.LIGHT_MODE, true);
        if (shuffleOn == 0) {

            if (lightModeOn) {
                if (FloatingViewService.serviceAlive)
                    FloatingViewService.getInstance().shuffleButton.setImageResource(R.drawable.ic_repeat_one_black_24dp);
            } else {
                if (FloatingViewService.serviceAlive)
                    FloatingViewService.getInstance().shuffleButton.setImageResource(R.drawable.ic_repeat_one_white_24dp);

            }

            Toast.makeText(MainBottomNavActivity.mContext, "Repeat Mode", Toast.LENGTH_SHORT).show();

            shuffleOn = 2;
        } else if (shuffleOn == 2) {

            if (lightModeOn) {
                if (FloatingViewService.serviceAlive)
                    FloatingViewService.getInstance().shuffleButton.setImageResource(R.drawable.ic_shuffle_black_24dp);
            } else {
                if (FloatingViewService.serviceAlive)
                    FloatingViewService.getInstance().shuffleButton.setImageResource(R.drawable.ic_shuffle_white_24dp);

            }

            Toast.makeText(MainBottomNavActivity.mContext, "Shuffle Mode", Toast.LENGTH_SHORT).show();
            shuffleOn = 1;
        } else {

            if (lightModeOn) {
                if (FloatingViewService.serviceAlive)
                    FloatingViewService.getInstance().shuffleButton.setImageResource(R.drawable.ic_repeat_black_24dp);
            } else {
                if (FloatingViewService.serviceAlive)
                    FloatingViewService.getInstance().shuffleButton.setImageResource(R.drawable.ic_repeat_white_24dp);

            }

            Toast.makeText(MainBottomNavActivity.mContext, "Normal Mode", Toast.LENGTH_SHORT).show();
            shuffleOn = 0;
        }
    }

    public static void setVolume(float newVolume) {
        volume = newVolume;
        if (mediaPlayer != null)
            mediaPlayer.setVolume(newVolume, newVolume);
    }

    public static int getProgress() {
        if (mediaPlayer == null)
            return 0;
        return mediaPlayer.getCurrentPosition();
    }

    public static void setMediaPlayerProgress(int progress) {
        if (mediaPlayer != null)
            mediaPlayer.seekTo(progress);
    }

    /*
     * musicPlayerSongChange takes a int and creates a new mediaplayer playing that song
     * */
    public static void musicPlayerSongChange(int position) {

        currentSong = position;
        if (currentSong >= FloatingViewService._songs.size()) {
            currentSong = 0;
            return;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setVolume(volume, volume);
                    mediaPlayer.setDataSource(FloatingViewService._songs.get(currentSong).getSongUrl());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {

                            if (FloatingViewService.serviceAlive) {
                                FloatingViewService.getInstance().playButton.setImageResource(
                                        FloatingViewService.getInstance().savedPausedDrawableID);
                                FloatingViewService.getInstance().seekBar.setProgress(0);
                                FloatingViewService.getInstance().seekBar.setMax(mediaPlayer.getDuration());
                            }

                            mp.start();
//                            Log.d("Prog", "run: " + mediaPlayer.getDuration());
                        }
                    });

                    //When the current mediafile is finish playing do this:
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {

                            nextSong();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
//                    Log.d(MainBottomNavActivity.TAG, e.getMessage());
                    //This statement below
                    Toast.makeText(FloatingViewService.getInstance().mContext, "Error Playing Song", Toast.LENGTH_SHORT).show();
                }
            }
        };
        /*We have a Toast message inside the runnable so we need a handler to take send information
        back into the runnable to be able to make that Toast Message work
         Also: Anything that runs on the main thread needs a handler, the handler will execute
         on the thread it was created, main in our case*/
//        new Thread(runnable).start();
        myHandler.postDelayed(runnable, 1000);
    }

    public void moveSeekBarWhilePlayingMusic() {
        seekBarProgression = new SeekBarTracker();
        seekBarProgression.start();
    }

}
