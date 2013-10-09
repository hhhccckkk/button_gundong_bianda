package com.hck;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.app.Activity;

public class MainActivity extends Activity implements OnTouchListener,
		OnClickListener {
	private Button button1, button2, button3, button4, button5;
	private Animation animation;
	private List<View> views;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		setAnim();
	}

	private void initView() {
		button1 = (Button) findViewById(R.id.bt1);
		button1.setOnTouchListener(this);
		button1.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.bt2);
		button2.setOnTouchListener(this);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.bt3);
		button3.setOnTouchListener(this);
		button3.setOnClickListener(this);
		button4 = (Button) findViewById(R.id.bt4);
		button4.setOnTouchListener(this);
		button4.setOnClickListener(this);
		button5 = (Button) findViewById(R.id.bt5);
		button5.setOnTouchListener(this);
		button5.setOnClickListener(this);
		animation = AnimationUtils.loadAnimation(this, R.anim.scale_to_large);
		views = new ArrayList<View>();
		views.add(button1);
		views.add(button2);
		views.add(button3);
		views.add(button4);
		views.add(button5);

	}

	private void setAnim() {
		button1.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in_after200));
		button2.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in_after400));
		button3.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in_after600));
		button4.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in_after800));
		button5.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in_after1000));
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
         if (event.getAction()==MotionEvent.ACTION_MOVE) {
			startViewAnim(getOnTouchedView(event,views));
		 }
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt1:
			startViewAnim(button1);
			break;
		case R.id.bt2:
			startViewAnim(button2);
			break;
		case R.id.bt3:
			startViewAnim(button3);
			break;
		case R.id.bt4:
			startViewAnim(button4);
			break;
		case R.id.bt5:
			startViewAnim(button5);
			break;

		default:
			break;
		}
	}
	/**
	 * onTouch界面时指尖在views中的哪个view当中
	 * 
	 * @param event
	 *            ontouch事件
	 * @param views
	 *            view list
	 * @return view
	 */
	public static View getOnTouchedView(MotionEvent event, List<View> views) {
		int[][] location = getViewsPosition(views);
		for (int index = 0; index < views.size(); index++) {
			if (event.getRawX() > location[index][0]
					&& event.getRawX() < location[index][0]
							+ views.get(index).getWidth()
					&& event.getRawY() > location[index][1]
					&& event.getRawY() < location[index][1]
							+ views.get(index).getHeight()) {
				return views.get(index);
			}
		}
		return null;
	}
	/**
	 * 传入一个views数组,返回一个int型的二维数组来存放每个
	 * view在手机屏幕上左上角的绝对坐标
	 * @param views 传入的view数组
	 * @return 返回int型二维数组
	 */
	public static int[][] getViewsPosition(List<View> views) {
		int[][] location = new int[views.size()][2];
		for (int index = 0; index < views.size(); index++) {
			views.get(index).getLocationOnScreen(location[index]);
		}
		return location;
	}
	private void startViewAnim(View view) {
		if (view==null) {
			return;
		}
		for (int i = 0; i < views.size(); i++) {
			if (view.getId() == views.get(i).getId()) {
				views.get(i).clearAnimation();
				views.get(i).startAnimation(animation);
			} else {
				views.get(i).clearAnimation();
			}
		}
	}

}
