package andrehitchman.interactivestory.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import andrehitchman.interactivestory.R;


public class MainActivity extends Activity {

    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// attaches layout to this activity

        mNameField = (EditText)findViewById(R.id.namEditText);
        mStartButton = (Button)findViewById(R.id.startButton);

        // set onClickListener
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get user's name
                String name = mNameField.getText().toString();
                startStory(name);
            }
        }); // anonymous inner class
    }

    private void startStory(String name) {
        // start new activity
        Intent intent = new Intent(this, StoryActivity.class);
        // extras - put information into intent
        intent.putExtra(getString(R.string.key_name), name);
        // express intention
        startActivity(intent);
    }

    // new life-cycle activity
    @Override
    protected void onResume() {
        super.onResume();
        // reset edit text
        //mNameField.setText(""); // to clear text/reset text
    }
}
