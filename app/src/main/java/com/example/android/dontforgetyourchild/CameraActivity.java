/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.android.dontforgetyourchild;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

/** Main {@code Activity} class for the Camera app. */
public class CameraActivity extends Activity {
  PowerConnectionReceiver myPowerReeiver = new PowerConnectionReceiver();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera);
    if (null == savedInstanceState) {
      getFragmentManager()
          .beginTransaction()
          .replace(R.id.container, Camera2BasicFragment.newInstance())
          .commit();
    }

    //battery
    BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
      int scale = -1;
      int level = -1;
      int voltage = -1;
      int temp = -1;
      @Override
      public void onReceive(Context context, Intent intent) {
        level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
        voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
        if (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) > 0)
        {
          //Log.e("BatteryManager", "level is "+level+"/"+scale+", temp is "+temp+", voltage is "+voltage);
        }
        else
        {
          Log.e("notplugged", "NOTPLUGGED");
        }
      }
    };
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    registerReceiver(batteryReceiver, filter);

  }
}
