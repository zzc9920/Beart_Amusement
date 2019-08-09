package online.beartreasure.beart_amusement.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import me.leefeng.promptlibrary.PromptDialog;

public class Beart_MyService extends Service {

    public Beart_MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
