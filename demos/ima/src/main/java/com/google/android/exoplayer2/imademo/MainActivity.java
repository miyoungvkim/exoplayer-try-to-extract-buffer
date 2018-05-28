/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.imademo;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Main Activity for the IMA plugin demo. {@link ExoPlayer} objects are created by
 * {@link PlayerManager}, which this class instantiates.
 */
public final class MainActivity extends Activity {

  private PlayerView playerView;
  private PlayerManager player;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    playerView = findViewById(R.id.player_view);
    player = new PlayerManager(this);

    Button buttonCapture = findViewById(R.id.capture);
    buttonCapture.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View arg0) {
          Toast.makeText(MainActivity.this, "start currentPosition : " + player.getCurrentPosition(), LENGTH_SHORT).show();
                try {
                    Log.w("TAG", "1");

                    Bitmap bmFrame = null ;
                    AlertDialog.Builder myCaptureDialog = new AlertDialog.Builder(MainActivity.this);
                    ImageView capturedImageView = new ImageView(MainActivity.this);
                    capturedImageView.setImageBitmap(bmFrame);

                    ViewGroup.LayoutParams capturedImageViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    capturedImageView.setLayoutParams(capturedImageViewLayoutParams);

                    myCaptureDialog.setView(capturedImageView);
                    myCaptureDialog.show();
                }
                catch (Exception e) {
                    Log.w("TAG", "error "+ e);

                }
      }});

  }

  @Override
  public void onResume() {
    super.onResume();
    player.init(this, playerView);
  }

  @Override
  public void onPause() {
    super.onPause();
    player.reset();
  }

  @Override
  public void onDestroy() {
    player.release();
    super.onDestroy();
  }

}
