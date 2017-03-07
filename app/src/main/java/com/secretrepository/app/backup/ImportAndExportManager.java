package com.secretrepository.app.backup;

import android.content.Context;
import android.os.Environment;
import android.util.JsonReader;
import android.util.JsonWriter;

import com.secretrepository.app.data.LoginMsg;
import com.secretrepository.app.data.source.LoginMsgDataSource;
import com.secretrepository.app.data.source.LoginMsgRepository;
import com.secretrepository.app.data.source.local.LoginMsgLocalDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2017/3/7.
 */

public class ImportAndExportManager {
    private Context mContext = null;
    LoginMsgRepository msgRepository;

    public ImportAndExportManager(Context context) {
        mContext = context;
        msgRepository = LoginMsgRepository.getInstance(LoginMsgLocalDataSource.getInstance(mContext.getApplicationContext()));
    }

    public File buildExportFile() {
        String rootDir = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(rootDir + "/SecretRepository/");
        if (!myDir.exists()) {
            myDir.mkdir();
        }
        String filename = "backup";
        return new File(myDir.getAbsolutePath(), filename);
    }

    public List<LoginMsg> getAllExportData() {
        final List<LoginMsg> list = new ArrayList<>();
        msgRepository.getAllMsgTitle(new LoginMsgDataSource.LoadAllMsgTitleCallBack() {
            @Override
            public void onDataLoaded(List<LoginMsg> msgList) {
                for (LoginMsg msg : msgList) {
                    msgRepository.getMsgDetail(msg.getId(), new LoginMsgDataSource.LoadMsgCallback() {
                        @Override
                        public void onDataLoaded(LoginMsg msg) {
                            list.add(msg);
                        }

                        @Override
                        public void onDataNotAvailable() {
                        }
                    });
                }
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
        return list;
    }

    public void importDataFromSdCard(File file) {
        try {
            List<LoginMsg> list = readJsonStream(new FileInputStream(file));
            for (LoginMsg msg : list) {
                msgRepository.insertLoginMsg(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<LoginMsg> readJsonStream(InputStream in) throws IOException {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessageArray(jsonReader);
        } finally {
            in.close();
        }
    }

    private List<LoginMsg> readMessageArray(JsonReader reader) throws IOException {
        List<LoginMsg> list = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            list.add(readMessage(reader));
        }
        reader.endArray();
        return list;
    }

    private LoginMsg readMessage(JsonReader reader) throws IOException {
        String username = null;
        String password = null;
        String title = null;
        String description = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("username")) {
                username = reader.nextString();
            } else if (name.equals("password")) {
                password = reader.nextString();
            } else if (name.equals("title")) {
                title = reader.nextString();
            } else if (name.equals("description")) {
                description = reader.nextString();
            }
        }
        reader.endObject();
        return new LoginMsg(title, username, password, description);
    }

    public void exportDataToSdCard() {
        File file = buildExportFile();
        try {
            FileOutputStream os = new FileOutputStream(file);
            writeJsonStream(os, getAllExportData());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void writeJsonStream(OutputStream out, List<LoginMsg> list) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        jsonWriter.setIndent("   ");
        writeMessagesArray(jsonWriter, list);
        jsonWriter.close();
    }

    private void writeMessagesArray(JsonWriter writer, List<LoginMsg> list) throws IOException {
        writer.beginArray();
        for (LoginMsg msg : list) {
            writeMessage(writer, msg);
        }
        writer.endArray();
    }

    private void writeMessage(JsonWriter writer, LoginMsg msg) throws IOException {
        writer.beginObject();
        writer.name("title").value(msg.getTitle());
        writer.name("username").value(msg.getUsername());
        writer.name("password").value(msg.getPassword());
        writer.name("description").value(msg.getDescription());
        writer.endObject();
    }

}
