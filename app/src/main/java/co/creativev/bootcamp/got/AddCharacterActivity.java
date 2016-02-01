package co.creativev.bootcamp.got;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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

    AddCharacterPresenter addCharacterPresenter;
    private EditText inputCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Add GoT Character");

        addCharacterPresenter = new AddCharacterPresenter(this, DatabaseHelper.getDatabaseHelper(this));

        inputCharacter = (EditText) findViewById(R.id.text_character_name);
        imageView = (ImageView) findViewById(R.id.image_character);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(galleryIntent, CHOOSE_IMAGE);
            }
        });
        final RadioGroup radioGroupHouse = (RadioGroup) findViewById(R.id.radio_group_house);
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputCharacter.getText().toString();
                String imagePath = (String) imageView.getTag();
                int selectedHouse = radioGroupHouse.getCheckedRadioButtonId();

                addCharacterPresenter.addCharacter(name, imagePath, selectedHouse);
            }
        });
    }

    @Override
    public void addCharacterSuccess() {
        Toast.makeText(AddCharacterActivity.this, "Inserted new character", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showDbInsertError() {
        Log.e(MainActivity.LOG_TAG, "Error while inserting data");
    }

    @Override
    public void setError(String message) {
        inputCharacter.setError(message);
    }

    @Override
    public void showAlertMessage(String message) {
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
