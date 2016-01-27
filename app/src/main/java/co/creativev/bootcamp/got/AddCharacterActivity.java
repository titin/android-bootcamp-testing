package co.creativev.bootcamp.got;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddCharacterActivity extends AppCompatActivity implements AddCharacterView {

    public static final int CHOOSE_IMAGE = 100;
    private ImageView imageView;
    private EditText inputCharacter;

    AddCharacterPresenter addCharacterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addCharacterPresenter = new AddCharacterPresenter(this,
                new AddCharacterService(
                        DatabaseHelper.getDatabaseHelper(this)));
        addCharacterPresenter.initView();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_add_character);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Add GoT Character");
        imageView = (ImageView) findViewById(R.id.image_character);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCharacterPresenter.openGallery();
            }
        });
        final RadioGroup radioGroupHouse = (RadioGroup) findViewById(R.id.radio_group_house);
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedHouse = radioGroupHouse.getCheckedRadioButtonId();
                inputCharacter = ((EditText) findViewById(R.id.text_character_name));
                String name = inputCharacter.getText().toString();

                String imagePath = (String) imageView.getTag();
                addCharacterPresenter.addCharacter(selectedHouse, name, imagePath);
            }
        });
    }

    @Override
    public void inputErrorMessage(String message) {
        inputCharacter.setError(message);
    }

    @Override
    public void errorAlert(String message) {
        new AlertDialog.Builder(AddCharacterActivity.this)
                .setTitle("Error")
                .setMessage(message)
                .setCancelable(true)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void openGallery() {
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryIntent, CHOOSE_IMAGE);
    }

    @Override
    public void closeActivity() {
        Toast.makeText(AddCharacterActivity.this, "Inserted new character", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onDbError() {
        Log.e(MainActivity.LOG_TAG, "Error while inserting data");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Log.d(MainActivity.LOG_TAG, "Received " + data);

            Uri selectedImage = data.getData();
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            String picturePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
//            cursor.close();
            String picturePath = selectedImage.getPath();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imageView.setTag("file://" + picturePath);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
