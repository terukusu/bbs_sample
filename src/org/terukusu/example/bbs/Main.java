package org.terukusu.example.bbs;

import java.util.List;

import org.terukusu.example.bbs.entity.Message;
import org.terukusu.example.bbs.entity.Person;
import org.terukusu.example.util.Log;
import org.terukusu.example.util.db.ConnectionManager;
import org.terukusu.example.util.db.QueryUtil;

/**
 * 実験用のメインクラス。
 */
public class Main {
    public static void main(String[] args) {
        
        // Excecute SQL sample
        String sql = "SELECT * from person";
        List<Person> list = QueryUtil.getInstance().queryAsBean(sql, Person.class);
        Log.debug("all person records: " + list);

        sql = "SELECT * from message";
        List<Message> messageList = QueryUtil.getInstance().queryAsBean(sql, Message.class);
        Log.debug("all message records: " + messageList);
        
        ConnectionManager.getInstance().rollback();
    }
}
