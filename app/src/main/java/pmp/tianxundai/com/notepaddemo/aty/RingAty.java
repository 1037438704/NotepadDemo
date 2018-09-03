package pmp.tianxundai.com.notepaddemo.aty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;

import java.util.Calendar;
import java.util.Random;

import pmp.tianxundai.com.notepaddemo.MainActivity;
import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：铃声设置
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_ring)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class RingAty extends BaseAty {
    private Button button_1;
    private AlarmManager alarmManager;
    private TimePickerDialog timePickerDialog;
    @Override
    public void initViews() {
        button_1 = findViewById(R.id.button_1);
        //获取闹钟管理者
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        //弹出时间对话框
        timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,i);
                c.set(Calendar.MINUTE,i1);

                Intent intent=new Intent();
                intent.setAction("com.example.cloakandnotifaction.shuting");

                //将来时态的跳转
                //生成一个随机数对象
                Random r=new Random();
                PendingIntent pendingIntent=PendingIntent.getBroadcast(me,r.nextInt(),intent,0);

                //设置闹钟
                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);

                //时间一到，发送广播（闹钟响了）//广播接受者中（跳转Activity）// 跳转Activity，在这个Activity中播放音乐
            }
        },hour,minute,true);
    }

    @Override
    public void setEvents() {
        //获取当前系统的时间
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }
}