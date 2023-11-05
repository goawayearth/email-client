import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.swing.*;

public class RecieveFrame extends JFrame implements ActionListener{

    JTextArea jTextArea=new JTextArea(50,30);
    JScrollPane jScrollPane=new JScrollPane(jTextArea);
    JButton cancelButton=new JButton("关闭");
    String Content="";

    RecieveFrame() throws Exception {

        cancelButton.addActionListener(this);
        this.setSize(600,400);
        this.setLocation(400,100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("收件箱");
        this.setLayout(null);
        this.add(jScrollPane);
        jScrollPane.setSize(400,270);
        jScrollPane.setLocation(100,40);
        jTextArea.setLineWrap(true); // 设置自动换行
        jTextArea.setWrapStyleWord(true); // 设置在单词边界处换行
        this.add(cancelButton);
        cancelButton.setSize(80,20);
        cancelButton.setLocation(500,340);
        // 邮箱服务器相关信息
        String pop3Host = "pop.163.com";
        String pop3Port = "995";

        // 创建一个会话对象，用于连接到邮件服务器
        Properties properties = new Properties();
        properties.put("mail.pop3.host", pop3Host);
        properties.put("mail.pop3.port", pop3Port);
        properties.put("mail.pop3.ssl.enable", "true");
        Session session = Session.getDefaultInstance(properties);

        // 3. 获取邮件存储对象并连接到邮箱账户
        Store store = session.getStore("pop3");
        System.out.println("开始联接");
        store.connect("17363045571@163.com", "UMOLUYWSEZDTJQUK");
        System.out.println("连接成功");
        // 4. 获取收件箱文件夹并打开
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // 5. 判断是否有未读邮件
        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        if (messages.length == 0) {
           // System.out.println("没有未读邮件");
            Content+="没有未读邮件";
        } else {
           // System.out.println("共有" + messages.length + "封未读邮件");
             //str="共有" + messages.length + "封未读邮件";

            Content+="共有" + messages.length + "封未读邮件"+"\n\n";

            // 6. 遍历未读邮件并输出邮件信息
            for (Message message : messages) {
               // System.out.println("主题：" + message.getSubject());

                Content+="主题：" + message.getSubject()+"\n";
                //System.out.println("发件人：" + message.getFrom()[0]);
                Content+="发件人：" + message.getFrom()[0]+"\n";
               // System.out.println("发送时间：" + message.getSentDate());
                Content+="发送时间：" + message.getSentDate()+"\n";

                Object content = message.getContent();
                if (content instanceof MimeMultipart) {
                    MimeMultipart multipart = (MimeMultipart) content;
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (bodyPart.isMimeType("text/plain")) {
                            String ContentStr= (String) bodyPart.getContent();
                            if(ContentStr.length()>90)ContentStr=ContentStr.substring(0,90);
                            Content+=("内容："+ContentStr+"\n");
                        }
                    }
                } else if (content instanceof String) {
                    if(((String) content).length()>90)content=((String) content).substring(0,90);
                    Content+=("内容："+content+"\n");
                }
            // 7. 判断是否有附件
                if (message.getContent() instanceof Multipart) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        String disposition = bodyPart.getDisposition();
                        if (disposition != null && (disposition.equalsIgnoreCase(BodyPart.ATTACHMENT))) {
                            //System.out.println("附件：" + bodyPart.getFileName());
                            Content+="附件：" + bodyPart.getFileName()+"\n";
                            // 8. 下载附件
                            InputStream inputStream = bodyPart.getInputStream();
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(bodyPart.getFileName()));
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, length);
                            }
                            inputStream.close();
                            fileOutputStream.close();
                        }
                    }
                }
                Content+="\n";
            }
        }
        jTextArea.append(Content);
        // 9. 关闭连接
        inbox.close(false);
        store.close();
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==cancelButton)
            this.setVisible(false);
    }
}
