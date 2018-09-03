package pmp.tianxundai.com.notepaddemo.guangbo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import pmp.tianxundai.com.notepaddemo.aty.RingActivity;

/**
 * Created by dell-pc on 2018/8/26.
 */

public class RingReceived extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.cloakandnotifaction.shuting".equals(intent.getAction())) {
            Log.i("test", "闹钟作死的响了。。");
            //跳转到Activity

            Intent intent1 = new Intent(context, RingActivity.class);
            //给Intent设置标志位
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }
}
