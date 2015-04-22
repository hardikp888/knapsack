package com.example.cemerademo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView mImgPhoto = null;
	private static final int INTENT_REQUEST_CODE = 100;
	private Uri mFileUri = null;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImgPhoto = (ImageView) findViewById(R.id.img_photo);
		final Button btnTakePhoto = (Button) findViewById(R.id.btn_take_photo);

		btnTakePhoto.setOnClickListener(takePhotoClickListener);
		
		Shader myShader = new LinearGradient(0, 0, 0, 100, Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
		
		btnTakePhoto.getPaint().setShader(myShader);
	}

	private final OnClickListener takePhotoClickListener = new OnClickListener() {

		@Override
		public void onClick(final View v) {

			mFileUri = getFileUri();

			final Intent cameraIntent = new Intent();
			cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
			startActivityForResult(cameraIntent, INTENT_REQUEST_CODE);

		}
	};

	public Uri getFileUri() {

		Log.i("MainActitvy", "File----------------- : "
				+ Environment.getExternalStorageDirectory().toString());
		final File photoFileDir = new File(
				Environment.getExternalStorageDirectory() + "/HarikPhoto");

		if (!photoFileDir.exists()) {
			if (!photoFileDir.mkdir()) {
				Toast.makeText(MainActivity.this, "Error in cerate directory",
						Toast.LENGTH_LONG).show();
			}
		}

		final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());

		final File photoFile = new File(photoFileDir.getPath() + File.separator
				+ "IMG_" + timeStamp + ".jpg");

		return Uri.fromFile(photoFile);
	}

	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable("file_uri", mFileUri);
	}

	@Override
	protected void onRestoreInstanceState(final Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mFileUri = savedInstanceState.getParcelable("file_uri");
	}

	@Override
	protected void onActivityResult(final int requestCode,
			final int resultCode, final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == INTENT_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {

				mImgPhoto.setVisibility(View.VISIBLE);

				try {
					final Bitmap bitmap = BitmapFactory.decodeFile(mFileUri
							.getPath());

					mImgPhoto.setImageBitmap(bitmap);

				} catch (final Exception e) {
					Log.d("MainActitivty", "Error in set Image ");
				}
			}
		}
	}
}
