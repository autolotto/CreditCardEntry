package com.devmarvel.creditcardentrydemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devmarvel.creditcardentry.library.BinChangeCallback;
import com.devmarvel.creditcardentry.library.CardType;
import com.devmarvel.creditcardentry.library.CardTypeChangeCallback;
import com.devmarvel.creditcardentry.library.CardValidCallback;
import com.devmarvel.creditcardentry.library.CreditCard;
import com.devmarvel.creditcardentry.library.CreditCardForm;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	CardValidCallback cardValidCallback = new CardValidCallback() {
		@Override
		public void cardValid(CreditCard card) {
			Log.d(TAG, "valid card: " + card);
			Toast.makeText(MainActivity.this, "Card valid and complete", Toast.LENGTH_SHORT).show();
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final CreditCardForm noZipForm = (CreditCardForm) findViewById(R.id.form_no_zip);
		noZipForm.setOnCardValidCallback(cardValidCallback);

		// we can track gaining or losing focus for any particular field.
		noZipForm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Log.d(TAG, v.getClass().getSimpleName() + " " + (hasFocus ? "gained" : "lost") + " focus. card valid: " + noZipForm.isCreditCardValid());
			}
		});

		noZipForm.setOnCardTypeChangeCallback(new CardTypeChangeCallback() {
			@Override
			public void changedCardType(CardType type) {
				Toast.makeText(MainActivity.this, "Card type changed -> " + type.name, Toast.LENGTH_SHORT).show();
			}
		});

		noZipForm.setOnBinChangeCallback(new BinChangeCallback() {
			@Override
			public void changedBin(String bin) {
				Toast.makeText(MainActivity.this, "Bin changed -> " + bin, Toast.LENGTH_SHORT).show();
			}
		});

		final CreditCardForm zipForm = (CreditCardForm) findViewById(R.id.form_with_zip);
		zipForm.setOnCardValidCallback(cardValidCallback);
		final CreditCardForm yellowForm = (CreditCardForm) findViewById(R.id.yellow_form);
		yellowForm.setOnCardValidCallback(cardValidCallback);
		final CreditCardForm justCard   = (CreditCardForm) findViewById(R.id.just_card_form);
		justCard.setOnCardValidCallback(cardValidCallback);
		final CreditCardForm cardAndZip   = (CreditCardForm) findViewById(R.id.card_and_zip_form);
		cardAndZip.setOnCardValidCallback(cardValidCallback);

		final CreditCardForm prepopulated = (CreditCardForm) findViewById(R.id.pre_populated_form);
		prepopulated.setOnCardValidCallback(cardValidCallback);
		// populate the card, but don't try to focus the next field
		prepopulated.setCardNumber("4242 4242 4242 4242", false);

		final CreditCardForm clear = (CreditCardForm) findViewById(R.id.clear_test_form);
		findViewById(R.id.clear_test_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clear.clearForm();
			}
		});
	}

}
