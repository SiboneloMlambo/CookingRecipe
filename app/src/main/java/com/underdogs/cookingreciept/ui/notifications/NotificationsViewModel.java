package com.underdogs.cookingreciept.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private final MutableLiveData<String> nText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");

        nText = new MutableLiveData<>();
        nText.setValue("This is dashboard fragment from View Model Class");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getNText() {
        return nText;
    }

}