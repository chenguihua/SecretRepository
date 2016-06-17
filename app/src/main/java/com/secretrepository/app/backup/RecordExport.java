package com.secretrepository.app.backup;

import android.content.Context;
import android.os.Environment;

import com.secretrepository.app.database.SecretDatabaseHelper;
import com.secretrepository.app.database.SecretDatabaseHelper.AddressBean;
import com.secretrepository.app.database.SecretDatabaseHelper.UserBean;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenguihua on 2016/6/17.
 */
public class RecordExport {

    public RecordExport() {
    }

    public void backup(Context context) {
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(context.getApplicationContext());

        String rootDir = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(rootDir + "/SecretRepository/");
        if (!myDir.exists()) {
            myDir.mkdir();
        }
        String filename = "backup.xml";
        File file = new File(myDir.getAbsolutePath(), filename);
        try {
            FileOutputStream os = new FileOutputStream(file);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer xmlSerializer = factory.newSerializer();
            xmlSerializer.setOutput(os, "utf-8");
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "users");

            List<AddressBean> addresses = helper.addressFindAll();
            for (int i = 0; i < addresses.size(); i++) {
                String address = addresses.get(i).address;
                String website = addresses.get(i).website;
                List<UserBean> users = helper.userFindByAddressId(addresses.get(i).id);
                for (int j = 0; j < users.size(); j++) {
                    String username = users.get(j).username;
                    String password = users.get(j).password;
                    xmlSerializer.startTag(null, "user");
                    xmlSerializer.startTag(null, "username");
                    xmlSerializer.text(username);
                    xmlSerializer.endTag(null, "username");
                    xmlSerializer.startTag(null, "password");
                    xmlSerializer.text(password);
                    xmlSerializer.endTag(null, "password");
                    xmlSerializer.startTag(null, "address");
                    xmlSerializer.text(address);
                    xmlSerializer.endTag(null, "address");
                    xmlSerializer.startTag(null, "website");
                    xmlSerializer.text(website);
                    xmlSerializer.endTag(null, "website");
                    xmlSerializer.endTag(null, "user");
                }
            }
            xmlSerializer.endTag(null, "users");
            xmlSerializer.endDocument();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
