package com.secretrepository.app.backup;

import android.content.Context;
import android.util.Xml;

import com.secretrepository.app.database.SecretDatabaseHelper;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/**
 * Created by chenguihua on 2016/6/17.
 */
public class RecordImport {

    public RecordImport() {
    }

    public void restore(Context context, InputStream is) throws Exception {
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(context.getApplicationContext());

        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(is, "UTF-8");
        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String username = "";
            String password = "";
            String address = "";
            String website = "";
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;

                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("user")) {
                        username = password = address = website = "";
                    } else if (xpp.getName().equals("username")) {
                        eventType = xpp.next();
                        username = xpp.getText();
                    } else if (xpp.getName().equals("password")) {
                        eventType = xpp.next();
                        password = xpp.getText();
                    } else if (xpp.getName().equals("address")) {
                        eventType = xpp.next();
                        address = xpp.getText();
                    } else if (xpp.getName().equals("website")) {
                        eventType = xpp.next();
                        website = xpp.getText();
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("user")) {
                        helper.insert(username, password, address, website);
                    }
                    break;
            }
            eventType = xpp.next();
        }

    }
}
