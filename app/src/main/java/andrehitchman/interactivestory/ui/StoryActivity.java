package andrehitchman.interactivestory.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import andrehitchman.interactivestory.R;
import andrehitchman.interactivestory.model.Page;
import andrehitchman.interactivestory.model.Story;


public class StoryActivity extends Activity {

    //
    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;
    private Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // get the intent used to start this activity
        Intent intent = getIntent();
        // get extra data
        mName = intent.getStringExtra(getString(R.string.key_name));

        // prevent a hard crash
        if (mName == null)  {
            mName = "Friend";
        }
        Log.d(TAG, mName);

        // variables that represent views in layout
        mImageView = (ImageView)findViewById(R.id.storyImageView);
        mTextView = (TextView)findViewById(R.id.storyTextView);
        mChoice1 = (Button)findViewById(R.id.choiceButton1);
        mChoice2 = (Button)findViewById(R.id.choiceButton2);

        loadPage(0);
    }

    private void loadPage(int choice) {
        // load new page of story based on what the user chooses
        // current page set based on user choice
        mCurrentPage = mStory.getPage(choice);
        // wire up data from this page to ui
        // get drawable by specific ID
        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        // change image dynamically
        // set as image drawable for image view
        mImageView.setImageDrawable(drawable);

        // hold text of story
        String pageText = mCurrentPage.getText();
        // add the name if placeholder included. Won't add if no placeholder
        pageText = String.format(pageText, mName);
        mTextView.setText(pageText);

        if (mCurrentPage.isFinal()) {
            mChoice1.setVisibility(View.INVISIBLE); // hide button
            mChoice2.setText("PLAY AGAIN");
            // re-create action bar
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // starting main activity
                    finish(); // finish current activity and get rid of it.
                }
            });
        }
        else {
            // set text for buttons
            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // index of the new page to load
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // index of the new page to load
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }


    }




}
