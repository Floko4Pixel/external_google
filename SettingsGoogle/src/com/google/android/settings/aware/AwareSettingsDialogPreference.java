package com.google.android.settings.aware;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import androidx.appcompat.app.AlertDialog;

import com.android.settings.R;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.overlay.FeatureFactory;

public class AwareSettingsDialogPreference extends AwareDialogPreferenceBase {

    public AwareSettingsDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isAvailable() {
        return FeatureFactory.getFactory(getContext()).getAwareFeatureProvider()
                .isSupported(getContext()) && !mHelper.isAirplaneModeOn();
    }

    public void performEnabledClick() {
        new SubSettingLauncher(getContext()).setDestination(AwareSettings.class.getName())
                .setSourceMetricsCategory(744).launch();
    }

    public void onPrepareDialogBuilder(AlertDialog.Builder builder,
            DialogInterface.OnClickListener onClickListener) {
        super.onPrepareDialogBuilder(builder, onClickListener);
        builder.setTitle((int) R.string.aware_settings_disabled_info_dialog_title);
        builder.setMessage((int) R.string.aware_settings_disabled_info_dialog_content);
        builder.setPositiveButton((int) R.string.nfc_how_it_works_got_it,
                (DialogInterface.OnClickListener) null);
        builder.setNegativeButton((CharSequence) "", (DialogInterface.OnClickListener) null);
    }
}
